<template>
  <div>
    <br/>
    <h1>About company</h1>
    {{ company.name }}
  </div>
</template>

<script>
    export default {
        name: "about",
        data() {
            return {
                company: {
                    id : '',
                    name: '',
                    directorName: '',
                    officeAddress: '',
                    telNumber: '',
                    description: '',
                    warehouses: {
                        type: Array,
                        required: true
                    }
                }
            }
        },
        created() {
            this.$http.get("http://localhost:8081/api/company/1")
                .then(function (data) {
                    if(data.status === 200) {
                        this.company = data.body;
                    } else {
                        alert("Error");
                    }
                })
        }
    }
</script>

<style scoped>

</style>
