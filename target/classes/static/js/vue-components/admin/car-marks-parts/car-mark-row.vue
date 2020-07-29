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
                <v-col align-self="center" cols="7">
                    {{ this.markProp.name }}
                </v-col>

                <v-col align-self="center" cols="2">
                    <v-btn
                            @click="del"
                            color="secondary" dark
                            outlined
                    >
                        Удалить
                    </v-btn>
                </v-col>
                <v-col align-self="center" cols="2">
                    <car-mark-update-dialog :btnTitle="this.btnTitle" :dialogTitle="this.dialogTitle"
                                            :propMark="this.markProp"/>
                </v-col>
            </v-row>
        </v-list-item>
    </div>
</template>

<script>
    import CarMarkFormDialog from './car-mark-form-dialog.vue'

    export default {
        name: "car-mark-row",
        components: {
            'car-mark-update-dialog': CarMarkFormDialog
        },
        props: ['markProp'],
        data() {
            return {
                dialogTitle: 'Редактирование',
                btnTitle: 'Обновить',
                imageSrc: 'data:image/jpeg;base64,'
            }
        },
        created() {
            this.imageSrc += this.markProp.logo;
        },
        methods: {
            del: function () {
                let url = "http://localhost:8081/api/car-marks/" + this.markProp.id;
                this.$http.delete(url)
                    .then(function (data) {
                        if (!data.ok)
                            alert("Error");
                    });
                Location.reload();
            }
        }
    }
</script>

<style scoped>
    * {
        font-size: 20px;
    }
</style>