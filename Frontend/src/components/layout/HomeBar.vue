<template>
  <b-navbar-brand @click="play" @focus="play">
    <div class="logo">
      <router-link to="/" class="link"
        ><i class="fas fa-leaf"></i>Happy House</router-link
      >
    </div>
  </b-navbar-brand>
</template>

<script>
import home from "@/assets/home.mp3";

import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  computed: {
    ...mapState(memberStore, ["sound"]),
  },

  methods: {
    play() {
      if (home) {
        var audio = new Audio(home);
        if (this.sound) {
          audio.play();
        }
      }
    },
  },
};
</script>

<style>
a {
  text-decoration: none;
}
.link {
  color: white;
}
</style>
