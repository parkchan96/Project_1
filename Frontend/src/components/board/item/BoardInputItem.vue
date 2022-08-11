<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset" :class="{ mode: mode }">
        <b-form-group
          id="subject-group"
          label="제목:"
          label-for="subject"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="subject"
            v-model="article.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>
        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>
        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          @click="play"
          @focus="play"
          >글작성</b-button
        >
        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          @click="mplay"
          @focus="mplay"
          v-else
          >글수정</b-button
        >
        <b-button
          type="reset"
          variant="danger"
          class="m-1"
          @click="cplay"
          @focus="cplay"
          >취소</b-button
        >
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { writeArticle, getArticle, modifyArticle } from "@/api/board";
import { mapState } from "vuex";
import write from "@/assets/write--.mp3";
import cancle from "@/assets/cancle --.mp3";
import mod from "@/assets/modarticle.mp3";

const memberStore = "memberStore";
const houseStore = "houseStore";

export default {
  name: "BoardInputItem",
  data() {
    return {
      article: {
        articleno: 0,
        userid: "",
        subject: "",
        content: "",
      },
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo", "sound"]),
    ...mapState(houseStore, ["mode"]),
  },
  created() {
    if (this.type === "modify") {
      getArticle(
        this.$route.params.articleno,
        ({ data }) => {
          this.article = data;
        },
        (error) => {
          console.log(error);
        },
      );
      this.isUserid = true;
    } else {
      this.article.userid = this.userInfo.id;
    }
  },
  methods: {
    play() {
      if (write) {
        var audio = new Audio(write);
        if (this.sound) {
          audio.play();
        }
      }
    },
    cplay() {
      if (cancle) {
        var audio = new Audio(cancle);
        if (this.sound) {
          audio.play();
        }
      }
    },
    mplay() {
      if (mod) {
        var audio = new Audio(mod);
        if (this.sound) {
          audio.play();
        }
      }
    },
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.subject = "";
      this.article.content = "";
      this.$router.push({ name: "boardList" });
    },
    registArticle() {
      writeArticle(
        {
          userid: this.userInfo.id,
          subject: this.article.subject,
          content: this.article.content,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    modifyArticle() {
      modifyArticle(
        {
          articleno: this.article.articleno,
          userid: this.article.userid,
          subject: this.article.subject,
          content: this.article.content,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push({ name: "boardList" });
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveList() {
      this.$router.push({ name: "boardList" });
    },
  },
};
</script>
<style></style>

<style scoped>
.mode {
  color: white;
}

.mode #subject,
.mode #content {
  background-color: rgb(190, 190, 190);
}
</style>
