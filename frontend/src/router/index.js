import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/pages/Login'
import Logout from '../components/pages/Logout'
import Home from '../components/pages/home/Home'
import NotFound from '../components/pages/error/NotFound'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      component: Login,
      meta: {authRequired: false}
    },
    {
      path: '/logout',
      name: 'Logout',
      component: Logout,
    },
    {
      path: '/',
      component: Home,
      meta: {authRequired: true}
    },
    {
      // not found handler
      path: '*',
      component: NotFound,
      meta: {authRequired: false}
    }
  ]
})

router.beforeEach((to, from, next) => {

  if (to.matched.some(record => record.meta.authRequired) &&
    (!localStorage.token)
  ) {

    console.log('Not authenticated')

    next({
      path: '/login',
      query: { redirect: to.fullPath }
    })
  } else {
    next()
  }

})

// if(transition.to.auth){
//   if(!transition.to.router.app.$store.state.auth.authorized){
//     transition.abort()
//     router.go({path:"/"})
//   }
// }
// transition.next()

export default router
