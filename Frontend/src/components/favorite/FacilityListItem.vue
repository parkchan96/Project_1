<template>
  <b-row class="m-2">
    <b-col cols="10">
      <b-alert show variant="secondary" v-b-toggle="faclTy.value">
        {{ faclTy.text }} ({{ faclTy.count }})
      </b-alert>
      <b-collapse :id="faclTy.value">
        <b-card :class="{ mode: mode }">
          <div v-for="(facl, index) in facility" :key="index">
            <p
              @click="getFaclDetail(facl.wfcltId._text)"
              v-if="facl.faclTyCd._text === faclTy.value"
              class="card-text"
              v-b-modal="faclTy.value"
            >
              {{ facl.faclNm._text }}
            </p>
          </div>
        </b-card>
      </b-collapse>
    </b-col>
    <!-- modal start -->
    <b-modal
      :id="faclTy.value"
      size="md"
      title="편의시설 기구표 상세 목록"
      scrollable
    >
      <b-container>
        <div>
          <b-row>
            <b-col> {{ this.facilityDetail }}</b-col>
          </b-row>
        </div>
      </b-container>
    </b-modal>
    <!-- model end -->
  </b-row>
</template>

<script>
import { mapActions, mapState } from "vuex";

const houseStore = "houseStore";
const favorStore = "favorStore";

export default {
  name: "FacilityListItem",
  data() {
    return {};
  },
  props: {
    faclTy: Object,
  },
  computed: {
    ...mapState(houseStore, ["house", "mode"]),
    ...mapState(favorStore, ["facility", "facilityType", "facilityDetail"]),
  },
  methods: {
    ...mapActions(favorStore, [
      "getFacility",
      "getFacilityType",
      "getFacilityDetail",
    ]),
    getFaclDetail(wfcltId) {
      this.getFacilityDetail(wfcltId);
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}

.mode {
  color: black;
}
.m-2 {
  display: flex;
  justify-content: center;
}
</style>
