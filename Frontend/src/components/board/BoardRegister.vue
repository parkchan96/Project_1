<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h3><b-icon icon="pencil"></b-icon> 글작성</h3>
      </b-col>
    </b-row>
    <hr />
    <board-input-item type="register" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem.vue";

export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
  },
};
</script>

<style></style>
