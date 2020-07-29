<template>
    <div>
        <v-list-item>
            <v-row>
                <v-col cols="2">
                    {{ this.typeProp.name }}
                </v-col>
                <v-col cols="6">
                    {{ this.typeProp.partType.name }}
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
                    <part-type-update-dialog :btnTitle="this.btnTitle" :dialogTitle="this.dialogTitle"
                                             :propType="this.typeProp"/>
                </v-col>
            </v-row>
        </v-list-item>
    </div>
</template>

<script>
    import PartSubtypeFormDialog from './part-subtype-form-dialog.vue'

    export default {
        name: "part-type-row",
        components: {
            'part-type-update-dialog': PartSubtypeFormDialog
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
                let url = "http://localhost:8081/api/car-parts-subtypes/" + this.typeProp.id;
                this.$http.delete(url)
                    .then(function (data) {
                        if (!data.ok)
                            alert("Error");
                        else
                            alert("deleted");
                    })
            }
        }
    }
</script>

<style scoped>

</style>