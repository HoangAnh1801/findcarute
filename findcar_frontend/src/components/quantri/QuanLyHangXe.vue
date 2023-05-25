<template>
  <div class="container-fluid" style="margin-top: 50px; padding: 0px 50px">
    <div class="row">
      <div class="col-12">
        <h2>Danh sách hãng xe</h2>
      </div>
      <div class="col-9">
        <button class="btn btn-success" @click="dialog=true"><v-icon icon="mdi:mdi-plus" /> Thêm mới</button>
      </div>
      <div class="col-3">
        <div class="input-group mb-3">
          <input type="text" class="form-control" style="border-right: 0" v-model="keySearch" @input="getAllHangXe" aria-label="Amount (to the nearest dollar)">
          <span class="input-group-text bg-white"><v-icon icon="mdi:mdi-magnify-expand" /></span>
        </div>
      </div>
    </div>
    <table class="table" style="box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.25);border-radius: 5px;">
      <thead>
      <tr>
        <th scope="col">STT</th>
        <th scope="col" v-for="itemHeader in headers" :key="itemHeader.id">{{ itemHeader.name }}</th>
        <th scope="col">Thao tác</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(entry, stt) in resultQuery" :key="entry.id">
        <th scope="row" style="width: 5%">{{ stt + 1 }}</th>
        <td>{{ entry.id }}</td>
        <td>{{ entry.name }}</td>
        <td><img :src="getUrlImage(entry.urlImage)" style="max-height: 100px; max-width: 230px"/></td>
        <td>
          <button @click="handleEdit(entry.id)"><v-icon icon="mdi:mdi-pencil" /></button>
          <button @click="deleteById(entry.id)" > <v-icon icon="mdi:mdi-trash-can-outline" /></button>
        </td>
      </tr>
      </tbody>
    </table>
    <div class="row float-right">
        <pagination v-model="page" :records="rows" :per-page="perPage" @paginate="onPageChanged"/>
    </div>


    <div class="row justify-content-center">
      <v-dialog
          v-model="dialog"
          persistent
          max-width="500"
      >
        <v-card>
          <h2 v-if="!hangXe.id" class="text-center color-06a303 py-2">Thêm mới hãng xe</h2>
          <h2 v-else class="text-center color-06a303 py-2">Cập nhật hãng xe</h2>
          <v-card-text>
            <v-container>
              <div class="row">
                <v-col cols="12">
                  <v-text-field
                      label="Tên hãng xe"
                      v-model="hangXe.name"
                      variant="outlined"
                      >
                  </v-text-field>
                </v-col>
              </div>
              <div class="row">
                <div class="image-preview position-relative d-inline-flex" v-if="imageData.length > 0">
                  <v-icon icon="mdi:mdi-close-circle-outline" class="position-absolute remove-img" @click="removeImg()"/>
                  <img :src="imageData" class="image-pre" style="max-height: 100px; max-width: 230px">
                </div>
                <div v-if="hangXe.id != '' && imageData.length == 0" class="image-preview position-relative d-inline-flex">
                  <v-icon icon="mdi:mdi-close-circle-outline" class="position-absolute remove-img" @click="removeImg()"/>
                  <img :src="getUrlImage(hangXe.urlImage)" class="image-pre" style="max-height: 100px; max-width: 230px">
                </div>
                <v-file-input class="input-image border-color-cus mt-2" density="counter"
                              counter
                              variant="outlined"
                              prepend-icon="mdi:mdi-camera"
                              @change="onFileChange" ref="file"
                              required></v-file-input>
              </div>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="closeDialog">Đóng</v-btn>
            <v-btn color="blue darken-1" text @click="save()">Lưu</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<script>
import DanhMucService from "@/services/danhmuc.service"
import ImageService from "@/services/image.service"
import Pagination from 'v-pagination-3'
import swal from 'sweetalert';

export default ({
  components: { Pagination },
  data () {
    return {
      page: 1,
      perPage: 5,
      paginatedItems : this.listHangXe,
      imageData: '',
      dialog: false,
      headers: [
        {
          name: 'ID',
          code: 'id',
          type: 'text'
        },
        {
          name: 'Hãng xe',
          code: 'name',
          type: 'text'
        },
        {
          name: 'Ảnh',
          code: 'urlImage',
          type: 'image'
        },
      ],
      listHangXe: [],
      hangXe: {
        id: '',
        name: '',
        urlImage: '',
      },
      keySearch: ''
    }
  },
  computed: {
    rows() {
      return this.listHangXe.length
    },
    resultQuery(){
      if(this.keySearch){
        return this.listHangXe.filter((item)=>{
          return this.keySearch.toLowerCase().split(' ').every(v => item.name.toLowerCase().includes(v))
        })
      }else{
        return this.paginatedItems;
      }
    }
  },
  methods: {
    paginate(page_size, page_number) {
      let itemsToParse = this.listHangXe;
      this.paginatedItems = itemsToParse.slice(
          page_number * page_size,
          (page_number + 1) * page_size
      );
    },
    onPageChanged(page) {
      this.paginate(this.perPage, page - 1);
    },
    resetModel() {
      this.hangXe.id = '',
      this.hangXe.name = '',
      this.hangXe.urlImage = ''
      this.imageData = '',
      this.$refs.file.value = ''
    },
    deleteById(id) {
      swal({
        title: "Bạn có chắc chắn muốn xoá?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
          .then((willDelete) => {
            if (willDelete) {
              var params = {};
              params['id'] = id;
              DanhMucService.delete('hangxe', params).then((response) => {
                if (response.data.status === "BAD_REQUEST") {
                  swal(response.data.message, "", "error")
                  return
                }
                this.getAllHangXe();
                swal(response.data.message, "", "success")
              })

            }
          })
    },
    getAllHangXe() {
      var params = {};
      params['search'] = this.keySearch;
      DanhMucService.getAll('hangxe', params).then(response => (
          this.listHangXe = response.data,
      this.paginate(this.perPage, 0)
      ))
    },
    save() {
      DanhMucService.add("hangxe", this.hangXe).then(response => {
        if (response.data.id != null) {
          let id = response.data.id;
          let name = response.data.name;
          let formData = new FormData();
          let file = this.$refs.file.files[0];
          formData.append('file', file);
          formData.append('path', "hangxe//" + id);
          if (file != null) {
            ImageService.uploadImage(formData)
                .then(response => {
                  let update = {
                    id: id,
                    name: name,
                    urlImage: response.data.urlFile,
                  }
                  DanhMucService.add("hangxe", update).then(() => {

                    // this.resetModel();

                    this.getAllHangXe();
                    this.closeDialog();
                  })
                      .catch(() => {
                        this.notification("error");
                      });
                })
                .catch(e => {
                  this.notification(e.response.data.message, "error");
                });
          } else {
            // this.resetModel();
            this.getAllHangXe();
            this.closeDialog();
          }
        }
            // this.resetModel();
            // this.getAllHangXe();
            // this.dialog = false;
      }

      )
    },
    getUrlImage(name) {
      return ImageService.getImage(name);
    },
    findHangXeById(id) {
      DanhMucService.findByID("hangxe", id).then(response => {
        this.hangXe = response.data
      })
    },
    handleEdit(id) {
      this.findHangXeById(id);
      this.dialog = true;
    },
    onFileChange(e) {
      var fileData = e.target.files[0]
      this.hangXe.urlImage = fileData.name
      var input = e.target
      if (input.files && fileData) {
        var reader = new FileReader()
        reader.onload = e => {
          this.imageData = e.target.result
        }
        reader.readAsDataURL(input.files[0])
      }
    },
    removeImg() {
      this.imageData = '';
      this.hangXe.urlImage = '';
      this.$refs.file.value = ''
    },
    closeDialog() {
      this.dialog = false
      this.resetModel()
    }
  },
  created() {
    this.getAllHangXe();
  }
})
</script>

<style>
@import "@/assets/css/custom.css";
  .v-input__prepend {
    align-self: center;
  }
</style>