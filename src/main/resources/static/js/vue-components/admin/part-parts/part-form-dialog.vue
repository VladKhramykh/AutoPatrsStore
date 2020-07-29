<template>
    <v-dialog max-height="max-height" max-width="800px" persistent v-model="dialog">
        <template v-slot:activator="{ on }">
            <v-btn color="secondary" dark
                   outlined v-on="on">{{dialogTitle}}
            </v-btn>
        </template>
        <v-card>
            <v-card-title>
                <span class="headline">{{dialogTitle}}</span>
            </v-card-title>
            <v-card-text>
                <v-row>
                    <v-col cols="12">
                        <v-text-field
                                :rules="rules"
                                hide-details="auto"
                                label="Название"
                                required
                                v-model="propPart.name"
                        ></v-text-field>
                    </v-col>
                    <v-col cols-6>
                        <v-select
                                :chips="true"
                                :items="this.partTypes"
                                :return-object="true"
                                item-text="name"
                                label="Категория"
                                outlined
                                v-model="propPart.partType"
                        >
                        </v-select>
                    </v-col>
                    <v-col cols="6">
                        <v-select
                                :chips="true"
                                :items="this.partSubtypesByPart"
                                :return-object="true"
                                item-text="name"
                                label="Раздел категории"
                                outlined
                                v-model="propPart.partSubtype"
                        >
                        </v-select>
                    </v-col>
                    <v-col cols="12">
                        <v-select
                                :chips="true"
                                :items="this.cars"
                                :multiple="true"
                                item-text="model"
                                :return-object="true"
                                label="Подходит к"
                                outlined
                                v-model="propPart.cars"
                        >
                            <template slot="selection" slot-scope="data">
                                {{ data.item.carMark.name }} {{ data.item.model }} |
                            </template>
                            <template slot="item" slot-scope="data">
                                {{ data.item.carMark.name }} {{ data.item.model }}
                            </template>
                        </v-select>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field
                                :rules="rules"
                                hide-details="auto"
                                label="Цена"
                                required
                                v-model="propPart.price"
                        ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                        <v-select
                                :chips="true"
                                :items="this.warehouses"
                                :multiple="true"
                                :return-object="true"
                                item-text="name"
                                label="Есть на складах"
                                outlined
                                v-model="propPart.warehouses"
                        >
                            <template slot="selection" slot-scope="data">
                                {{ data.item.name }} {{ data.item.address }}
                            </template>
                            <template slot="item" slot-scope="data">
                                {{ data.item.name }} {{ data.item.address }}
                            </template>
                        </v-select>
                    </v-col>
                </v-row>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn @click="dialog = false" color="blue darken-1" text>Закрыть</v-btn>
                <v-btn
                        @click="submit()"
                        class="green darken-1"
                        dark
                >
                    {{ btnTitle }}
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    export default {
        name: "part-form-dialog",
        props: ['propPart', 'dialogTitle', 'btnTitle'],
        watch: {
            'propPart.partType': function (val, oldVal) {
                this.partSubtypesByPart = this.partSubtypes.filter(item => item.partType.id === val.id);
            }
        },
        data() {
            return {
                dialog: false,
                partTypes: [],
                partSubtypes: [],
                warehouses: [],
                partSubtypesByPart: [],
                cars: [],
                rules: [
                    value => !!value || 'Required.',
                    value => (value && value.length >= 1) || 'Min 1 characters',
                ]
            }
        },
        created() {
            this.$http.get("http://localhost:8081/api/cars")
                .then(function (data) {
                    if (data.ok)
                        this.cars = data.body;
                });
            this.$http.get("http://localhost:8081/api/car-parts-subtypes")
                .then(function (data) {
                    if (data.ok){
                        this.partSubtypes = data.body;
                        this.filterSubtypes(this.propPart.partType.id);
                    }

                });

            this.$http.get("http://localhost:8081/api/warehouses")
                .then(function (data) {
                    if (data.ok)
                        this.warehouses = data.body;
                });

            this.$http.get("http://localhost:8081/api/car-parts-types")
                .then(function (data) {
                    if (data.ok)
                        this.partTypes = data.body;
                });


        },
        methods: {
            filterSubtypes: function(partTypeId) {
              this.partSubtypesByPart = this.partSubtypes.filter(item => item.partType.id === partTypeId);
            },
            submit: function () {
                let obj = {
                    id: this.propPart.id ? this.propPart.id : '',
                    name: this.propPart.name,
                    price: this.propPart.price,
                    partType: {
                        id: this.propPart.partType.id,
                        name: this.propPart.partType.name
                    },
                    partSubtype: {
                        id: this.propPart.partSubtype.id,
                        name: this.propPart.partSubtype.name
                    },
                    cars: this.propPart.cars.map(function (item, index, array) {
                        return {
                            id: item.id,
                            name: item.name
                        };
                    }),
                    warehouses: this.propPart.warehouses.map(function (item, index, array) {
                        return {
                            id: item.id,
                            name: item.name
                        };
                    })
                };

                if (obj.id) {
                    this.$http.put("http://localhost:8081/api/car-parts", obj)
                        .then(function (data) {
                            if (data.ok)
                                alert("updated");
                        });
                } else {
                    this.$http.post("http://localhost:8081/api/car-parts", obj)
                        .then(function (data) {
                            if (data.ok)
                                alert("created");
                        });
                }
                this.dialog = false;
            }
        }
    }
</script>

<style scoped>

</style>