import Home from './Home.vue'
import DBVue from './views/DBVue/DBVue.vue'
import hello from './views/hello.vue'

const routes = [
    {
        path: '/',
        component: Home,
        children: [// 嵌套路由
            {path: '/hello', component: hello, name: 'hello', hidden: true},
            {path: '/DBVue', component: DBVue, name: '增删改查例子', hidden: true, meta: ['cbatm_lbs']},
        ]
    }
];

export default routes;