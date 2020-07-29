import Vue from 'vue'
import Vuex from 'vuex'
import userApi from '../api/users'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        profile: frontendData.profile,
        basket: 0
    },
    getters: {
        getProfileUrl: state => "/profle" + state.profile.id
    },
    mutations: {
        addToBasket(state) {
            state.basket++;
        },
        updateUserMutation(state, profile) {
            if (state.profile.id === profile.id) {
                state.profile = profile;
            }
        },
        removeUserMutation(state, profile) {
            state.profile = null;
        },
    },
    actions: {
        async updateUserAction({commit}, profile) {
            const result = await userApi.update(profile)
            const data = await result.json()
            commit('updateUserMutation', data)
        },
        async removeUserAction({commit}, profile) {
            const result = await userApi.remove(profile.id)
            if (result.ok) {
                commit('removeUserMutation', profile)
            }
        },
    }
})