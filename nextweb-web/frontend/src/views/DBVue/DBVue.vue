//hello.vue
<template>
<div>
    <!-- 查询 -->
        <div class="wrapper">
        <el-row class="container">
            <el-col :span="20" class="content">
                <db-filterinput></db-filterinput>
                <db-table></db-table>
            </el-col>
        </el-row>
    </div>
</div>
</template>
<script>
    
    import DbFilterinput from './DbFilterinput.vue'
    import DbTable from './DbTable.vue'
    

	export default {
	  name: 'DBVue',
	  components: {
		     DbFilterinput,
		     DbTable,
		}
	}
</script>
