<template>
  <div id="map"></div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  data() {
    return {
      map: null,
      markers: [],
    };
  },
  watch: {
    house(house) {
      if (house != null) {
        let lat = house.lat;
        let lng = house.lng;

        const container = document.getElementById("map");
        const options = {
          center: new kakao.maps.LatLng(lat, lng),
          level: 5,
        };
        this.map = new kakao.maps.Map(container, options);
        var markerPosition = new kakao.maps.LatLng(lat, lng);

        var marker = new kakao.maps.Marker({
          position: markerPosition,
          title: house.aptName,
        });

        marker.setMap(this.map);
      }
    },
  },
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.5012743, 127.039585),
        level: 8,
      };
      this.map = new kakao.maps.Map(container, options);
    },
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");

      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}`;
      /* global kakao */
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
      });
      document.head.appendChild(script);
    } else {
      this.initMap;
    }
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  min-height: 450px;
  margin-bottom: 20px;
}
</style>
