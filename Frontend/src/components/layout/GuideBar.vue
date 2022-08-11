<template>
  <div class="example-modal-window">
    <b-nav-item
      class="link align-self-center"
      @click="[openGuideModal(), play()]"
      ><b-icon icon="file-person" aria-hidden="true" @focus="play" />
      가이드</b-nav-item
    >
    <!-- 컴포넌트 MyModal -->
    <guide-modal @close="closeGuideModal" v-if="guidemodal">
      <!-- default 슬롯 콘텐츠 -->
      <p>키보드 사용 안내 가이드</p>
      <div class="form-group">포커스 이동 : Tab</div>
      <div class="form-group">포커스 뒤로 이동 : Shift + Tab</div>
      <div class="form-group">클릭 : Enter</div>
      <div class="form-group">뒤로가기 : Alt + ←</div>
      <div class="form-group">앞으로 가기 : Alt + →</div>
      <!-- /default -->
      <!-- footer 슬롯 콘텐츠 -->
      <!-- /footer -->
    </guide-modal>
  </div>
</template>

<script>
import GuideModal from "../guide/GuideModal.vue";
import { useSound } from "@vueuse/sound";
import guide from "@/assets/guide --.mp3";

export default {
  setup() {
    const { play, stop } = useSound(guide);
    return {
      play,
      stop,
    };
  },
  data() {
    return {
      guidemodal: false,
    };
  },
  components: {
    GuideModal,
  },
  methods: {
    openGuideModal() {
      this.guidemodal = true;
    },
    closeGuideModal() {
      this.guidemodal = false;
    },
  },
};
</script>

<style></style>
