import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/pages/auth/Login'
import Logout from '../components/pages/auth/Logout'
import MainLayout from '../components/layouts/MainLayout'
import Home from '../components/pages/home/Home'
import Alunos from '../components/pages/alunos/Alunos'
import Cursos from '../components/pages/cursos/Cursos'
import Funcionarios from '../components/pages/funcionarios/Funcionarios'
import NotFound from '../components/pages/error/NotFound'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      component: Login,
      meta: { authRequired: false }
    },
    {
      path: '/logout',
      name: 'Logout',
      component: Logout,
    },
    {
      path: '/',
      component: MainLayout,
      meta: { authRequired: true },
      children: [
        {
          path: '',
          name: 'Home',
          component: Home,
        },
        {
          path: '/alunos',
          name: 'Alunos',
          component: Alunos,
        },
        {
          path: '/cursos',
          name: 'Cursos',
          component: Cursos,
        },
        {
          path: '/funcionarios',
          name: 'Funcionarios',
          component: Funcionarios,
        },
      ]
    },
    {
      // not found handler
      path: '*',
      component: NotFound,
      meta: { authRequired: false }
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
