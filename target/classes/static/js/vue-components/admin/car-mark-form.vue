<template>
    <v-container>
        <car-mark-dialog :btnTitle="this.btnTitle" :dialogTitle="this.dialogTitle" :propMark="this.mark"/>
        <v-card
                class="mx-auto"
                max-width="auto"
                tile
        >
            <v-list>
                <v-subheader>Уже обслуживаемые марки</v-subheader>
                <v-list-item-group
                        color="primary"
                >
                </v-list-item-group>
                <v-list-item-content>
                    <v-list-item>
                        <v-row class="listTitle">
                            <v-col cols="1">Логотип</v-col>
                            <v-col cols="2">Название</v-col>
                        </v-row>
                    </v-list-item>
                    <hr />
                    <car-mark-row :markProp="mark" v-for="mark in marks"/>
                </v-list-item-content>

            </v-list>
        </v-card>
    </v-container>
</template>

<script>
    import CarMarkDialog from './car-marks-parts/car-mark-form-dialog.vue';
    import CarRow from './car-marks-parts/car-mark-row.vue'

    export default {
        name: "car-mark-form",
        components: {
            'car-mark-dialog': CarMarkDialog,
            'car-mark-row': CarRow
        },
        data() {
            return {
                rules: [
                    value => !value || value.size < 1000000 || 'Image size should be less than 1 MB!'
                ],
                mark: {
                    id: '',
                    name: '',
                    logo: ''
                },
                dialog: false,
                marks: [],
                markNameRules: [
                    value => !!value || 'Required.',
                    value => (value && value < 6 && value > 0) || 'плез, проверьте количество дверей',
                ],
                dialogTitle: "Добавить марку авто",
                btnTitle: "Добавить"
            }
        },
        created() {
            this.$http.get("http://localhost:8081/api/car-marks")
                .then(function (data) {
                    if(data.ok)
                        this.marks = data.body;
                    else
                        alert("Error");
                })
        }
    }
</script>

<style scoped>

</style>