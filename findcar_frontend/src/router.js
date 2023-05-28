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
import DanhSachXe from "@/components/xe/DanhSachXe"
import DanhSachXeThue from "@/components/xe/DanhSachXeThue"
import ThongTinCaNhan from "@/components/nguoidung/ThongTinCaNhan"
import DangKy from "@/components/DangKy"
import HellWorld from "@/components/HelloWorld"

const routes = [
    {
        path: "/test",
        name: "test",
        component: HellWorld,
    },
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
        path: "/findcar/dangky",
        name: "dangky",
        component: DangKy,
    },
    {
        path: "/chitietxe/:id",
        name: "ChiTietXe",
        component: ChiTietXe,
    },
    {
        path: "/danhsachxe",
        name: "DanhSachXe",
        component: DanhSachXe,
    },
    {
        path: "/danhsachxe/:quanid",
        name: "DanhSachXeQuan",
        component: DanhSachXe,
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
        path: "/danhsachxethue",
        name: "danhsachxethue",
        component: DanhSachXeThue,
    },
    {
        path: "/thongtincanhan",
        name: "thongtincanhan",
        component: ThongTinCaNhan,
    },
    {
        path: "/xe/:id",
        name: "update-xe",
        component: Xe,
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    const publicPages = ['', '/', '/trangchu','/findcar/login', '/findcar/dangky', '/chitietxe', '/danhsachxe', '/test'];
    console.log('to[path', to.path)
    const authRequired = !publicPages.includes(to.path);
    const publics = to.path.includes(['/chitietxe/']);
    const loggedIn = localStorage.getItem('user');

    if (authRequired && !loggedIn) {
        if (publics) {
            next()
        }
        next('/findcar/login');
    } else {
        next();
    }
    next();
});

export default router;