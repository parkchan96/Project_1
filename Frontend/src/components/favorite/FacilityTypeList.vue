<template>
  <div>
    <b-alert show style="text-align: center"> 장애인 편의시설 목록 </b-alert>

    <b-container
      v-if="favorfacilityType.length != 1"
      class="bv-example-row mt-3 text-center"
    >
      <div v-for="(faclTy, index) in favorfacilityType" :key="index">
        <facility-list-item v-if="faclTy.count != 0" :faclTy="faclTy" />
      </div>
    </b-container>
    <b-container
      class="bv-example-row mt-3 text-center"
      v-else-if="favorfacilityType.length == 1 && house != null"
    >
      <b-row class="m-2">
        <b-col cols="10"
          ><b-alert show variant="secondary">없음</b-alert></b-col
        >
      </b-row>
    </b-container>
  </div>
</template>

<script>
import FacilityListItem from "@/components/favorite/FacilityListItem.vue";
import { mapState } from "vuex";

const favorStore = "favorStore";
const houseStore = "houseStore";

export default {
  name: "FacilityTypeList",
  components: {
    FacilityListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(favorStore, ["facility", "favorfacilityType"]),
    ...mapState(houseStore, ["house"]),
  },
};
</script>

<style>
.m-2 {
  display: flex;
  justify-content: center;
}
</style>
