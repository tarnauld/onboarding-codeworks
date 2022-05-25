import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue')
    }, {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue')
    }, {
      path: '/purchases',
      name: 'purchases',
      component: () => import('../views/PurchasesView.vue')
    },{
      path: '/users',
      name: 'users',
      component: () => import('../views/UsersView.vue')
    }, {
      path: '/archives',
      name: 'archives',
      component: () => import('../views/ArchivesView.vue')
    }
  ]
})

export default router
