<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row-mt-3">
    <div style="overflow: scroll; height: 400px" class="box">
      <house-list-item
        v-for="(house, index) in houses"
        :key="index"
        :house="house"
      />
    </div>
  </b-container>
  <b-container v-else class="bv-example-row-mt-3" :class="{ mode: mode }">
    <br />
    <b-row>
      <b-col
        ><b-alert show variant="secondary"
          >주택 목록이 없습니다.</b-alert
        ></b-col
      >
    </b-row>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(houseStore, ["houses", "mode"]),
  },
};
</script>

<style>
.bv-example-row-mt-3 {
  background-color: rgb(253, 255, 225);
  border: 2px solid gray;
  height: 405px;
  border-radius: 10px;
}
.box {
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
.box::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
</style>

<style scoped>
.mode .bv-example-row-mt-3 {
  background-color: rgb(80, 80, 80);
  border: 2px solid gray;
  height: 405px;
  border-radius: 10px;
}

.mode .box > div:hover {
  background-color: gray;
}

.box > div:hover {
  background-color: rgb(220, 220, 220);
  border-radius: 8px;
}
</style>
