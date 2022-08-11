import Vue from "vue";
import Vuex from "vuex";
//import http from "@/api";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import memberStore from "@/store/modules/memberStore.js";
import houseStore from "@/store/modules/houseStore.js";
import favorStore from "@/store/modules/favorStore.js";
// import houseStore from "@/store/modules/houseStore.js";
// import todoStore from "@/store/modules/todoStore.js";

const store = new Vuex.Store({
  modules: {
    houseStore,
    memberStore,
    favorStore,
    // boardStore,
    // houseStore,
    // todoStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

export default store;
