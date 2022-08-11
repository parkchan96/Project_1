<template>
  <div>
    <div id="app" style="font-size: 16px" :class="{ mode: mode }">
      <navi-bar />
      <side-guide class="side" />
      <router-view class="router" />
      <footer-bar />
    </div>
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";
import FooterBar from "@/components/layout/FooterBar.vue";
import SideGuide from "@/components/guide/SideGuide.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "App",
  components: {
    NaviBar,
    FooterBar,
    SideGuide,
  },
  computed: {
    ...mapState(houseStore, ["mode"]),
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap");
.side {
  position: absolute;
  z-index: 1;
}

#app {
  font-family: "Jua", sans-serif;
}

.router {
  position: relative;
}

a {
  text-decoration: none;
}

a:hover {
  text-decoration: none;
  font-weight: bold;
  color: chartreuse;
}

a.router-link-exact-active {
  color: white;
}

#wrapper {
  min-height: calc(100vh - 132px);
}

*:focus {
  outline: 5px solid #a00;
}

.mode {
  background-color: rgb(35, 35, 35);
  color: white;
}
</style>
