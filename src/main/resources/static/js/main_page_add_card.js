function submitNewCard() {
    var bankSelect = document.getElementById('banks');
    var productSelect = document.getElementById('products');
    var lastOperationDateInput = document.getElementById('last_operation_date_input');

    $.ajax({
            type: "POST",
            url : '/user_products/add_card',
            data : {
                    productId : productSelect.value,
                    lastOperationDate : lastOperationDateInput.value,
            },
            success : function(response) {

            }
        });
}

function backToCards() {
    window.parent.document.getElementById('inner_window').src = 'main_page_cards';
}

function getProductsFunc() {
    var productSelect = document.getElementById('products');
    var bankSelect = document.getElementById('banks');

    $.ajax({
        type: "GET",
        url : '/user_products',
        data : {
            bankName : bankSelect.value,
        },
        success : function(response) {
            productSelect.innerHTML = "";
            for(i in response)
            {
               var option = document.createElement("option");
               option.value = response[i].id;
               option.text = response[i].tariffName;

               productSelect.appendChild(option);
            }
        }
    });
}