<template>
	
    <el-dialog title="Edit" @open="openUserDetail()" @close="closeUserDetail()" v-model="dialogFormVisible" :close-on-click-modal="false" :show-close="false">
        <el-form :model="form">
            <el-form-item label="item_id" :label-width="formLabelWidth">
                <el-input :disabled="true" v-model="form.id" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="username" :label-width="formLabelWidth">
                <el-input :disabled="true" v-model="form.name" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item label="email" :label-width="formLabelWidth">
                <el-input :disabled="true" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item label="phone" :label-width="formLabelWidth">
                <el-input auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="sex" :label-width="formLabelWidth">
                <el-input :disabled="true" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="zone" :label-width="formLabelWidth">
                <el-input auto-complete="off"></el-input>
            </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button :plain="true" type="danger" v-on:click="canclemodal">Cancel</el-button>
            <el-button :plain="true" @click="updateForm(form)">Save</el-button>
        </div>
    </el-dialog>
</template>


<script>
    export default {
        data(){
            return {
                formLabelWidth: '120px',
            }
        },
        props: ['dialogFormVisible', 'form'],

        methods: {
        closeUserDetail: function(){
    		console.log('close')
		},
		openUserDetail: function(){
		    console.log('open')
		},
            updateForm: function (formName) {
                let itemId = formName.id;
                alert('update id = ' + itemId)
                //let phone = formName.phone;
                //let zone = formName.zone;
                //this.$axios.put('http://127.0.0.1:8000/api/persons/detail/' + itemId, {
                    //phone: phone,
                    //zone: zone
                //})
                //    .then(function (response) {
                //        console.log(response);
                //        this.form = response.data;

                //    })
                //    .catch(function (error) {
                //        console.log(error);
                //    });
                location.reload();
            },
            canclemodal: function () {
                this.$emit('canclemodal');
            }
        }

    }

</script>