import RegForm from './vue-components/reg-form.vue'
import CarTypes from './vue-components/car-types.vue'
import LoginForm from './vue-components/login-form.vue'
import AdminMenu from './pages/AdminMenu.vue'
import HomePage from './pages/Home.vue'
import Profile from "./pages/Profile";
import Orders from "./pages/Orders";

export default [
  { path: '/car-types', component: CarTypes},
  { path: '/registration', component: RegForm},
  { path: '/login', component: LoginForm},
  { path: '/home', component: HomePage},
  { path: '/profile/:id', component: Profile},
  { path: '/admin-menu', component: AdminMenu},
  { path: '/', component: HomePage},
  { path: '/orders', component: Orders},

]
