<template>
  <div class="container">
    <h2 class="text-center" style="margin-top: 100px">Đăng xe mới</h2>
    <div class="row" style="margin-top: 100px">
      <div class="col-12">
        <v-text-field
            label="Tiêu đề"
            variant="outlined"
            v-model="xe.tieuDe"
        >
        </v-text-field>
      </div>
    </div>
    <div class="row mt-5">
      <div class="col-lg-7">
        <v-text-field
            label="Tên xe"
            variant="outlined"
            v-model="xe.tenXe"
        >
        </v-text-field>
      </div>
      <div class="col-lg-3">
        <v-combobox
            label="Hãng xe"
            item-title="name"
            :items="listHangXe"
            v-model="xe.hangXe"
            variant="outlined"
        ></v-combobox>
      </div>
      <div class="col-lg-2">
        <v-text-field
            label="Năm sản xuất"
            variant="outlined"
            type="number"
            v-model="xe.namSX"
        >
        </v-text-field>
      </div>
    </div>
    <div class="row mt-5">
      <div class="col-lg-4">
        <v-combobox
            label="Loại xe"
            item-title="name"
            :items="listLoaiXe"
            v-model="xe.loaiXe"
            variant="outlined"
        ></v-combobox>
      </div>
      <div class="col-lg-4">
        <v-combobox
            label="Nhiên liệu"
            item-title="name"
            v-model="xe.nhienLieu"
            :items="listNhienLieu"
            variant="outlined"
        ></v-combobox>
      </div>
      <div class="col-lg-4">
        <v-text-field
            label="Số điện thoại"
            variant="outlined"
            v-model="xe.sdt"
        >
        </v-text-field>
      </div>
    </div>

    <div class="row mt-5">
      <div class="col-12">
        <v-text-field
            label="Địa chỉ"
            variant="outlined"
            v-model="xe.diaChi"
        >
        </v-text-field>
      </div>
    </div>

    <div class="row mt-5">
      <div class="col-lg-4">
        <v-combobox
            label="Quận/huyện"
            item-title="name"
            :items="listQuanHuyen"
            v-model="xe.phuongXa.quanHuyen"
            variant="outlined"
        ></v-combobox>
      </div>
      <div class="col-lg-4">
        <v-combobox
            label="Phường/xã"
            item-title="name"
            :items="listPhuongXa"
            v-model="xe.phuongXa"
            variant="outlined"
        ></v-combobox>
      </div>
      <div class="col-lg-4">
        <v-text-field
            label="Giá xe"
            variant="outlined"
            v-model="xe.giaXe"
            type="number"
        >
        </v-text-field>
      </div>
    </div>
<!--    <div class="row mt-5">-->
<!--      <div class="image-preview position-relative d-inline-flex" v-if="imageData.length > 0">-->
<!--        <v-icon icon="mdi:mdi-close-circle-outline" class="position-absolute remove-img" @click="remove()"/>-->
<!--        <img :src="imageData" class="image-pre" style="max-height: 100px; max-width: 230px">-->
<!--      </div>-->
<!--      <div v-if="xe.id != '' && imageData.length == 0" class="image-preview position-relative d-inline-flex">-->
<!--        <v-icon icon="mdi:mdi-close-circle-outline" class="position-absolute remove-img" @click="remove()"/>-->
<!--        <img :src="getUrlImage(xe.anhNen)" class="image-pre" style="max-height: 100px; max-width: 230px">-->
<!--      </div>-->
<!--      <v-file-input class="input-image border-color-cus mt-2" density="counter"-->
<!--                    counter-->
<!--                    variant="outlined"-->
<!--                    prepend-icon="mdi:mdi-camera"-->
<!--                    @change="onFileChange" ref="avt"-->
<!--                    required></v-file-input>-->
<!--    </div>-->
    <div class="row mt-5">
      <v-textarea label="Mô tả" variant="outlined" v-model="xe.mota"></v-textarea>
    </div>
    <div class="col-12">
      <v-select
          v-model="selectdTinhNang"
          :items="tinhNangs"
          item-title="name"
          item-value="id"
          chips
          label="Tính năng"
          multiple
          variant="outlined"
      ></v-select>

<!--      <v-item-group multiple selected-class="bg-primary-tb" v-model="selectdTinhNang">-->
<!--        <v-item-->
<!--            v-for="entry in tinhNang"-->
<!--            :key="entry.id"-->
<!--            :value="entry.id"-->
<!--            v-slot="{ selectedClass, toggle }"-->
<!--        >-->
<!--          <v-chip-->
<!--              prepend-icon="mdi:mdi-label"-->
<!--              :class="selectedClass"-->
<!--              class="ma-2 pa-4"-->
<!--              @click="toggle"-->

<!--          >-->
<!--            {{ entry.name }}-->
<!--          </v-chip>-->
<!--        </v-item>-->
<!--      </v-item-group>-->
    </div>

    <div class="col-12">

      <v-card-item class="pa-5">
<!--        <ul>-->
<!--          <li>{{ $t('post-form.note-img1') }}</li>-->
<!--          <li>{{ $t('post-form.note-img2') }}</li>-->
<!--          <li>{{ $t('post-form.note-img3') }}</li>-->
<!--        </ul>-->
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
            <div v-for="file in xe.xeImages" :key="file.id" class="preview-card">
              <div class="preview-img"><img :src="getUrlImage(file.urlImage)"/></div>
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
      <button class="btn btn-outline-success col-1 mr-2 font-weight-bold" @click="save">Lưu</button>
      <button class="btn btn-outline-danger col-1">anh</button>
    </div>

  </div>
</template>

<script>
import DanhMucService from "@/services/danhmuc.service"
import XeService from "@/services/xe.service"
import ImageService from "@/services/image.service"

export default {
  name: "Xe",
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
        createdDate: '',
        tieuDe: '',
        tenXe: '',
        hangXe: {
          id: '',
          name: '',
          urlImage: ''
        },
        namSX: '',
        loaiXe: {
          id: '',
          name: ''
        },
        nhienLieu: {
          id: '',
          name: ''
        },
        sdt: '',
        diaChi: '',
        phuongXa: {
          id: '',
          name: '',
          quanHuyen: {
            id: '',
            name: ''
          },
        },
        giaXe: '',
        anhNen: '',
        mota: '',
        tinhNangs: [],
        xeImages: [],
        trangThaiDuyet: ''
      }
    }
  },
  methods: {
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
        // console.log(this.selectdTinhNang)
        this.xe = response.data

      })
    },
    save() {
      this.xe.nguoiDung.id = 1
      this.xe.trangThaiDuyet = 1
      var dataTinhNang = [];
      for (let i in this.selectdTinhNang) {
        dataTinhNang.push({id: this.selectdTinhNang[i]})
      }
      this.xe.tinhNangs = dataTinhNang


      // var currentDate = new Date().toJSON().slice(0,10).replace(/-/g,'/');
      // this.xe.createdDate = currentDate;

      // var data = {
      //   id: this.xe.id,
      //   user: {
      //     id: this.xe.user.id
      //   },
      //   createdDate: this.xe.createdDate,
      //   tenXe: this.xe.tenXe,
      //   tieuDe: this.xe.tieuDe,
      //   hangXe: {
      //     id: this.xe.hangXe.id
      //   },
      //   namSX: this.xe.namSx,
      //   loaiXe: {
      //     id: this.xe.loaiXe.id
      //   },
      //   nhienLieu: {
      //     id: this.xe.nhienLieu.id
      //   },
      //   sdt: this.xe.sdt,
      //   phuongXa: {
      //     id: this.xe.phuongXa.id
      //   },
      //   giaXe: this.xe.giaXe,
      //   mota: this.xe.mota,
      //   tinhNangs: this.xe.tinhNangs,
      // }

      XeService.add(this.xe).then(response => {
        let id = response.data.id
        this.xe = response.data
        this.updateAvatarXe()
        this.updateImages(id)
      }).catch(() => {
        // this.notification(e.response.data.message, "error");

      });
    },
    updateAvatarXe() {
      let file = this.$refs.file.files[0];
      let formData = new FormData();
      formData.append('file', file);
      formData.append('path', "xe//" + this.xe.id);
      if (file != null) {
        ImageService.uploadImage(formData)
            .then(response => {
              this.xe.anhNen = response.data.urlFile
              this.xe.xeImages = []
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
    updateImages(id) {
      for (let i = 0; i < this.files.length; i++) {
        let file = this.$refs.file.files[i];
        let formData = new FormData();
        formData.append('file', file);
        formData.append('path', "xe//" + id);
        if (file != null) {
          ImageService.uploadImage(formData)
              .then(response => {
                let postImage = {
                  urlImage: response.data.urlFile,
                  nameImage: response.data.fileName,
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
  },
  watch: {
    'xe.phuongXa.quanHuyen.id'() {
      console.log('watch', this.xe.phuongXa.quanHuyen.id)
      this.getPhuongXa(this.xe.phuongXa.quanHuyen.id);
    }
  },
  created() {
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