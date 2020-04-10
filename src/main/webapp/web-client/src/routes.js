import RegForm from './vue-components/reg-form.vue'
import CarTypes from './vue-components/car-types.vue'
import LoginForm from './vue-components/login-form.vue'
import HomePage from './pages/Home.vue'

export default [
  { path: '/car-types', component: CarTypes},
  { path: '/registration', component: RegForm},
  { path: '/login', component: LoginForm},
  { path: '/home', component: HomePage}
]
