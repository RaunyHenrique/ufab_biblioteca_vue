import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/pages/auth/Login'
import Logout from '../components/pages/auth/Logout'
import MainLayout from '../components/layouts/MainLayout'
import Home from '../components/pages/home/Home'
import Alunos from '../components/pages/alunos/Alunos'
import Cursos from '../components/pages/cursos/Cursos'
import Funcionarios from '../components/pages/funcionarios/Funcionarios'

import Anais from '../components/pages/acervo/Anais'
import Livros from '../components/pages/acervo/Livros'
import Revistas from '../components/pages/acervo/Revistas'
import Tccs from '../components/pages/acervo/Tccs'
import Jornais from '../components/pages/acervo/Jornais'
import Midias from '../components/pages/acervo/Midias'

import Autores from '../components/pages/acervo/outros/Autores'
import Editoras from '../components/pages/acervo/outros/Editoras'
import Temas from '../components/pages/acervo/outros/Temas'
import AreasDeConhecimento from '../components/pages/acervo/outros/AreasDeConhecimento'

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
      path: '/acervo',
      component: MainLayout,
      meta: { authRequired: true },
      children: [
        {
          path: '/livros',
          name: 'Livros',
          component: Livros,
        },
        {
          path: '/revistas',
          name: 'Revistas',
          component: Revistas,
        },
        {
          path: '/tccs',
          name: 'Tccs',
          component: Tccs,
        },
        {
          path: '/jornais',
          name: 'Jornais',
          component: Jornais,
        },
        {
          path: '/anais',
          name: 'Anais',
          component: Anais,
        },
        {
          path: '/midias',
          name: 'Midias',
          component: Midias,
        },
        {
          path: '/autores',
          name: 'Autores',
          component: Autores,
        },
        {
          path: '/editoras',
          name: 'Editoras',
          component: Editoras,
        },
        {
          path: '/temas',
          name: 'Temas',
          component: Temas,
        },
        {
          path: '/areas-de-conhecimento',
          name: 'AreasDeConhecimento',
          component: AreasDeConhecimento,
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
