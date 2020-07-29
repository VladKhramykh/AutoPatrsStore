<template>

    <v-container>
        <car-type-form-dialog :btnTitle="this.btnTitle" :dialogTitle="dialogTitle" :propType="type"/>
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
                        </v-row>
                    </v-list-item>
                    <hr/>
                    <car-type-row :typeProp="type" v-for="type in types"/>
                </v-list-item-content>

            </v-list>
        </v-card>
    </v-container>

</template>

<script>
    import CarTypeRow from "./car-types-parts/car-type-row.vue"
    import CarTypeFormDialog from "./car-types-parts/car-type-form-dialog.vue"

    export default {
        name: "car-type-form",
        components: {
            "car-type-row": CarTypeRow,
            "car-type-form-dialog": CarTypeFormDialog
        },
        data() {
            return {
                type: {
                    id: '',
                    typeName: ''
                },
                types: [],
                dialogTitle: 'Создание нового типа авто',
                btnTitle: 'Добавить'
            }
        },
        methods: {
            editMethod(type) {
                this.type = type;
            }
        },
        created() {
            this.$http.get("http://localhost:8081/api/car-types")
                .then(function (data) {
                    if (data.ok) {
                        this.types = data.body;
                    } else {
                        alert(data.statusText);
                    }
                })
        }
    }
</script>

<style scoped>

</style>