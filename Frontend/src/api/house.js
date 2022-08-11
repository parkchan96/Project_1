import { apiInstance, facInstance } from "./index.js";

const api = apiInstance();
const fac = facInstance();

async function sido(success, fail) {
  await api.get(`/apt/sido`).then(success).catch(fail);
}

async function gugun(sidoCode, success, fail) {
  const params = { sido: sidoCode };
  await api.get(`/apt/gugun`, { params }).then(success).catch(fail);
}

async function dong(gugunCode, success, fail) {
  const params = { gugun: gugunCode };
  await api.get(`/apt/dong`, { params }).then(success).catch(fail);
}

async function houseListByGugun(gugunCode, success, fail) {
  const params = { siGugunCode: gugunCode };
  await api
    .get(`/apt/searchBySiGugunCode`, { params })
    .then(success)
    .catch(fail);
}

async function houseListByDong(dongCode, success, fail) {
  const params = { dongCode };
  await api.get(`/apt/searchByDongCode`, { params }).then(success).catch(fail);
}

async function houseListBySearchName(aptName, success, fail) {
  const params = { aptName };
  await api.get(`/apt/searchName`, { params }).then(success).catch(fail);
}

async function houseListBySearchDong(dongName, success, fail) {
  const params = { dongName };
  await api.get(`/apt/searchDong`, { params }).then(success).catch(fail);
}

async function detail(house, success, fail) {
  const params = {
    aptName: house.aptName,
    dongCode: house.dongCode,
  };
  await api.get(`/apt/detail`, { params }).then(success).catch(fail);
}

async function dealList(aptCode, success, fail) {
  const params = {
    aptCode,
  };
  await api.get(`/apt/dealList`, { params }).then(success).catch(fail);
}

async function facility(areaInfo, success, fail) {
  const SERVICE_KEY = process.env.VUE_APP_FACILITY_INFO_API_KEY;
  const params = {
    serviceKey: decodeURIComponent(SERVICE_KEY),
    numOfRows: "1000",
    siDoNm: areaInfo.sidoName,
    cggNm: areaInfo.gugunName,
    roadNm: areaInfo.roadName,
  };
  await fac.get(``, { params }).then(success).catch(fail);
}

async function facilityType(success, fail) {
  await api.get(`/apt/faclType`).then(success).catch(fail);
}

export {
  sido,
  gugun,
  dong,
  houseListByGugun,
  houseListByDong,
  houseListBySearchName,
  houseListBySearchDong,
  detail,
  dealList,
  facility,
  facilityType,
};
