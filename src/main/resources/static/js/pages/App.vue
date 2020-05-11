<template>
    <div class="container">
        <div class="form-row text-center">
            <div class="col-sm-6 m-auto">
                <LinkForm />
            </div>
        </div>

    </div>

</template>

<script>
    import LinkForm from 'components/link/LinkForm.vue'

    export default {
        components: {
            LinkForm,
        }
    }
</script>

<style>
    .my-col {
        display: inline-block;
    }
</style>