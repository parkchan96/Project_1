import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api
    .post(`/member/login`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/member/info/${userid}`).then(success).catch(fail);
}

async function findId(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/member/view/${userid}`).then(success).catch(fail);
}

async function checkId(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/member/checkid/${userid}`).then(success).catch(fail);
}

async function regist(user, success, fail) {
  await api
    .post(`/member/regist`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function modify(user, success, fail) {
  await api
    .put(`/member/update`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function del(user, success, fail) {
  await api
    .delete(`/member/delete/${user.id}`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

export { login, findById, regist, modify, del, findId, checkId };
