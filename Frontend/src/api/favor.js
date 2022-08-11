import { apiInstance, facInstance, facDetailInstance } from "./index.js";

const api = apiInstance();
const fac = facInstance();
const facDetail = facDetailInstance();

async function insertFavor(favor, success, fail) {
  await api
    .post(`/favor/regist`, JSON.stringify(favor))
    .then(success)
    .catch(fail);
}

async function deleteFavor(favor, success, fail) {
  const params = {
    id: favor.id,
    aptName: favor.aptName,
    dongCode: favor.dongCode,
  };
  await api.delete(`/favor/delete`, { params }).then(success).catch(fail);
}

async function favorItem(id, success, fail) {
  const params = { id };
  await api.get(`/favor/info`, { params }).then(success).catch(fail);
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

async function facilityDetail(wfcltId, success, fail) {
  const SERVICE_KEY = process.env.VUE_APP_FACILITY_INFO_API_KEY;
  const params = {
    serviceKey: decodeURIComponent(SERVICE_KEY),
    wfcltId: wfcltId,
  };
  await facDetail.get(``, { params }).then(success).catch(fail);
}

async function checkFavor(favorInfo, success, fail) {
  const params = {
    id: favorInfo.id,
    aptName: favorInfo.aptName,
    dongCode: favorInfo.dongCode,
  };
  await api.get(`/favor/check`, { params }).then(success).catch(fail);
}

export {
  insertFavor,
  deleteFavor,
  favorItem,
  facility,
  facilityType,
  facilityDetail,
  checkFavor,
};
