<template>
<!--  <div class="col-md-12">-->
<!--    <div class="card card-container">-->
<!--      <img id="profile-img" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" class="profile-img-card" />-->
<!--      <Form @submit="handleLogin">-->
<!--        <div class="form-group">-->
<!--          <label for="tenDangNhap">Username</label>-->
<!--          <Field name="tenDangNhap" type="text" class="form-control" />-->
<!--          <ErrorMessage name="tenDangNhap" class="error-feedback" />-->
<!--        </div>-->
<!--        <div class="form-group">-->
<!--          <label for="matKhau">Password</label>-->
<!--          <Field name="matKhau" type="password" class="form-control" />-->
<!--          <ErrorMessage name="matKhau" class="error-feedback" />-->
<!--        </div>-->

<!--        <div class="form-group">-->
<!--          <button class="btn btn-primary btn-block" :disabled="loading">-->
<!--            <span v-show="loading" class="spinner-border spinner-border-sm"></span>-->
<!--            <span>Login</span>-->
<!--          </button>-->
<!--        </div>-->

<!--        <div class="form-group">-->
<!--          <div v-if="message" class="alert alert-danger" role="alert">-->
<!--            {{ message }}-->
<!--          </div>-->
<!--        </div>-->
<!--      </Form>-->
<!--    </div>-->

<!--  </div>-->


  <section class="vh-100">
    <div class="container-fluid h-custom">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-md-9 col-lg-6 col-xl-5">
          <img src="@/assets/images/logo_no_background.png"
               class="img-fluid" alt="Sample image">
        </div>
        <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
          <Form @submit="handleLogin">

            <div class="divider d-flex align-items-center my-4">
              <h2 class="text-center fw-bold mb-0">Đăng nhập</h2>
            </div>


            <!-- Email input -->
            <div class="form-outline mb-4">
<!--              <input type="email" id="form3Example3" class="form-control form-control-lg"-->
<!--                     placeholder="Enter a valid email address" />-->
              <label class="form-label" for="form3Example3">Email address</label>
                        <Field name="tenDangNhap" type="text" class="form-control form-control-lg" id="form3Example3"/>
                        <ErrorMessage name="tenDangNhap" class="error-feedback" />

            </div>

            <!-- Password input -->
            <div class="form-outline mb-3">
<!--              <input type="password" id="form3Example4" class="form-control form-control-lg"-->
<!--                     placeholder="Enter password" />-->
              <label class="form-label" for="form3Example4">Password</label>
                        <Field name="matKhau" type="password" class="form-control form-control-lg" id="form3Example4"/>
                        <ErrorMessage name="matKhau" class="error-feedback" />

            </div>

            <div class="text-center text-lg-start mt-4 pt-2">
              <button class="btn btn-primary btn-lg" :disabled="loading"
                      style="padding-left: 2.5rem; padding-right: 2.5rem;">
                Login
              </button>
              <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account?
                <a href="#!" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo" class="link-danger">Register</a></p>
            </div>

            <div class="form-group">
                        <div v-if="message" class="alert alert-danger" role="alert">
                          {{ message }}
                        </div>
                      </div>
          </Form>
        </div>
      </div>
    </div>
  </section>

  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Đăng ký thành viên</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <Form @submit="saveNguoiDung" ref="form" lazy-validation>
            <div class="mb-3">
              <label class="col-form-label">Họ và tên</label>
              <Field name="hoTen" rules="required" v-model="nguoiDung.hoTen" class="form-control form-control-lg" v-slot="{field, errors }">
                <input type="text" v-bind="field" v-model="nguoiDung.hoTen" :class="[{'is-invalid': !!errors.length },'form-control']">
              </Field>
              <ErrorMessage name="hoTen" class="text-danger" />
            </div>
            <div class="mb-3">
              <label for="tendangnhap" class="col-form-label">Tên đăng nhập</label>
              <Field name="tendangnhap" rules="required" v-model="nguoiDung.tenDangNhap" class="form-control form-control-lg" v-slot="{field, errors }">
                <input type="text"  v-bind="field" v-model="nguoiDung.tenDangNhap" :class="[{'is-invalid': !!errors.length },'form-control']" id="tendangnhap">
              </Field>
              <ErrorMessage name="tendangnhap" class="text-danger" />
            </div>
            <div class="mb-3">
              <label for="sdt" class="col-form-label">Số điện thoại</label>
              <Field name="sdt" rules="required" v-model="nguoiDung.sdt" class="form-control form-control-lg" v-slot="{field, errors }">
                <input type="text" v-bind="field" v-model="nguoiDung.sdt" :class="[{'is-invalid': !!errors.length },'form-control']" class="form-control" id="sdt">
              </Field>
              <ErrorMessage name="sdt" class="text-danger" />
            </div>
            <div class="mb-3">
              <label class="col-form-label">Mật khẩu</label>
              <Field name="matKhau" rules="required" v-model="nguoiDung.matKhau" class="form-control form-control-lg" v-slot="{field, errors }">
                <el-input
                    style="height: 37px!important;"
                    class="w-100 h-100"
                    v-model="nguoiDung.matKhau"
                    v-bind="field"
                    :class="[{'is-invalid': !!errors.length }]"
                    type="password"
                    show-password
                />
              </Field>
              <ErrorMessage name="matKhau" class="text-danger" />
            </div>
            <div class="mb-3">
              <label class="col-form-label">Nhập lại mật khẩu</label>
              <Field name="rePass" rules="required" v-model="rePass" class="form-control form-control-lg" v-slot="{field, errors }">
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

            <div class="modal-footer">
              <button type="button" @click="resetData" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
              <button type="submit" class="btn btn-primary">Đăng ký</button>
            </div>
          </Form>
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
  name: "Login",
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  data() {
    return {
      loading: false,
      message: "",
      files: [],
      isDragging: false,
      rePass: '',
      nguoiDung: {
        hoTen: '',
        tenDangNhap: '',
        sdt: '',
        matKhau: '',

        trangThaiDuyet: true,
        role: ['user']
      }
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  created() {
    if (this.loggedIn) {
      this.$router.push("/trangchu");
    }
  },
  methods: {
    resetData() {
      this.nguoiDung.sdt = '',
      this.nguoiDung.matKhau = '',
      this.nguoiDung.hoTen = '',
      this.nguoiDung.tenDangNhap = '',
      this.rePass = ''
    },
    saveNguoiDung() {
      console.log('nguoi dung', this.nguoiDung)
      AuthenService.signUp(this.nguoiDung).then(response => {
        swal(response.data, "", "success")
      })
    },
    handleLogin(user) {
      this.loading = true;
      this.$store.dispatch("auth/login", user).then(
          () => {
            this.$router.push("/trangchu");
            this.$router.go()
          },
          (error) => {
            this.loading = false;
            this.message =
                (error.response &&
                    error.response.data &&
                    error.response.data.message) ||
                error.message ||
                error.toString();
          }
      );
    },
  },
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}

.error-feedback {
  color: red;
}
.date-picker {
  padding-left: 0px !important;
}

.dropzone-container {
  border: 1px dashed #ABABAB;
  margin-top: 10px;
}

.preview-container {
  height: 200px;
  display: flex;
  overflow: scroll;
}

.preview-card {
  width: 150px;
  height: 150px;
  position: relative;
  background: white;
  margin: 5px;
  box-shadow: white;
}

.preview-img {
  width: 100%;
  height: 80%;
}

.preview-img img {
  width: 100%;
  height: 100%;
}

.btn-img-delete {
  position: absolute;
  right: 0;
  top: 0;
  margin-right: 5px;
}

.p-img-name {

  height: 15%;
  padding: 2px;
  overflow: hidden;
}
</style>