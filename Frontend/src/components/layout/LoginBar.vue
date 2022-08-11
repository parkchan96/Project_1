<template>
  <div class="example-modal-window">
    <b-nav-item
      class="cursor_test"
      @click="[openLoginModal(), loplay()]"
      @focus="loplay"
    >
      로그인
    </b-nav-item>
    <user-login-modal
      @close="closeLoginModal"
      :class="{ mode: mode }"
      v-if="loginmodal"
    >
      <p>로그인</p>
      <div class="form-group">
        ID<input v-model="user.id" class="form-control" />
      </div>
      <div class="form-group">
        PW<input
          @keyup.enter="confirm"
          v-model="user.password"
          class="form-control"
          type="password"
        />
      </div>

      <template slot="footer">
        <button
          @click="[confirm(), loplay()]"
          type="button"
          class="btn btn-primary"
        >
          로그인
        </button>
        <button
          @click="[closeLoginModal(), play()]"
          type="button"
          class="btn btn-primary"
        >
          취소
        </button>
      </template>
    </user-login-modal>
  </div>
</template>

<script>
import login from "@/assets/login.mp3";
import UserLoginModal from "../user/UserLoginModal.vue";
import { mapState, mapActions, mapMutations } from "vuex";
import cancle from "@/assets/cancle --.mp3";
const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  components: {
    UserLoginModal,
  },

  data() {
    return {
      loginmodal: false,
      user: { id: null, password: null, name: null, email: null, age: null },
    };
  },

  created() {
    this.CLEAR_USER_INFO();
    this.user = {
      id: null,
      password: null,
      name: null,
      email: null,
      age: null,
    };
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo", "userRegist"]),
    ...mapMutations(memberStore, [
      "CLEAR_USER_INFO",
      "SET_IS_LOGIN",
      "SET_USER_INFO",
    ]),
    play() {
      if (cancle) {
        var audio = new Audio(cancle);
        if (this.sound) {
          audio.play();
        }
      }
    },
    loplay() {
      if (login) {
        var audio = new Audio(login);
        if (this.sound) {
          audio.play();
        }
      }
    },
    openLoginModal() {
      this.loginmodal = true;
    },
    closeLoginModal() {
      this.CLEAR_USER_INFO();
      this.loginmodal = false;
      this.user = {
        id: null,
        password: null,
        name: null,
        email: null,
        age: null,
      };
    },
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.closeLoginModal();
      } else {
        alert("회원정보가 다릅니다.");
      }
    },
  },
  computed: {
    ...mapState(houseStore, ["mode"]),
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo", "sound"]),
  },
};
</script>

<style>
b-nav-item {
  color: white;
}
</style>

<style scoped>
.mode {
  color: black;
}
</style>
