import { createRouter, createWebHistory } from "vue-router";
import { LoginCallback } from "@okta/okta-vue";
import { navigationGuard } from "@okta/okta-vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import("../views/HomeView.vue"),
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: "/about",
      name: "about",
      component: () => import("../views/AboutView.vue"),
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: "/purchases",
      name: "purchases",
      component: () => import("../views/PurchasesView.vue"),
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: "/users",
      name: "users",
      component: () => import("../views/UsersView.vue"),
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: "/archives",
      name: "archives",
      component: () => import("../views/ArchivesView.vue"),
      meta: {
        requiresAuth: true,
      },
    },
    { path: "/login/callback", component: LoginCallback },
  ],
});

router.beforeEach(navigationGuard);

export default router;
