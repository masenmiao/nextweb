//hello.vue
<template>
    <div>
        <h2>Hello Vue.js</h2>
        <hr/>
        <p>{{msg}}</p>
    </div>
</template>
<script>
    export default{
    name: 'hello',
        data(){
            return {
                msg:"This is hello components"
            }
        }
    }
</script>