import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import {
    Button,
    Select,
    Row,
    Col,
    Pagination,
    Table,
    TableColumn,
    Form,
    FormItem,
    Input,
    Dialog,
    Option
} from 'element-ui'

//element-ui 2.0 theme-chalk dialog无法显示，所以更改版本到1.x
import 'element-ui/lib/theme-default/index.css'
import lang from 'element-ui/lib/locale/lang/en'
import locale from 'element-ui/lib/locale'

// more grace import third package !
import moment from 'moment'//日期
import axios from 'axios'//网络
import curvejs from 'curvejs'//动画

import routes from './routes'//菜单路由

Object.defineProperty(Vue.prototype, '$moment', { value: moment });
Object.defineProperty(Vue.prototype, '$axios', { value: axios });
Object.defineProperty(Vue.prototype, '$curvejs', { value: curvejs });

Vue.use(VueRouter);  
Vue.use(Button);
Vue.use(Select);
Vue.use(Row);
Vue.use(Col);
Vue.use(Pagination);
Vue.use(Table);
Vue.use(TableColumn);
Vue.use(Form);
Vue.use(FormItem);
Vue.use(Input);
Vue.use(Dialog);
Vue.use(Option);

locale.use(lang);

//创建router实例
const router = new VueRouter({
  //mode指定路由模式，默认'hash'，另一种可选的模式是'history'
  //mode: 'hash',
  routes
});

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
