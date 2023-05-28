<template>
  <el-date-picker
      v-model="selectedDate"
      type="date"
      :picker-options="pickerOptions"
      :disabled-date="disdabledate1"
  ></el-date-picker>
</template>

<script>

export default {
  data() {
    return {
      selectedDate: null,
      pickerOptions: {
      },
      disabledRangesData: []
    }
  },
  methods: {
    disdabledate1(time) {
        // const startDate = new Date("2023-05-25");
        // const endDate = new Date("2023-05-30");

      const disabledRanges = [
        // Khoảng thời gian không được chọn thứ nhất
        {
          startDate: new Date("2023-05-01"),
          endDate: new Date("2023-05-15"),
        },
        // Khoảng thời gian không được chọn thứ hai
        {
          startDate: new Date("2023-05-29"),
          endDate: new Date("2023-05-31"),
        },
      ];

      for (const range of disabledRanges) {
        if (
            time >= range.startDate.getTime() &&
            time <= range.endDate.getTime()
        ) {
          console.log('range.startDate.getTime()', range.startDate.getTime())
          return true;
        }
      }

      return false

      // return time >= startDate && time.getTime() <= endDate;
    },
  }
};
</script>
