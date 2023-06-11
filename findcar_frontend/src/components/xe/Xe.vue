<template>
  <div class="container">
    <h2 class="text-center" style="margin-top: 50px">Đăng xe mới</h2>
    <Form @submit="save" ref="form" lazy-validation>
    <div class="row" style="margin-top: 50px">
      <div class="col-9">
        <label class="font-weight-medium">Tiêu đề<span class="text-danger">*</span></label>
        <Field name="tieuDe" rules="required" v-model="xe.tieuDe" class="" v-slot="{field, errors }">
          <v-text-field
              :class="[{'is-invalid': !!errors.length },'form-control']"
              v-bind="field"
              variant="outlined"
              v-model="xe.tieuDe"
          >
          </v-text-field>
        </Field>
        <ErrorMessage name="tieuDe" class="text-danger" />
      </div>
      <div class="col-lg-3">
        <label class="font-weight-medium">Hãng xe</label>
        <Field name="hangXe" rules="selected" v-model="xe.hangXe" class="" v-slot="{ errors }">
          <v-combobox
              item-title="ten"
              :class="[{'is-invalid': !!errors.length },'form-control']"
              :items="listHangXe"
              v-model="xe.hangXe"
              variant="outlined"
          ></v-combobox>
        </Field>
        <ErrorMessage name="hangXe" class="text-danger" />
      </div>
    </div>
    <div class="row mt-5">
      <div class="col-lg-7">
        <label class="font-weight-medium">Tên xe<span class="text-danger">*</span></label>
        <Field name="tenXe" rules="required" v-model="xe.tenXe" class="" v-slot="{field, errors }">
        <v-text-field
            variant="outlined"
            v-model="xe.tenXe"
            :class="[{'is-invalid': !!errors.length },'form-control']"
            v-bind="field"
        >
        </v-text-field>
        </Field>
        <ErrorMessage name="tenXe" class="text-danger" />
      </div>
      <div class="col-lg-3">
        <label class="font-weight-medium">Số ghế<span class="text-danger">*</span></label>
        <Field name="soGhe" rules="required" v-model="xe.soGhe" v-slot="{field, errors }">
          <v-text-field
              variant="outlined"
              type="text"
              v-model="xe.soGhe"
              v-bind="field"
              :class="[{'is-invalid': !!errors.length },'form-control']"
          >
          </v-text-field>
        </Field>
        <ErrorMessage name="soGhe" class="text-danger" />
      </div>
      <div class="col-lg-2">
        <label class="font-weight-medium">Năm sản xuất</label>
        <v-text-field
            variant="outlined"
            type="number"
            v-model="xe.namSX"
        >
        </v-text-field>
      </div>
    </div>
    <div class="row mt-5">
      <div class="col-lg-4">
        <label class="font-weight-medium">Loại xe<span class="text-danger">*</span></label>
        <Field name="loaiXe" rules="selected" v-model="xe.loaiXe" class="" v-slot="{ errors }">
          <v-combobox
              item-title="ten"
              :items="listLoaiXe"
              :class="[{'is-invalid': !!errors.length },'form-control']"
              v-model="xe.loaiXe"
              variant="outlined"
          ></v-combobox>
        </Field>
        <ErrorMessage name="loaiXe" class="text-danger" />
      </div>
      <div class="col-lg-4">
        <label class="font-weight-medium">Loại nhiên liệu<span class="text-danger">*</span></label>
        <Field name="nhienLieu" rules="selected" v-model="xe.nhienLieu" class="" v-slot="{ errors }">
          <v-combobox
              item-title="ten"
              :class="[{'is-invalid': !!errors.length },'form-control']"
              v-model="xe.nhienLieu"
              :items="listNhienLieu"
              variant="outlined"
          ></v-combobox>
        </Field>
        <ErrorMessage name="nhienLieu" class="text-danger" />
      </div>
      <div class="col-lg-4">
        <label class="font-weight-medium">Số điện thoại<span class="text-danger">*</span></label>
        <Field name="sdt" rules="required" v-model="xe.sdt" class="" v-slot="{field, errors }">
          <v-text-field
              v-bind="field"
              variant="outlined"
              :class="[{'is-invalid': !!errors.length },'form-control']"
              v-model="xe.sdt"
          >
          </v-text-field>
        </Field>
        <ErrorMessage name="sdt" class="text-danger" />
      </div>
    </div>

    <div class="row mt-5">
      <div class="col-12">
        <label class="font-weight-medium">Địa chỉ<span class="text-danger">*</span></label>
        <Field name="diaChi" rules="required" v-model="xe.diaChi" class="" v-slot="{field, errors }">
          <v-text-field
              v-bind="field"
              :class="[{'is-invalid': !!errors.length },'form-control']"
              variant="outlined"
              v-model="xe.diaChi"
          >
          </v-text-field>
        </Field>
        <ErrorMessage name="diaChi" class="text-danger" />
      </div>
    </div>

    <div class="row mt-5">
      <div class="col-lg-4">
        <label class="font-weight-medium">Quận/huyện<span class="text-danger">*</span></label>
        <Field name="quanHuyen" rules="selected" v-model="xe.nhienLieu" class="" v-slot="{ errors }">
        <v-combobox
            item-title="ten"
            :items="listQuanHuyen"
            :class="[{'is-invalid': !!errors.length },'form-control']"
            v-model="xe.phuongXa.quanHuyen"
            variant="outlined"
        ></v-combobox>
        </Field>
        <ErrorMessage name="quanHuyen" class="text-danger" />
      </div>
      <div class="col-lg-4">
        <label class="font-weight-medium">Phường/ xã<span class="text-danger">*</span></label>
        <Field name="phuongXa" rules="selected" v-model="xe.nhienLieu" class="" v-slot="{ errors }">
          <v-combobox
              item-title="ten"
              :class="[{'is-invalid': !!errors.length },'form-control']"
              :items="listPhuongXa"
              v-model="xe.phuongXa"
              variant="outlined"
          ></v-combobox>
        </Field>
        <ErrorMessage name="phuongXa" class="text-danger" />
      </div>
      <div class="col-lg-4">
        <label class="font-weight-medium">Giá xe<span class="text-danger">*</span></label>
        <Field name="giaXe" rules="required" v-model="xe.giaXe" class="" v-slot="{field, errors }">
          <v-text-field
              v-bind="field"
              :class="[{'is-invalid': !!errors.length },'form-control']"
              variant="outlined"
              v-model="xe.giaXe"
              @input="formatCurrency()"
              type="number"
          >
          </v-text-field>
        </Field>
        <ErrorMessage name="giaXe" class="text-danger" />
      </div>
    </div>
    <div class="row mt-5">
      <v-textarea label="Mô tả" variant="outlined" v-model="xe.mota"></v-textarea>
    </div>
    <div class="col-12 mt-4">
      <label class="font-weight-medium">Tính năng<span class="text-danger">*</span></label>
      <v-select
          v-model="selectdTinhNang"
          :items="tinhNangs"
          item-title="ten"
          item-value="id"
          chips
          multiple
          variant="outlined"
      ></v-select>
    </div>

    <div class="col-12">

      <v-card-item class="pa-5">
        <div
            class="dropzone-container"
            @dragover="dragover"
            @dragleave="dragleave"
            @drop="drop"
        >
          <input
              type="file"
              multiple
              name="file"
              id="fileInput"
              class="hidden-input"
              hidden
              @change="onChange"
              ref="file"
              accept=".jpg,.jpeg,.png"
          />
          <label for="fileInput" class="file-label">
            <div class="pa-5">
              <p class="file-input-label mt-2">
                <span><v-icon icon="mdi:mdi-camera-plus-outline mr-2"></v-icon></span>
                <span>Chọn file</span>
              </p>
            </div>
          </label>
          <div class="preview-container mt-4" v-if="files.length">
            <div v-for="file in files" :key="file.name" class="preview-card">
              <div class="preview-img"><img :src="generateURL(file)"/></div>
              <p class="p-img-name">
                {{ file.name }}
              </p>
              <button
                  class="ml-2 btn-img-delete"
                  type="button"
                  @click="remove(files.indexOf(file))"
                  title="Remove file"
              >
                <v-icon icon="mdi:mdi-asterisk-circle-outline" />
              </button>
            </div>
          </div>
          <div class="preview-container mt-4" v-if="xe.id && files.length == 0">
            <div v-for="file in xe.xeAnhs" :key="file.id" class="preview-card">
              <div class="preview-img"><img :src="getUrlImage(file.urlAnh)"/></div>
              <p class="p-img-name">
                {{ file.name }}
              </p>
              <button
                  class="ml-2 btn-img-delete"
                  type="button"
                  @click="remove(files.indexOf(file))"
                  title="Remove file"
              >
                <v-icon icon="mdi:mdi-asterisk-circle-outline" />
              </button>
            </div>
          </div>
        </div>
        <!--</div>-->
      </v-card-item>

    </div>
      <div class="row justify-content-center">
        <button class="btn btn-outline-success col-1 mr-2 font-weight-bold" type="submit">Lưu</button>
        <button class="btn btn-outline-danger col-1" @click="back">Quay lại</button>
      </div>
    </Form>
  </div>
</template>

<script>
import DanhMucService from "@/services/danhmuc.service"
import XeService from "@/services/xe.service"
import ImageService from "@/services/image.service"
import { Form, Field, ErrorMessage } from "vee-validate";
import swal from 'sweetalert';

export default {
  name: "Xe",
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  data() {
    return {
      toggle: 1,
      imageData: '',
      isDragging: false,
      typeInteriors: ['Đơn giản', 'Tầm Trung', 'Cao Cấp', "Khác"],
      listHangXe: [],
      selectdTinhNang: [],
      files: [],
      listLoaiXe: [],
      listNhienLieu: [],
      listQuanHuyen: [],
      tinhNangs: [],
      listPhuongXa: [],
      xe: {
        id: '',
        nguoiDung: {
          id: ''
        },
        ngayTao: '',
        tieuDe: '',
        tenXe: '',
        hangXe: {
          id: '',
          ten: '',
          urlAnh: ''
        },
        soGhe: '',
        namSX: '',
        loaiXe: {
          id: '',
          ten: ''
        },
        nhienLieu: {
          id: '',
          ten: ''
        },
        sdt: '',
        diaChi: '',
        phuongXa: {
          id: '',
          ten: '',
          quanHuyen: {
            id: '',
            ten: ''
          },
        },
        giaXe: '',
        anhNen: '',
        mota: '',
        tinhNangs: [],
        xeAnhs: [],
        trangThaiDuyet: 1
      }
    }
  },
  methods: {
      resetData() {
          this.xe.id = '',
          this.xe.tieuDe = '',
          this.xe.hangXe.id = '',
              this.xe.hangXe.ten = '',
              this.xe.tenXe = '',
              this.xe.soGhe = '',
              this.xe.namSX = '',
              this.xe.loaiXe.id = '',
              this.xe.nhienLieu.id = '',
              this.xe.sdt = '',
              this.xe.diaChi = '',
              this.xe.phuongXa.quanHuyen.id = '',
              this.xe.phuongXa.id = '',
              this.xe.mota = '',
              this.xe.tinhNangs = []
      },
    back() {
      this.$router.go(-1)
    },
    getUrlImage(name) {
      return ImageService.getImage(name);
    },
    images(e){
      e.map(res=>console.log(res))
    },
    clear(){
      this.component.clear = true;
    },
    getAllQuanHuyen() {
      DanhMucService.getAll('quanhuyen').then(response => (
          this.listQuanHuyen = response.data
      ))
    },
    getPhuongXa(id) {
      var params = {};
      params["id"] = id
      DanhMucService.getPhuongXaByQuanHuyenId(params).then(response => (
          this.listPhuongXa = response.data
      ))
    },
    getAllLoaiXe() {
      DanhMucService.getAll('loaixe').then(response => (
          this.listLoaiXe = response.data
      ))
    },
    getAllHangXe() {
      DanhMucService.getAll('hangxe').then(response => (
          this.listHangXe = response.data
      ))
    },
    getAllTinhNang() {
      DanhMucService.getAll('tinhnang').then(response => (
          this.tinhNangs = response.data
      ))
    },
    getAllNhienLieu() {
      DanhMucService.getAll('nhienlieu').then(response => (
          this.listNhienLieu = response.data
      ))
    },
    getXeById(id) {
      XeService.findByID(id).then(response => {
        for (let i in response.data.tinhNangs) {
          this.selectdTinhNang.push(response.data.tinhNangs[i].id)
        }
        // this.selectdTinhNang = (dataTinhNang);
        this.xe = response.data

      })
    },
    async save() {
      this.xe.ngayTao = new Date().getTime();
      var dataTinhNang = [];
      for (let i in this.selectdTinhNang) {
        dataTinhNang.push({id: this.selectdTinhNang[i]})
      }
      this.xe.tinhNangs = dataTinhNang
      await XeService.add(this.xe).then(response => {
        let id = response.data.id
        this.xe = response.data
        this.updateAvatarXe()
        this.updateImages(id)

      }).catch(() => {
        // this.notification(e.response.data.message, "error");

      });
    },
    async updateAvatarXe() {
      let file = this.$refs.file.files[0];
      let formData = new FormData();
      formData.append('file', file);
      formData.append('path', "xe//" + this.xe.id);
      if (file != null) {
        await ImageService.uploadImage(formData)
            .then(response => {
              this.xe.anhNen = response.data.urlFile
              this.xe.xeAnhs = []
              XeService.add(this.xe).then(() => {
              }).catch(e => {
                this.notification(e.response.data.message, "error");
              });
            })
            .catch(e => {
              this.notification(e.response.data.message, "error");
            });
      }
    },
    async updateImages(id) {
      for (let i = 0; i < this.files.length; i++) {
        let file = this.$refs.file.files[i];
        let formData = new FormData();
        formData.append('file', file);
        formData.append('path', "xe//" + id);
        if (file != null) {
          await ImageService.uploadImage(formData)
              .then(response => {
                let postImage = {
                  urlAnh: response.data.urlFile,
                  tenAnh: response.data.fileName,
                  xe: {
                    id: id,
                  },
                }

                XeService.addImage(postImage).then(() => {
                }).catch(e => {
                  this.notification(e.response.data.message, "error");
                });
              })
              .catch(e => {
                this.notification(e.response.data.message, "error");
              });
        }
      }
      swal('Thêm mới thành công!', '', 'success').then(
          // window.location.href = 'http://localhost:8080/admin/quanlyxe'
          this.$router.push('/findcar/quanlyxe'),
          window.scrollTo(0, 0)
      )
    },
    /*image*/
    remove(i) {
      this.files.splice(i, 1)
    },
    generateURL(file) {
      let fileSrc = URL.createObjectURL(file)
      setTimeout(() => {
        URL.revokeObjectURL(fileSrc)
      }, 1000)
      return fileSrc
    },
    onChange() {
      var data = this.files
      this.files = [...this.$refs.file.files]
      data.forEach(function (item){
        this.files.push(item)
      })
    },
    dragover(e) {
      e.preventDefault()
      this.isDragging = true
    },
    dragleave() {
      this.isDragging = false
    },
    drop(e) {
      e.preventDefault()
      this.$refs.file.files = e.dataTransfer.files
      this.onChange()
      this.isDragging = false
    },
    /*image-end*/
    formatCurrency() {
      this.xe.giaXe = this.xe.giaXe.replace(/[^\d.]/g, '');

      // Định dạng số tiền theo định dạng tiền tệ
      const formattedAmount = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND'
      }).format(this.xe.giaXe);

      // Gán số tiền đã được định dạng vào trường văn bản
      this.xe.giaXe = formattedAmount;
      console.log('this.xe.giaXe', this.xe.giaXe)
    }
  },
  watch: {
    'xe.phuongXa.quanHuyen.id'() {
      this.getPhuongXa(this.xe.phuongXa.quanHuyen.id);
    }
  },
  created() {
    var user = JSON.parse(localStorage.getItem('user'));
    this.xe.nguoiDung.id = user.id;
    var id = this.$route.params.id;
    if(id != null && id != undefined) {
      this.getXeById(id)
    }
    this.getAllHangXe();
    this.getAllLoaiXe();
    this.getAllNhienLieu();
    this.getAllQuanHuyen();
    this.getAllTinhNang();
  }
}
</script>

<style>
.el-input.el-input--prefix.el-input--suffix.el-date-editor.el-date-editor--year.el-tooltip__trigger.el-tooltip__trigger {
  width: 100%;
  height: 73%;
}
.dropzone-container {
  border: 1px dashed #ABABAB;
  margin-top: 10px;
  text-align: center;
}
.dropzone-container .file-label {
  width: 100%;
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