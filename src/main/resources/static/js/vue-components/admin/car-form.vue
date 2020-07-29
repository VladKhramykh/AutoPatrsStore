<template>
    <v-container>
        <car-dialog :btnTitle="this.btnTitle" :dialogTitle="this.dialogTitle" :propCar="this.car"/>
        <v-card
                class="mx-auto"
                max-width="auto"
                tile
        >
            <v-list>
                <v-subheader>Уже обслуживаемые авто</v-subheader>
                <v-list-item-group
                        color="primary"
                >
                </v-list-item-group>
                <v-list-item-content>
                    <v-list-item>
                        <v-row class="listTitle">
                            <v-col cols="1" offset-lg="1">Марка</v-col>
                            <v-col cols="1">Модель</v-col>
                            <v-col cols="1">Год выпуска</v-col>
                            <v-col cols="1">Тип кузова</v-col>
                            <v-col cols="1">Кол-во дверей</v-col>
                            <v-col cols="1">Тип двигателя</v-col>
                            <v-col cols="1">Объём двигателя</v-col>
                            <v-col cols="1">Тип КПП</v-col>
                        </v-row>
                    </v-list-item>
                    <hr/>
                    <car-row :carProp="car" v-for="car in cars"/>
                </v-list-item-content>

            </v-list>
        </v-card>
    </v-container>
</template>

<script>
    import CarRow from './car-parts/car-row.vue'
    import CarFormDialog from "./car-parts/car-form-dialog";

    export default {
        name: "car-form",
        components: {
            'car-dialog': CarFormDialog,
            'car-row': CarRow
        },
        data() {
            return {
                cars: [],
                carTypes: [],
                carMarks: [],
                car: {
                    model: '',
                    carMark: {
                        name: '',
                        logo: []
                    },
                    carType: {
                        typeName: '',
                        id: ''
                    },
                    countOfDoors: '',
                    engineType: '',
                    engineVolume: '',
                    transmissionType: '',
                    year: ''
                },
                dialog: false,
                yearRules: [
                    value => !!value || 'Required.',
                    value => (value && value < 2020 && value > 1900) || 'Плез, введите корректную дату',
                ],
                doorRules: [
                    value => !!value || 'Required.',
                    value => (value && value < 6 && value > 0) || 'плез, проверьте количество дверей',
                ],
                dialogTitle: "Добавить авто",
                btnTitle: "Добавить"
            }
        },
        created() {
            this.$http.get("http://localhost:8081/api/cars")
                .then(function (data) {
                    if (data.ok) {
                        this.cars = data.body;
                    } else {
                        alert("error");
                    }
                });
        }
    }
</script>

<style scoped>


</style>