<script>
import axios from "axios"

export default {
    name: "archive-item",
    props: {
        purchase: Object,
    },
    emits: ["item-deleted"],
    data() {
        return {
            showModal: false,
            idToDelete: undefined,
            currentPurchase: undefined,
            displayItem: false,
            message: "Do you really want to delete this purchase?",
            calculatedShipping: undefined
        }
    },
    methods: {
        deleteItem() {
            axios.delete(`http://localhost:8080/purchases/${this.idToDelete}`).then(() => {
                this.$emit("item-deleted", this.idToDelete);
            });
        },
        clickOnDelete(id) {
            this.idToDelete = id;
            this.showModal = true;
        },
        displayItems(purchase) {
            this.currentPurchase = purchase;
            this.displayItem = true;
        },
        calculateShipping() {
            const recap = {
                shipping: this.currentPurchase.shippingFee,
                items: this.currentPurchase.items.map(item => {
                    return {
                        name: item.user.name,
                        quantity: item.quantity,
                        price: item.unitPrice,
                        birthday: item.user.birthDate
                    }
                })
            };
            axios.post("http://localhost:8080/purchases/compute", recap).then(response => {
                this.calculatedShipping = response.data;
            });
        }
    }
}
</script>
<template>
    <va-list-item-section avatar>
        <va-avatar>
            <img src="/icons/shopping.png">
        </va-avatar>
    </va-list-item-section>

    <va-list-item-section>
        <va-list-item-label>
            Purchase n°{{ purchase.id }}
        </va-list-item-label>

        <va-list-item-label caption>
            {{ purchase.creationDate }}
        </va-list-item-label>
    </va-list-item-section>

    <va-list-item-section icon>
        <va-icon @click="displayItems(purchase)" name="remove_red_eye" color="gray" />
        <va-icon @click="clickOnDelete(purchase.id)" name="delete" color="gray" />
    </va-list-item-section>
    <va-modal v-model="showModal" :message="message" @ok="deleteItem" title="Overview" />
    <va-modal v-model="displayItem" hide-default-actions overlay-opacity="0.2">
        <template #header>
            <h2>Order recap</h2>
        </template>
        <div class="modal">
            <div class="va-table-responsive">
                <table class="va-table va-table--hoverable">
                    <thead>
                        <tr>
                            <th>Label</th>
                            <th>Quantity</th>
                            <th>Unit price</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="item in currentPurchase.items">
                            <td>{{ item.label }}</td>
                            <td class="quantity">{{ item.quantity.toFixed(2) }}</td>
                            <td class="money">{{ item.unitPrice.toFixed(2) }}</td>
                            <td>{{ item.user.name }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <va-button @click="calculateShipping()">
                Calculate shipping...
            </va-button>
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
                        <tr v-for="item in calculatedShipping">
                            <td>{{ item.name }}</td>
                            <td class="money">{{ item.total.toFixed(2) }}</td>
                            <td class="money">{{ item.shipping.toFixed(2) }}</td>
                        </tr>
                        <tr v-if="calculatedShipping">
                            <td></td>
                            <td class="money">
                                {{ calculatedShipping.map(item => item.total).reduce((acc, value) => acc + value)
                                }}</td>
                            <td class="money">
                                {{ calculatedShipping.map(item => item.shipping).reduce((acc, value) => acc + value)
                                }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </va-modal>
</template>
<style>
</style>