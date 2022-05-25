<script>
import axios from "axios"
import emitter from 'tiny-emitter/instance';

let users = [];

export default {
    data() {
        return {
            users: null
        }
    },
    mounted() {
        emitter.on('create-user-event', (user) => {
            this.users.push(user);
        });

        axios.get('http://localhost:8080/users').then(response => {
            this.users = response.data
        });
    },
    methods: {
        deleteById(userId) {
            axios.delete(`http://localhost:8080/users/${userId}`).then(() => {
                this.users = this.users.filter(user => user.id !== userId);
            })
        }
    }
}
</script>

<template>
    <h1>Users list</h1>
    <table class="va-table">
        <thead>
            <tr>
                <th>Name</th>
                <th>Birthdate</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="user in users" :key="user.id">
                <td>{{ user.name }}</td>
                <td>{{ user.birthDate }}</td>
                <td>
                    <va-icon @click="deleteById(user.id)" name="delete" color="gray" />
                </td>
            </tr>
        </tbody>
    </table>
</template>