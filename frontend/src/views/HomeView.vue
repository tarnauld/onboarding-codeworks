<script>
import { marked } from 'marked';
import axios from 'axios';

export default {
    name: 'Home',
    data() {
        return {
            markdown: "",
        };
    },
    mounted() {
        axios.get('/README.md').then(response => {
            this.markdown = response.data;
        });
    },
    computed: {
        markdownToHtml() {
            return marked(this.markdown);
        }
    }
}
</script>
<template>
    <div class="home">
        <h1>Home</h1>
        <div v-html="markdownToHtml"></div>
    </div>
</template>
<style>
.home {
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

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>