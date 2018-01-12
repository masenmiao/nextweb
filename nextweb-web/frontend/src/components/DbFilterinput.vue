<template>
    <el-form :inline="true" :model="formInline">
       <el-input style="width:300px" v-model="formInline.userid"  placeholder="Please input suffix of id"></el-input>
	   
	   <el-button @click="queryItem()" type="button" size="large">Query</el-button>
    </el-form>
</template>

<script>
    import lodash from 'lodash'
    import Bus from '../eventBus'

    export default {
        name: 'db-filterinput',
        data() {
            return {
                type_options: [],
                formInline: {
                    userid: ''
                },
                formLabelWidth: '120px'
            }
        },

        watch: {
            'formInline.userid': 'filterResultData'
        },

        methods: {
            filterResultData: _.debounce(
                function () {
                    	//this.$axios.get("http://localhost:8081/api/users").then((response) => {
                        //response.data['userid'] = this.formInline.userid;
                        //Bus.$emit('filterResultData', response.data);
                        //console.log('aaa'+response.data);
                        //console.log(response.data);
                    //}).catch(function (response) {
                    //    console.log(response)
                    //});
                },
                500
            ),
            queryItem: function () {

                const itemId = this.formInline.userid;
                const idurl = 'http://127.0.0.1:8081/api/users/' + itemId;
                this.$axios.get(idurl).then((response) => {
                    Bus.$emit('filterResultData', response.data);
                }).catch(function (response) {
                    console.log(response)
                });
            }
        }
    }


</script>