// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import axios from './axios-backend/http-common'
import VueAxios from 'vue-axios'
import router from './router'
import store from './store'
import BootstrapVue from 'bootstrap-vue'
import Vuelidate from 'vuelidate'
import VueMoment from 'vue-moment'
import CxltToastr from 'cxlt-vue2-toastr'
import Multiselect from 'vue-multiselect'

Vue.config.productionTip = false
Vue.config.devtools = true

/* eslint-disable */
// Bootstrap
Vue.use(BootstrapVue)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

//Serve para fazer o binding do axios (preconfigurado) na instancia do Vue
//(não precisa mais fazer o import do axios nos components)
Vue.use(VueAxios, axios)

Vue.use(Vuelidate)
Vue.use(VueMoment)

Vue.use(CxltToastr)
import 'cxlt-vue2-toastr/dist/css/cxlt-vue2-toastr.css'

Vue.component('multiselect', Multiselect)

new Vue({
  el: '#app',
  axios,
  store,
  router,
  components: { App },
  template: '<App/>'
})
