<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h3><b-icon icon="exclamation-circle"></b-icon> 공지사항</h3>
      </b-col>
    </b-row>
    <hr />
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button
          variant="primary"
          @click="[moveWrite(), play()]"
          @focus="play"
          v-if="userInfo.access == 0"
          >글 작성</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          striped
          hover
          :items="articles"
          :fields="fields"
          @row-clicked="viewArticle"
          :class="{ mode: mode }"
        >
        </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { listArticle } from "@/api/board";
import { mapState } from "vuex";
import write from "@/assets/write--.mp3";
const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  name: "BoardList",
  data() {
    return {
      articles: [],
      fields: [
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticle(
      param,
      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  computed: {
    ...mapState(houseStore, ["mode"]),
    ...mapState(memberStore, ["userInfo", "sound"]),
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardDetail",
        params: { articleno: article.articleno },
      });
    },
    play() {
      if (write) {
        var audio = new Audio(write);
        if (this.sound) {
          audio.play();
        }
      }
    },
  },
};
</script>

<style>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
.mode > tbody > tr:hover {
  background-color: gray;
  color: white;
}
.mode > thead {
  background-color: gray;
}
</style>

<style scoped>
.mode {
  background-color: rgb(80, 80, 80);
  color: white;
}
</style>
