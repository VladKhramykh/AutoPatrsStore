<template>
    <v-container>
        <v-row>
            <v-col cols="4" md="3">
                <categories-list :parts="parts" :selectSubtype="selectSubType"/>
                <search-form :parts="parts"/>
            </v-col>
            <v-col cols="12" md="8">
                <v-row>
                    <parts-list :parts="parts"/>
                </v-row>

            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import CategoriesList from '../vue-components/main-page-parts/categories-list.vue';
    import PartsList from '../vue-components/main-page-parts/parts-list.vue';
    import SearchForm from "../vue-components/main-page-parts/search-form.vue";

    export default {
        name: "home-page",
        components: {
            'search-form': SearchForm,
            'categories-list': CategoriesList,
            'parts-list': PartsList
        },
        data() {
            return {
                parts: []
            }
        },
        methods: {
            selectSubType: function (partSubtype) {
                let url = "http://localhost:8081/api/car-parts?partSubtype=" + partSubtype.id;
                this.$http.get(url)
                    .then(function (data) {
                        if (data.ok) {
                            this.parts = data.body;
                        }
                    })
            }
        }
    }
</script>

<style scoped>

</style>
