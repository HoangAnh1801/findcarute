import {createRouter, createWebHistory} from "vue-router"

import HomePage from "@/components/HomePage";
// import Hello from "@/components/HelloWorld"
import ChiTietXe from "@/components/ChiTietXe";
import QuanLyHangXe from "@/components/quantri/QuanLyHangXe";
import QuanLyLoaiXe from "@/components/quantri/QuanLyLoaiXe"
import QuanLyNhienLieu from "@/components/quantri/QuanLyNhienLieu"
import QuanLyQuanHuyen from "@/components/quantri/QuanLyQuanHuyen"
import QuanLyPhuongXa from "@/components/quantri/QuanLyPhuong"
import Xe from "@/components/nguoidung/Xe"
import TinhNang from "@/components/quantri/QuanLyTinhNang"
import LayoutQuanTri from "@/components/quantri/LayoutQuanTri"

const routes = [
    {
        path: "",
        name: "homepage",
        component: HomePage,
    },
    {
        path: "/chitietxe/:id",
        name: "ChiTietXe",
        component: ChiTietXe,
    },
    {
        path: "/xe",
        name: "xe",
        component: Xe,
    },
    {
        path: "/admin/",
        name: "quantri",
        component: LayoutQuanTri,
        children: [
            { path: 'quanlyhangxe',name: 'quanlyhangxe',component: QuanLyHangXe },
            { path: 'quanlyquanhuyen',name: 'quanlyquanhuyen',component: QuanLyQuanHuyen },
            { path: 'quanlyphuongxa',name: 'quanlyphuongxa',component: QuanLyPhuongXa },
            { path: 'quanlynhienlieu',name: 'quanlynhienlieu',component: QuanLyNhienLieu },
            { path: 'quanlyloaixe',name: 'quanlyloaixe',component: QuanLyLoaiXe },
            { path: 'quanlytinhnang',name: 'quanlytinhnang',component: TinhNang },
        ]
    },
    {
        path: "/xe/:id",
        name: "update-xe",
        component: Xe,
    },
    // {
    //     path: '/admin/quanlyhangxe',
    //     name: 'quanlyhangxe',
    //     component: QuanLyHangXe,
    // },
    // {
    //     path: '/admin/quanlytinhnang',
    //     name: 'quanlytinhnang',
    //     component: TinhNang,
    // },
    // {
    //     path: '/admin/quanlyloaixe',
    //     name: 'quanlyloaixe',
    //     component: QuanLyLoaiXe,
    // },
    // {
    //     path: '/admin/quanlynhienlieu',
    //     name: 'quanlynhienlieu',
    //     component: QuanLyNhienLieu,
    // },
    // {
    //     path: '/admin/quanlyquanhuyen',
    //     name: 'quanlyquanhuyen',
    //     component: QuanLyQuanHuyen,
    // },
    // {
    //     path: '/admin/quanlyphuongxa',
    //     name: 'quanlyphuongxa',
    //     component: QuanLyPhuongXa,
    // },
    // {
    //     path: "/hello",
    //     name: "hello",
    //     component: Hello,
    // }
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes,
});

export default router;