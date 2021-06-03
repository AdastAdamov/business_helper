function loginFunc() {
    var loginPanel = document.getElementById("loginPanel");
    var passwordPanel = document.getElementById("passwordPanel");
    var loginErrorMessage = document.getElementById("loginErrorMessage");
    var passwordErrorMessage = document.getElementById("passwordErrorMessage");

    resetState();

    $.ajax({
        type: "POST",
        url : '/login',
        data : {
                login : document.getElementById('login').value,
                password : document.getElementById('password').value
        },
        success : function(response) {
            if (response.isAuthorized == true) {
                document.getElementById('dashboardForm').submit()
            }
            else if (response.errorCode == 1) {
                loginErrorMessage.textContent = response.reason;
                loginErrorMessage.hidden = false;
                loginPanel.classList.remove("mb-4");
            }
            else if (response.errorCode == 2) {
                passwordErrorMessage.textContent = response.reason;
                passwordErrorMessage.hidden = false;
                passwordPanel.classList.remove("mb-4");
            }
        }
    });
}

function resetState() {
    loginErrorMessage.hidden = true;
    passwordErrorMessage.hidden = true;
    loginPanel.classList.add("mb-4");
    passwordPanel.classList.add("mb-4");
}