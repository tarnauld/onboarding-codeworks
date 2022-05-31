<script>
import axios from "axios";
import emitter from 'tiny-emitter/instance';

export default {
    data() {
        return {
            file: undefined,
            message: "Required field",
            shipping: undefined,
            fileSelected: false
        }
    },
    methods: {
        upload(file) {
            let formData = new FormData();
            formData.append('file', this.file);
            formData.append('shipping', this.shipping)

            axios.post("http://localhost:8080/api/purchases/upload", formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then((response) => {
                emitter.emit('file-uploaded-event', response.data);
            });
        },
        isFormDisabled() {
            return !(this.fileSelected && this.shipping !== undefined && this.shipping !== "")
        }
    },
    mounted() {
        emitter.on('add-file-event', (event) => {
            this.file = event;
            this.fileSelected = true;
        });
    }
}
</script>
<template>
    <va-input class="mb-4" v-model="shipping" :messages="message" label="Shipping fee"/>
    <va-button class="upload" @click="upload()" v-bind:disabled="isFormDisabled()">Upload</va-button>
</template>
<style>
.upload {
    margin-top: 2em;
}
</style>