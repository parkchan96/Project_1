<template>
  <div>
    <div class="aa">
      <button
        class="btn btn-secondary"
        @click="[changeguide(), gplay()]"
        @focus="gplay"
      >
        가이드
      </button>
    </div>

    <div class="s_float" v-if="guide" :class="{ mode: mode }">
      <br />
      <div>
        <h4 class="jm-font">화면 모드</h4>
      </div>
      <div align="center">
        <b-button
          @click="[switchMode(), lplay()]"
          @focus="lplay"
          variant="secondary"
          size="sm"
          v-if="mode"
        >
          라이트모드로 보기
        </b-button>
        <b-button
          @click="[switchMode(), dplay()]"
          @focus="dplay"
          variant="secondary"
          size="sm"
          v-else
        >
          다크모드로 보기
        </b-button>
      </div>
      <hr />
      <div><h4>소리</h4></div>
      <div align="center">
        <b-button variant="secondary" size="sm" @click="onoff" v-if="sound"
          >ON</b-button
        >
        <b-button variant="secondary" size="sm" @click="onoff" v-else
          >OFF</b-button
        >
      </div>
      <hr />
      <div>
        <h4 class="jm-font">글자 크기 조정</h4>
      </div>
      <div align="center">
        <b-button-group size="sm" class="sideguide">
          <b-button variant="secondary" @click="fontSizeDown"
            ><b-icon icon="zoom-out" variant="light"
          /></b-button>
          <b-button variant="secondary" @click="fontSizeReset">
            <b-icon icon="arrow-clockwise" variant="light" /> 초기화
          </b-button>
          <b-button variant="secondary" @click="fontSizeUp"
            ><b-icon icon="zoom-in" variant="light"
          /></b-button>
        </b-button-group>
      </div>
      <hr />
      <div>
        <h4 class="jm-font">키보드 가이드</h4>
        <p class="jm-font">포커스 이동 : Tab</p>
        <p class="jm-font">포커스 뒤로 이동 : Shift + Tab</p>
        <p class="jm-font">클릭 : Enter</p>
        <p class="jm-font">뒤로가기 : Alt + ←</p>
        <p class="jm-font">앞으로 가기 : Alt + →</p>
      </div>
    </div>
    <div v-else></div>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";

import guide from "@/assets/guide2 --.mp3";
import light from "@/assets/light --.mp3";
import dark from "@/assets/dark --.mp3";
const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  data() {
    return {
      guide: false,
    };
  },
  computed: {
    ...mapState(houseStore, ["mode"]),
    ...mapState(memberStore, ["sound"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_SOUND"]),
    onoff() {
      this.SET_SOUND();
      console.log(this.sound);
    },
    gplay() {
      if (guide) {
        var audio = new Audio(guide);
        if (this.sound) {
          audio.play();
        }
      }
    },
    lplay() {
      if (light) {
        var audio = new Audio(light);
        if (this.sound) {
          audio.play();
        }
      }
    },
    dplay() {
      if (dark) {
        var audio = new Audio(dark);
        if (this.sound) {
          audio.play();
        }
      }
    },
    changeguide() {
      if (this.guide) {
        this.guide = false;
      } else {
        this.guide = true;
      }
    },
    fontSizeDown() {
      var regex = /[^0-9]/g;
      var size = Number(
        document.getElementById("app").style.fontSize.replace(regex, ""),
      );
      if (size < 11) {
        alert("최소 크기입니다.");
      } else {
        size--;
        document.getElementById("app").style.fontSize = size + "px";
      }
    },
    fontSizeReset() {
      document.getElementById("app").style.fontSize = "16px";
    },
    fontSizeUp() {
      var regex = /[^0-9]/g;
      var size = Number(
        document.getElementById("app").style.fontSize.replace(regex, ""),
      );
      if (size > 21) {
        alert("최대 크기입니다.");
      } else {
        size++;
        document.getElementById("app").style.fontSize = size + "px";
      }
    },

    ...mapMutations(houseStore, ["SET_WINDOW_MODE"]),
    switchMode() {
      this.SET_WINDOW_MODE();
    },
  },
};
</script>

<style>
.s_float {
  width: 230px;
  background: white;
  color: black;
  border: 2px solid grey;
  border-radius: 20px;
  margin-left: 5px;
}

h4 {
  text-align: center;
}

p {
  text-align: center;
}

b-button:focus {
  outline: 5px solid #a00;
}
</style>

<style scoped>
.mode {
  background-color: #343a40;
  color: white;
}
.mode h4,
.mode p {
  color: white;
}

.aa {
  padding: 5px;
}
</style>
