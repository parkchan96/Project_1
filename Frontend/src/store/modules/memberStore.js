import jwt_decode from "jwt-decode";
import { checkId, login } from "@/api/member.js";
import { findById } from "../../api/member.js";
import { regist } from "@/api/member.js";
import { findId } from "../../api/member.js";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: {
      id: null,
      password: null,
      email: null,
      name: null,
      age: null,
      access: null,
    },
    sound: true,
    isComplete: false,
    ok: true,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_SOUND(state) {
      state.sound = !state.sound;
    },
    CLEAR_USER_INFO(state) {
      state.userInfo = {
        id: null,
        password: null,
        email: null,
        name: null,
        age: null,
        access: null,
      };
      state.isLogin = false;
    },
    SET_IS_COMPLETE: (state, isComplete) => {
      state.isComplete = isComplete;
    },
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_ID_CHECK: (state, ok) => {
      state.ok = ok;
    },
    SET_ID_FALSE: (state) => {
      state.ok = false;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {},
      );
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.id,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
            console.log("login success!!");
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getInfo({ commit }, id) {
      findId(
        id,
        (response) => {
          commit("SET_USER_INFO", response.data.userInfo);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    check({ commit }, id) {
      checkId(
        id,
        (response) => {
          if (response.data.res == "success") {
            commit("SET_ID_CHECK", true);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },

    async userRegist({ commit }, user) {
      await regist(user, (response) => {
        if (response.data.res === "success") {
          commit("SET_IS_COMPLETE", true);
          console.log("success");
        } else {
          commit("SET_IS_COMPLETE", false);
          console.log("fail");
        }
      });
    },
  },
};

export default memberStore;
