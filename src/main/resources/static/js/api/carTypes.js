import Vue from 'vue'

const types = Vue.resource('/api/car-types{/id}')

export default {
    add: type => types.save({}, type),
    update: type => types.update({id: type.id}, type),
    remove: id => types.remove({id}),
    get: () => types.get()
}