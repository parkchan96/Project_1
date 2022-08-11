<template>
  <div id="wrapper">
    <div class="cover">
      <div class="cover-title">
        <p>Happy House</p>
      </div>
      <img class="cover-img" src="@/assets/people.png" />
    </div>
  </div>
</template>
<script></script>

<style>
/* cover part css */
.cover {
  position: relative;
}
.cover-title {
  color: black;
  position: absolute;
  top: 70px;
  font-family: "Lobster", cursive;
  /* font-family: 'Cookie', cursive; */
  font-size: 100px;
  width: 100vw;
  animation-name: titleAnimation;
  animation-duration: 1.5s;
  text-align: center;
}
.cover-img {
  width: 100%;
  height: calc(100vw / 2 - 33px);
}
@keyframes titleAnimation {
  from {
    top: 200px;
  }
  to {
    top: 70px;
  }
}
/* 반응형 */
@media screen and (max-width: 835px) {
  .cover-title {
    font-size: 80px;
  }
}
@media screen and (max-width: 660px) {
  .cover-title {
    font-size: 50px;
  }
}
</style>
