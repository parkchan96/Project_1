<template>
  <b-container v-if="house" class="bv-example-row mt-3 text-center">
    <b-row>
      <b-col>
        <b-alert show variant="dark">법정동 : {{ house.dongName }} </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="dark"
          >건축년도 : {{ house.buildYear }}년</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="dark"
          >거래금액 :
          {{
            (parseInt(house.recentPrice.replace(",", "")) * 10000) | price
          }}원</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="dark">면적 : {{ house.area }}m²</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="dark">층 : {{ house.floor }}층</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="dark"
          >가까운 지하철역 : {{ house.subway }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col
        @mouseover="[cursorChange1(true), boldChange(true)]"
        @mouseout="[cursorChange1(false), boldChange(false)]"
        :class="{ 'mouse-over-cursor-type1': isPointer }"
      >
        <b-alert
          show
          variant="info "
          v-b-modal.modal-deal
          :class="{ 'text-bold': isBold }"
          >거래 현황
        </b-alert>
        <!-- modal start -->
        <b-modal
          id="modal-deal"
          size="lg"
          title="아파트 전체 거래 현황"
          scrollable
        >
          <b-container>
            <div v-if="houseDealList != []">
              <b-row>
                <b-col>
                  <b-table
                    striped
                    :items="houseDealList"
                    :fields="fields"
                  ></b-table>
                </b-col>
              </b-row>
              <hr />
              <!-- </div> -->
            </div>
            <div v-else>
              <h4>시설 목록이 없습니다.</h4>
            </div>
          </b-container>
        </b-modal>
        <!-- model end -->
      </b-col>
    </b-row>
    <b-row>
      <b-col
        @click="deleteFavor"
        @mouseover="[cursorChange2(true), boldChange2(true)]"
        @mouseout="[cursorChange2(false), boldChange2(false)]"
        :class="{ 'mouse-over-cursor-type2': isGrab }"
      >
        <b-alert show variant="danger" :class="{ 'text-bold2': isBoold }">
          관심 아파트에서 삭제
        </b-alert>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const houseStore = "houseStore";
const memberStore = "memberStore";
const favorStore = "favorStore";

export default {
  name: "FavoriteDetail",
  data() {
    return {
      fields: [
        { key: "dealDate", label: "거래 일자", tdClass: "tdSubject" },
        { key: "dealAmount", label: "거래 금액 (원)", tdClass: "tdClass" },
        { key: "area", label: "면적 (m²)", tdClass: "tdClass" },
        { key: "floor", label: "층", tdClass: "tdClass" },
      ],
      isPointer: false,
      isGrab: false,
      isBold: false,
      isBoold: false,
    };
  },
  computed: {
    ...mapState(houseStore, ["house", "houseDealList"]),
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(favorStore, ["facility", "facilityType"]),
  },
  created() {
    this.CLEAR_HOUSE_Detail_LIST();
    this.CLEAR_HOUSE_LIST();
  },
  methods: {
    ...mapActions(favorStore, ["getFavor", "favorDelete"]),
    ...mapMutations(houseStore, [
      "CLEAR_HOUSE_Detail_LIST",
      "CLEAR_HOUSE_LIST",
    ]),
    deleteFavor() {
      const favor = {
        id: this.userInfo.id,
        aptName: this.house.aptName,
        dongCode: this.house.dongCode,
      };
      this.favorDelete(favor);
      this.getFavor(this.userInfo.id);
      this.CLEAR_HOUSE_Detail_LIST();
      this.CLEAR_HOUSE_LIST();
    },
    cursorChange1(flag) {
      this.isPointer = flag;
    },
    cursorChange2(flag) {
      this.isGrab = flag;
    },
    boldChange(flag) {
      this.isBold = flag;
    },
    boldChange2(flag) {
      this.isBoold = flag;
    },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style scoped>
.mouse-over-cursor-type1 {
  cursor: pointer;
}
.mouse-over-cursor-type2 {
  cursor: grab;
}
.tdClass {
  width: 60px;
  text-align: center;
}
.tdSubject {
  width: 80px;
  text-align: left;
}
.text-bold {
  font-weight: bold;
}
.text-bold2 {
  font-weight: bold;
}
</style>
