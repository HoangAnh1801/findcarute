<template>



  <section class="vh-100" style="margin-top: 50px">
    <div class="container-fluid h-custom">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-md-9 col-lg-6 col-xl-5">
          <img src="@/assets/images/logo_no_background.png"
               class="img-fluid" alt="Sample image">
        </div>
        <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
          <div class="custom-form-dangky">
            <Form @submit="saveNguoiDung" ref="form" lazy-validation>
              <h2>Đăng ký tài khoản</h2>
              <div class="">
                <div class="row justify-content-center">
                  <div class="col-12 mt-5">
                    <div class="form-check">
                      <v-radio-group v-model="nguoiDung.role">
                        <div class="row">
                            <div class="col-6">
                              <v-radio class="text-black font-weight-medium" label="Chủ xe" :value="chuxe"></v-radio>
                            </div>
                          <div class="col-6">
                            <v-radio class="font-weight-medium" label="Thuê xe" :value="'user'"></v-radio>
                          </div>

                        </div>

                      </v-radio-group>
<!--                      <input class="form-check-input" type="radio" v-model="nguoiDung.role" :value="chuxe" name="flexRadioDefault" id="flexRadioDefault1">-->
<!--                      <label class="form-check-label" for="flexRadioDefault1">-->
<!--                        Chủ xe-->
<!--                      </label>-->
                    </div>
                  </div>
<!--                  <div class="col-6">-->
<!--                    <div class="form-check">-->
<!--&lt;!&ndash;                      <input class="form-check-input" type="radio" v-model="nguoiDung.role" :value="user" name="flexRadioDefault" id="flexRadioDefault2" checked>&ndash;&gt;-->
<!--&lt;!&ndash;                      <label class="form-check-label" for="flexRadioDefault2">&ndash;&gt;-->
<!--&lt;!&ndash;                        Thuê xe&ndash;&gt;-->
<!--&lt;!&ndash;                      </label>&ndash;&gt;-->
<!--                    </div>-->
<!--                  </div>-->
                </div>


              </div>
            <div class="mb-3 mt-0">
              <label class="col-form-label mt-0">Họ và tên</label>
              <Field name="hoTen" rules="required" v-model="nguoiDung.hoTen" class="form-control form-control-lg" v-slot="{field, errors }">
                <input type="text" v-bind="field" v-model="nguoiDung.hoTen" :class="[{'is-invalid': !!errors.length },'form-control']">
              </Field>
              <ErrorMessage name="hoTen" class="text-danger" />
            </div>
            <div class="mb-3">
              <label for="tendangnhap" class="col-form-label">Tên đăng nhập</label>
              <Field name="tendangnhap" rules="required" v-model="nguoiDung.tenDangNhap" class="form-control form-control-lg" v-slot="{field, errors }">
                <input type="text" @change="checkExistTenDangNhap(nguoiDung.tenDangNhap)"  v-bind="field" v-model="nguoiDung.tenDangNhap" :class="[{'is-invalid': !!errors.length || checkTenDN },'form-control']" id="tendangnhap">
              </Field>
              <span role="alert" class="text-danger" v-if="checkTenDN && nguoiDung.tenDangNhap.length > 0">Tên đăng nhập đã tồn tại!</span>
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
              <Field name="rePass" :rules="'required|confirmed:matKhau'" v-model="rePass" class="form-control form-control-lg" v-slot="{field, errors }">
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
              <button type="submit" class="btn btn-warning col-12  text-white" :disabled="checkTenDN">Đăng ký</button>
            </div>
          </Form>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { Form, Field, ErrorMessage } from "vee-validate";
import AuthenService from "@/services/auth.services"
import swal from 'sweetalert';

export default {
  name: "DangKy",
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
        trangThaiDuyet: false,
        role: []
      },
      checkTenDN: false
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
      if (this.nguoiDung.role == 'user') {
        this.nguoiDung.role = ['user']
      } else {
        this.nguoiDung.role = ['chuxe']
      }
      AuthenService.signUp(this.nguoiDung).then(response => {
        swal(response.data, "", "success")
        // this.$router.push('/fincar/login')
        window.location.href = 'http://localhost:8080/findcar/login'
      })
    },
    checkExistTenDangNhap(tendn) {
      AuthenService.findByTenDangNhap(tendn).then(response => {
        if (response.data instanceof Object) {
          this.checkTenDN = true
        } else {
          this.checkTenDN = false
        }
      })
    },
    handleLogin(user) {
      this.loading = true;
      this.$store.dispatch("auth/login", user).then(response => {
            if(response.status == 0) {
              this.$store.dispatch('auth/logout');
              localStorage.removeItem('user');
              // this.showAlert('danger',"Vui lòng thử lại!")
              swal('Tên đăng nhập hoặc mật khẩu không đúng!', '', 'error')
              this.loading = false
            } else {
              let quantri = response.permissions.includes('admin')
              if(quantri) {
                this.$router.push("/trangchu");
              } else {
                this.$router.push("/trangchu");
              }

              this.$router.go()
            }
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
  }
};
</script>

<style scoped>
.custom-form-dangky {
  box-shadow: 0px 4px 5px 3px rgba(0, 0, 0, 0.25);
  padding: 20px;
  border-radius: 5px;
}
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
.v-selection-control.v-selection-control--inline.v-selection-control--density-default.v-radio {
  margin-right: 50px;
}
</style>