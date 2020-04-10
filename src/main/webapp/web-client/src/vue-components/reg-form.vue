<template>
  <div>
    <form v-if="!submitted">
      <div>
        <label for="firstName">First name</label><br/>
        <input type="text" v-model="user.firstName" id="firstName" />
      </div>
      <div>
        <label for="lastName">Last name</label><br/>
        <input type="text" v-model="user.lastName" id="lastName" />
      </div>
      <div>
        <label for="email">Email</label><br/>
        <input type="text" v-model="user.email" id="email" />
      </div>
      <div>
        <label for="telNumber">Phone number</label><br/>
        <input type="text" v-model="user.telNumber" id="telNumber" />
      </div>
      <div>
        <label for="address">Address</label><br/>
        <input type="text" v-model="user.address" id="address" />
      </div>
      <div>
        <label for="password">Password</label><br/>
        <input type="password" v-model="user.password" id="password" />
      </div>
      <input type="button" id="submit" v-on:click.prevent="submit()" value="Add"/>
      <input type="button" onclick="location.href='/'" value="Home"/>
    </form>
    <div v-if="submitted">
      <h2>Thanks for registration</h2>
    </div>
  </div>
</template>

<script>

    export default {
        data() {
            return {
                user: {
                    firstName: '',
                    lastName: '',
                    email: '',
                    telNumber: '',
                    address: '',
                    password: '',
                },
                submitted: false
            }
        },
        methods: {
            submit: function () {
                this.$http.post('http://localhost:8081/api/users', {
                    firstName: this.user.firstName,
                    lastName: this.user.lastName,
                    email: this.user.email,
                    telNumber: this.user.telNumber,
                    address: this.user.address,
                    password: this.user.password
                }).then(function (data) {
                    if (data.status !== 201) {
                        this.submitted = true
                    } else {
                        alert("Error while creating");
                    }
                });
            }
        }
    }
</script>

<style scoped>
  *{
    text-align: center;
    font-size: 20px;
    padding: 5px;
  }
</style>
