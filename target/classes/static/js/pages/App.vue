<template>
    <v-app>

        <app-header></app-header>
        <v-content>
            <router-view></router-view>
        </v-content>
        <app-footer></app-footer>
    </v-app>
</template>

<script>
    import Header from '../vue-components/header.vue';
    import Footer from '../vue-components/footer.vue';

    import {mapMutations, mapState} from 'vuex'


    export default {
        components: {
            'app-header': Header,
            'app-footer': Footer
        }
    }

</script>

<style>

    html,
    body {
        height: 100%;
        background: #EEEEEE;
    }

    .wrapper {
        display: flex;
        flex-direction: column;
        min-height: 100%;
    }

    .content {
        flex: 1 1 auto;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #F4A460;
    }
</style>
