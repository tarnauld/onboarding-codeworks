<script>
import axios from "axios"
import Item from "./Item.vue"

export default {
    name: "archives",
    components: {
        Item
    },
    data() {
        return {
            purchases: [],
            showModal: false,
            idToDelete: undefined,
            currentPurchase: undefined,
            displayItem: false,
            message: "Do you really want to delete this purchase?",
            calculatedShipping: undefined
        }
    },
    methods: {
        deleteItem(event) {
            this.purchases = this.purchases.filter(purchase => purchase.id !== event);
        }
    },
    mounted() {
        axios.get("http://localhost:8080/purchases").then(response => {
            this.purchases = response.data
        });
    }
}
</script>
<template>
    <va-list fit>
        <va-list-label>
            Purchases
        </va-list-label>
        <va-list-item v-for="(purchase, index) in purchases" :key="index">
            <Item :purchase="purchase" @item-deleted="deleteItem"></Item>
        </va-list-item>
    </va-list>
</template>
<style>
.va-table-responsive {
    overflow: auto;
}

.va-table .quantity {
    text-align: right;
}

.va-table .money {
    text-align: right;
}

.modal {
    display: flex;
    flex-direction: column;
    align-items: center;
}
</style>