<template>
  <b-container class="bv-example-row mt-3 text-center" id="wrapper">
    <h3 class="underline-orange">
      <b-icon icon="bookmark-check"></b-icon> 관심 아파트
    </h3>
    <hr />
    <b-row>
      <b-col cols="6" align="left">
        <favorite-search-bar />
      </b-col>
    </b-row>
    <b-row>
      <b-col align="left">
        <b-row>
          <b-col>
            <kakao-map />
          </b-col>
        </b-row>
        <b-row v-if="house != null">
          <b-col align="center">
            <h4>{{ house.aptName }}</h4>
          </b-col>
        </b-row>
        <b-row>
          <b-col
            align="left"
            @mouseover="boldchange(true)"
            @mouseout="boldchange(false)"
          >
            <div @click="openInfo" v-if="house != null">
              <b-alert
                show
                variant="success"
                style="text-align: center"
                v-b-toggle.infoCollapse
                :class="{ 'text-bold': isBold }"
                v-if="isClose"
              >
                정보 보기
              </b-alert>
              <b-alert
                show
                variant="success"
                style="text-align: center"
                :class="{ 'text-bold': isBold }"
                v-b-toggle.infoCollapse
                v-else
              >
                정보 접기
              </b-alert>
            </div>
            <b-collapse id="infoCollapse">
              <favorite-detail />
            </b-collapse>
          </b-col>
        </b-row>
      </b-col>
      <b-col align="left">
        <b-row>
          <b-col align="left">
            <facility-type-list />
          </b-col>
        </b-row>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import FavoriteSearchBar from "@/components/favorite/FavoriteSearchBar.vue";
import FavoriteDetail from "@/components/favorite/FavoriteDetail.vue";
import FacilityTypeList from "@/components/favorite/FacilityTypeList.vue";
import KakaoMap from "@/components/map/KakaoMap.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "FavoriteView",
  data() {
    return {
      isClose: true,
      isBold: false,
      isBoold: false,
    };
  },
  components: { FavoriteSearchBar, FavoriteDetail, FacilityTypeList, KakaoMap },
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  methods: {
    openInfo() {
      this.isClose = !this.isClose;
    },
    boldchange(flag) {
      this.isBold = flag;
    },
    boldchange2(flag) {
      this.isBoold = flag;
    },
  },
};
</script>

<style>
.text-bold {
  font-weight: bold;
}
.text-bold2 {
  font-weight: bold;
}
</style>
