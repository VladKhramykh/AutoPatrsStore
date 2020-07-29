<template>
    <div>
        <v-list-item>
            <v-row>
                <v-col cols="1">
                    <v-img :src="this.imageSrc"
                           max-height="80"
                           max-width="80"
                    ></v-img>
                </v-col>
                <v-col align-self="center" cols="1">
                    {{ this.carProp.carMark.name }}
                </v-col>
                <v-col align-self="center" cols="1">
                    {{ this.carProp.model }}
                </v-col>
                <v-col align-self="center" cols="1">
                    {{ this.carProp.year }}
                </v-col>
                <v-col align-self="center" cols="1">
                    {{ this.carProp.carType.typeName }}
                </v-col>
                <v-col align-self="center" cols="1">
                    {{ this.carProp.countOfDoors }}
                </v-col>
                <v-col align-self="center" cols="1">
                    {{ this.carProp.engineType }}
                </v-col>
                <v-col align-self="center" cols="1">
                    {{ this.carProp.engineVolume }}
                </v-col>
                <v-col align-self="center" cols="1">
                    {{ this.carProp.transmissionType }}
                </v-col>
                <v-col align-self="center" cols="1">
                    <v-btn @click="del" color="secondary" dark
                           outlined>
                        Удалить
                    </v-btn>
                </v-col>
                <v-col align-self="center" cols="1">
                    <car-update-dialog :btnTitle="this.btnTitle" :dialogTitle="this.dialogTitle"
                                       :propCar="this.carProp"/>
                </v-col>
            </v-row>
        </v-list-item>
    </div>
</template>

<script>
    import CarUpdateDialog from './car-form-dialog.vue'

    export default {
        name: "car-row",
        components: {
            'car-update-dialog': CarUpdateDialog
        },
        props: ['carProp'],
        data() {
            return {
                dialogTitle: 'Редактирование',
                btnTitle: 'Обновить',
                imageSrc: 'data:image/jpeg;base64,'
            }
        },
        created() {
            this.imageSrc += this.carProp.carMark.logo;
        }
    }
</script>

<style scoped>

</style>