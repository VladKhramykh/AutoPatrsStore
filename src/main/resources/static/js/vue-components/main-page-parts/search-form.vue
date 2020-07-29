<template>
    <v-container>
        <v-card
                class="mx-auto"
                width="300"
        >
            <v-card-title>
                <span class="headline">Поиск</span>
            </v-card-title>
            <v-card-text>
                <v-row class="mb-3">
                    <v-text-field
                            hide-details="auto"
                            label="Название"
                            required
                            v-model="searchDto.name"
                    ></v-text-field>
                </v-row>
                <v-row class="my-3">
                    <v-select
                            :items="this.partTypes"
                            flat
                            item-text="name"
                            item-value="id"
                            label="Категория"
                            outlined
                            v-model="searchDto.partTypeId"
                    >
                    </v-select>
                </v-row>
                <v-row class="my-3">
                    <v-select
                            :items="this.cars"
                            flat
                            item-text="model"
                            item-value="id"
                            label="Авто"
                            outlined
                            v-model="searchDto.carId"
                    >
                    </v-select>
                </v-row>
                <v-row class="my-3">
                    <v-col cols="6">
                        <v-text-field
                                hide-details="auto"
                                label="От"
                                required
                                type="number"
                                v-model="searchDto.fromPrice"
                        ></v-text-field>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field
                                hide-details="auto"
                                label="До"
                                required
                                type="number"
                                v-model="searchDto.toPrice"
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-btn @click="search(searchDto)" class="ma-2" color="indigo" outlined>
                        Поиск ({{ searchedCount }})
                    </v-btn>
                </v-row>
            </v-card-text>

        </v-card>
    </v-container>
</template>

<script>
    export default {
        name: "search-form",
        props: ['parts', 'search', 'searchedCount'],
        data() {
            return {
                partTypes: [],
                cars: [],
                searchDto: {
                    name: '',
                    partTypeId: '',
                    carId: '',
                    fromPrice: '',
                    toPrice: ''
                }
            }
        },
        created() {
            this.$http.get("http://localhost:8081/api/cars")
                .then(function (data) {
                    if (data.ok)
                        this.cars = data.body;
                });
            this.$http.get("http://localhost:8081/api/car-parts-types")
                .then(function (data) {
                    if (data.ok)
                        this.partTypes = data.body;
                });
            this.$http.get("http://localhost:8081/api/car-parts/count")
                .then(function (data) {
                    if (data.ok)
                        this.searchedCount = data.body;
                });

        },
        methods: {}
    }
</script>

<style scoped>

</style>