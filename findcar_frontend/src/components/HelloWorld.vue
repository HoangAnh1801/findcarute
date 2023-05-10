<template>
  <v-col lg="10" class="px-5 rounded bg-background ">
    <v-breadcrumbs :items="items" class="bread">
      <template v-slot:title="{ item }">
        {{ item.title }}
      </template>
    </v-breadcrumbs>
    <h2 class="text-title-page my-5">{{ $t('post-form.title') }}</h2>
    <v-form ref="form" v-model="valid" lazy-validation  >
      <v-card class="form-container">
        <v-card-item>
          <div class="text-title-color">
            I.{{ $t('post-form.base-information') }}
          </div>
        </v-card-item>
        <v-card-item>
          <div class="d-flex">

            <div class="w50 pa-3">
              <v-row>
                <v-col cols="12" md="3" lg="3" xl="3" class="px-0">
                  <v-subheader class="text-label-form">{{ $t('form.label-project') }}</v-subheader>
                </v-col>
                <v-col cols="12" md="8" lg="8" xl="8" class="pa-0">
                  <v-text-field
                      v-model="post.projectName"
                      :placeholder="$t('form.place-text')"
                      variant="outlined"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row class="mb-2">
                <v-col cols="3" class="px-0 ">
                  <v-subheader class="text-label-form">{{ $t('form.label-classified') }}</v-subheader>
                </v-col>
                <v-col cols="8" class="pa-0">
                  <v-item-group mandatory v-model="toggle">
                    <v-item v-slot="{ isSelected, toggle }"
                            v-for="item in classifiedReatlys"
                            :key="item.id"
                            :value="item.id">
                      <v-btn
                          :color="isSelected ? 'classified-select' : 'classified'"
                          class="mr-2 btn-classified "
                          variant="outlined"
                          @click="toggle"
                      >
                        {{ item.typeClassifiedName }}
                      </v-btn>
                    </v-item>
                  </v-item-group>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="3" class="px-0">
                  <v-subheader class="text-label-form">{{ $t('form.label-type-reatly') }}*</v-subheader>
                </v-col>
                <v-col cols="8" class="pa-0">
                  <v-select
                      v-model="post.typeReatly.id"
                      :placeholder="$t('form.place-select')"
                      :items="typeReatlys"
                      item-title="typeReatlyName"
                      item-value="id"
                      variant="outlined"
                      density="comfortable"
                      class="select-option"
                  ></v-select>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="3" class="px-0">
                  <v-subheader class="text-label-form">{{ $t('form.label-province') }}*</v-subheader>
                </v-col>
                <v-col cols="8" class="pa-0">
                  <v-select
                      v-model="provinceInput"
                      :placeholder="$t('form.place-select')"
                      :items="provinces"
                      item-title="name"
                      item-value="id"
                      variant="outlined"
                      density="comfortable"
                      class="select-option"
                  ></v-select>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="3" class="px-0">
                  <v-subheader class="text-label-form">{{ $t('form.label-district') }}</v-subheader>
                </v-col>
                <v-col cols="8" class="pa-0">
                  <v-select
                      v-model="districtInput"
                      :placeholder="$t('form.place-select')"
                      :items="districts"
                      item-title="name"
                      item-value="id"
                      variant="outlined"
                      density="comfortable"
                      class="select-option"
                      ref="selectedDistrict"
                  ></v-select>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="3" class="px-0">
                  <v-subheader class="text-label-form">{{ $t('form.label-ward') }}</v-subheader>
                </v-col>
                <v-col cols="8" class="pa-0">
                  <v-select
                      v-model="post.ward.id"
                      :placeholder="$t('form.place-select')"
                      :items="wards"
                      item-title="name"
                      item-value="id"
                      variant="outlined"
                      density="comfortable"
                      class="select-option"
                      ref="selectedWard"
                  ></v-select>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="3" class="px-0">
                  <v-subheader class="text-label-form">{{ $t('form.label-address') }}</v-subheader>
                </v-col>
                <v-col cols="8" class="pa-0">
                  <v-text-field
                      v-model="post.address"
                      :placeholder="$t('form.place-text')"
                      variant="outlined"
                  ></v-text-field>
                </v-col>
              </v-row>
            </div>

            <div class="w50 bg-red">
              <l-map :zoom="zoom" :center="coordinates" :options="{zoomControl: false}">
                <l-tile-layer
                    :url=mapToken
                    minZoom="10"
                    maxZoom="10"
                ></l-tile-layer>
              </l-map>
            </div>
          </div>
        </v-card-item>
      </v-card>

      <v-card class="form-container">
        <v-card-item>
          <div class="text-title-color">
            II.THÔNG TIN MÔ TẢ
          </div>
        </v-card-item>
        <v-card-item>
          <div class="d-flex flex-column">
            <v-row class="w-100 pa-3 ">
              <div class="w-50 px-3">
                <v-row>
                  <v-col cols="3" class="px-0">
                    <v-subheader class="text-label-form">{{ $t('form.label-price') }} (VNĐ)*
                    </v-subheader>
                  </v-col>
                  <v-col
                      cols="8"
                      md="4"
                  >
                    <v-text-field
                        v-model="post.minPrice"
                        :placeholder="$t('form.label-price-min')"
                        required
                        variant="outlined"
                        type="number"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="8"
                      md="4"
                  >
                    <v-text-field
                        v-model="post.maxPrice"
                        :placeholder="$t('form.label-price-max')"
                        required
                        variant="outlined"
                        type="number"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </div>
              <div class="w-50 px-3">
                <v-row>
                  <v-col cols="3" class="px-0">
                    <v-subheader class="text-label-form">{{ $t('form.label-direction') }}</v-subheader>
                  </v-col>
                  <v-col
                      cols="8"
                      md="8"
                  >
                    <v-select
                        v-model="post.postDetail.direction"
                        :placeholder="$t('form.place-select')"
                        :items="listDirection"
                        variant="outlined"
                        density="comfortable"
                        class="select-option"
                    ></v-select>
                  </v-col>
                </v-row>
              </div>
            </v-row>
            <v-row class="w-100 px-3 ">
              <div class="w-50 px-3">
                <v-row>
                  <v-col cols="3" class="px-0">
                    <v-subheader class="text-label-form">{{ $t('form.label-acreage') }} (m2)*
                    </v-subheader>
                  </v-col>

                  <v-col
                      cols="8"
                      md="4"
                  >
                    <v-text-field
                        v-model="post.minAcreage"
                        :placeholder="$t('form.place-text')"
                        required
                        variant="outlined"
                        type="number"
                    ></v-text-field>
                  </v-col>
                  <v-col
                      cols="8"
                      md="4"
                  >
                    <v-text-field
                        v-model="post.maxAcreage"
                        :placeholder="$t('form.place-text')"
                        required
                        variant="outlined"
                        type="number"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </div>
              <div class="w-50 px-3">
                <v-row>
                  <v-col cols="3" class="px-0">
                    <v-subheader class="text-label-form">{{ $t('form.label-legal') }}</v-subheader>
                  </v-col>
                  <v-col
                      cols="8"
                      md="8"
                  >

                    <v-select
                        v-model="post.postDetail.legalDocuments"
                        :placeholder="$t('form.place-select')"
                        :items="listLegal"
                        variant="outlined"
                        density="comfortable"
                        class="select-option"
                    ></v-select>
                  </v-col>
                </v-row>
              </div>
            </v-row>
            <div class="w-50 px-3">
              <v-row>
                <v-col cols="3" class="px-0">

                </v-col>
                <v-col
                    cols="8"
                    md="8"
                >
                  <v-checkbox
                      :true-icon="'fa-solid fa-circle-check'"
                      :false-icon="'fa-solid fa-circle'"
                      v-model="post.postDetail.priceStatus"
                      :label="$t('form.label-negotiable-price')"
                  ></v-checkbox>
                </v-col>
              </v-row>
            </div>

            <v-row class="w-100 px-3">
              <v-col cols="1" class="px-0">
                <v-subheader class="text-label-form">{{ $t('form.label-title') }}</v-subheader>
              </v-col>
              <v-col cols="11" class="pl-16">
                <v-text-field
                    v-model="post.title"
                    :placeholder="$t('form.place-text')"
                    variant="outlined"
                    :rules="titleRules"
                    @input="validateForm"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row class="w-100 px-3 ">
              <v-col cols="1" class="px-0">
                <v-subheader class="text-label-form">{{ $t('form.label-description') }}</v-subheader>
              </v-col>
              <v-col cols="11" class="pl-16">
                <v-textarea
                    v-model="post.description"
                    counter
                    :placeholder="$t('form.place-area')"
                    variant="outlined"
                    :rules="descriptionRules"
                    @input="validateForm"
                ></v-textarea>
              </v-col>
            </v-row>

            <v-row class="w-100 px-3">

              <div class="w-50 px-3">
                <v-row>
                  <v-col cols="3" class="px-0">
                    <v-subheader class="text-label-form">{{ $t('form.label-facade') }}</v-subheader>
                  </v-col>
                  <v-col
                      cols="8"
                      md="8"
                  >
                    <v-text-field

                        :placeholder="$t('form.place-text')"
                        required
                        variant="outlined"
                        type="number"
                    ></v-text-field
                    >
                  </v-col>
                </v-row>
              </div>
              <div class="w-50 px-3">
                <v-row>
                  <v-col cols="3" class="px-0">
                    <v-subheader class="text-label-form">{{ $t('form.label-way') }}</v-subheader>
                  </v-col>
                  <v-col
                      cols="8"
                      md="8"
                  >
                    <v-text-field
                        :placeholder="$t('form.place-text')"
                        required
                        variant="outlined"
                        type="number"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </div>
              <div class="w-50 px-3">
                <v-row>
                  <v-col cols="3" class="px-0">
                    <v-subheader class="text-label-form">{{ $t('form.label-floor') }}</v-subheader>
                  </v-col>
                  <v-col
                      cols="8"
                      md="8"
                  >
                    <v-text-field
                        v-model="post.postDetail.numberFloors"
                        :placeholder="$t('form.place-text')"
                        required
                        variant="outlined"
                        type="number"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </div>
              <div class="w-50 px-3">
                <v-row>
                  <v-col cols="3" class="px-0">
                    <v-subheader class="text-label-form">{{ $t('form.label-bed-room') }}</v-subheader>
                  </v-col>
                  <v-col
                      cols="8"
                      md="8"
                  >
                    <v-text-field
                        v-model="post.postDetail.numberBedrooms"
                        :placeholder="$t('form.place-text')"
                        required
                        variant="outlined"
                        type="number"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </div>
              <div class="w-50 px-3">
                <v-row>
                  <v-col cols="3" class="px-0">
                    <v-subheader class="text-label-form">{{ $t('form.label-bath-room') }}</v-subheader>
                  </v-col>
                  <v-col
                      cols="8"
                      md="8"
                  >
                    <v-text-field
                        v-model="post.postDetail.numberToilet"
                        :placeholder="$t('form.place-text')"
                        required
                        variant="outlined"
                        type="number"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </div>


              <div class="w-50 px-3">
                <v-row>
                  <v-col cols="3" class="px-0">
                    <v-subheader class="text-label-form">{{ $t('form.label-year-build') }}</v-subheader>
                  </v-col>
                  <v-col
                      cols="8"
                      md="8"
                  >
                    <v-text-field
                        v-model="post.postDetail.yearBuilt"
                        :placeholder="$t('form.place-text')"
                        required
                        variant="outlined"
                        type="number"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </div>
              <div class="w-50 px-3">
                <v-row>
                  <v-col cols="3" class="px-0">
                    <v-subheader class="text-label-form">{{ $t('form.label-furniture') }}</v-subheader>
                  </v-col>
                  <v-col
                      cols="8"
                      md="8"
                  >
                    <v-select
                        v-model="post.postDetail.classifiedInterior"
                        :placeholder="$t('form.place-select')"
                        :items="listTypeInterior"
                        variant="outlined"
                        density="comfortable"
                        class="select-option"
                    ></v-select>
                  </v-col>
                </v-row>
              </div>

            </v-row>

            <v-row class="w-100 px-3">
              <v-col cols="1" class="px-0">
                <v-subheader class="text-label-form">{{ $t('form.label-interior') }}</v-subheader>
              </v-col>
              <v-col cols="11" class="pl-16">
                <v-item-group multiple selected-class="bg-primary-tb" v-model="selectInterior">
                  <v-item
                      v-for="entry in typeInteriors"
                      :key="entry.id"
                      :value="entry.id"
                      v-slot="{ selectedClass, toggle }"
                  >
                    <v-chip
                        prepend-icon="mdi:mdi-label"
                        :class="selectedClass"
                        class="ma-2 pa-4"
                        @click="toggle"

                    >
                      {{ entry.typeInteriorName }}
                    </v-chip>
                  </v-item>
                </v-item-group>
              </v-col>
            </v-row>
          </div>
        </v-card-item>
      </v-card>

      <v-card class="form-container">
                <span class="float-end mr-3 mt-3"><v-icon
                    icon="mdi:mdi-information-outline"/>{{ $t('post-form.rules-img') }}</span>
        <v-card-item>
          <div class="text-title-color">
            III.{{ $t('post-form.img-information') }}
          </div>
        </v-card-item>
        <v-card-item class="pa-5">
          <ul>
            <li>{{ $t('post-form.note-img1') }}</li>
            <li>{{ $t('post-form.note-img2') }}</li>
            <li>{{ $t('post-form.note-img3') }}</li>
          </ul>
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
                accept=".pdf,.jpg,.jpeg,.png"
            />
            <label for="fileInput" class="file-label">
              <div class="pa-5">
                <p class="file-input-label mt-2">
                  <span><v-icon icon="fa fa-file-circle-plus"></v-icon></span>
                  {{ $t('post-form.select-img') }}
                  <span>{{ $t('post-form.drop-img') }}</span>
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
                  <i class="fa-solid fa-circle-xmark "></i>
                </button>
              </div>
            </div>
          </div>
          <!--</div>-->
        </v-card-item>
      </v-card>

      <v-card class="form-container">
        <v-card-item>
          <div class="text-title-color">
            IV. {{ $t('post-form.type-and-pay') }}
          </div>
        </v-card-item>
        <v-card-item class="pa-5">
          <p>{{ $t('post-form.note-type-post1') }}</p>
          <p>{{ $t('post-form.note-type-post2') }}</p>
          <v-row>
            <v-col cols="8" md="3">
              <v-subheader class="text-label-form">{{ $t('form.label-type-post') }}</v-subheader>
              <v-select
                  v-model="post.typePost.id"
                  :placeholder="$t('form.place-select')"
                  :items="typePosts"
                  item-title="typePostName"
                  item-value="id"
                  variant="outlined"
                  density="comfortable"
                  class="select-option"
                  @update:modelValue=onChangeTypePost
              ></v-select>
            </v-col>
            <v-col cols="8" md="3">
              <v-subheader class="text-label-form">{{ $t('form.label-time') }}</v-subheader>
              <v-select
                  v-model="activateDate"
                  :placeholder="$t('form.place-select')"
                  :items="postDay"
                  variant="outlined"
                  density="comfortable"
                  class="select-option"
                  @update:modelValue=onChangeTypePost
              ></v-select>
            </v-col>
            <v-col cols="8" md="3">
              <v-subheader class="text-label-form">{{ $t('form.label-date-start') }}</v-subheader>
              <Datepicker class="date-picker select-option" v-model="date" :format="format" :clearable="false"
                          :enable-time-picker="false"
                          :min-date="new Date()"
                          :placeholder="$t('form.place-select')" required/>
            </v-col>
            <v-col cols="8" md="3">
              <v-subheader class="text-label-form">{{ $t('form.label-date-end') }}</v-subheader>
              <v-text-field
                  v-model="endDate"
                  readonly="true"
                  :placeholder="$t('form.place-text')"
                  required
                  variant="outlined"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-subheader class="text-label-form">{{ $t('post-form.post-value') }}: {{ money }} VNĐ</v-subheader>
          <span class="px-2 text-red" v-if="enoughMoney">{{ $t('post-form.not-enough-money') }}</span>
        </v-card-item>
      </v-card>

      <v-card class="form-container">
        <v-card-item>
          <div class="text-title-color">
            V. {{ $t('post-form.contact-information') }}
          </div>
        </v-card-item>
        <v-card-item class="pa-5">
          <div class="d-flex flex-wrap px-3">
            <v-row class="w-50 mt-0">
              <v-col cols="3" class="px-0">
                <v-subheader class="text-label-form">{{ $t('form.label-name-contact') }}</v-subheader>
              </v-col>
              <v-col cols="8" md="8">
                <v-text-field
                    v-model="post.postDetail.userContact"
                    :placeholder="$t('form.place-text')"
                    required
                    variant="outlined"
                ></v-text-field>
              </v-col>
            </v-row>

            <v-row class="w-50 mt-0">
              <v-col cols="3" class="px-0">
                <v-subheader class="text-label-form">{{ $t('form.label-address') }}</v-subheader>
              </v-col>
              <v-col cols="8" md="8">
                <v-text-field
                    v-model="post.postDetail.addressContact"
                    :placeholder="$t('form.place-text')"
                    required
                    variant="outlined"
                ></v-text-field>
              </v-col>
            </v-row>

            <v-row class="w-50">
              <v-col cols="3" class="px-0">
                <v-subheader class="text-label-form">{{ $t('form.label-phone') }}</v-subheader>
              </v-col>
              <v-col cols="8" md="8">
                <v-text-field
                    v-model="post.postDetail.phoneContact"
                    :placeholder="$t('form.place-text')"
                    required
                    variant="outlined"
                ></v-text-field>
              </v-col>
            </v-row>

            <v-row class="w-50 pa-0">
              <v-col cols="3" class="px-0">
                <v-subheader class="text-label-form">Email</v-subheader>
              </v-col>
              <v-col cols="8" md="8">
                <v-text-field
                    v-model="post.postDetail.emailContact"
                    :placeholder="$t('form.place-text')"
                    required
                    variant="outlined"
                ></v-text-field>
              </v-col>
            </v-row>
          </div>
        </v-card-item>
      </v-card>

      <v-row class="justify-center">
        <v-btn :disabled="!validate" @click="save" variant="flat" size="large" class="btn-outside bg-primary-tb">
          <span class="text-white"> {{ $t('button.post') }}</span>
        </v-btn>
        <v-btn @click="saveDraft" variant="text" size="large" class="btn-outside br-primary-tb">
          {{ $t('button.draft') }}
        </v-btn>
      </v-row>
    </v-form>
  </v-col>
</template>

<script>

import {LMap, LTileLayer} from "@vue-leaflet/vue-leaflet";
import "leaflet/dist/leaflet.css";
import CategoriesService from "../../services/categories";
import PostService from "../../services/post";
import ImageService from "../../services/image";
import '@vuepic/vue-datepicker/dist/main.css'
import swal from "sweetalert";
import axios from "axios";
import UserService from "../../services/user";
export default ({
  components: {LMap, LTileLayer},

  data() {
    return {
      items: [
        {
          title: this.$t('sidebar.admin.reatly.title'),
          disabled: false,
          href: 'list-post',
        },
        {
          title: this.$t('sidebar.admin.reatly.post'),
          disabled: true,
          href: 'breadcrumbs_link_2',
        },
      ],
      validate: false,
      titleRules: [
        v => !!v || ' Text Is required',
        v => (v && v.length >= 10) || 'Text Is must be more than 10 characters',
      ],
      descriptionRules: [
        v => !!v || ' Text Is required',
        v => (v && v.length >= 30) || 'Text Is must be more than 30 characters',
      ],
      isSelected: true,
      isDragging: false,
      mapToken: 'https://{s}.tile.jawg.io/jawg-streets/{z}/{x}/{y}{r}.png?access-token=Fex5d5eyqlYiSD2ZaoWL6YU9h93DgjM5a4k1NvuRXTu0xaniFCx17j7Ymon9sw9r',
      zoom: 10,
      coordinates: [16.074539, 108.218002],
      listLegal: ['Có Sổ Đỏ', 'Hợp đồng mua nhà', 'Đang chờ sổ', 'Khác'],
      listDirection: ['Bắc', 'Đông Bắc', 'Đông', 'Đông Nam', 'Nam', 'Tây Nam', 'Tây', 'Tây Bắc'],
      listTypeInterior: ['Đơn giản', 'Tầm Trung', 'Cao Cấp', "Khác"],
      postDay: ['7', '8', '9', '10', '20', '30', '31', '60', '90'],
      enoughMoney: false,
      activateDate: 7,
      toggle: 1,
      files: [],
      endDate: null,
      money: 0,
      classifiedReatlys: [],
      typeInteriors: [],
      typePosts: [],
      typeReatlys: [],
      provinces: [],
      districts: [],
      wards: [],
      provinceInput: null,
      districtInput: null,
      wardInput: null,
      postList: [],
      typePost: {
        id: null,
        typePostName: null,
        typePostPrice: null
      },
      selectInterior: [],
      post: {
        id: null,
        projectName: null,
        title: null,
        description: null,
        thumbnail: null,
        address: null,
        minPrice: null,
        maxPrice: null,
        minAcreage: null,
        maxAcreage: null,
        viewCount: 0,
        postDate: null,
        endDate: null,
        status: 0,
        userId: null,
        ward: {
          id: null,
        },
        classifiedReatly: {
          id: null,
        },
        typePost: {
          id: null,
        },
        typeReatly: {
          id: null,
        },
        typeInteriors: [],
        postDetail: {
          id: null,
          postPrice: null,
          numberBedrooms: null,
          numberToilet: null,
          numberFloors: null,
          yearBuilt: null,
          direction: null,
          classifiedInterior: null,
          legalDocuments: null,
          priceStatus: false,
          userContact: null,
          addressContact: null,
          phoneContact: null,
          emailContact: null
        }

      },
      date: null,
      format: null,
      surplus: null,

    }
  },
  setup() {
    let user = JSON.parse(localStorage.getItem("user"))
    return {
      user
    }
  },
  mounted() {
    this.getAllCategories();
    var paramID = this.$route.params.id;
    console.log(this.$store.state.auth.user);
    if (typeof (paramID) !== 'undefined') {
      this.findByID(paramID);

    } else {
      this.post.id = null;
      this.date = new Date()
      this.format = this.formatDate(this.date)

    }
    this.mapperModeltoForm()
    this.findUserById()
  },
  methods: {
    validateForm(){
      const { valid } =  this.$refs.form.validate()

      if (!valid) {
        this.validate = true
      }
    },
    mapperModeltoForm() {
      if (Object.is(this.post.userId, null)) {
        this.post.userId = this.user.id
      }
      if (Object.is(this.post.postDetail.userContact, null)) {
        this.post.postDetail.userContact = this.user.userName
      }

      if (Object.is(this.post.postDetail.addressContact, null)) {
        this.post.postDetail.addressContact = this.user.address
      }

      if (Object.is(this.post.postDetail.phoneContact, null)) {
        this.post.postDetail.phoneContact = this.user.phone
      }
      if (Object.is(this.post.postDetail.emailContact, null)) {
        this.post.postDetail.emailContact = this.user.email
      }
    },
    formatDate(dateNew) {
      const day = dateNew.getDate()
      const month = dateNew.getMonth() + 1
      const year = dateNew.getFullYear()

      return `${year}-${month}-${day}`
    },
    notification(message, type) {
      swal({
        text: message,
        icon: type,
        timer: 5000,
      })
    },
    onChangeTypePost() {
      for (let key in this.typePosts) {
        if (this.typePosts[key].id === this.post.typePost.id) {
          this.typePost = this.typePosts[key]
          break
        }
      }
      this.money = this.typePost.typePostPrice * this.activateDate
      this.money = this.money.toFixed(3)
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
      this.files = [...this.$refs.file.files]
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
    getAllCategories() {
      var params = {};
      params["isDelete"] = 'false'
      CategoriesService.list("province", params)
          .then(response => {
            this.provinces = response.data;
          })
          .catch(e => {
            this.notification(e.response.data.message, "error");
          });
      CategoriesService.list("classifiedreatly", params)
          .then(response => {
            this.classifiedReatlys = response.data
          })
          .catch(e => {
            this.notification(e.response.data.message, "error");
          });
      CategoriesService.list("typeinterior", params)
          .then(response => {
            this.typeInteriors = response.data
          })
          .catch(e => {
            this.notification(e.response.data.message, "error");
          });
      CategoriesService.list("typepost", params)
          .then(response => {
            this.typePosts = response.data;
          })
          .catch(e => {
            this.notification(e.response.data.message, "error");
          });

      CategoriesService.list("typereatly", params)
          .then(response => {
            this.typeReatlys = response.data
          })
          .catch(e => {
            this.notification(e.response.data.message, "error");
          });

    },
    getDistrictsByProvinceId() {
      var params = {};
      params["isDelete"] = 'false'
      CategoriesService.findByParentId("district", "province", params, this.provinceInput)
          .then(response => {
            this.districts = response.data
          })
          .catch(e => {
            this.notification(e.response.data.message, "error");
          });
    },
    getWardsByDistrictId() {
      var params = {};
      params["isDelete"] = 'false'
      CategoriesService.findByParentId("ward", "district", params, this.districtInput)
          .then(response => {
            this.wards = response.data
          })
          .catch(e => {
            this.notification(e.response.data.message, "error")
          });
    },
    save() {
      this.post.userId = this.user.id
      this.post.status = 1;
      var data = [];
      for (let i in this.selectInterior) {
        data.push({id: this.selectInterior[i]})
      }
      this.post.typeInteriors = data
      this.post.classifiedReatly.id = this.toggle
      this.post.postDetail.postPrice = this.typePost.typePostPrice * this.activateDate
      PostService.add(this.post)
          .then(response => {
            let id = response.data.id
            this.post = response.data
            this.updatePostThumbnail()
            this.updateImages(id)

            if(this.user.roles[0].includes('ROLE_ADMIN')){
              this.$router.push({name: 'admin-list-post'});
            }else {
              this.$router.push({name: 'user-list-post'});
            }
            this.notification("Success", "success");
          })
          .catch(e => {
            this.notification(e.response.data.message, "error");
          });
    },
    saveDraft() {
      this.post.status = 0;
      this.save()
    },
    findByID(id) {
      var params = {};
      params["isDelete"] = 'false';
      PostService.findByID(params, id)
          .then(response => {
            this.post = response.data;
            this.provinceInput = response.data.ward.district.province.id
            this.districtInput = response.data.ward.district.id
            for (let i in response.data.typeInteriors) {
              this.selectInterior.push(response.data.typeInteriors[i].id)
            }
            const date1 = new Date(response.data.postDate).getTime();
            const date2 = new Date(response.data.endDate).getTime();
            const diffTime = date2 - date1 ;
            let daysDifference = Math.round(diffTime/1000/60/60/24);
            this.date = new Date(response.data.postDate)
            this.format = this.formatDate(this.date)
            this.activateDate = daysDifference
            this.money = (this.post.typePost.typePostPrice * this.activateDate).toFixed(3)
          })
          .catch(e => {
            this.notification(e.response.data.message, "error");
          });
    },
    updatePostThumbnail() {
      let file = this.$refs.file.files[0];
      let formData = new FormData();
      formData.append('file', file);
      formData.append('path', "post//" + this.post.id);
      if (file != null) {
        ImageService.uploadImage(formData)
            .then(response => {
              this.post.thumbnail = response.data.urlFile
              this.post.images = []
              PostService.add(this.post).then(() => {
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
      for (let i = 1; i < this.files.length; i++) {
        let file = this.$refs.file.files[i];
        let formData = new FormData();
        formData.append('file', file);
        formData.append('path', "post//" + id);
        if (file != null) {
          ImageService.uploadImage(formData)
              .then(response => {
                let postImage = {
                  urlImage: response.data.urlFile,
                  nameImage: response.data.fileName,
                  post: {
                    id: id,
                  },
                }

                PostService.addImage(postImage).then(() => {
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
    findUserById(){
      axios.get('http://localhost:8080/api/user/v1/'+this.user.id, {
        headers: {
          'Authorization': 'Bearer ' + this.user.token
        }
      })
          .then((res) => {
            this.surplus = res.data.surplus

            console.log(res.data)
          })
          .catch((error) => {
            console.error(error)
          })
    },
    getUser() {
      UserService.findUserById(this.user.id)
          .then((response) => {
            console.log("12:" + response)
            this.user = response.data;
            this.role = response.data.roles[0].name;
            this.typeUserId = response.data.typeUserId.id;
          })
    },
    updateUserMoney(){
      let role_ = "";
      if (this.role == "ROLE_ADMIN") {
        role_ = "admin"
      }
      else {
        role_ = this.role
      }
      var data = {
        id: this.id,
        firstName: this.user.firstName,
        lastName: this.user.lastName,
        address: this.user.address,
        email: this.user.email,
        phone: this.user.phone,
        password: this.password,
        typeUser: {id: this.typeUserId},
        role: [role_],
        surplus: null,
      };
      UserService.addUser(data)
          .then(() => {
            swal({
              text: this.$t('message.swal.txt_user'),
              icon: this.$t('message.swal.icon.success'),
              timer: 5000,
            })
          })
    }
  },
  watch: {
    provinceInput() {
      var item = this.provinces.find(item => item.id === this.provinceInput)
      this.coordinates = [item.lat, item.lng]
      this.getDistrictsByProvinceId()
      this.districts = []
      this.wards = []
      this.$refs["selectedDistrict"].reset();
      this.$refs["selectedWard"].reset();
    },
    districtInput() {
      if(this.districtInput != null){
        this.getWardsByDistrictId(this.districtInput)
      }

      this.wards = []
      this.$refs["selectedWard"].reset();
    },
    activateDate() {
      let dateEnd = new Date()
      let res = dateEnd.setTime(this.date.getTime() + (this.activateDate * 24 * 60 * 60 * 1000))
      this.endDate = this.formatDate(new Date(res))
      let ts1 = this.date.getTime()
      this.post.postDate = ts1
      let resEnd = this.endDate
      let ts = new Date(resEnd).getTime()
      this.post.endDate = ts
    },
    date() {
      let dateEnd = new Date()
      let res = dateEnd.setTime(this.date.getTime() + (this.activateDate * 24 * 60 * 60 * 1000))
      this.endDate = this.formatDate(new Date(res))
      let ts1 = this.date.getTime()
      this.post.postDate = ts1
      let resEnd = this.endDate
      let ts = new Date(resEnd).getTime()
      this.post.endDate = ts
    },
    money(){
      if(this.surplus < this.money){
        this.enoughMoney = true
        this.validate = false
      }else {
        this.enoughMoney = false
        this.validate = true
      }
    }
  }
});
</script>

<style scoped>
.date-picker {
  padding-left: 0px !important;
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