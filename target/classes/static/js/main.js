import Vue from 'vue'
import App from './pages/App.vue'
import '@babel/polyfill'
import 'api/resource'
import VueRouter from 'vue-router'
import Routes from './routes.js'
import store from './store/store'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'


Vue.use(Vuetify);

const router = new VueRouter({
    routes: Routes,
    mode: 'history'
});

Vue.use(VueRouter);




new Vue({
    el: '#app',
    render: h => h(App),
    router: router,
    store: store,
    vuetify: new Vuetify()
});

