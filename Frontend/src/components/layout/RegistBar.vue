<template>
  <div class="example-modal-window">
    <b-nav-item
      class="cursor_test"
      @mousedown="[openRegistModal(), play()]"
      @focus="play"
    >
      회원가입
    </b-nav-item>
    <!-- 컴포넌트 MyModal -->
    <user-regist-modal
      @close="closeRegistModal"
      :class="{ mode: mode }"
      v-if="registmodal"
    >
      <!-- default 슬롯 콘텐츠 -->
      <p>회원가입</p>
      <div class="form-group">
        ID<input type="text" v-model="user.id" class="form-control" />
      </div>
      <div class="form-group">
        PW<input type="password" v-model="user.password" class="form-control" />
      </div>
      <div class="form-group">
        Email<input type="email" v-model="user.email" class="form-control" />
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
        <button type="submit" @click="registUser" class="btn btn-primary">
          가입
        </button>
        <button @click="closeRegistModal" type="button" class="btn btn-primary">
          취소
        </button>
      </template>
      <!-- /footer -->
    </user-regist-modal>
  </div>
</template>

<script>
import UserRegistModal from "../user/UserRegistModal.vue";
import { regist, checkId } from "@/api/member";
import reg from "@/assets/regist--.mp3";
import { mapActions, mapMutations, mapState } from "vuex";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  components: {
    UserRegistModal,
  },
  data() {
    return {
      registmodal: false,
      user: { id: null, password: null, name: null, email: null, age: null },
    };
  },
  computed: {
    ...mapState(houseStore, ["mode"]),
    ...mapState(memberStore, ["ok", "sound"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_ID_FALSE"]),
    ...mapActions(memberStore, ["check"]),

    play() {
      if (reg) {
        var audio = new Audio(reg);
        if (this.sound) {
          audio.play();
        }
      }
    },
    openRegistModal() {
      this.registmodal = true;
    },
    closeRegistModal() {
      this.registmodal = false;
      this.user = {
        id: null,
        password: null,
        name: null,
        email: null,
        age: null,
      };
    },
    registUser() {
      let err = true;
      let msg = "";
      !this.user.id && ((msg = "아이디를 입력해주세요!"), (err = false));
      err &&
        !this.user.password &&
        ((msg = "비밀번호를 입력해주세요!"), (err = false));
      err &&
        !this.user.email &&
        ((msg = "이메일를 입력해주세요!"), (err = false));
      err && !this.user.name && ((msg = "이름을 입력해주세요!"), (err = false));
      err && !this.user.age && ((msg = "나이를 입력해주세요!"), (err = false));

      if (!err) alert(msg);
      else
        checkId(this.user.id, ({ data }) => {
          if (data.res === "success") {
            this.registU();
          } else {
            alert("이미 사용 중인 아이디입니다!");
          }
        });
    },
    registU() {
      regist(
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
          if (data.res === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.closeRegistModal();
        },
        (error) => {
          console.log(error);
          alert("나이를 숫자로 입력해주세요!");
        },
      );
    },
  },
};
</script>

<style scoped>
.mode {
  color: black;
}
</style>
