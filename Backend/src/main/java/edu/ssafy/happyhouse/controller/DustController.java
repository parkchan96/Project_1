package edu.ssafy.happyhouse.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.ssafy.happyhouse.DTO.DustDTO;
import edu.ssafy.happyhouse.service.DustService;

@Controller
@RequestMapping("/dust")
public class DustController {
	private static final Logger logger = LoggerFactory.getLogger(DustController.class);

	@Autowired
	private DustService dustService;

//	@GetMapping("/airshow")
//	public String air() {
//		return "jsp/air";
//	}

	@GetMapping("/airshow")
	public String viewDust(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
		urlBuilder.append("/" +  URLEncoder.encode("68764f4775626c6131303271744b6a58","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
		urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
		urlBuilder.append("/" + URLEncoder.encode("ListAirQualityByDistrictService","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
		urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
		urlBuilder.append("/" + URLEncoder.encode("30","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
		
		// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
//		urlBuilder.append("/" + URLEncoder.encode("111121","UTF-8")); /* 서비스별 추가 요청인자들*/
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/xml");
		System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
		BufferedReader rd;

		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		String line;
		String result = null;
		while ((line = rd.readLine()) != null) {
			result = line;
		}
		rd.close();
		conn.disconnect();
		result = "{\"" + result.substring(result.indexOf("row"), result.length() - 1);
		System.out.println(result);
		try {
			JSONObject jObject = new JSONObject(result);
			JSONArray jArray = jObject.getJSONArray("row");
			System.out.println("jArray.length() : " + jArray.length());
			dustService.deleteDust();
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject obj = jArray.getJSONObject(i);
		        String date = obj.getString("MSRDATE");
		        String guguncode = AreaMapping(obj.getString("MSRADMCODE"));
		        String gugunname = obj.getString("MSRSTENAME");
		        String state = obj.getString("GRADE");
		        String totalnum = obj.getString("MAXINDEX");
		        String material = obj.getString("POLLUTANT");
		        String NO2 = obj.getString("NITROGEN");
		        String Ozone = obj.getString("OZONE");
		        String CO = obj.getString("CARBON");
		        String gas = obj.getString("SULFUROUS");
		        String dust = obj.getString("PM10");
		        String ultradust = obj.getString("PM25");
		        DustDTO dustDto = new DustDTO(date, guguncode, gugunname, state, totalnum, material, NO2, Ozone, CO, gas, dust, ultradust);
		        dustService.insertDust(dustDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<DustDTO> list = dustService.viewDust();
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
		int[] dust = new int[list.size()];
		int[] ultradust = new int[list.size()];
		for (int i = 0; i < dust.length; i++) {
			if (list.get(i).getDust().equals("점검중")) {
				dust[i] = -1;
				continue;
			}
			dust[i] = Integer.parseInt(list.get(i).getDust());
		}
		for (int i = 0; i < ultradust.length; i++) {
			if (list.get(i).getUltradust().equals("점검중")) {
				ultradust[i] = -1;
				continue;
			}
			ultradust[i] = Integer.parseInt(list.get(i).getUltradust());
		}
		for (int i = 1; i < dust.length; i++) {
			for (int j = i; j >= 1; j--) {
				if (dust[j] < dust[j - 1]) {
					int tmp = dust[j];
					dust[j] = dust[j - 1];
					dust[j - 1] = tmp;
				} else
					break;
			}
		}
		for (int i = 1; i < ultradust.length; i++) {
			for (int j = i; j >= 1; j--) {
				if (ultradust[j] < ultradust[j - 1]) {
					int tmp = ultradust[j];
					ultradust[j] = ultradust[j - 1];
					ultradust[j - 1] = tmp;
				} else
					break;
			}
		}
		List<DustDTO> res = new ArrayList<>();
		for (int i = 0; i < dust.length; i++) {
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).getDust().equals("점검중")) {
					String condition = "점검중";
					boolean v = true;
					for (int k = 0; k < res.size(); k++) {
						if (res.get(k).getGugunname().equals(list.get(j).getGugunname())) {
							v = false;
						}
					}
					if (v) {
						res.add(new DustDTO(list.get(j).getGugunname(), list.get(j).getDust(),
								list.get(j).getUltradust(), condition));
					}
					continue;
				}
				if (dust[i] == Integer.parseInt(list.get(j).getDust())) {
					String condition = "";
					if (dust[i] <= 30) {
						condition = "좋음";
					} else if (dust[i] <= 35) {
						condition = "보통";
					} else if (dust[i] <= 75) {
						condition = "나쁨";
					} else {
						condition = "매우 나쁨";
					}
					boolean v = true;
					for (int k = 0; k < res.size(); k++) {
						if (res.get(k).getGugunname().equals(list.get(j).getGugunname())) {
							v = false;
						}
					}
					if (v) {
						res.add(new DustDTO(list.get(j).getGugunname(), list.get(j).getDust(),
								list.get(j).getUltradust(), condition));
					}
				}
			}
		}

		List<DustDTO> ultrares = new ArrayList<>();
		for (int i = 0; i < ultradust.length; i++) {
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).getUltradust().equals("점검중")) {
					String condition = "점검중";
					boolean v = true;
					for (int k = 0; k < ultrares.size(); k++) {
						if (ultrares.get(k).getGugunname().equals(list.get(j).getGugunname())) {
							v = false;
						}
					}
					if (v) {
						ultrares.add(new DustDTO(list.get(j).getGugunname(), list.get(j).getDust(),
								list.get(j).getUltradust(), condition));
					}
					continue;
				}
				if (ultradust[i] == Integer.parseInt(list.get(j).getUltradust())) {
					String condition = "";
					if (ultradust[i] <= 30) {
						condition = "좋음";
					} else if (ultradust[i] <= 35) {
						condition = "보통";
					} else if (ultradust[i] <= 75) {
						condition = "나쁨";
					} else {
						condition = "매우 나쁨";
					}
					boolean v = true;
					for (int k = 0; k < ultrares.size(); k++) {
						if (ultrares.get(k).getGugunname().equals(list.get(j).getGugunname())) {
							v = false;
						}
					}
					if (v) {
						ultrares.add(new DustDTO(list.get(j).getGugunname(), list.get(j).getDust(),
								list.get(j).getUltradust(), condition));
					}
				}
			}
		}
		System.out.println("========");
		System.out.println(res.get(0));
		System.out.println(res.get(1));
		System.out.println(res.get(2));
		request.setAttribute("res", res);
		request.setAttribute("ultra", ultrares);
		return "jsp/aircondition";
	}
	
	public static String AreaMapping(String area) {
		switch (area) {
		case "111123":
			return "1111000000";
		case "111121":
			return "1114000000";
		case "111131":
			return "1117000000";
		case "111142":
			return "1120000000";
		case "111141":
			return "1121500000";
		case "111152":
			return "1123000000";
		case "111151":
			return "1126000000";
		case "111161":
			return "1129000000";
		case "111291":
			return "1130500000";
		case "111171":
			return "1132000000";
		case "111311":
			return "1135000000";
		case "111181":
			return "1138000000";
		case "111191":
			return "1141000000";
		case "111201":
			return "1144000000";
		case "111301":
			return "1147000000";
		case "111212":
			return "1150000000";
		case "111221":
			return "1153000000";
		case "111281":
			return "1154500000";
		case "111231":
			return "1156000000";
		case "111241":
			return "1159000000";
		case "111251":
			return "1162000000";
		case "111262":
			return "1165000000";
		case "111261":
			return "1168000000";
		case "111273":
			return "1171000000";
		case "111274":
			return "1174000000";
		}
		return null;
	}
}