<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h3><b-icon icon="book"></b-icon> 글보기</h3>
      </b-col>
    </b-row>
    <hr />
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button
          variant="primary"
          @focus="lplay"
          @click="[listArticle(), lplay()]"
          :class="{ mode: mode }"
          >목록</b-button
        >
      </b-col>
      <b-col
        class="text-right"
        v-if="userInfo.access == 0 && userInfo.id == this.article.userid"
      >
        <b-button
          variant="info"
          size="sm"
          @click="[moveModifyArticle(), mplay()]"
          @focus="mplay"
          class="mr-2"
          :class="{ mode: mode }"
          >글수정</b-button
        >
        <b-button
          variant="danger"
          size="sm"
          @click="[deleteArticle(), dplay()]"
          @focus="dplay"
          :class="{ mode: mode }"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.articleno}.
          ${article.subject} [${article.hit}]</h3><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
          :class="{ boardmode: mode }"
        >
          <b-card-body class="text-left" :class="{ cardbodymode: mode }">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import { getArticle, deleteArticle } from "@/api/board";
import { mapState } from "vuex";
import mod from "@/assets/modarticle.mp3";
import del from "@/assets/delete--.mp3";
import list from "@/assets/list --.mp3";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState(houseStore, ["mode"]),
    ...mapState(memberStore, ["userInfo", "sound"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getArticle(
      this.$route.params.articleno,
      (response) => {
        this.article = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      },
    );
  },
  methods: {
    mplay() {
      if (mod) {
        var audio = new Audio(mod);
        if (this.sound) {
          audio.play();
        }
      }
    },
    dplay() {
      if (del) {
        var audio = new Audio(del);
        if (this.sound) {
          audio.play();
        }
      }
    },
    lplay() {
      if (list) {
        var audio = new Audio(list);
        if (this.sound) {
          audio.play();
        }
      }
    },
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { articleno: this.article.articleno },
      });
    },
    deleteArticle() {
      if (confirm("정말 삭제하시겠습니까?")) {
        deleteArticle(this.article.articleno, () => {
          this.$router.push({ name: "boardList" });
        });
      }
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style scoped>
.mode {
  color: white;
}
.boardmode {
  background-color: gray;
  color: white;
}
.boardmode > .cardbodymode {
  background-color: rgb(80, 80, 80);
}
</style>
