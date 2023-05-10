<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-6">
        <h2>Danh sách nhiên liệu</h2>
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
          <h2 v-if="!nhienLieu.id" class="text-center color-06a303 py-2">Thêm mới</h2>
          <h2 v-else class="text-center color-06a303 py-2">Cập nhật hãng xe</h2>
          <v-card-text>
            <v-container>
              <div class="row">
                <v-col cols="12">
                  <v-text-field
                      label="Nhiên liệu"
                      v-model="nhienLieu.name"
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
      imageData: '',
      dialog: false,
      headers: [
        {
          name: 'ID',
          code: 'id',
          type: 'text'
        },
        {
          name: 'Nhiên liệu',
          code: 'name',
          type: 'text'
        }
      ],
      listNhienLieu: [],
      nhienLieu: {
        id: '',
        name: '',
        urlImage: '',
      },
    }
  },
  methods: {
    resetModel() {
      this.nhienLieu.id = '',
      this.nhienLieu.name = '',
      this.nhienLieu.urlImage = ''
      this.imageData = '',
      this.$refs.file.value = ''
    },
    getAllNhienLieu() {
      DanhMucService.getAll('nhienlieu').then(response => (
          this.listNhienLieu = response.data
      ))
    },
    save() {
      DanhMucService.add("nhienlieu", this.nhienLieu).then(() => {
            // this.resetModel();
            this.getAllNhienLieu();
            this.closeDialog();

      }

      )
    },
    getUrlImage(name) {
      return ImageService.getImage(name);
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