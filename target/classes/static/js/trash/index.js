


function getUsers() {
    alert("ss");
    fetch("/api/users", {method: "GET", headers: {"Content-Type": "application/json"}})
        .then(
            function (value) {
                value.json()
                    .then(function (data) {
                        var users = data.map(function (user) {
                            return "<p>" + user.firstName + "</p>";
                        });
                        document.getElementById("users").innerHTML += users;
                    })
            });

}

function loadData() {
    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var email = document.getElementById("email").value;
    var address = document.getElementById("address").value;
    var password = document.getElementById("password").value;
    var telNumber = document.getElementById("telNumber").value;

    var user = {
        firstName: firstName,
        lastName: lastName,
        telNumber: telNumber,
        password: password,
        address: address,
        email: email
    };

    fetch("/api/users", {method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify(user)})
        .then(function (value) {
            alert(JSON.parse(value).firstName)
        });
}