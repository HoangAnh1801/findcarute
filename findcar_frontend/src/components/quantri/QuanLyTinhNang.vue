<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-6">
        <h2>Danh sách tính năng</h2>
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
      <tr v-for="(entry, stt) in listTinhNang" :key="entry.id">
        <th scope="row" style="width: 5%">{{ stt + 1 }}</th>
        <td>{{ entry.id }}</td>
        <td>{{ entry.name }}</td>
        <td>
          <button @click="handleEdit(entry.id)"><v-icon icon="mdi:mdi-pencil" /></button>
          <button>{{entry.id}}</button>
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
          <h2 v-if="!tinhNang.id" class="text-center color-06a303 py-2">Thêm mới tính năng</h2>
          <h2 v-else class="text-center color-06a303 py-2">Cập nhật tính năng</h2>
          <v-card-text>
            <v-container>
              <div class="row">
                <v-col cols="12">
                  <v-text-field
                      label="Tính năng"
                      v-model="tinhNang.name"
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

export default ({
  components: {
  },
  data () {
    return {
      dialog: false,
      headers: [
        {
          name: 'ID',
          code: 'id',
          type: 'text'
        },
        {
          name: 'Tính năng',
          code: 'name',
          type: 'text'
        }
      ],
      listTinhNang: [],
      tinhNang: {
        id: '',
        name: '',
      },
    }
  },
  methods: {
    resetModel() {
      this.tinhNang.id = '',
      this.tinhNang.name = ''
    },
    getAll() {
      DanhMucService.getAll('tinhnang').then(response => (
          this.listTinhNang = response.data
      ))
    },
    save() {
      DanhMucService.add("tinhnang", this.tinhNang).then(() => {
            // this.resetModel();
            this.getAll();
            this.closeDialog();
      }

      )
    },
    getUrlImage(name) {
      return ImageService.getImage(name);
    },
    findHangXeById(id) {
      DanhMucService.findByID("tinhnang", id).then(response => {
        this.tinhNang = response.data
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
    this.getAll();
  }
})
</script>

<style>
@import "@/assets/css/custom.css";
  .v-input__prepend {
    align-self: center;
  }
</style>