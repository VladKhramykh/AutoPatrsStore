<template>
    <v-dialog max-height="max-height" max-width="800px" persistent v-model="dialog">
        <template v-slot:activator="{ on }">
            <v-btn color="secondary" dark
                   outlined v-on="on">{{dialogTitle}}
            </v-btn>
        </template>
        <v-card>
            <v-card-title>
                <span class="headline">{{dialogTitle}}</span>
            </v-card-title>
            <v-card-text>
                <form action="http://localhost:8081/api/car-marks" enctype="multipart/form-data" method="post">
                    <v-row>
                        <v-col cols="12">
                            <v-text-field
                                    hide-details="auto"
                                    label="Название"
                                    name="name"
                                    required
                                    v-model="propMark.name"
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12">
                            <v-file-input
                                    :rules="rules"
                                    accept="image/png, image/jpeg, image/bmp"
                                    label="Image"
                                    name="logo"
                                    placeholder="Pick an image"
                                    prepend-icon="mdi-camera"
                                    v-model="propMark.logo"
                            ></v-file-input>
                        </v-col>
                    </v-row>
                    <input class="green darken-1" type="submit" value="Send"/>
                </form>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn @click="dialog = false" color="blue darken-1" text>Close</v-btn>
                <v-btn
                        class="green darken-1"
                >
                    Save
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    export default {
        name: "car-mark-form-dialog",
        props: ['propMark', 'dialogTitle', 'btnTitle'],
        data() {
            return {
                dialog: false,
                rules: [
                    value => !value || value.size < 1000000 || 'Image size should be less than 1 MB!'
                ],
                markNameRules: [
                    value => !!value || 'Required.',
                    value => (value && value > 0) || 'Плез, проверьте название марки',
                ]
            }
        }
    }
</script>

<style scoped>

</style>