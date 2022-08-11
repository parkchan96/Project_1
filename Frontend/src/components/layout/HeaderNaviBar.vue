<template>
  <div id="header">
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <home-bar />
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <apt-bar />
          <favor-bar />
          <board-bar />
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-if="userInfo.id != null">
          <b-nav-item class="align-self-center">
            {{ userInfo.name }}({{ userInfo.id }})님 환영합니다.
          </b-nav-item>
          <b-nav-item class="align-self-center">
            <modify-bar />
          </b-nav-item>
          <b-nav-item
            class="link align-self-center"
            @click.prevent="[onClickLogout(), play()]"
            @focus="play"
            ><b-icon icon="power" aria-hidden="true" /> 로그아웃
          </b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-else>
          <login-bar />
          <regist-bar />
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import BoardBar from "./BoardBar.vue";
import AptBar from "./AptBar.vue";
import FavorBar from "./FavorBar.vue";
import HomeBar from "./HomeBar.vue";
import LoginBar from "./LoginBar.vue";
import RegistBar from "./RegistBar.vue";
import ModifyBar from "./ModifyBar.vue";

import lo from "@/assets/logout --.mp3";

const memberStore = "memberStore";

export default {
  name: "HeaderNaviBar",
  components: {
    BoardBar,
    AptBar,
    FavorBar,
    HomeBar,
    LoginBar,
    RegistBar,
    ModifyBar,
  },
  data() {
    return {};
  },

  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo", "sound"]),
  },
  methods: {
    ...mapMutations(memberStore, ["CLEAR_USER_INFO"]),
    onClickLogout() {
      this.CLEAR_USER_INFO();
      sessionStorage.removeItem("access-token");

      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
    play() {
      if (lo) {
        var audio = new Audio(lo);
        if (this.sound) {
          audio.play();
        }
      }
    },
  },
};
</script>

<style>
.fa-leaf {
  color: green;
  margin-right: 5px;
}
.logo {
  height: 26px;
}
.logo a {
  font-family: "Lobster", cursive;
  font-size: 20px;
}
.cursor_test {
  cursor: pointer;
}

.nav-item.align-self-center {
  color: white;
}
</style>
