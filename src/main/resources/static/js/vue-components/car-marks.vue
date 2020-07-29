<template>
  <div>
    <li>
      <div v-for="mark in marks">{{ mark.name }}</div>
    </li>
  </div>
</template>

<script>
    export default {
        name: "car-marks",
        props: {
            marks: {
                type: Array,
                required: true
            }
        },
        created() {
            this.$http.get("http://localhost:8081/api/car-marks")
                .then(function (result) {
                    if (result.status === 200) {
                        this.marks = result.body;
                    } else {
                        alert("Error: " + result.statusMessage);
                    }
                })
        }
    }
</script>

<style scoped>

</style>
