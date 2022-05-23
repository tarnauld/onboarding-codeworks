<script>
import emitter from 'tiny-emitter/instance'

export default {
    data() {
        return {
            output: undefined
        }
    },
    mounted() {
        emitter.on('file-uploaded-event', (response) => {
            this.output = response;
            console.log(this.output)
        });
    }
}
</script>
<template>
    <h2>Output</h2>
    <div class="va-table-responsive">
        <table class="va-table va-table--hoverable">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Country</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="user in output" :key="user.id">
                    <td>{{ user.label }}</td>
                    <td>
                        <va-badge :text="user.status" :color="user.status" />
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
<style>
.va-table-responsive {
    overflow: auto;
}
</style>