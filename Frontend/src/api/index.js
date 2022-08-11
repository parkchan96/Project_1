import axios from "axios";
import { API_BASE_URL } from "@/config";
import { FACILITY_INFO_URL } from "@/config";
import { FACILITY_DETAIL_URL } from "@/config";

// axios 객체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

function facInstance() {
  const instance = axios.create({
    baseURL: FACILITY_INFO_URL,
    headers: {
      "Content-type": "application/xml",
    },
  });
  return instance;
}

function facDetailInstance() {
  const instance = axios.create({
    baseURL: FACILITY_DETAIL_URL,
    headers: {
      "Content-type": "application/xml",
    },
  });
  return instance;
}

export { apiInstance, facInstance, facDetailInstance };
