<template>
  <b-container class="bv-example-row mt-3 text-center" id="wrapper">
    <h3><b-icon icon="house-fill"></b-icon> 아파트 검색</h3>
    <hr />
    <b-row>
      <b-col cols="6">
        <house-search-bar />
        <b-row>
          <b-col>
            <house-list />
          </b-col>
        </b-row>
      </b-col>
      <b-col cols="6">
        <br />
        <b-row>
          <b-col>
            <kakao-map />
          </b-col>
        </b-row>
        <div
          @click="openInfo"
          @mouseover="boldChange(true)"
          @mouseout="boldChange(false)"
        >
          <b-alert
            show
            variant="success"
            v-b-toggle.infoCollapse
            :class="{ 'text-bold': isBold }"
            v-if="isClose"
          >
            정보 보기
          </b-alert>
          <b-alert
            show
            variant="success"
            :class="{ 'text-bold': isBold }"
            v-b-toggle.infoCollapse
            v-else
          >
            정보 접기
          </b-alert>
        </div>
        <b-collapse id="infoCollapse">
          <house-detail />
        </b-collapse>
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import KakaoMap from "@/components/map/KakaoMap.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseView",
  data() {
    return {
      isClose: true,
      isBold: false,
    };
  },
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
    KakaoMap,
  },
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  methods: {
    openInfo() {
      this.isClose = !this.isClose;
    },
    boldChange(flag) {
      this.isBold = flag;
    },
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
.text-bold {
  font-weight: bold;
}
</style>
