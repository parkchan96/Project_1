<template>
  <div class="example-modal-window">
    <b-nav-item
      class="link align-self-center"
      @click="[openModifyModal(), gplay()]"
      @focus="gplay"
      ><b-icon icon="file-person" aria-hidden="true" /> 정보수정</b-nav-item
    >
    <!-- 컴포넌트 MyModal -->
    <user-modify-modal
      @close="closeModifyModal"
      class="fontColor"
      v-if="modifymodal"
    >
      <!-- default 슬롯 콘텐츠 -->
      <p>정보수정</p>
      <div class="form-group">
        ID<input type="text" v-model="user.id" class="form-control" readonly />
      </div>
      <div class="form-group">
        PW<input type="password" v-model="user.password" class="form-control" />
      </div>
      <div class="form-group">
        Email<input
          type="email"
          v-model="user.email"
          class="form-control"
          readonly
        />
      </div>
      <div class="form-group">
        이름<input type="text" v-model="user.name" class="form-control" />
      </div>
      <div class="form-group">
        나이<input type="number" v-model="user.age" class="form-control" />
      </div>
      <!-- /default -->
      <!-- footer 슬롯 콘텐츠 -->
      <template slot="footer">
        <button @click="modifyUser" type="button" class="btn btn-primary">
          수정
        </button>
        <button @click="deleteUser" type="button" class="btn btn-danger">
          탈퇴
        </button>
      </template>
      <!-- /footer -->
    </user-modify-modal>
  </div>
</template>

<script>
import { modify, del } from "@/api/member";
import UserModifyModal from "../user/UserModifyModal.vue";
import mod from "@/assets/modify --.mp3";
import { mapState, mapActions, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  components: {
    UserModifyModal,
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  data() {
    return {
      modifymodal: false,
      user: { id: null, password: null, name: null, email: null, age: null },
    };
  },
  methods: {
    ...mapMutations(memberStore, [
      "CLEAR_USER_INFO",
      "SET_IS_LOGIN",
      "SET_USER_INFO",
    ]),
    ...mapActions(memberStore, ["userConfirm", "getUserInfo", "userRegist"]),

    gplay() {
      if (mod) {
        var audio = new Audio(mod);
        if (this.sound) {
          audio.play();
        }
      }
    },
    openModifyModal() {
      this.user = this.userInfo;
      this.modifymodal = true;
    },
    closeModifyModal() {
      this.modifymodal = false;
    },
    modifyUser() {
      modify(
        {
          id: this.user.id,
          password: this.user.password,
          email: this.user.email,
          name: this.user.name,
          age: this.user.age,
        },
        ({ data }) => {
          console.log(data);
          let msg = "등록처리시 문제발생";
          if (data.res === "업데이트 성공") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.closeModifyModal();
        },
        (error) => {
          console.log(error);
          alert("나이를 숫자로 입력해주세요!");
        },
      );
    },
    deleteUser() {
      del(
        {
          id: this.user.id,
          password: this.user.password,
          email: this.user.email,
          name: this.user.name,
          age: this.user.age,
        },
        alert("탈퇴가 완료되었습니다."),
      );
      this.closeModifyModal();
      this.CLEAR_USER_INFO();
      this.$router.push({ name: "home" });
    },
  },
};
</script>

<style scoped>
.fontColor p,
.fontColor div {
  color: black;
}
</style>
