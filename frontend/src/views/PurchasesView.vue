<script>
import Purchases from '../components/purchases/Purchases.vue'
import Upload from '../components/purchases/Upload.vue'
import Output from '../components/purchases/Output.vue'

export default {
    components: {
        Purchases,
        Upload,
        Output
    }
}
</script>
<template>
    <div class="purchases">
        <h1>Purchases</h1>
        <Purchases />
        <Upload />
        <Output />
    </div>
</template>
<style>
.purchases {
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