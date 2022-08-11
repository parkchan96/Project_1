<template>
  <b-container v-if="house" class="bv-example-row">
    <b-row>
      <b-col
        ><h3 align="center">{{ house.aptName }}</h3></b-col
      >
    </b-row>

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
          variant="success"
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
        @mouseover="[cursorChange1(true), boldChange2(true)]"
        @mouseout="[cursorChange1(false), boldChange2(false)]"
        :class="{ 'mouse-over-cursor-type1': isPointer }"
      >
        <b-alert
          show
          variant="warning"
          v-b-modal.modal-facility
          :class="{ 'text-bold': isBoold }"
          >장애인 편의시설 목록
        </b-alert>
        <!-- modal start -->
        <b-modal
          id="modal-facility"
          size="md"
          title="장애인 편의시설 목록"
          scrollable
        >
          <b-container>
            <div v-if="detailfacilityType.length != 1">
              <div v-for="(faclTy, index) in detailfacilityType" :key="index">
                <b-row v-if="faclTy.count != 0">
                  <b-col> {{ faclTy.text }} ({{ faclTy.count }}) </b-col>
                </b-row>
              </div>
            </div>
            <div v-else>
              <h4>시설 목록이 없습니다.</h4>
            </div>
          </b-container>
        </b-modal>
        <!-- model end -->
      </b-col>
    </b-row>
    <b-row v-if="userInfo.id != null && !checkFavor">
      <b-col
        @click="registFavor"
        @mouseover="[cursorChange2(true), boldChange3(true)]"
        @mouseout="[cursorChange2(false), boldChange3(false)]"
        :class="{ 'mouse-over-cursor-type2': isGrab }"
      >
        <b-alert show variant="primary" :class="{ 'text-bold2': isBooold }"
          >관심 아파트 추가</b-alert
        >
      </b-col>
    </b-row>
    <b-row v-else-if="userInfo.id != null && checkFavor">
      <b-col
        @click="deleteFavor"
        @mouseover="[cursorChange2(true), boldChange3(true)]"
        @mouseout="[cursorChange2(false), boldChange3(false)]"
        :class="{ 'mouse-over-cursor-type2': isGrab }"
      >
        <b-alert show variant="danger" :class="{ 'text-bold2': isBooold }"
          >관심 아파트 삭제</b-alert
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const houseStore = "houseStore";
const favorStore = "favorStore";
const memberStore = "memberStore";

export default {
  name: "HouseDetail",
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
      isBooold: false,
    };
  },
  computed: {
    ...mapState(houseStore, [
      "house",
      "houseDealList",
      "facility",
      "detailfacilityType",
    ]),
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(favorStore, ["checkFavor"]),
  },
  methods: {
    ...mapActions(houseStore, [
      "detailHouse",
      "getHouseDealList",
      "getFacility",
    ]),
    ...mapActions(favorStore, ["favorRegist", "favorDelete", "getCheckFavor"]),
    ...mapMutations(houseStore, ["CLEAR_HOUSE_Detail_LIST"]),
    async registFavor() {
      const favor = {
        id: this.userInfo.id,
        aptName: this.house.aptName,
        dongCode: this.house.dongCode,
        sidoName: this.house.sidoName,
        gugunName: this.house.gugunName,
        roadName: this.house.roadName.split(
          /[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]/,
          1,
        )[0],
      };
      await this.favorRegist(favor);
      await this.detailHouse(this.house);
      const favorInfo = {
        id: this.userInfo.id,
        aptName: this.house.aptName,
        dongCode: this.house.dongCode,
      };
      await this.getCheckFavor(favorInfo);
    },
    async deleteFavor() {
      const favor = {
        id: this.userInfo.id,
        aptName: this.house.aptName,
        dongCode: this.house.dongCode,
      };
      await this.favorDelete(favor);
      await this.detailHouse(this.house);
      const favorInfo = {
        id: this.userInfo.id,
        aptName: this.house.aptName,
        dongCode: this.house.dongCode,
      };
      await this.getCheckFavor(favorInfo);
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
    boldChange3(flag) {
      this.isBooold = flag;
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
.tdClass {
  width: 60px;
  text-align: center;
}
.tdSubject {
  width: 80px;
  text-align: left;
}
.mouse-over-cursor-type1 {
  cursor: pointer;
}
.mouse-over-cursor-type2 {
  cursor: grab;
}
.text-bold {
  font-weight: bold;
}
.text-bold2 {
  font-weight: bold;
}
.text-bold3 {
  font-weight: bold;
}
</style>
