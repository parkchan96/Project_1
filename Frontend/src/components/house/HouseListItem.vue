<template>
  <b-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" class="text-center align-self-center">
      <img
        thumbnail
        src="@/assets/home_img.png"
        alt="Image 1"
        style="width: 100%; height: 100%"
      />
    </b-col>
    <b-col cols="10" class="align-self-center">
      {{ house.aptName }}
    </b-col>
  </b-row>
</template>

<script>
import { mapActions, mapState } from "vuex";

const houseStore = "houseStore";
const memberStore = "memberStore";
const favorStore = "favorStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(houseStore, [
      "detailHouse",
      "getHouseDealList",
      "getFacility",
    ]),
    ...mapActions(favorStore, ["getCheckFavor"]),
    async selectHouse() {
      const areaInfo = {
        sidoName: this.house.sidoName,
        gugunName: this.house.gugunName,
        roadName: this.house.roadName.split(
          /[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]/,
          1,
        )[0],
      };
      await this.getFacility(areaInfo);
      await this.detailHouse(this.house);
      await this.getHouseDealList(this.house.aptCode);
      const favorInfo = {
        id: this.userInfo.id,
        aptName: this.house.aptName,
        dongCode: this.house.dongCode,
      };
      await this.getCheckFavor(favorInfo);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
