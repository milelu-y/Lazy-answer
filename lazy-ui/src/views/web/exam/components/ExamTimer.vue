<template>
  <div>
    <span style="color: rgb(255, 0, 0); font-weight: 700;">{{ min }}分钟{{sec}}秒</span>
  </div>
</template>

<script>
export default {
  name: "ExamTimer",
  props: {
    value: Number
  },
  watch: {
    value: {
      handler: function handler() {
        this.leftSeconds = this.value * 60;
        this.countdown();
      }
    }
  },
  data() {
    return {
      leftSeconds: 0,
      min: '00',
      sec: '00'
    }
  },
  created() {
    this.leftSeconds = this.value * 60;
  },
  methods: {
    countdown() {
      if (this.leftSeconds < 0) {
        this.$emit('timeout');
        return;
      } // 时
      var min = parseInt(this.leftSeconds / 60);
      var sec = parseInt(this.leftSeconds % 60);
      this.min = min > 9 ? min : '0' + min;
      this.sec = sec > 9 ? sec : '0' + sec;
      this.leftSeconds -= 1;
      var that = this;
      setTimeout(function () {
        that.countdown();
      }, 1000);
    }
  }
}
</script>

<style scoped>

</style>
