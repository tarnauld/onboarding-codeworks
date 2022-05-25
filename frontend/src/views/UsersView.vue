<script>
import CreateUserForm from '../components/users/CreateUserForm.vue'
import ListUsers from '../components/users/ListUsers.vue'

export default {
    components: {
        CreateUserForm,
        ListUsers
    }
}
</script>

<template>
    <div class="users">
        <h1>Users</h1>
        <CreateUserForm></CreateUserForm>
        <ListUsers></ListUsers>
    </div>
</template>
<style>
.users {
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
}
</style>