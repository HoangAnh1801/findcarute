<template>
  <div class="container-fluid" style="margin-top: 50px; padding: 0px 50px">
    <div class="row">
      <div class="col-12">
        <h2>Danh sách loại nhiên liệu</h2>
      </div>
      <div class="col-9">
        <button class="btn btn-warning" @click="dialog=true"><v-icon icon="mdi:mdi-plus" /> Thêm mới</button>
      </div>
      <div class="col-3">
        <div class="input-group mb-3">
          <input type="text" class="form-control" style="border-right: 0" v-model="keySearch" @input="getAllNhienLieu" aria-label="Amount (to the nearest dollar)">
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
      <tr v-for="(entry, stt) in listNhienLieu" :key="entry.id">
        <th scope="row" style="width: 5%">{{ stt + 1 }}</th>
<!--        <td>{{ entry.id }}</td>-->
        <td>{{ entry.ten }}</td>
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
          <h2 v-if="!nhienLieu.id" class="text-center color-06a303 py-2">Thêm mới</h2>
          <h2 v-else class="text-center color-06a303 py-2">Cập nhật hãng xe</h2>
          <v-card-text>
            <v-container>
              <div class="row">
                <v-col cols="12">
                  <v-text-field
                      label="Nhiên liệu"
                      v-model.trim="nhienLieu.ten"
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
import swal from 'sweetalert';

export default ({
  components: {
  },
  data () {
    return {
      dialog: false,
      headers: [
        // {
        //   name: 'ID',
        //   code: 'id',
        //   type: 'text'
        // },
        {
          name: 'Nhiên liệu',
          code: 'ten',
          type: 'text'
        }
      ],
      listNhienLieu: [],
      nhienLieu: {
        id: '',
        ten: '',
      },
      keySearch: ''
    }
  },
  methods: {
    resetModel() {
      this.nhienLieu.id = '',
      this.nhienLieu.ten = ''
    },
    getAllNhienLieu() {
      var params = {};
      params['search'] = this.keySearch;
      DanhMucService.getAll('nhienlieu', params).then(response => (
          this.listNhienLieu = response.data
      ))
    },
    save() {
      DanhMucService.add("nhienlieu", this.nhienLieu).then(response => {
        if (response.data.status == "BAD_REQUEST") {
          swal(response.data.message, "", "error")
          return
        }
        swal(response.data.message, "", "success")
        this.getAllNhienLieu();
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
              DanhMucService.delete('nhienlieu', params).then((response) => {
                if (response.data.status === "BAD_REQUEST") {
                  swal(response.data.message, "", "error")
                  return
                }
                this.getAllNhienLieu();
                swal(response.data.message, "", "success")
              })

            }
          })
    },
    findHangXeById(id) {
      DanhMucService.findByID("nhienlieu", id).then(response => {
        this.nhienLieu = response.data
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
    this.getAllNhienLieu();
  }
})
</script>

<style>
@import "@/assets/css/custom.css";
  .v-input__prepend {
    align-self: center;
  }
</style>