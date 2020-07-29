<template>
    <v-container>
        <part-form-dialog :btnTitle="this.btnTitle" :dialogTitle="dialogTitle" :propPart="part"/>
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
                            <v-col cols="1">Номер</v-col>
                            <v-col cols="2">Название</v-col>
                            <v-col cols="1">Цена (бел.руб)</v-col>
                            <v-col cols="1">Категория</v-col>
                            <v-col cols="1">Подкатегория</v-col>

                        </v-row>
                    </v-list-item>
                    <hr/>
                    <part-row :partProp="part" v-for="part in parts"/>
                </v-list-item-content>

            </v-list>
        </v-card>
    </v-container>
</template>

<script>
    import PartRow from "./part-parts/part-row.vue"
    import PartFormDialog from "./part-parts/part-form-dialog.vue"

    export default {
        name: "part-form",
        components: {
            "part-row": PartRow,
            "part-form-dialog": PartFormDialog
        },
        data() {
            return {
                part: {
                    id: '',
                    name: '',
                    price: '',
                    partType: {
                        id: '',
                        name: ''
                    },
                    partSubtype: {
                        id: '',
                        name: ''
                    },
                    cars: [],
                    warehouses: []
                },
                parts: [],
                dialogTitle: 'Добавление новой запчасти',
                btnTitle: 'Добавить'
            }
        },
        created() {
            this.$http.get("http://localhost:8081/api/car-parts")
                .then(function (data) {
                    if (data.ok) {
                        this.parts = data.body;
                    } else {
                        alert(data.statusText);
                    }
                })
        }
    }
</script>

<style scoped>

</style>