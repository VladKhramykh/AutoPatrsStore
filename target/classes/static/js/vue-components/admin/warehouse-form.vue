<template>
    <v-container>
        <warehouse-dialog :btnTitle="this.btnTitle" :dialogTitle="dialogTitle" :propWarehouse="warehouse"/>
        <v-card
                class="mx-auto"
                max-width="auto"
                tile
        >
            <v-list>
                <v-subheader>Уже обслуживаемые типы авто</v-subheader>
                <v-list-item-group
                        color="primary"
                >
                </v-list-item-group>
                <v-list-item-content>
                    <v-list-item>
                        <v-row class="listTitle">
                            <v-col cols="2">Название</v-col>
                            <v-col cols="2">Адрес</v-col>
                            <v-col cols="2">Телефон</v-col>
                            <v-col cols="2">Компания</v-col>
                        </v-row>
                    </v-list-item>
                    <hr/>
                    <warehouse-row :warehouseProp="warehouse" v-for="warehouse in warehouses"/>
                </v-list-item-content>

            </v-list>
        </v-card>
    </v-container>
</template>

<script>
    import WarehouseRow from "./warehouse-parts/warehouse-row.vue"
    import WarehouseFormDialog from "./warehouse-parts/warehouse-form-dialog.vue"

    export default {
        name: "warehouse-form",
        components: {
            "warehouse-row": WarehouseRow,
            "warehouse-dialog": WarehouseFormDialog
        },
        data() {
            return {
                warehouse: {
                    id: '',
                    name: '',
                    address: '',
                    telNumber: '',
                    company: {
                        id: '',
                        directorName: '',
                        officeAddress: ''
                    },
                    parts: []
                },
                warehouses: [],
                dialogTitle: 'Новый склад',
                btnTitle: 'Добавить'
            }
        },
        methods: {
            editMethod(type) {
                this.type = type;
            }
        },
        created() {
            this.$http.get("http://localhost:8081/api/warehouses")
                .then(function (data) {
                    if (data.ok) {
                        this.warehouses = data.body;
                    } else {
                        alert(data.statusText);
                    }
                })
        }
    }
</script>

<style scoped>

</style>