<template>
  <div>
    <h1>Categories</h1>
    <li>
      <div v-for="type in types">{{ type.name }}</div>
    </li>
    <h3>Add new part type</h3>
    <form v-on:submit="submit">
      <label>Name
        <input type="text" v-model="newType.name" name="name">
      </label>
      <input type="submit" value="Add" />
    </form>
  </div>
</template>

<script>
    export default {
        name: "part-types",
        data() {
            return {
                types: {
                    type: Array,
                    required: true
                },
                newType: {
                    name: ''
                }
            }
        },
        created() {
            this.$http.get("http://localhost:8081/api/car-parts-types")
                .then(function (data) {
                    if (data.status === 200) {
                        this.types = data.body;
                    } else {
                        alert("Error: " + data.statusMessage)
                    }
                });
        },
        methods: {
            submit: function () {
                this.$http.post("http://localhost:8081/api/car-parts-types", {
                    name: this.newType.name
                })
                    .then(function (data) {
                        if (data.status === 201) {
                            alert("OK");
                        } else {
                            alert(data.body);
                        }
                    })
            }
        }
    }
</script>

<style scoped>

</style>
