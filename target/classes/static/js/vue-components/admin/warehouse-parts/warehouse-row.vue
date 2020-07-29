<template>
    <div>
        <v-list-item>
            <v-row>
                <v-col cols="2">
                    {{ this.warehouseProp.name }}
                </v-col>
                <v-col cols="2">
                    {{ this.warehouseProp.address }}
                </v-col>
                <v-col cols="1">
                    {{ this.warehouseProp.telNumber }}
                </v-col>
                <v-col cols="3">
                    {{ this.warehouseProp.company.name }}
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
                    <warehouse-update-dialog :btnTitle="this.btnTitle" :dialogTitle="this.dialogTitle"
                                             :propType="this.warehouseProp"/>
                </v-col>
            </v-row>
        </v-list-item>
    </div>
</template>

<script>
    import WarehouseFormDialog from './warehouse-form-dialog.vue'

    export default {
        name: "warehouse-row",
        components: {
            'warehouse-update-dialog': WarehouseFormDialog
        },
        props: ['warehouseProp'],
        data() {
            return {
                dialogTitle: 'Редактирование',
                btnTitle: 'Обновить'
            }
        },
        methods: {
            del: function () {
                let url = "http://localhost:8081/api/warehouses/" + this.warehouseProp.id;
                this.$http.delete(url)
                    .then(function (data) {
                        if (!data.ok)
                            alert("Error");
                        else
                            alert("deleted")
                    })
            }
        }
    }
</script>

<style scoped>

</style>