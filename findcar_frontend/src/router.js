import {createRouter, createWebHistory} from "vue-router"

import HomePage from "@/components/HomePage";
import ChiTietXe from "@/components/ChiTietXe";
import QuanLyHangXe from "@/components/quantri/QuanLyHangXe";
import QuanLyLoaiXe from "@/components/quantri/QuanLyLoaiXe"
import QuanLyNhienLieu from "@/components/quantri/QuanLyNhienLieu"
import QuanLyQuanHuyen from "@/components/quantri/QuanLyQuanHuyen"
import QuanLyPhuongXa from "@/components/quantri/QuanLyPhuong"
import Xe from "@/components/xe/Xe"
import TinhNang from "@/components/quantri/QuanLyTinhNang"
import QuanLyXe from "@/components/xe/QuanLyXe"
import Login from "@/components/Login"
import DuyetXe from "@/components/quantri/DuyetXe"
import QuanLyNguoiDung from "@/components/quantri/QuanLyNguoiDung"
import QuanLyThueXe from "@/components/quantri/QuanLyThueXe"

const routes = [
    {
        path: "/trangchu",
        alias: ["", "/"],
        name: "homepage",
        component: HomePage,
    },
    {
        path: "/findcar/login",
        name: "login",
        component: Login,
    },
    {
        path: "/chitietxe/:id",
        name: "ChiTietXe",
        component: ChiTietXe,
    },
    {
        path: "/admin/quanlyhangxe",
        name: "quanlyhangxe",
        component: QuanLyHangXe,
    },
    {
        path: "/admin/quanlyquanhuyen",
        name: "quanlyquanhuyen",
        component: QuanLyQuanHuyen,
    },
    {
        path: "/admin/quanlyphuongxa",
        name: "quanlyphuongxa",
        component: QuanLyPhuongXa,
    },
    {
        path: "/admin/quanlynhienlieu",
        name: "quanlynhienlieu",
        component: QuanLyNhienLieu,
    },
    {
        path: "/admin/quanlyloaixe",
        name: "quanlyloaixe",
        component: QuanLyLoaiXe,
    },
    {
        path: "/admin/quanlytinhnang",
        name: "quanlytinhnang",
        component: TinhNang,
    },
    {
        path: "/admin/quanlyxe",
        name: "quanlyxe",
        component: QuanLyXe,
    },
    {
        path: "/admin/quanlynguoidung",
        name: "quanlynguoidung",
        component: QuanLyNguoiDung,
    },
    {
        path: "/admin/duyetxe/:id",
        name: "duyetxe",
        component: DuyetXe,
    },
    {
        path: "/admin/xe",
        name: "xe",
        component: Xe,
    },
    {
        path: "/quanlythuexe",
        name: "quanlythuexe",
        component: QuanLyThueXe,
    },
    {
        path: "/xe/:id",
        name: "update-xe",
        component: Xe,
    },

    // {
    //     path: "/admin/",
    //     name: "App",
    //     component: App,
    //     children: [
    //         // { path: 'quanlyhangxe',name: 'quanlyhangxe',component: QuanLyHangXe },
    //         { path: 'quanlyquanhuyen',name: 'quanlyquanhuyen',component: QuanLyQuanHuyen },
    //         { path: 'quanlyphuongxa',name: 'quanlyphuongxa',component: QuanLyPhuongXa },
    //         { path: 'quanlynhienlieu',name: 'quanlynhienlieu',component: QuanLyNhienLieu },
    //         { path: 'quanlyloaixe',name: 'quanlyloaixe',component: QuanLyLoaiXe },
    //         { path: 'quanlytinhnang',name: 'quanlytinhnang',component: TinhNang },
    //         { path: 'quanlyxe',name: 'quanlyxe',component: QuanLyXe },
    //         { path: 'xe',name: 'xe',component: Xe },
    //         { path: 'xe/:id',name: 'update-xe',component: Xe },
    //     ]
    // },

]

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    const publicPages = ['', '/', '/trangchu','/findcar/login', '/ttld/noPermission', '/cstlht/admin/home'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    if (authRequired && !loggedIn) {
        next('/findcar/login');
    } else {
        next();
    }
    next();
});

export default router;