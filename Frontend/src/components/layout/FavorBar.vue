<template>
  <b-nav-item href="#" @mousedown="play" @focus="play"
    ><router-link :to="{ name: 'favor' }" class="link">
      관심 아파트</router-link
    ></b-nav-item
  >
</template>

<script>
// import { useSound } from "@vueuse/sound";
import favor from "@/assets/favorapt --.mp3";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  // setup() {
  //   const { play, stop } = useSound(favor);
  //   return {
  //     play,
  //     stop,
  //   };
  // },
  computed: {
    ...mapState(memberStore, ["sound"]),
  },
  methods: {
    play() {
      if (favor) {
        var audio = new Audio(favor);
        if (this.sound) {
          audio.play();
        }
      }
    },
  },
};
</script>

<style></style>
