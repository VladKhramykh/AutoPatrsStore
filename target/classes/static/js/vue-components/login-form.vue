<template>
    <div>
        <form v-on:submit="login()" method="post">
            <div>
                <label for="username"></label>
                <input type="email" v-model="user.username" id="username" name="username">
            </div>
            <div>
                <label for="password"></label>
                <input type="password" v-model="user.password" id="password" name="password">
            </div>
            <div>
                <input type="submit" value="login">
            </div>
        </form>

        <div>
            <p>If you do not have an account, click here</p>
            <router-link to="/registration" exact>Registration</router-link>
        </div>
    </div>
</template>

<script>
    export default {
        name: "login-form",
        data() {
            return {
                user: {
                    username: '',
                    password: ''
                }
            }
        },
        methods: {
            login() {
                if (this.user.username && this.user.password) {
                    this.$http.post('/login', this.user);
                }
            }
        }
    }
</script>

<style scoped>
    * {
        font-size: 20px;
        padding: 5px;
    }

</style>
