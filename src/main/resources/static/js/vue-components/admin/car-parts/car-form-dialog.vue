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
                        <v-select
                                :items="carMarks"
                                :return-object="true"
                                :rules="requiredRule"
                                flat
                                item-text="name"
                                label="Марка"
                                outlined
                                v-model="propCar.carMark"
                        >
                        </v-select>
                    </v-col>
                    <v-col cols="12">
                        <v-text-field
                                :rules="requiredRule"
                                label="Введите модель"
                                single-line
                                v-model="propCar.model"
                        ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                        <v-select
                                :items="carTypes"
                                :return-object="true"
                                :rules="requiredRule"
                                flat
                                item-text="typeName"
                                label="Тип авто"
                                outlined
                                v-model="propCar.carType"
                        >
                        </v-select>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field
                                :rules="doorRules"
                                label="Количество дверей"
                                single-line
                                type="number"
                                v-model="propCar.countOfDoors"
                        ></v-text-field>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field
                                :rules="yearRules"
                                label="Год выпуска"
                                single-line
                                type="number"
                                v-model="propCar.year"
                        ></v-text-field>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field
                                :rules="engineVolumeRules"
                                label="Объём двигателя"
                                single-line
                                type="number"
                                v-model="propCar.engineVolume"
                        ></v-text-field>
                    </v-col>
                    <v-col cols="6">
                        <v-select
                                :items="engineTypes"
                                :rules="requiredRule"
                                label="Тип двигателя"
                                single-line
                                type="number"
                                v-model="propCar.engineType"
                        ></v-select>
                    </v-col>
                    <v-col cols="6">
                        <v-select
                                :items="transmissionTypes"
                                :rules="requiredRule"
                                label="Тип КПП"
                                single-line
                                type="number"
                                v-model="propCar.transmissionType"
                        ></v-select>
                    </v-col>
                </v-row>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn @click="dialog = false" color="blue darken-1" text>Close</v-btn>
                <v-btn @click="submit" color="blue darken-1" text>{{this.btnTitle}}</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    export default {
        name: "car-form-dialog",
        props: ['propCar', 'dialogTitle', 'btnTitle'],
        data() {
            return {
                dialog: false,
                carTypes: [],
                carMarks: [],
                yearRules: [
                    value => !!value || 'Required.',
                    value => (value && value < 2020 && value > 1900) || 'Пожалуйста, введите корректную дату',
                ],
                doorRules: [
                    value => !!value || 'Required.',
                    value => (value && value < 6 && value > 0) || 'Пожалуйста, проверьте количество дверей',
                ],
                engineVolumeRules: [
                    value => !!value || 'Required.',
                    value => (value && value < 20000 && value > 0) || 'Пожалуйста, проверьте введёный объём ',
                ],
                requiredRule: [
                    value => !!value || 'Required.'
                ],
                transmissionTypes: ['Механическая', 'Автомат', 'Гибрид'],
                engineTypes: ['Бензин', 'Дизель', 'Газ-бензин', 'Газ']
            }
        },
        created() {
            this.$http.get("http://localhost:8081/api/car-types")
                .then(function (data) {
                    if (data.status === 200) {
                        this.carTypes = data.body;
                    } else {
                        alert("error");
                    }
                });
            this.$http.get("http://localhost:8081/api/car-marks")
                .then(function (data) {
                    if (data.status === 200) {
                        this.carMarks = data.body;
                    } else {
                        alert("error");
                    }
                });
        },
        methods: {
            submit: function () {
                if (this.propCar.id) {
                    this.$http.put("http://localhost:8081/api/cars", this.propCar)
                        .then(function (data) {
                            if (data.ok)
                                alert("Updated");
                        })
                } else {
                    this.$http.post("http://localhost:8081/api/cars", this.propCar)
                        .then(function (data) {
                            if (data.ok)
                                alert("Created");
                        })
                }
                this.dialog = false;
            }
        }
    }
</script>

<style scoped>

</style>