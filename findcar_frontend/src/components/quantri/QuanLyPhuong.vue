<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-6">
        <h2>Danh sách phường xã</h2>
      </div>
      <div class="col-6">
        <button class="btn btn-success float-right" @click="dialog=true"><v-icon icon="mdi:mdi-plus" /> Thêm mới</button>
      </div>
    </div>
    <table class="table table-striped">
      <thead>
      <tr>
        <th scope="col">STT</th>
        <th scope="col" v-for="itemHeader in headers" :key="itemHeader.id">{{ itemHeader.name }}</th>
        <th scope="col">Thao tác</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(entry, stt) in listNhienLieu" :key="entry.id">
        <th scope="row" style="width: 5%">{{ stt + 1 }}</th>
        <td>{{ entry.id }}</td>
        <td>{{ entry.name }}</td>
        <td>
          <button @click="handleEdit(entry.id)"><v-icon icon="mdi:mdi-pencil" /></button>
          <button @click="deleteById(entry.id)" > <v-icon icon="mdi:mdi-trash-can-outline" /></button>
        </td>
      </tr>
      </tbody>
    </table>

    <div class="row justify-content-center">
      <v-dialog
          v-model="dialog"
          persistent
          max-width="500"
      >
        <v-card>
          <h2 v-if="!phuongXa.id" class="text-center color-06a303 py-2">Thêm mới phường xã</h2>
          <h2 v-else class="text-center color-06a303 py-2">Cập nhật phường xã</h2>
          <v-card-text>
            <v-container>
              <div class="row">
                <v-col cols="12">
                  <v-combobox
                      v-model="phuongXa.quanHuyen"
                      label="Quận huyện"
                      :items="listQuanHuyen"
                      item-title="name"
                      variant="outlined"
                  ></v-combobox>
                </v-col>
              </div>
              <div class="row">
                <v-col cols="12">
                  <v-text-field
                      label="Tên phường/xã"
                      v-model.trim="phuongXa.name"
                      variant="outlined"
                      >
                  </v-text-field>
                </v-col>
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
import swal from 'sweetalert';

export default ({
  components: {
  },
  data () {
    return {
      imageData: '',
      dialog: false,
      headers: [
        {
          name: 'ID',
          code: 'id',
          type: 'text'
        },
        {
          name: 'Phường xã',
          code: 'name',
          type: 'text'
        }
      ],
      listNhienLieu: [],
      listQuanHuyen: [],
      phuongXa: {
        id: '',
        name: '',
        urlImage: '',
      },
    }
  },
  methods: {
    resetModel() {
      this.phuongXa.id = '',
      this.phuongXa.name = ''
    },
    getAllQuanHuyen() {
      DanhMucService.getAll('quanhuyen').then(response => (
          this.listQuanHuyen = response.data
      ))
    },
    getAllPhuongXa() {
      DanhMucService.getAll('phuongxa').then(response => (
          this.listNhienLieu = response.data
      ))
    },
    save() {
      DanhMucService.add("phuongxa", this.phuongXa).then(response => {
        if (response.data.status == "BAD_REQUEST") {
          swal(response.data.message, "", "error")
          return
        }
        swal(response.data.message, "", "success")
          this.getAllPhuongXa();
          this.closeDialog();
      })
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
              DanhMucService.delete('phuongxa', params).then((response) => {
                if (response.data.status === "BAD_REQUEST") {
                  swal(response.data.message, "", "error")
                  return
                }
                this.getAllPhuongXa();
                swal(response.data.message, "", "success")
              })

            }
          })
    },
    getUrlImage(name) {
      return ImageService.getImage(name);
    },
    findHangXeById(id) {
      DanhMucService.findByID("phuongxa", id).then(response => {
        this.phuongXa = response.data
      })
    },
    handleEdit(id) {
      this.findHangXeById(id);
      this.dialog = true;
    },
    closeDialog() {
      this.dialog = false
      this.resetModel()
    }
  },
  created() {
    this.getAllPhuongXa();
    this.getAllQuanHuyen();
  }
})
</script>

<style>
@import "@/assets/css/custom.css";
  .v-input__prepend {
    align-self: center;
  }
</style>