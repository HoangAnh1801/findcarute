<template>
  <div>
    <sidebar-menu :menu="menu" @update:collapsed="onToggleCollapse"
                  :collapsed="menuCollapsed"/>
  </div>
</template>

<script>
import { SidebarMenu } from 'vue-sidebar-menu'
import 'vue-sidebar-menu/dist/vue-sidebar-menu.css'

export default {
  name: "LeftSidebar",
  components: {
    SidebarMenu,
  },
  data() {
    return {
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
              href: '/admin/quanlyxe',
              title: 'Quản lý người dùng',
              icon: 'fa fa-user',
            },
          ],
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/fincar/login');
    }
  },
  created() {
    // console.log('store', JSON.parse(localStorage.getItem('user')))
    // var user = JSON.parse(localStorage.getItem('user'));
    // if (user) {
    //   console.log('user', user.permissions)
    // }
    // let permisson = user.permissions ? user.permissions : ''
    // let isQuanTri = permisson.includes('admin') ? true : false

  }
}
</script>

<style scoped>

</style>