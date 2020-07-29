import Vue from 'vue'

const users = Vue.resource('http://localhost:8081/api/users{/id}')

export default {
    add: user => users.save({}, user),
    update: user => users.update({id: user.id}, user),
    remove: id => users.remove({id}),
    get: () => users.get()
}