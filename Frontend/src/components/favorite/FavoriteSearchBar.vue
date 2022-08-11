<template>
  <b-row class="mt-4 mb-4 text-center">
    <b-col class="sm-3" @click="check">
      <b-form-select
        v-model="favor"
        :options="favorName"
        @change="searchDetail"
      ></b-form-select>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";
const favorStore = "favorStore";
const memberStore = "memberStore";

export default {
  data() {
    return {
      favor: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["house"]),
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(favorStore, ["favorInfo", "favorName"]),
  },
  watch: {
    userInfo(userInfo) {
      if (userInfo.id != null) {
        this.getFavor(userInfo.id);
      }
      this.getFacilityType();
      this.favor = null;
    },
  },
  created() {
    if (this.userInfo.id != null) {
      this.getFavor(this.userInfo.id);
    }
    this.CLEAR_HOUSE_Detail_LIST();
    this.CLEAR_FAVOR_FACILITY_TYPE_LIST();
    this.getFacilityType();
    this.favor = null;
  },
  methods: {
    ...mapMutations(favorStore, ["CLEAR_FAVOR_FACILITY_TYPE_LIST"]),
    ...mapMutations(houseStore, ["CLEAR_HOUSE_Detail_LIST"]),
    ...mapActions(favorStore, ["getFavor", "getFacility", "getFacilityType"]),
    ...mapActions(houseStore, ["detailHouse", "getHouseDealList"]),

    check() {
      if (this.userInfo.id == null) {
        alert("로그인후 사용 가능합니다.");
      }
    },
    async searchDetail() {
      const params = {
        aptName: this.favor.aptName,
        dongCode: this.favor.dongCode,
        sidoName: this.favor.sidoName,
        gugunName: this.favor.gugunName,
        roadName: this.favor.roadName,
      };
      this.getFacility(params);
      await this.detailHouse(params);
      this.favor = null;
      this.getHouseDealList(this.house.aptCode);
    },
  },
};
</script>

<style></style>
