<template>
  <footer :class="{ mode: mode }">
    Copyright by PARK CHAN & JEON BYOUNGCHAN in SSAFY 7th.
  </footer>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";
export default {
  name: "FooterBar",
  computed: {
    ...mapState(houseStore, ["mode"]),
  },
};
</script>

<style scoped>
footer {
  height: 60px;
  width: 100%;
  bottom: 0;
  left: 0;
  padding: 20px;
  text-align: center;
  color: gray;
  background-color: #e8e7e7;
}

.mode {
  background-color: #343a40;
}
</style>
