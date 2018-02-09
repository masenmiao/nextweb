<template>
    <div>
        <el-table
                :data="tableData"
                border
                style="width: 100%"
                class="table">
            <el-table-column
                    fixed
                    prop="id"
                    label="user_id"
                    width="300">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="user_name"
                    >
            </el-table-column>

            <el-table-column
                    fixed="right"
                    label="Operation"
                    width="100">
                <template scope="scope">
                    <el-button @click="editItem(scope.$index, tableData)" type="text" size="large">Edit</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination class="pagination" layout="prev, pager, next" :total="total" :page-size="pageSize"
                       v-on:current-change="changePage">
        </el-pagination>
        <db-modal :dialogFormVisible="dialogFormVisible" :form="form" v-on:canclemodal="dialogVisible"></db-modal>
    </div>

</template>

<script>
    import Bus from '../../eventBus'
    import DbModal from './DbModal.vue'

    export default {
        data(){
            return {
                tableData: [],
                apiUrl: 'http://localhost:8081/api/users',
                total: 0,
                pageSize: 10,
                currentPage: 1,
                dialogFormVisible: false,
                form: '',
            }
        },
        components: {
            DbModal
        },
        mounted () {//生命周期的一个阶段,el 被新创建的 vm.$el 替换，并挂载到实例上去之后调用该钩子
            this.getCustomers();
            Bus.$on('filterResultData', (data) => {
            	console.log('bbb'+data);
            	this.tableData = data;
                //this.tableData = data.results;
                //this.total = data.total_pages;
                //this.pageSize = data.count;

            });
        },

        methods: {

            dialogVisible: function () {
                this.dialogFormVisible = false;
            },

            getCustomers: function () {
                this.$axios.get(this.apiUrl, {
                    params: {
                        page: this.currentPage,
                    }
                }).then((response) => {
                    this.tableData = response.data;
                    //this.total = response.data.data.total;
                    //this.pageSize = response.data.data.count;
                    console.log(response.data.data);
                }).catch(function (response) {
                    console.log(response)
                });
            },
            changePage: function (currentPage) {
                this.currentPage = currentPage;
                this.getCustomers()
            },
            editItem: function (index, rows) {

                this.dialogFormVisible = true;
                const itemId = rows[index].id;
                //const idurl = 'http://127.0.0.1:8000/api/persons/detail/' + itemId;
                const idurl = 'http://127.0.0.1:8081/api/users/detail/' + itemId;
                this.$axios.get(idurl).then((response) => {
                    this.form = response.data;
                    console.log(this.form.name);
                }).catch(function (response) {
                    console.log(response)
                });
            },

            formatter(row, column) {
                let data = this.$moment(row.create_datetime, this.$moment.ISO_8601);
                return data.format('YYYY-MM-DD')
            },
        }
    }
</script>

<style>
    .table {
        margin-top: 30px;
    }

    .pagination {
        margin-top: 10px;
        float: right;
    }

</style>