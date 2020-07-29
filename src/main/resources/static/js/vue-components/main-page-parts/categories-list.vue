<template>
    <v-container>
        <v-card
                class="mx-auto"
                width="300"
        >
            <v-list>
                <v-list-item>
                    <v-list-item-icon>
                        <v-icon>home</v-icon>
                    </v-list-item-icon>

                    <v-list-item-title class="headline">Категории</v-list-item-title>
                </v-list-item>
                <v-list-group
                        :key="i"
                        no-action
                        prepend-icon="account_circle"
                        sub-group
                        v-for="(type, i) in categories"
                        value="true"
                >
                    <template v-slot:activator>
                        <v-list-item-content>
                            <v-list-item-title>{{type.name}}</v-list-item-title>
                        </v-list-item-content>
                    </template>

                    <v-list-item
                            :key="i"
                            @click="selectSubtype(partSubtype)"
                            v-for="(partSubtype, i) in type.partSubtypes"
                            value="true"
                    >
                        <v-list-item-title v-text="partSubtype.name"></v-list-item-title>
                        <v-list-item-icon>
                            <v-icon v-text="partSubtype.id"></v-icon>
                        </v-list-item-icon>
                    </v-list-item>
                </v-list-group>
            </v-list>
        </v-card>
    </v-container>
</template>

<script>
    export default {
        name: "categories-list",
        props: ['parts', 'selectSubtype'],
        data() {
            return {
                categories: [],
                link: 'http://localhost:8081/about'
            }
        },
        created() {
            this.$http.get("http://localhost:8081/api/car-parts-types")
                .then(function (data) {
                    if (data.ok)
                        this.categories = data.body;
                })
        }
    }
</script>

<style scoped>

</style>