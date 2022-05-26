<script>
import emitter from "tiny-emitter/instance";
import { RouterView } from 'vue-router'

export default {
    data() {
        return {
            items: [
                { name: 'Home', path: '/', icon: 'home' },
                { name: 'Users', path: '/users', icon: 'account_circle' },
                { name: 'Purchases', path: '/purchases', icon: 'shopping_cart' },
                { name: 'Archives', path: 'archives', icon: 'bookmark' },
                { name: 'About', path: '/about', icon: 'info' },
            ],
            minimized: false,
            activeRouteName: 'Home',
        }
    },
    methods: {
        triggerMenu(status) {
            this.minimized = status;
        },
        isRouteActive(route) {
            return this.activeRouteName === route.name
        },
        setRouteActive(route) {
            this.activeRouteName = route.name
        }
    },
    mounted() {
        emitter.on("trigger-menu-event", (response) => {
            this.triggerMenu(response);
        });
    }
}
</script>
<template>
    <main class="base-layout">
        <div class="flex">
            <va-sidebar class="va-sidebar" :minimized="minimized" textColor="dark" minimizedWidth="0">
                <va-sidebar-item v-for="(route, idx) in items" :to="route.path" @click="setRouteActive(route)"
                    :active="isRouteActive(route)">
                    <va-sidebar-item-content>
                        <va-icon :name="route.icon" />
                        <va-sidebar-item-title v-if="!minimized" style="height: 24px;">
                            {{ route.name }}
                        </va-sidebar-item-title>
                    </va-sidebar-item-content>
                </va-sidebar-item>
            </va-sidebar>
        </div>
        <div class="base-layout-content">
            <RouterView />
        </div>
    </main>
</template>
<style>
.base-layout {
    display: flex;
    flex-direction: row;
    height: 100%;
    position: relative;
    flex-grow: 2;
    overflow: hidden;
    z-index: 0;
}

.flex {
    display: flex;
    flex-direction: row;
}

.base-layout-content {
    height: 100%;
    width: 100%;
    padding: 2em;
    padding-top: 0;
    overflow-y: auto;
    overflow-x: hidden;
}
</style>