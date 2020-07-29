<template>
    <v-container>
        <v-card>
            <v-card-title>Профиль</v-card-title>
            <v-card-text>
                <v-col cols="12" sm="6">
                    <v-text-field
                            v-model="current.firstName"
                            label="First Name"
                            outlined
                            shaped
                    ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                    <v-text-field
                            v-model="current.lastName"
                            label="Last Name"
                            outlined
                            shaped
                    ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                    <v-text-field
                            v-model="current.email"
                            label="Email"
                            outlined
                            shaped
                    ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                    <v-text-field
                            v-model="current.telNumber"
                            label="Phone number"
                            outlined
                            shaped
                    ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                    <v-text-field
                            v-model="current.address"
                            label="Address"
                            outlined
                            shaped
                    ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                    <v-text-field
                            v-model="current.password"
                            label="Password"
                            outlined
                            shaped
                    ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                    <v-btn class="ma-2" outlined color="indigo" v-on:click="update">Сохранить</v-btn>
                </v-col>
                <v-col cols="12" sm="6">
                    <div>Так же вы можете <a id="deleteButton" v-on:click="remove()">удалить</a> свой профиль</div>
                </v-col>
            </v-card-text>
        </v-card>


    </v-container>
</template>

<script>
    import {mapState, mapActions} from 'vuex'

    export default {
        name: "Profile",
        computed: mapState(['profile']),
        data() {
            return {
                current: {
                    id: 0,
                    firstName: '',
                    lastName: '',
                    address: '',
                    telNumber: '',
                    email: '',
                    password: '',
                    activationCode: ''
                }
            }

        },
        created() {
            if(this.profile) {
                this.current.id = this.profile.id;
                this.current.firstName = this.profile.firstName;
                this.current.lastName = this.profile.lastName;
                this.current.address = this.profile.address;
                this.current.telNumber = this.profile.telNumber;
                this.current.email = this.profile.email;
                this.current.password = this.profile.password;
                this.current.activationCode = this.profile.activationCode;
            }

        },
        methods: {
            ...mapActions(['removeUserAction', 'updateUserAction']),
            remove: function () {
                this.removeUserAction(this.current);
                window.location.href="/logout";
            },
            update: function () {
                this.updateUserAction(this.current);
            }

        },

    }
</script>

<style scoped>

</style>