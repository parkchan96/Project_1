import {
  insertFavor,
  deleteFavor,
  favorItem,
  facility,
  facilityType,
  facilityDetail,
  checkFavor,
} from "@/api/favor";

const favorStore = {
  namespaced: true,
  state: {
    favorInfo: [],
    isComplete: false,
    favorName: [{ value: null, text: "선택하세요" }],
    house: [],
    facility: [],
    facilityType: [],
    favorfacilityType: [{ value: null, text: null, count: 0 }],
    facilityDetail: null,
    checkFavor: false,
  },
  mutations: {
    CLEAR_FACILITY_LIST(state) {
      state.facility = [];
    },
    CLEAR_FAVOR_FACILITY_TYPE_LIST(state) {
      state.favorfacilityType = [{ value: null, text: null, count: 0 }];
    },
    SET_IS_COMPLETE: (state, isComplete) => {
      state.isComplete = isComplete;
    },
    SET_FAVORINFO: (state, favorInfo) => {
      state.favorInfo = favorInfo;
      state.favorName = [{ value: null, text: "선택하세요" }];
      favorInfo.forEach((favor) => {
        state.favorName.push({
          value: favor,
          text: favor.aptName,
        });
      });
    },
    SET_FACILITY_LIST(state, facility) {
      if (facility == null) {
        state.facility = [];
      } else {
        state.facility = facility;
      }
      state.favorfacilityType = [{ value: null, text: null, count: 0 }];
      state.facilityType.forEach((faclTy) => {
        let total = 0;
        state.facility.forEach((facl) => {
          if (facl.faclTyCd._text === faclTy.faclTyCd) {
            total += 1;
          }
        });
        if (total != 0) {
          state.favorfacilityType.push({
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
    SET_FACILITY_Detail_LIST(state, facilityDetail) {
      if (facilityDetail.servList) {
        state.facilityDetail = facilityDetail.servList.evalInfo._text;
        console.log("sucess : ", state.facilityDetail);
      } else {
        console.log("fail");
      }
    },
    SET_CHECK_FAVOR(state, checkFavor) {
      state.checkFavor = checkFavor;
    },
  },
  actions: {
    async favorRegist({ commit }, favor) {
      await insertFavor(favor, (response) => {
        if (response.data === "success") {
          commit("SET_IS_COMPLETE", true);
          alert("관심 아파트에 등록되었습니다!");
        } else {
          commit("SET_IS_COMPLETE", false);
          alert("관심 아파트 등록이 실패하였습니다!");
        }
      });
    },
    async favorDelete({ commit }, favor) {
      await deleteFavor(favor, (response) => {
        if (response.data === "success") {
          commit("SET_IS_COMPLETE", true);
          alert("관심 아파트에서 삭제되었습니다!");
          favorItem(favor.id, (response) => {
            if (response.data != null) {
              commit("SET_FAVORINFO", response.data);
              commit("CLEAR_FAVOR_FACILITY_TYPE_LIST");
            } else {
              console.log("fail");
            }
          });
        } else {
          commit("SET_IS_COMPLETE", false);
          alert("관심 아파트 삭제가 실패하였습니다!");
        }
      });
    },
    async getFavor({ commit }, id) {
      await favorItem(id, (response) => {
        if (response.data != null) {
          commit("SET_FAVORINFO", response.data);
          commit("CLEAR_FAVOR_FACILITY_TYPE_LIST");
        } else {
          console.log("fail");
        }
      });
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
    async getFacilityDetail({ commit }, wfcltId) {
      await facilityDetail(
        wfcltId,
        (response) => {
          var convert = require("xml-js");
          var xml = response.data;
          var json = convert.xml2json(xml, { compact: true });
          commit("SET_FACILITY_Detail_LIST", JSON.parse(json).facInfoList);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async getCheckFavor({ commit }, favorInfo) {
      await checkFavor(
        favorInfo,
        (response) => {
          if (response.data === "success") {
            commit("SET_CHECK_FAVOR", true);
          } else {
            commit("SET_CHECK_FAVOR", false);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default favorStore;
