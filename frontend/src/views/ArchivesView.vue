<script>
import List from '../components/archives/List.vue'

export default {
  components: {
    List
  }
}
</script>
<template>
  <div class="archives">
    <h1>Archives</h1>
    <List></List>
  </div>
</template>
<style>
.archives {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 2em;
}

h1 {
    margin-top: 2em;
    margin-bottom: 2em;
}
</style>