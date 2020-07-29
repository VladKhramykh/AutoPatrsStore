<template>
    <div>
        <v-toolbar
                dark
                prominent
                src="https://picsum.photos/1920/1080?random"
        >
            <v-toolbar-title class="ma-1 pa-1 display-1 font-italic font-weight-light">Антилопа Гну</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
                <v-row>
                    <v-col>
                        <router-link to="/home" flat>Главная</router-link>
                    </v-col>
                    <v-col>
                        <router-link to="/about" flat>Контакты</router-link>
                    </v-col>
                    <v-col v-if="profile">
                        <router-link to="/orders" flat>Корзина</router-link>
                    </v-col>
                    <v-col v-if="profile">
                        <router-link to="/admin-menu" flat>Админка</router-link>
                    </v-col>
                    <v-col v-if="profile">
                        <app-menu flat></app-menu>
                    </v-col>
                    <v-col v-if="!profile">
                        <router-link to="/login" flat>Войти</router-link>
                    </v-col>
                </v-row>


            </v-toolbar-items>
        </v-toolbar>
    </div>
</template>

<script>
    import AppMenu from './app-menu.vue'
    import {mapState, mapGetters} from 'vuex'

    export default {
        components: {
            "app-menu": AppMenu
        },
        data() {
            return {
                profileUrl: '/profile/',
                menu: [
                    {title: "Главная", link: "/home"},
                    {title: "Типы авто", link: "/car-types"}
                ]
            }
        },
        computed: {
            ...mapState(['profile']),
            ...mapGetters['profileUrl']
        },
        created() {
            if (this.profile)
                this.profileUrl += this.profile.id;
        }
    };


</script>

<style scoped>

    * {
        font-size: 25px;
        font-weight: bold;
    }

    a {
        color: #fff;
        padding: 15px 20px;
    }

    .router-link-active {
        background: rgba(255, 255, 255, 0.1);
        color: #eee;
    }

</style>
