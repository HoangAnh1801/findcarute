<template>
  <div class="container-fluid px-0">
    <div class="d-flex">
      <SideBar />
      <div class="container">
        <router-view></router-view>
      </div>

    </div>
  </div>

</template>

<script>
import SideBar from '@/components/quantri/LeftSidebar'

export default {
  components: { SideBar },
  name: "LayoutQuanTri"
}
</script>

<style scoped>

</style>