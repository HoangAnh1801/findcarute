<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-dark fixed-top">
      <div class="container">
        <router-link to="/"><a class="navbar-brand text-white" href="#">Findcar</a></router-link>

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
              <a class="nav-link active text-white" aria-current="page" href="#">Trang chủ</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
              <a
                  class="nav-link dropdown-toggle text-white"
                  href="#"
                  id="loaixe"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
              >
                Loại xe
              </a>
              <ul class="dropdown-menu" aria-labelledby="loaixe">
                <li><a class="dropdown-item text-white" href="#">Action</a></li>
                <li><a class="dropdown-item text-white" href="#">Another action</a></li>
                <li><hr class="dropdown-divider" /></li>
                <li>
                  <a class="dropdown-item text-white" href="#">Something else here</a>
                </li>
              </ul>
            </li>
            <li class="nav-item dropdown">
              <a
                  class="nav-link dropdown-toggle text-white"
                  href="#"
                  id="navbarDropdown"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
              >
                Hãng xe
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="#">Action</a></li>
                <li><a class="dropdown-item" href="#">Another action</a></li>
                <li><hr class="dropdown-divider" /></li>
                <li>
                  <a class="dropdown-item" href="#">Something else here</a>
                </li>
              </ul>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white" href="#">Link</a>
            </li>
          </ul>

          <div v-if="isLoggin">
            <el-dropdown size="large">
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
          <div v-else>
            <span class="navbar-text">
            <a class="login" href="/findcar/login">Log In</a>
          </span>
            <a class="btn btn-light action-button" role="button" href="#">Signup</a>
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
      isLoggin: ''
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/fincar/login');
    },
    listxe() {
      this.$router.push({name: 'quanlyxe'});
    },
    listxethue() {
      this.$router.push({name: 'quanlythuexe'});
    }
  },
  created() {
    this.isLoggin = localStorage.getItem('user');
   console.log('this.isLoggin', this.isLoggin)
  }
}
</script>

<style scoped>

</style>