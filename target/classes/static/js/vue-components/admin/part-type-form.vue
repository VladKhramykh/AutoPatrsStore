<template>
    <v-container>
        <part-type-dialog :btnTitle="this.btnTitle" :dialogTitle="this.dialogTitle" :propType="this.type"/>
        <v-card
                class="mx-auto"
                max-width="auto"
                tile
        >
            <v-list>
                <v-subheader>Уже обслуживаемые типы авто</v-subheader>
                <v-list-item-group
                        color="secondary"
                >
                </v-list-item-group>
                <v-list-item-content>
                    <v-list-item>
                        <v-row class="listTitle">
                            <v-col cols="1">Название категории</v-col>
                        </v-row>
                    </v-list-item>
                    <part-type-row :typeProp="type" v-for="type in types"/>
                </v-list-item-content>

            </v-list>
        </v-card>
    </v-container>
</template>

<script>
    import PartTypeDialog from './part-types-parts/part-type-form-dialog.vue'
    import PartTypeRow from './part-types-parts/part-type-row.vue'

    export default {
        name: "part-type-form",
        components: {
            'part-type-dialog': PartTypeDialog,
            'part-type-row': PartTypeRow
        },
        data() {
            return {
                types: [],
                type: {
                    id: '',
                    name: ''
                },
                rules: [
                    value => !!value || 'Required.',
                    value => (value && value.length >= 1) || 'Min 1 characters',
                ],
                dialogTitle: 'Добавить новую категорию запчастей',
                btnTitle: 'Добавить',
                dialog: false
            }
        },
        created() {
            this.$http.get("http://localhost:8081/api/car-parts-types")
                .then(function (data) {
                    if (data.status === 200) {
                        this.types = data.body;
                    } else {
                        alert("Error: " + data.statusMessage)
                    }
                });
        }
    }
</script>

<style scoped>

</style>