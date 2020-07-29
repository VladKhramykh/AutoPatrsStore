<template>
    <div>
        <v-list-item>
            <v-row v-if="this.partProp">
                <v-col cols="1">
                    {{ this.partProp.id }}
                </v-col>
                <v-col cols="2">
                    {{ this.partProp.name }}
                </v-col>
                <v-col cols="1">
                    {{ this.partProp.price }}
                </v-col>
                <v-col cols="1">
                    {{ this.partProp.partType.name }}
                </v-col>
                <v-col cols="1">
                    {{ this.partProp.partSubtype.name }}
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
                    <part-update-dialog :btnTitle="this.btnTitle" :dialogTitle="this.dialogTitle"
                                             :propPart="this.partProp"/>
                </v-col>
            </v-row>
            <v-row v-if="!partProp">
                Пусто
            </v-row>
        </v-list-item>
    </div>
</template>

<script>
    import PartFormDialog from './part-form-dialog.vue'

    export default {
        name: "part-row",
        components: {
            'part-update-dialog': PartFormDialog
        },
        props: ['partProp'],
        data() {
            return {
                dialogTitle: 'Редактирование',
                btnTitle: 'Обновить'
            }
        },
        methods: {
            del: function () {
                let url = "http://localhost:8081/api/car-parts/" + this.partProp.id;
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