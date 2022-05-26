<script>
import emitter from "tiny-emitter/instance";

export default {
  data() {
    return {
      minimized: false
    }
  },
  methods: {
    triggerMenu() {
      this.minimized = !this.minimized
      emitter.emit("trigger-menu-event", this.minimized);
    }
  }
}
</script>
<template>
  <va-navbar color="#fff" class="header">
    <template #left>
      <div class="brand">
        <va-button color="#fff" size="large" icon="menu" v-if="minimized" @click="triggerMenu"></va-button>
        <va-button color="#fff" size="large" icon="menu_open" v-if="!minimized" @click="triggerMenu"></va-button>
        <a href="/">
          <img src="/codeworks.png" width="200" />
        </a>
      </div>
    </template>
    <template #center>
      <a href="https://github.com/tarnauld/onboarding-codeworks" class="github">
        <img src="/icons/logo-github.svg" width="30" />
        <span>GitHub</span>
      </a>
    </template>
    <template #right>
      <div class="version">v1.4.0</div>
    </template>
  </va-navbar>
</template>
<style>
.header {
  background-color: #fff;
  box-shadow: 0 2px 8px rgb(122 139 173 / 20%);
}

@media screen and (max-width: 767px) {
  .va-navbar__content {
    flex-direction: row !important;
  }

  .version {
    display: none;
  }
}

.github {
  display: flex;
  align-items: center;
}

.github>* {
  filter: invert(27%) sepia(76%) saturate(1410%) hue-rotate(202deg) brightness(92%) contrast(98%);
  ;
}

.github>span {
  margin-left: 0.4rem;
  font-weight: 600;
  color: rgb(37, 80, 192);
}

.brand {
  display: flex;
  align-items: center;
}

.version {
  font-weight: 600;
  filter: invert(27%) sepia(76%) saturate(1410%) hue-rotate(202deg) brightness(92%) contrast(98%);
}
</style>