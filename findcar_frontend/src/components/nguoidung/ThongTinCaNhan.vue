<template>
  <div class="container" style="margin-top: 100px">
    <div class="row justify-content-center">
      <div class="col-8">
        <ul class="nav nav-tabs" id="myTab" role="tablist">
          <li class="nav-item" role="presentation">
            <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home" type="button" role="tab" aria-controls="home" aria-selected="true">Thông tin cá nhân</button>
          </li>
          <li class="nav-item" role="presentation">
            <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" type="button" role="tab" aria-controls="profile" aria-selected="false">Đổi mật khẩu</button>
          </li>
        </ul>
        <div class="tab-content" id="myTabContent" style="box-shadow: 0px 2px 16px rgba(0, 0, 0, 0.25); padding: 50px;">
          <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h4 class="panel-title text-center">Thông tin cá nhân</h4>
              </div>
              <div class="panel-body">
                <Form @submit="saveThongTin" ref="form" lazy-validation>
                <div class="form-group">
                  <label class="col-12 control-label">Họ và tên</label>
                  <div class="col-12">
                    <input type="text" v-model="nguoiDung.hoTen" class="form-control">
                  </div>
                </div>
                <div class="form-group mt-5">
                  <label class="col-12 control-label">Tên đăng nhập</label>
                  <div class="col-12">
                    <input type="text" v-model="nguoiDung.tenDangNhap" class="form-control" disabled>
                  </div>
                </div>
                <div class="form-group mt-5">
                  <label class="col-12 control-label">Số điện thoại</label>
                  <div class="col-12">
                    <input type="text" v-model="nguoiDung.sdt" class="form-control">
                  </div>
                </div>
                <div class="row justify-content-center mt-5">
                  <button class="btn btn-outline-success col-2" type="submit">Cập nhật</button>
                </div>
                </Form>
              </div>
            </div>
          </div>
          <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h4 class="panel-title text-center">Đổi mật khẩu</h4>
              </div>
              <div class="panel-body">
                <Form @submit="updateMatKhau" ref="form" lazy-validation>
                <div class="form-group">
                  <label class="col-12 control-label">Mật khẩu cũ<span class="text-danger">*</span></label>
                  <div class="col-12">
                    <Field name="matKhauCu" @change="checkOldPassword(matKhauCu)" :rules=" !checkOldPass ? 'oldPassword' : 'required'" v-model="matKhauCu" class="form-control form-control-lg" v-slot="{field, errors }">
                      <el-input
                          style="height: 37px!important;"
                          class="w-100 h-100"
                          v-model="matKhauCu"
                          v-bind="field"
                          :class="[{'is-invalid': !!errors.length }]"
                          type="password"
                          show-password
                      />
                    </Field>
                    <ErrorMessage name="matKhauCu" class="text-danger" />
                  </div>
                </div>
                <div class="form-group mt-5">
                  <label class="col-12 control-label">Mật khẩu mới<span class="text-danger">*</span></label>
                  <div class="col-12">
                    <Field name="matKhauMoi" rules="required" v-model="matKhauMoi" class="form-control form-control-lg" v-slot="{field, errors }">
                      <el-input
                          style="height: 37px!important;"
                          class="w-100 h-100"
                          v-model="matKhauMoi"
                          v-bind="field"
                          :class="[{'is-invalid': !!errors.length }]"
                          type="password"
                          show-password
                      />
                    </Field>
                    <ErrorMessage name="matKhauMoi" class="text-danger" />
                  </div>
                </div>
                <div class="form-group mt-5">
                  <label class="col-12 control-label">Nhập lại mật khẩu<span class="text-danger">*</span></label>
                  <div class="col-12">
                    <Field name="rePass" :rules="'required|confirmed:matKhauMoi'" v-model="rePass" class="form-control form-control-lg" v-slot="{field, errors }">
                      <el-input
                          style="height: 37px!important;"
                          class="w-100 h-100"
                          v-model="rePass"
                          v-bind="field"
                          :class="[{'is-invalid': !!errors.length }]"
                          type="password"
                          show-password
                      />
                    </Field>
                    <ErrorMessage name="rePass" class="text-danger" />
                  </div>
                </div>
                  <div class="row justify-content-center mt-5">
                    <button class="btn btn-outline-success col-2" type="submit">Cập nhật</button>
                  </div>
                </Form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

</template>

<script>
import { Form, Field, ErrorMessage } from "vee-validate";
import AuthenService from "@/services/auth.services"
import swal from 'sweetalert';

export default {
  name: "ThongTinCaNhan",
  data() {
    return {
      checkOldPass: true,
      matKhauCu: '',
      rePass: '',
      matKhauMoi: '',
      // nguoiDung: ''
      nguoiDung: {
        id: '',
        matKhau: '',
        sdt: '',
        hoTen: '',
        tenDangNhap: ''
      }
    }
  },
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  methods: {
    saveThongTin() {
      console.log('savenguoidung', this.nguoiDung)
      AuthenService.updateNguoiDung(this.nguoiDung).then(() => {
        swal('Cập nhật thành công', "", "success")
      })
    },
    updateMatKhau() {
      this.nguoiDung.matKhau = this.matKhauMoi
      AuthenService.updateNguoiDung(this.nguoiDung).then(() => {
        swal('Cập nhật thành công', "", "success")
        this.$store.dispatch('auth/logout')
        window.location.href = 'http://localhost:8081/findcar/login'
        // this.$router.push('/fincar/login')
      })
    },
    getUser(tenDN) {
      AuthenService.findByTenDangNhap(tenDN).then(response => {
        // console.log('dâtnguoidung', response.data)
        this.nguoiDung = response.data

      })
    },
    checkOldPassword(pass) {
      console.log('pas', pass)
      console.log('this.nguoiDung.matKhau', this.nguoiDung.matKhau)
      if (this.nguoiDung.matKhau === pass) {
        console.log('pas voo day')
        this.checkOldPass = true
      } else {
        this.checkOldPass = false
      }
    }
  },
  created() {
    var user = JSON.parse(localStorage.getItem('user'));
    if (user) {
      this.getUser(user.tenDangNhap)
      // this.nguoiDung = user;
    }

  }
}
</script>

<style scoped>

</style>