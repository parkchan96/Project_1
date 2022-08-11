import {
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
} from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: null,
    houseDealList: [],
    subway: null,
    facility: [],
    facilityType: [{ value: null, text: null, count: 0 }],
    detailfacilityType: [{ value: null, text: null, count: 0 }],
    mode: false,
  },
  getters: {},
  mutations: {
    SET_WINDOW_MODE(state) {
      state.mode = !state.mode;
    },
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_HOUSE_LIST(state) {
      state.houses = [];
    },
    CLEAR_HOUSE_Detail_LIST(state) {
      state.house = null;
      state.houseDealList = [];
    },
    SET_HOUSE_LIST_BY_GUGUN(state, houses) {
      state.houses = houses;
    },
    SET_HOUSE_LIST_BY_DONG(state, houses) {
      state.houses = houses;
    },
    SET_HOUSE_LIST_BY_SEARCH_NAME(state, houses) {
      state.houses = houses;
    },
    SET_HOUSE_LIST_BY_SEARCH_DONG(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
    SET_HOUSE_DEAL_LIST(state, dealList) {
      state.houseDealList = [];
      if (dealList != []) {
        dealList.forEach((deal) => {
          state.houseDealList.push({
            dealDate:
              deal.dealYear +
              "년 " +
              deal.dealMonth +
              "월 " +
              deal.dealDay +
              "일",
            dealAmount: (parseInt(deal.dealAmount.replace(",", "")) * 10000)
              .toString()
              .replace(/\B(?=(\d{3})+(?!\d))/g, ","),
            area: deal.area,
            floor: deal.floor,
          });
        });
      }
    },
    SET_FACILITY_LIST(state, facility) {
      if (facility == null) {
        state.facility = [];
      } else {
        state.facility = facility;
      }
      state.detailfacilityType = [{ value: null, text: null, count: 0 }];
      state.facilityType.forEach((faclTy) => {
        let total = 0;
        state.facility.forEach((facl) => {
          if (facl.faclTyCd._text === faclTy.faclTyCd) {
            total += 1;
          }
        });
        if (total != 0) {
          state.detailfacilityType.push({
            value: faclTy.faclTyCd,
            text: faclTy.faclTyName,
            count: total,
          });
        }
      });
    },
    SET_FACILITY_TYPE_LIST(state, facilityType) {
      state.facilityType = facilityType;
    },
  },
  actions: {
    async getSido({ commit }) {
      await sido(
        (response) => {
          commit("SET_SIDO_LIST", response.data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async getGugun({ commit }, sidoCode) {
      await gugun(
        sidoCode,
        (response) => {
          commit("SET_GUGUN_LIST", response.data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async getDong({ commit }, gugunCode) {
      await dong(
        gugunCode,
        (response) => {
          commit("SET_DONG_LIST", response.data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async getHouseListByGugun({ commit }, gugunCode) {
      await houseListByGugun(
        gugunCode,
        (response) => {
          commit("SET_HOUSE_LIST_BY_GUGUN", response.data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async getHouseListByDong({ commit }, dongCode) {
      await houseListByDong(
        dongCode,
        (response) => {
          commit("SET_HOUSE_LIST_BY_DONG", response.data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async getHouseListBySearchName({ commit }, aptName) {
      await houseListBySearchName(
        aptName,
        (response) => {
          commit("SET_HOUSE_LIST_BY_SEARCH_NAME", response.data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async getHouseListBySearchDong({ commit }, dongName) {
      await houseListBySearchDong(
        dongName,
        (response) => {
          commit("SET_HOUSE_LIST_BY_SEARCH_DONG", response.data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async detailHouse({ commit }, house) {
      await detail(
        house,
        (response) => {
          commit("SET_DETAIL_HOUSE", response.data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async getHouseDealList({ commit }, aptCode) {
      await dealList(
        aptCode,
        (response) => {
          commit("SET_HOUSE_DEAL_LIST", response.data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async getFacility({ commit }, areaInfo) {
      await facility(
        areaInfo,
        (response) => {
          var convert = require("xml-js");
          var xml = response.data;
          var json = convert.xml2json(xml, { compact: true });
          commit("SET_FACILITY_LIST", JSON.parse(json).facInfoList.servList);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async getFacilityType({ commit }) {
      await facilityType(
        (response) => {
          commit("SET_FACILITY_TYPE_LIST", response.data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default houseStore;
