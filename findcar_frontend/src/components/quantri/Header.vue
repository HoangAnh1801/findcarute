<template>
  <div class="bg-9dc5e7 container-fluid">
    <div class="row">
      <el-dropdown size="large" class="justify-content-end">
        <el-avatar
            src="https://e7.pngegg.com/pngimages/348/800/png-clipart-man-wearing-blue-shirt-illustration-computer-icons-avatar-user-login-avatar-blue-child.png"
        />

        <template #dropdown>
          <el-dropdown-menu class="user-infor-toolbar pb-0">
            <el-dropdown-item @click="updateThongTin()"><v-icon icon="mdi:mdi-account mr-2" ></v-icon>Trang cá nhân</el-dropdown-item>
            <el-dropdown-item @click="listxe"><v-icon icon="mdi:mdi-car-sports mr-2" ></v-icon>Danh sách xe</el-dropdown-item>
            <el-dropdown-item @click="listxethue"><v-icon icon="mdi:mdi-car-sports mr-2" ></v-icon>Quản lý thuê xe</el-dropdown-item>
            <el-dropdown-item @click="updatePassword()"><v-icon icon="mdi:mdi-key mr-2" ></v-icon>Đổi mật khẩu</el-dropdown-item>

            <el-dropdown-item class="bg-primary-custom"  @click="logOut"><v-icon icon="mdi:mdi-backburger mr-2"></v-icon>Đăng xuất</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {

    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/fincar/login');
    },
    listxethue() {
      this.$router.push({name: 'quanlythuexe'});
    }
  }
}
</script>

<style>

</style>