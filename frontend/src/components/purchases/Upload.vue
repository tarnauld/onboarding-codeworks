<script>
import axios from "axios";
import emitter from 'tiny-emitter/instance';

export default {
    data() {
        return {
            file: undefined
        }
    },
    methods: {
        upload(file) {
            let formData = new FormData();
            formData.append('file', this.file);

            axios.post("http://localhost:8080/purchases/upload", formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then((response) => {
                emitter.emit('file-uploaded-event', response.data);
            });
        }
    },
    mounted() {
        emitter.on('add-file-event', (event) => {
            this.file = event;
        });
    }
}
</script>
<template>
    <va-button @click="upload()">Upload</va-button>
</template>
<style>
</style>