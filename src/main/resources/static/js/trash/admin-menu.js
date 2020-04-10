// window.onload = function (ev) {
//     // var carTypes = document.getElementById("car-types").innerHTML;
//     // var carMarks = document.getElementById("car-marks").innerHTML;
//
//     fetch("/api/car-types", {method: "GET", headers: {"Content-type": "application/json"}})
//         .then(function (value) {
//             value.json().then(function (data) {
//                 var str = data.map(function (item) {
//                     return "<p>" + item.typeName + "</p>"
//                 });
//                 document.getElementById("car-types").innerHTML += str;
//             })
//         });
// };

function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}


var typesApi = Vue.resource('/api/car-types{/id}');

Vue.component('type-form', {
    props: ['types', 'typeAttr'],
    data: function() {
        return {
            typeName: '',
            id: ''
        }
    },
    watch: {
        typeAttr: function(newVal, oldVal) {
            this.typeName = newVal.typeName;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Write something" v-model="typeName" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var type = { typeName: this.typeName };

            if (this.id) {
                typesApi.update({id: this.id}, type).then(result =>
                result.json().then(data => {
                    var index = getIndex(this.types, data.id);
                this.types.splice(index, 1, data);
                this.typeName = ''
                this.id = ''
            })
            )
            } else {
                typesApi.save({}, type).then(result =>
                result.json().then(data => {
                    this.types.push(data);
                this.typeName = ''
            })
            )
            }
        }
    }
});

Vue.component('type-row', {
    props: ['type', 'editMethod', 'types'],
    template: '<div>' +
        '<i>({{ type.id }})</i> {{ type.typeName }}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" v-on:click="edit" />' +
        '<input type="button" value="X" v-on:click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.type);
        },
        del: function() {
            typesApi.remove({id: this.type.id}).then(result => {
                if (result.ok) {
                    this.types.splice(this.types.indexOf(this.type), 1)
                }
        })
        }
    }
});

Vue.component('car-types-list', {
    props: ['types'],
    data: function() {
        return {
            type: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<type-form :types="types" :typeAttr="type" />' +
        '<type-row v-for="type in types" :key="type.id" :type="type" ' +
        ':editMethod="editMethod" :types="types" />' +
        '</div>',
    created: function() {
        typesApi.get().then(result =>
        result.json().then(data =>
        data.forEach(type => this.types.push(type))
    )
    )
    },
    methods: {
        editMethod: function(type) {
            this.type = type;
        }
    }
});

var app = new Vue({
    el: '#car-types',
    template: '<car-types-list :types="types" />',
    data: {
        types: []
    }
});


// function submitCarMark() {
//     var form = document.forms.carMarkForm;
//     var carMarkName = form.elements.name;
//     var carMarkLogo = form.elements.logo;
//
//     var carMark = {
//         name: carMarkName,
//         logo: carMarkLogo
//     };
//
//     fetch("/api/car-marks", {
//         method: "POST",
//         headers: {"Content-type": "application/json"},
//         body: JSON.stringify(carMark)
//     })
//         .then(function (value) {
//             value
//                 .json()
//                 .then(function (data) {
//
//                 })
//         })
// }
//
// function submitCarType() {
//     var form = document.forms.carMarkType;
//     var carMarkName = form.elements.typeName.value;
//     if (carMarkName.length === 0) {
//         alert("This field can not be empty");
//         return;
//     }
//
//     var carType = {
//         typeName: carMarkName
//     };
//     fetch("/api/car-types", {
//         method: "POST",
//         headers: {"Content-type": "application/json"},
//         body: JSON.stringify(carType)
//     })
//         .then(function (value) {
//             value
//                 .json()
//                 .then(function (data) {
//                     alert(data.typeName + " created");
//                 })
//         })
// }