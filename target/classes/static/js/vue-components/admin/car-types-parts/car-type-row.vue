<template>
    <div>
        <v-list-item>
            <v-row>
                <v-col cols="8">
                    {{ this.typeProp.typeName }}
                </v-col>
                <v-col cols="2">
                    <v-btn
                            @click="del"
                            color="secondary" dark
                            outlined
                    >
                        Удалить
                    </v-btn>
                </v-col>
                <v-col cols="2">
                    <car-type-update-dialog :btnTitle="this.btnTitle" :dialogTitle="this.dialogTitle"
                                            :propType="this.typeProp"/>
                </v-col>
            </v-row>
        </v-list-item>
    </div>
</template>

<script>
    import CarTypeFormDialog from './car-type-form-dialog.vue'

    export default {
        name: "car-type-row",
        components: {
            'car-type-update-dialog': CarTypeFormDialog
        },
        props: ['typeProp'],
        data() {
            return {
                dialogTitle: 'Редактирование',
                btnTitle: 'Обновить'
            }
        },
        methods: {
            del: function () {
                let url = "http://localhost:8081/api/car-types/" + this.typeProp.id;
                this.$http.delete(url)
                    .then(function (data) {
                        if (!data.ok)
                            alert("Error");
                    })
            }
        }
    }
</script>

<style scoped>

</style>