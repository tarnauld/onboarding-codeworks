<script>
import {RouterLink} from 'vue-router';
</script>

<template>
    <va-app-bar>
      <va-button icon="home" color="#fff" flat :rounded="false">
      <RouterLink to="/">Onboarding CodeWorks</RouterLink>
      </va-button>
      <va-spacer />
      <va-button  color="#fff" flat :rounded="false">
        <RouterLink to="/users">Users</RouterLink>
      </va-button>
      <va-button  color="#fff" flat :rounded="false">
        <RouterLink to="/about">About</RouterLink>
      </va-button>
    </va-app-bar>
</template>

<style>
</style>