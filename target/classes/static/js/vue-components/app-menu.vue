<template>
    <div data-app>
        <v-menu class="text-center"
                offset-y
                dark
                close-on-click
        >
            <template v-slot:activator="{ on }">
                <v-btn
                        dark
                        color="primary"
                        v-on="on"
                >
                    {{profile.firstName}}
                </v-btn>
            </template>
            <v-list>
                <v-list-item>
                    <router-link v-bind:to="this.url">Профиль</router-link>
                </v-list-item>
                <v-list-item>
                    <a href="/logout">Выйти</a>
                </v-list-item>
            </v-list>
        </v-menu>
    </div>
</template>

<script>
    import {mapState, mapGetters} from 'vuex'

    export default {
        name: "app-menu",
        data() {
            return {
                url: profileUrl
            }
        },
        computed: {
            ...mapState(['profile']),
            ...mapGetters(['profileUrl'])
        }
    }
</script>

<style scoped>

</style>