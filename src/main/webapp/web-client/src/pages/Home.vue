<template>
  <div>
    <car-types></car-types>
    <hr/>
    <part-types></part-types>
    <hr>
    <about></about>
  </div>
</template>

<script>
  import CarTypes from '../vue-components/car-types.vue';
  import PartTypes from '../vue-components/part-types.vue';
  import About from '../vue-components/about.vue';

    export default {
        name: "home-page",
        components: {
            'car-types': CarTypes,
            'part-types' : PartTypes,
            'about': About
        }
    }
</script>

<style scoped>

</style>
