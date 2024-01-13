let orderItems = [];
let amount = 0;


function handleRadioClick() {
    const radioButtons = document.querySelectorAll('.radioBtn');
    const items = document.querySelectorAll('.order');

    radioButtons.forEach(button => {
        button.addEventListener('click', () => {
            items.forEach(item => {
                item.classList.remove('selected');

            });
        });
    });
}
function addToOrder(data) {
    const items = document.querySelectorAll('.order');
    console.log("loaded");
    let id;
    items.forEach(item => {
        item.addEventListener('click', () => {
            id = item.getAttribute("data-value");
            let x = getItem(id, data);
            orderItems.push(x);
        });
    });
}
function getItem(id, data) {
    let orderPanel = document.querySelector('#orderItem');
    let itemList = JSON.parse(JSON.stringify(data));
    var item;
    for (let x of itemList.list) {
        if (x.id == id) {
            item = x;
            break;
        }
    }
    let code = '<div class="item">' +
            '<div class="image"><img src="data:' + item.content + ';base64,' + item.imageData + '" alt=""></div>' +
            '<div class="name">' + item.name + '</div>' +
            '<div class="price">RM ' + item.price.toFixed(2) + '</div>' +
            '<div class="minus new">-</div>' +
            '<div class="qty">' + item.qty + '</div>' +
            '<div class="add new">+</div>' +
            '</div>';
    orderPanel.insertAdjacentHTML('beforeend', code);
    activateQtyButton("minus");
    activateQtyButton("add");
    return item;
}
function loadOrder() {
    let object = document.getElementById('sideDetail');
    let code = '<h2>Current Order</h2>' +
            '<div class="itemcover" id="orderItem">' +
            '</div>' +
            '<div class="wrapButton">' +
            '<div>' +
            '<label>Phone no</label>'+
            '<input name="phoneno" type="text" id="phoneno" required>'+
            '</div>' +
            '<button onclick="loadPayment()">Confirm Order</button>' +
            '</div>';
    object.innerHTML = code;
}
function payItem() {

    let code = "";
    for (let x of orderItems) {
        let append = '<div class="item">' +
                '<div class="image"><img src="data:' + x.content + ';base64,' + x.imageData + '" alt=""></div>' +
                '<div class="name">' + x.name + '</div>' +
                '<div class="price">RM ' + x.price.toFixed(2) + '</div>' +
                '<div class="qty">x ' + x.qty + '</div>' + '</div>';
        code += append;
        amount += x.price * x.qty;
    }
    return code;
}
function loadPayment() {
    /*disable add to order*/
    var clickfn = function () { };
    let orderItems = document.querySelectorAll('.order');
    orderItems.forEach(orderItem => {
        orderItem.removeEventListener('click', clickfn);
    });
    let object = document.getElementById('sideDetail');
    let phoneno = document.getElementById('phoneno').value;
    object.className = 'sideDetail2';
    let code = '<h2>Payment</h2>' +
            '<h4># 1435</h4>' +
            '<div class="itemcover">' +
            payItem() +
            '</div>' +
            '<div class="calculation">' +
            '<div>' +
            '<p>Subtotal</p>' +
            '<p>RM ' + amount.toFixed(2) + '</p>' +
            '<p>GST %6</p>' +
            '<p>-RM ' + (0.06 * amount).toFixed(2) + '</p>' +
            '<p><b>Total</b></p>' +
            '<p><b>RM ' + (amount - 0.06 * amount).toFixed(2) + '</b></p>' +
            '</div>' +
            '</div>' +
            '<div class="wrapButton">' +
                '<div>' +
                    '<form action="orderServlet" method="post" enctype="multipart/form-data">'+
                    '<input type="hidden" name="orderData" id="orderData"/>'+
                    '<input type="hidden" name="phoneno" id="phoneno"/>'+
                    '<input type="hidden" name="orderAmount" id="orderAmount"/>'+
                    '<input type="hidden" name="method" id="method"/>'+
                    '<input type="hidden" name="operation" value="addOrder"/>'+
                    '<input type="hidden" name="cookid" value="1108"/>'+
                    '<div class="buttonCover" id="first">' +
                    '<input type="text" id="Cash" name="Cash" value="Cash" readonly>' +
                    '<label for="Online" id="OnlineLabel">'+
                    '<p>E-Wallet</p>'+
                    '<input type="file" name="Online" id="Online" style="display:none">' +
                    '</label>'+
                    '</div>' +
                    '<div class="buttonCover" id="second">' +
                    '<input type="submit" value="Approve">' +
                    '<div id="cancelOrder"><p>Cancel</p></div>' +
                    '</div>' +
                    '</form>'+
                '</div>' +
            '</div>';
    object.innerHTML = code;
    paymentMethod();
    cancelOrder();
    approveOrder(phoneno);

}

function activateQtyButton(operation) {
    let object;
    if (operation == "minus"){
        object = document.querySelector('.item .minus.new');
    }
    else{
        object = document.querySelector('.item .add.new');
    }
    
    object.addEventListener('click', event => {
        const clickedButton = event.target;
        if (operation == "minus")
            minusQty(clickedButton);
        else
            addQty(clickedButton);
    });
    object.classList.remove('new');
}

function addQty(parent) {
    var qtyVal = parent.previousElementSibling;
    let name = parent.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling;
    var num = parseInt(qtyVal.innerHTML, 10);
    qtyVal.innerHTML = "";
    num += 1;
    qtyVal.innerHTML = num;
    let itemIndex = findItem(name.innerHTML);
    orderItems[itemIndex].qty = num;
}


function minusQty(parent) {
    var qtyVal = parent.nextElementSibling;
    let name = parent.previousElementSibling.previousElementSibling;
    var num = parseInt(qtyVal.innerHTML, 10);
    qtyVal.innerHTML = "";
    var index = findItem(name.innerHTML);
    if (num > 1) {
        num -= 1;
        orderItems[index].qty = num;
        qtyVal.innerHTML = num;
    } else {
        parent.parentNode.remove();
        orderItems.splice(index, 1);
    }
}

function findItem(name) {
    var index = -1;
    for (let x of orderItems) {
        index += 1;
        if (x.name == name) {
            break;
        }
    }
    return index;
}

function cancelOrder() {
    let button = document.getElementById('cancelOrder');
    button.addEventListener('click', () => {
        let object = document.getElementById('sideDetail');
        object.className = 'sideDetail';
        loadOrder();
        orderItems.splice(0, orderItems.length);
        amount = 0;
    });
}

function approveOrder(phoneno){
    let field1 = document.getElementById('orderData');
    let field2 = document.getElementById('orderAmount');
    let field3 = document.getElementById('phoneno');
    field1.value = JSON.stringify(orderItems);
    field2.value = amount;
    field3.value = phoneno;
}

function paymentMethod(){
    let cash = document.getElementById('Cash');
    let online = document.getElementById('Online');
    let method = document.getElementById('method');
    
    cash.addEventListener('click',()=>{
        method.value = "cash";
    });
    online.addEventListener('click',()=>{
        method.value = "online";
    });
}