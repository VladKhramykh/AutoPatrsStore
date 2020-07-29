<template>
    <v-container class="flex-wrap">
        <v-card

                loading="true"
                max-width="450"
                tile
                v-for="part in parts"
                v-model="parts"

        >
            <v-list-item three-line>
                <v-list-item-content>
                    <div class="overline mb-4">{{ part.partType.name }}</div>
                    <v-list-item-title class="headline">{{ part.name }}</v-list-item-title>
                    <v-list-item-subtitle>{{ part.price }} бел.р</v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-avatar
                        color="grey"
                        size="80"
                        tile
                ></v-list-item-avatar>
            </v-list-item>

            <v-card-actions>
                <v-btn text>В корзину</v-btn>
            </v-card-actions>
        </v-card>
    </v-container>
</template>

<script>
    export default {
        name: "parts-list",
        props: ['parts'],
        data() {
            return {}
        }
    }
</script>

<style scoped>

</style>