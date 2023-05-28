<template>
  <div class="container-fluid py-3" style="background-color: #f9c63f !important; box-shadow: 0px 6px 5px rgba(0, 0, 0, 0.25)">
    <div class="row">
      <div class="col-4">
        <span class="" style="color: white;
    font-family: monospace;
    font-size: 30px;">Findcar</span>
      </div>
      <div class="col-8 justify-content-end d-flex">
        <div class="row">
          <div class="col-3">
            <el-dropdown size="large" class="justify-content-end float-right">
              <el-avatar
                  src="https://e7.pngegg.com/pngimages/348/800/png-clipart-man-wearing-blue-shirt-illustration-computer-icons-avatar-user-login-avatar-blue-child.png"
              />

              <template #dropdown>
                <el-dropdown-menu class="user-infor-toolbar pb-0">
                  <el-dropdown-item @click="trangCaNhan"><v-icon icon="mdi:mdi-account mr-2" ></v-icon>Trang cá nhân</el-dropdown-item>
                  <el-dropdown-item @click="listxe"><v-icon icon="mdi:mdi-car-sports mr-2" ></v-icon>Danh sách xe</el-dropdown-item>
                  <el-dropdown-item @click="listxethue"><v-icon icon="mdi:mdi-car-sports mr-2" ></v-icon>Quản lý thuê xe</el-dropdown-item>
                  <el-dropdown-item @click="listxedathue"><v-icon icon="mdi:mdi-car-sports mr-2" ></v-icon>Xe đã thuê</el-dropdown-item>

                  <el-dropdown-item class="bg-primary-custom"  @click="logOut"><v-icon icon="mdi:mdi-backburger mr-2"></v-icon>Đăng xuất</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
          <div class="w-auto align-self-center">
            <span class="text-white">{{ hoTen }}</span>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      isLoggin: '',
      hoTen: ''
    }
  },
  methods: {
    listxedathue() {
      this.$router.push({name: 'danhsachxethue'});
      window.scrollTo(0, 0);
    },
    trangCaNhan() {
      this.$router.push({name: 'thongtincanhan'});
      window.scrollTo(0, 0);
    },
    logOut() {
      this.$store.dispatch('auth/logout');
      window.location.href = 'http://localhost:8080/findcar/login'
    },
    listxe() {
      this.$router.push({name: 'quanlyxe'});
      window.scrollTo(0, 0);
    },
    listxethue() {
      this.$router.push({name: 'quanlythuexe'});
      window.scrollTo(0, 0);
    }
  },
  created() {
    this.isLoggin = localStorage.getItem('user');
    var user = JSON.parse(localStorage.getItem('user'));
    if (user) {
      this.hoTen = user.hoVaTen
    }
  }
}
</script>

<style>

</style>