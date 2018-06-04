import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/pages/Login'
import Logout from '../components/pages/Logout'
import Home from '../components/pages/home/Home'
import NotFound from '../components/pages/error/NotFound'

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      component: Login
    },
    {
      path: '/logout',
      name: 'Logout',
      component: Logout
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
