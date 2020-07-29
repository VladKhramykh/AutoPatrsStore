<template>
    <v-container>
        <part-subtype-form-dialog :btnTitle="btnTitle" :dialogTitle="dialogTitle" :propType="this.subtype"/>
        <v-card
                class="mx-auto"
                max-width="auto"
                tile
        >
            <v-list>
                <v-subheader>Уже обслуживаемые подкатегории запчастей</v-subheader>
                <v-list-item-group
                        color="primary"
                >
                </v-list-item-group>
                <v-list-item-content>
                    <v-list-item>
                        <v-row class="listTitle">
                            <v-col cols="2">Название подкатегории</v-col>
                            <v-col cols="2">Название категории</v-col>
                        </v-row>
                    </v-list-item>
                    <part-subtype-row :typeProp="type" v-for="type in subtypes"/>
                </v-list-item-content>

            </v-list>
        </v-card>
    </v-container>
</template>

<script>
    import PartSubtypeDialog from './part-subtypes-parts/part-subtype-form-dialog.vue'
    import PartSubtypeRow from './part-subtypes-parts/part-subtype-row.vue'

    export default {
        name: "part-subtype-form",
        components: {
            'part-subtype-form-dialog': PartSubtypeDialog,
            'part-subtype-row': PartSubtypeRow
        },
        data() {
            return {
                subtype: {
                    id: '',
                    name: '',
                    partType: {
                        id: '',
                        name: ''
                    }
                },
                subtypes: [],
                dialogTitle: 'Создание новой подкатегории запчастей',
                btnTitle: 'Добавить'
            }
        },
        created() {


            this.$http.get("http://localhost:8081/api/car-parts-subtypes")
                .then(function (data) {
                    if (data.ok)
                        this.subtypes = data.body;
                });
        }
    }
</script>

<style scoped>

</style>