//hello.vue
<template>
<div>
	<h1>{{ msg }}</h1>
    <h2>Essential Links</h2>
    <ul>
      <li><a href="https://vuejs.org" target="_blank">Core Docs</a></li>
      <li><a href="https://forum.vuejs.org" target="_blank">Forum</a></li>
      <li><a href="https://chat.vuejs.org" target="_blank">Community Chat</a></li>
      <li><a href="https://twitter.com/vuejs" target="_blank">Twitter</a></li>
    </ul>
    <h2>Ecosystem</h2>
    <ul>
      <li><a href="http://router.vuejs.org/" target="_blank">vue-router</a></li>
      <li><a href="http://vuex.vuejs.org/" target="_blank">vuex</a></li>
      <li><a href="http://vue-loader.vuejs.org/" target="_blank">vue-loader</a></li>
      <li><a href="https://github.com/vuejs/awesome-vue" target="_blank">awesome-vue</a></li>
    </ul>
    
    <!-- 查询 -->
        <div class="wrapper">
        <db-header></db-header>
        <el-row class="container">
            <el-col :span="4" class="menu">
                <li><router-link to="/hello">Go to hello</router-link></li>
    			<li><router-link to="/DBVue">Go to DBVue</router-link></li>
    			<!--可以动态获取routers进行展示-->
            </el-col>
            <el-col :span="20" class="content">
                
                <router-view></router-view>

            </el-col>


        </el-row>

        <el-row>
            <el-col :span="24">
                <footer class="footer">
                    <db-footer></db-footer>
                </footer>

            </el-col>
        </el-row>
    </div>
</div>
</template>
<script>
    import DbHeader  from './components/DbHeader.vue'
    import DbSidebar from './components/DbSidebar.vue'
    import DbFooter from './components/DbFooter.vue'
    import ElRow from "element-ui/packages/row/src/row";


	export default {
	  name: 'Home',
	  components: {
		     ElRow, 
		     DbHeader,
		     DbFooter
		},
	  data () {
	    return {
	      msg: 'Welcome to Your Vue.js App'
	    }
	  },
		
	}
</script>
