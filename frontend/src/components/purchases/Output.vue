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
                    <th>Total</th>
                    <th>Shipping</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="user in output" :key="user.id">
                    <td>{{ user.name }}</td>
                    <td class="money">{{ user.total.toFixed(2) }}</td>
                    <td class="money">{{ user.shipping.toFixed(2) }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
<style>
.va-table-responsive {
    overflow: auto;
}

h2 {
    margin-top: 2em;
}

.va-table .money {
    text-align: right;
}
</style>