<script>
import emitter from 'tiny-emitter/instance';

export default {
    data: () => {
        return {
            basic: []
        }
    },
    methods: {
        addFile() {
            emitter.emit('add-file-event', this.basic[0]);
        }
    }
}
</script>
<template>
    <va-file-upload v-model="basic" dropzone type="single" file-types=".csv" @file-added="addFile()" />
</template>
<style>
</style>