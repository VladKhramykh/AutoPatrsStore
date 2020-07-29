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
                                :rules="this.rules"
                                hide-details="auto"
                                label="Название"
                                required
                                v-model="propType.name"
                        ></v-text-field>
                    </v-col>
                    <v-col cols-12>
                        <v-select
                                :items="this.types"
                                :return-object="true"
                                flat
                                item-text="name"
                                label="Категория"
                                outlined
                                v-model="propType.partType"
                        >
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
        name: "part-type-form-dialog",
        props: ['propType', 'dialogTitle', 'btnTitle'],
        data() {
            return {
                dialog: false,
                types: [],
                rules: [
                    value => !!value || 'Required.',
                    value => (value && value.length >= 1) || 'Min 1 characters',
                ]
            }
        },
        created() {
            this.$http.get("http://localhost:8081/api/car-parts-types")
                .then(function (data) {
                    if (data.ok)
                        this.types = data.body;
                })
        },
        methods: {
            submit: function () {
                if (this.propType.id) {
                    this.$http.put("http://localhost:8081/api/car-parts-subtypes", this.propType)
                        .then(function (data) {
                            if (data.ok)
                                alert("OK");
                        });
                } else {
                    this.$http.post("http://localhost:8081/api/car-parts-subtypes", this.propType)
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