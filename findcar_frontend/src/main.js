import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import 'bootstrap/dist/js/bootstrap.bundle.min'
import 'bootstrap/dist/css/bootstrap.min.css'
import Vue3EasyDataTable from 'vue3-easy-data-table'
import 'vue3-easy-data-table/dist/style.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// import jQuery from 'jquery'
import 'animate.css'
import '@mdi/font/css/materialdesignicons.css'
import 'vuetify/styles'
import {createVuetify} from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import {mdi} from 'vuetify/iconsets/mdi'

const vuetify = createVuetify({
    components,
    directives,
    icons: {
        defaultSet: 'mdi',
        sets: {
            mdi,
        }
    },
})

const app = createApp(App).use(router);
app.use(router);
app.use(vuetify);
// app.use(jQuery);
app.use(ElementPlus);
app.component('EasyDataTable', Vue3EasyDataTable)
app.mount("#app");