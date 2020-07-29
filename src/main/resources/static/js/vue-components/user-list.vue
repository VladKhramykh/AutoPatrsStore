<template>
  <div>
    <ul>
      <li v-for="user in users">
        <h2>{{user.firstName}}</h2>
        <h3>{{user.id}}</h3>
      </li>
    </ul>
  </div>
</template>

<script>
    export default {
        name: "user-list",
        props: {
            users: {
                type: Array,
                required: true
            }
        },
        data() {
            return {}
        }
    }
</script>

<style scoped>

</style>
