<template>
  <b-nav-item @focus="gplay" @click="gplay">
    <router-link :to="{ name: 'house' }" class="link">
      아파트 검색</router-link
    ></b-nav-item
  >
</template>

<script>
import apt from "@/assets/apt.mp3";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  computed: {
    ...mapState(memberStore, ["sound"]),
  },
  methods: {
    gplay() {
      if (apt) {
        var audio = new Audio(apt);
        if (this.sound) {
          audio.play();
        }
      }
    },
  },
};
</script>

<style>
b-nav-item > a {
  text-decoration: none;
}
.link {
  color: white;
}
a:hover {
  text-decoration: none;
  font-weight: bold;
}
a.link.router-link-exact-active.router-link-active {
  text-decoration: none;
}
a.link.router-link-exact-active.router-link-active:hover {
  text-decoration: none;
  color: white;
}
</style>
