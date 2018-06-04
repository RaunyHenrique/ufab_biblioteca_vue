// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import axios from './axios-backend/http-common'
import VueAxios from 'vue-axios'
import router from './router'
import store from './store'
import BootstrapVue from 'bootstrap-vue'

Vue.config.productionTip = false;
Vue.config.devtools = true;

/* eslint-disable */
// Bootstrap
Vue.use(BootstrapVue);
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

//Serve para fazer o binding do axios (preconfigurado) na instancia do Vue
//(não precisa mais fazer o import do axios nos components)
Vue.use(VueAxios, axios);

new Vue({
  el: '#app',
  router,
  axios,
  store,
  components: { App },
  template: '<App/>'
});
