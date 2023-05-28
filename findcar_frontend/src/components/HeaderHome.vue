<template>
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" style="background-color: #f9c63f !important; box-shadow: 0px 6px 5px rgba(0, 0, 0, 0.25)">
      <div class="container">
        <router-link to="/" class="text-decoration-none mr-5"><a class="navbar-brand text-white" href="#">Findcar</a></router-link>

        <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <router-link to="/" class="text-decoration-none"><a class="nav-link active" href="#">Trang chủ</a></router-link>
            </li>
            <li class="nav-item">
              <RouterLink to="/admin/xe" @click="handleRouterLinkClick" class="text-decoration-none"><a class="nav-link" aria-current="page" href="#">Trở thành chủ xe</a></RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink to="/admin/quanlyxe" @click="handleRouterLinkClick" class="text-decoration-none"><a class="nav-link">Xe của tôi</a></RouterLink>
            </li>
          </ul>

          <div v-if="isLoggin" class="row">
            <div class="col-3">
            <el-dropdown size="large">
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
          <div v-else>
            <span class="navbar-text">
            <a class="login btn btn-warning" href="/findcar/login">Đăng nhập</a> / <a class="login btn btn bg-e9a900" href="/findcar/dangky">Đăng ký</a>
          </span>
<!--            <a class="btn btn-light action-button" role="button" href="#">Signup</a>-->
          </div>

        </div>
      </div>
    </nav>
</template>

<script>
export default {
  name: "HeaderHome",
  data() {
    return {
      isLoggin: '',
      hoTen: ''
    }
  },
  methods: {
    handleRouterLinkClick() {
      // Di chuyển đến đầu trang
      window.scrollTo(0, 0);
    },
    logOut() {
      this.$store.dispatch('auth/logout');
      window.location.href = 'http://localhost:8080/findcar/login'
      // this.$router.push('/fincar/login');
    },
    listxe() {
      this.$router.push({name: 'quanlyxe'});
      window.scrollTo(0, 0);
    },
    listxethue() {
      this.$router.push({name: 'quanlythuexe'});
      window.scrollTo(0, 0);
    },
    listxedathue() {
      this.$router.push({name: 'danhsachxethue'});
      window.scrollTo(0, 0);
    },
    trangCaNhan() {
      this.$router.push({name: 'thongtincanhan'});
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

<style scoped>

</style>