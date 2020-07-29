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
                    <v-col cols="8">
                        <v-text-field
                                :rules="this.rules"
                                hide-details="auto"
                                label="Название"
                                required
                                v-model="propWarehouse.name"
                        ></v-text-field>
                    </v-col>
                    <v-col cols="8">
                        <v-text-field
                                :rules="this.rules"
                                hide-details="auto"
                                label="Адрес"
                                required
                                v-model="propWarehouse.address"
                        ></v-text-field>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field
                                :rules="this.rules"
                                hide-details="auto"
                                label="Телефон"
                                required
                                v-model="propWarehouse.telNumber"
                        ></v-text-field>
                    </v-col>
                    <v-col cols="8">
                        <v-select
                                hide-details="auto"
                                :items="this.companies"
                                :return-object="true"
                                label="Компания"
                                required
                                item-text="name"
                                v-model="propWarehouse.company"
                        ></v-select>
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
                    {{ this.btnTitle }}
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    export default {
        name: "warehouse-form-dialog",
        props: ['propWarehouse', 'dialogTitle', 'btnTitle'],
        data() {
            return {
                dialog: false,
                companies: [],
                rules: [
                    value => !!value || 'Required.',
                    value => (value && value.length >= 1) || 'Min 1 characters',
                ]
            }
        },
        created() {
            this.$http.get("http://localhost:8081/api/companies")
                .then(function (data) {
                    if(data.ok)
                        this.companies = data.body;
                })
        },
        methods: {
            submit: function () {
                if (this.propWarehouse.id) {
                    this.$http.put("http://localhost:8081/api/warehouses", this.propWarehouse)
                        .then(function (data) {
                            if (data.ok)
                                alert("OK");
                        });
                } else {
                    this.$http.post("http://localhost:8081/api/warehouses", this.propWarehouse)
                        .then(function (data) {
                            if (data.ok)
                                alert("OK");
                        });
                }
                this.dialog = false;
            }
        }
    }
</script>

<style scoped>

</style>