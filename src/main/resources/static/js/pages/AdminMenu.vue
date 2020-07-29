<template>
    <v-container>
        <v-tabs
                class="elevation-4"
                flat
        >

            <v-tab :href="`#tab-${1}`" :key="1">Типы авто</v-tab>
            <v-tab :href="`#tab-${2}`" :key="2">Марки авто</v-tab>
            <v-tab :href="`#tab-${3}`" :key="3">Авто</v-tab>
            <v-tab :href="`#tab-${4}`" :key="4">Категории запчастей</v-tab>
            <v-tab :href="`#tab-${5}`" :key="5">Подкатегории запчастей</v-tab>
            <v-tab :href="`#tab-${6}`" :key="6">Запчасти</v-tab>
            <v-tab :href="`#tab-${7}`" :key="7">Склады</v-tab>

            <v-tab-item :key="1" :value="'tab-' + 1">
                <car-types></car-types>
            </v-tab-item>

            <v-tab-item :key="2" :value="'tab-' + 2">
                <car-marks></car-marks>
            </v-tab-item>

            <v-tab-item :key="3" :value="'tab-' + 3">
                <cars></cars>
            </v-tab-item>

            <v-tab-item :key="4" :value="'tab-' + 4">
                <part-types></part-types>
            </v-tab-item>

            <v-tab-item :key="5" :value="'tab-' + 5">
                <part-subtypes></part-subtypes>
            </v-tab-item>

            <v-tab-item :key="6" :value="'tab-' + 6">
                <parts></parts>
            </v-tab-item>

            <v-tab-item :key="7" :value="'tab-' + 7">
                <warehouses></warehouses>
            </v-tab-item>
        </v-tabs>
    </v-container>
</template>

<script>
    import CarType from '../vue-components/admin/car-type-form.vue'
    import CarMark from '../vue-components/admin/car-mark-form.vue'
    import Car from '../vue-components/admin/car-form.vue'
    import PartType from '../vue-components/admin/part-type-form.vue'
    import PartSubtype from '../vue-components/admin/part-subtype-form.vue'
    import Part from '../vue-components/admin/part-form.vue'
    import Warehouse from '../vue-components/admin/warehouse-form.vue'

    export default {
        name: "AdminMenu",
        components: {
            "car-types": CarType,
            "car-marks": CarMark,
            "cars": Car,
            "part-types": PartType,
            'part-subtypes': PartSubtype,
            "parts": Part,
            "warehouses": Warehouse
        }
    }
</script>

<style>
    .listTitle {
        font-size: 12px;
        font-weight: bold;
    }
</style>