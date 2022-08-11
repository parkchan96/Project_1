<template>
  <b-container>
    <b-row class="mt-4 mb-4 text-center" :class="{ mode: mode }">
      <b-col class="sm-3">
        <b-form-select v-model="searchBy" :options="options"></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-input
          v-model.trim="searchKeyWord"
          placeholder="검색어 입력"
          @keypress.enter="sendKeyword"
          @click="siplay"
          @focus="siplay"
        ></b-form-input>
      </b-col>
      <b-col class="sm-3" align="left">
        <b-button
          variant="outline-primary"
          @click="[sendKeyword(), ssplay()]"
          @focus="ssplay"
          :class="{ mode: mode }"
          >검색</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mt-4 mb-4 text-center">
      <b-col class="sm-3" @keypress.enter="sidoplay">
        <b-form-select
          @click="sidoplay"
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3" @keypress.enter="gugunplay">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="[dongList(), searchAptGugun()]"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3" @keypress.enter="dongplay">
        <b-form-select
          v-model="dongCode"
          :options="dongs"
          @change="searchAptDong"
        ></b-form-select>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import ss from "@/assets/ss --.mp3";
import si from "@/assets/si --.mp3";
import sido from "@/assets/sido --.mp3";
import gugun from "@/assets/gugun --.mp3";
import dong from "@/assets/dong --.mp3";
const houseStore = "houseStore";
const memberStore = "memberStore";
export default {
  name: "HouseSearchBar",
  data() {
    return {
      options: [
        { value: "아파트 이름", text: "아파트 이름" },
        { value: "동 이름", text: "동 이름" },
      ],
      searchBy: null,
      searchKeyWord: null,
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses", "mode"]),
    ...mapState(memberStore, ["sound"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.CLEAR_HOUSE_LIST();
    this.CLEAR_HOUSE_Detail_LIST();
    this.getSido();
    this.getFacilityType();
    this.searchBy = "아파트 이름";
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseListByGugun",
      "getHouseListByDong",
      "getHouseListBySearchName",
      "getHouseListBySearchDong",
      "getFacilityType",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_HOUSE_LIST",
      "CLEAR_HOUSE_Detail_LIST",
    ]),

    ssplay() {
      if (ss) {
        var audio = new Audio(ss);
        if (this.sound) {
          audio.play();
        }
      }
    },
    siplay() {
      if (si) {
        var audio = new Audio(si);
        if (this.sound) {
          audio.play();
        }
      }
    },
    sidoplay() {
      if (sido) {
        var audio = new Audio(sido);
        if (this.sound) {
          audio.play();
        }
      }
    },
    gugunplay() {
      if (gugun) {
        var audio = new Audio(gugun);
        if (this.sound) {
          audio.play();
        }
      }
    },
    dongplay() {
      if (dong) {
        var audio = new Audio(dong);
        if (this.sound) {
          audio.play();
        }
      }
    },
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.gugunCode = null;
      this.dongCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchAptGugun() {
      this.CLEAR_HOUSE_LIST();
      if (this.gugunCode) this.getHouseListByGugun(this.gugunCode);
    },
    searchAptDong() {
      this.CLEAR_HOUSE_LIST();
      if (this.dongCode) this.getHouseListByDong(this.dongCode);
    },
    sendKeyword() {
      this.CLEAR_HOUSE_LIST();
      if (this.searchKeyWord && this.searchBy == "아파트 이름") {
        this.getHouseListBySearchName(this.searchKeyWord);
      } else if (this.searchKeyWord && this.searchBy == "동 이름") {
        this.getHouseListBySearchDong(this.searchKeyWord);
      }
    },
  },
};
</script>

<style>
.mode .custom-select > option {
  background-color: gray;
  color: snow;
}
</style>

<style scoped>
.mode {
  color: white;
}
</style>
