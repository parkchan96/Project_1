<template>
  <b-nav-item @focus="gplay" @click="gplay">
    <router-link :to="{ name: 'board' }" class="link">
      공지사항</router-link
    ></b-nav-item
  >
</template>

<script>
import board from "@/assets/gongji --.mp3";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  computed: {
    ...mapState(memberStore, ["sound"]),
  },
  methods: {
    gplay() {
      if (board) {
        var audio = new Audio(board);
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
.link:hover {
  text-decoration: none;
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
