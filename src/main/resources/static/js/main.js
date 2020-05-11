import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'


Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(VueResource)

new Vue({
    el: '#app',
    render: a => a(App)
})
