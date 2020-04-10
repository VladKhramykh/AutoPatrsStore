<template>
  <div>
    <div>
      <label for="email"></label>
      <input type="email" v-model="user.email" id="email">
    </div>
    <div>
      <label for="password"></label>
      <input type="password" v-model="user.password" id="password">
    </div>
    <div>
      <input type="button" v-on:click="login()" value="login">
    </div>
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
                    email: '',
                    password: ''
                }
            }
        },
        methods: {
            login: function () {
                this.$http.post('http://localhost:8081/login', {
                    username: this.user.email,
                    password: this.user.password
                }).then(function (result) {
                    if (result.status === 200) {
                        result.json().then(function (data) {
                            alert('Hello, ' + data.body.email);
                        })
                    } else {
                        alert("Please check your entries");
                    }
                })
            }
        }
    }
</script>

<style scoped>
  *{
    font-size: 20px;
    padding: 5px;
  }

</style>
