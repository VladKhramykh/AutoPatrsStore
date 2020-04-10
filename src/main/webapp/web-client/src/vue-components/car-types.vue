<template>
  <div id="car-types">
    <h1>Car types</h1>
    <li>
      <div v-for="type in types" class="single-blog">{{ type.typeName }}</div>
    </li>
  </div>
</template>

<script>
    export default {
        name: "car-types",
        data() {
            return {
                types: {
                    type: Array,
                    required: true
                }
            }
        },
        created() {
            this.$http.get("http://localhost:8081/api/car-types")
                .then(function (data) {
                    if (data.status === 200) {
                        this.types = data.body;
                    } else {
                        alert(data.statusText);
                    }
                })
        }
    }
</script>

<style scoped>

</style>
