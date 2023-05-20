<template>
<!--  <div class="w-100">-->
<!--    -->
<!--    <v-app>-->
<!--      <router-view />-->
<!--    </v-app>-->
<!--  </div>-->

  <div class="container-fluid px-0" v-if="isQuanTri">
    <div id="view" :class="[{'collapsed' : collapsed}]">

      <sidebar-menu :menu="menu" @update:collapsed="onToggleCollapse"
                    :collapsed="menuCollapsed"/>
      <div id="container-view" :class="menuCollapsed ? 'collapsed': ''" class="container-fluid px-0">
        <v-app>
          <Header @update:collapsed="onToggleCollapse"/>
          <router-view></router-view>
        </v-app>

      </div>
    </div>
  </div>
  <div class="container-fluid px-0" v-else>
    <div class="d-flex">
      <div class="container-fluid px-0">
        <HeaderHome/>
        <v-app style="margin-top: 58px">
          <router-view></router-view>
        </v-app>

      </div>
    </div>
  </div>
</template>

<script>
import "@/mixin/validateMixin"
import HeaderHome from "@/components/HeaderHome"
import Header from "@/components/quantri/Header"
import { SidebarMenu } from 'vue-sidebar-menu'
import 'vue-sidebar-menu/dist/vue-sidebar-menu.css'

export default {
  name: 'App',
  data() {
    return {
      menuCollapsed: false,
      menu: [
        {
          header: 'Findcar',
          hiddenOnCollapse: true,
        },
        {
          href: '/',
          title: 'Trang chủ',
          icon: 'fa fa-user',
        },
        {
          title: 'Quản lý danh mục',
          icon: 'mdi:mdi-eye',
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
          ],
        },
        {
          href: '/admin/quanlyxe',
          title: 'Quản lý xe',
          icon: 'fa fa-user',
        },
        {
          href: '/admin/quanlynguoidung',
          title: 'Quản lý người dùng',
          icon: 'fa fa-user',
        },
      ],
    }
  },
  components: {
    SidebarMenu,
    HeaderHome,
    Header
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
