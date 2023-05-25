<template>
  <div></div>
  <div class="container-fluid px-0" v-if="isQuanTri">
    <div id="view" :class="[{'collapsed' : collapsed}]">

      <sidebar-menu :menu="menu" @update:collapsed="onToggleCollapse"
                    :collapsed="menuCollapsed"/>
      <div id="container-view" :class="menuCollapsed ? 'collapsed': ''" class="container-fluid px-0">
        <v-app>
          <Header @update:collapsed="onToggleCollapse"/>
          <router-view></router-view>
          <Footer />
        </v-app>

      </div>
    </div>

  </div>
  <div class="container-fluid px-0" v-else>
    <div class="d-flex">
      <div class="container-fluid px-0">
        <HeaderHome/>
        <v-app style="margin-top: 56px;">
          <router-view></router-view>
        </v-app>

      </div>
    </div>
    <Footer />
  </div>

</template>

<script>
import "@/mixin/validateMixin"
import HeaderHome from "@/components/HeaderHome"
import Header from "@/components/quantri/Header"
import { SidebarMenu } from 'vue-sidebar-menu'
import 'vue-sidebar-menu/dist/vue-sidebar-menu.css'
import Footer from '@/components/Footer'

export default {
  name: 'App',
  data() {
    return {
      menuCollapsed: false,
      menu: [
        {
          header: 'Quản trị viên',
          hiddenOnCollapse: true,
        },
        {
          href: '/',
          title: 'Trang chủ',
          icon: 'mdi mdi-home-circle-outline',
        },
        {
          title: 'Quản lý danh mục',
          icon: 'mdi mdi-format-list-checkbox',
          child: [
            {
              href: '/admin/quanlyloaixe',
              title: 'Quản lý loại xe',
            },
            {
              href: '/admin/quanlyhangxe',
              title: 'Quản lý hãng xe',
            },
            {
              href: '/admin/quanlynhienlieu',
              title: 'Quản lý nhiên liệu',
            },
            {
              href: '/admin/quanlyquanhuyen',
              title: 'Quản lý quận huyện',
            },
            {
              href: '/admin/quanlyphuongxa',
              title: 'Quản lý phường xã',
            },
            {
              href: '/admin/quanlytinhnang',
              title: 'Quản lý tính năng',
            },
          ],
        },
        {
          href: '/admin/quanlyxe',
          title: 'Quản lý xe',
          icon: 'mdi mdi-car-convertible',
        },
        {
          href: '/admin/quanlynguoidung',
          title: 'Quản lý người dùng',
          icon: 'mdi mdi-account-circle-outline',
        },
      ],
    }
  },
  components: {
    SidebarMenu,
    HeaderHome,
    Header,
    Footer
  },
  computed: {
    isQuanTri() {
      let user = JSON.parse(window.localStorage.getItem('user'))
      user = user ? user : ''
      let permisson = user.permissions ? user.permissions : []
      let isQuanTri = permisson.includes('admin') ? true : false

      return isQuanTri
    },
    // menuCollapsed(){
    //   console.log('coll', window.localStorage.getItem('collapsed'))
    //   return window.localStorage.getItem('collapsed')
    // }
  },
  methods: {
      onToggleCollapse(collapse) {
        window.localStorage.setItem('collapsed', collapse);
        this.menuCollapsed = collapse
      },
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/fincar/login');
    }
  }
}
</script>

<style>
#container-view,.padding-sidebar {
  padding-left: 290px !important;
}

</style>
