import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/pages/Login'
import Home from '../components/pages/home/Home'
import NotFound from '../components/pages/error/NotFound'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/login',
      component: Login
    },
    {
      path: '/',
      component: Home
    },
    {
      // not found handler
      path: '*',
      component: NotFound
    }
  ]
})
