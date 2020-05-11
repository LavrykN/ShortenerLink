<template>
    <div>
        <div class="d-flex">
            <b-form-input type="text" placeholder="Write something" class v-model="url" />
            <b-button @click="create">Save</b-button>
        </div>
        <div v-for="urls in urlLocalStorage">
            <p>
                <a :href="'http://localhost:8080/' + urls.shortLink" target="_blank">http://localhost:8080/{{ urls.shortLink }}</a>
            </p>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                urlLocalStorage: [],
                url: ''
            }
        },
        mounted() {
            if (localStorage.getItem('hashes')) {
                try {
                    this.urlLocalStorage = JSON.parse(localStorage.getItem('hashes'));
                } catch(e) {
                    localStorage.removeItem('hashes');
                }
            }
        },
        methods: {
            create() {
                const message = { url: this.url }
                this.$resource('/api/link{/id}').save({}, message).then(result => {
                    result.json().then(data => {
                        if(data.status_code == 200){
                            this.urlLocalStorage.push(data.data)
                            this.url = ''
                            this.saveLink()
                        }else{
                            console.log(data.status_txt)
                        }
                    })
                }, response => {
                   console.log(response.body)
                })
            },
            saveLink() {
                const parsed = JSON.stringify(this.urlLocalStorage);
                localStorage.setItem('hashes', parsed);
            }
        },
    }
</script>

<style>

</style>