import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login'
import AppIndex from '../components/home/AppIndex'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/index',
      name: 'AppIndex',
      component: AppIndex
    }
  ]
})
