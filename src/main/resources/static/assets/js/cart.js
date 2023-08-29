
const products = JSON.parse(localStorage.getItem("products"));


const userId = +document.getElementById("id_user").value;
console.log(userId)

const getAllProducts = () => {
    $.ajax({
        url: "/api/products",
        method: "GET",
    }).done((data) => {
        localStorage.setItem(`products`, JSON.stringify(data))
    })
}

getAllProducts();
const getCart = (userId) => {
    const cartKey = `cart_${userId}`;
    return JSON.parse(localStorage.getItem(cartKey)) || [];
}

const renderCart = (userId) => {
    const cart = getCart(userId);
    console.log(cart)
    const cartBody = document.getElementById("cart-body");
    cartBody.innerHTML = "";
    let str = "";
    cart.forEach(billdetail => {
        if (billdetail.product.user.id === userId){
        str += `<tr class="table-body-row" id="bill-item-${billdetail.product.id}">
                    <td class="product-remove"><button type="button" onclick="removeItem(${billdetail.product.id})" style="border: none; background:none;"><i class="far fa-window-close"></i></button></i></a></td>
                    <td class="product-image"><img src="/img/products/product-img-1.jpg" alt=""></td>
                    <td class="product-name">${billdetail.product.name}</td>
                    <td class="product-price">${billdetail.product.price} VND</td>
                    <td class="product-quantity"><input id="quantity-${billdetail.product.id}" min="1" type="number" value="${billdetail.quantity}" onchange="updateProductTotal(this, ${billdetail.product.price}),renderTotal(userId)"></td>
                    <td class="product-total">${billdetail.product.price} VND</td>
                </tr>`
        }
    })
    cartBody.innerHTML = str;
}
renderCart(userId);

const removeItem = (idProduct) => {
    const cartKey = `cart_${userId}`;
    const cart = getCart(userId);

    const productIndex = cart.findIndex(product => product.id === idProduct);

    if (productIndex !== -1) {
        cart.splice(productIndex, 1);
        localStorage.setItem(cartKey, JSON.stringify(cart));
        renderCart(userId);
    }
}
const updateProductTotal = (inputElement, price) => {
    const newQuantity = parseInt(inputElement.value);
    const productTotalElement = inputElement.parentNode.nextElementSibling;

    const newTotal = price * newQuantity;

    productTotalElement.textContent = newTotal +" VND";
};
const getAllProvinces = () => {
    return $.ajax({
        url: 'https://vapi.vnappmob.com/api/province/'
    });
};

const getAllDistrictsByProvinceId = (provinceId) => {
    return $.ajax({
        url: 'https://vapi.vnappmob.com/api/province/district/' + provinceId
    });
};

const getAllWardsByDistrictId = (districtId) => {
    return $.ajax({
        url: 'https://vapi.vnappmob.com/api/province/ward/' + districtId
    });
};

const renderOptionProvince = (obj) => {
    return `<option value="${obj.province_id}">${obj.province_name}</option>`;
};

const renderOptionDistrict = (obj) => {
    return `<option value="${obj.district_id}">${obj.district_name}</option>`;
};

const renderOptionWard = (obj) => {
    return `<option value="${obj.ward_id}">${obj.ward_name}</option>`;
};

const loadData = async () => {
    try {
        const provincesResponse = await getAllProvinces();
        const provinces = provincesResponse.results;

        const provinceSelect = document.getElementById("provinceCre");
        const districtSelect = document.getElementById("districtCre");
        const wardSelect = document.getElementById("wardCre");

        provinceSelect.innerHTML = provinces.map(renderOptionProvince).join("");

        // Lấy province_id của đối tượng đầu tiên
        const defaultProvinceId = provinces[0].province_id;

        const districtsResponse = await getAllDistrictsByProvinceId(defaultProvinceId);
        const districts = districtsResponse.results;

        districtSelect.innerHTML = districts.map(renderOptionDistrict).join("");

        // Lấy district_id của đối tượng đầu tiên
        const defaultDistrictId = districts[0].district_id;

        const wardsResponse = await getAllWardsByDistrictId(defaultDistrictId);
        const wards = wardsResponse.results;

        wardSelect.innerHTML = wards.map(renderOptionWard).join("");

        // Thêm sự kiện onchange cho các ô select
        provinceSelect.addEventListener("change", async () => {
            const selectedProvinceId = provinceSelect.value;

            // Cập nhật danh sách quận/huyện
            const districtsResponse = await getAllDistrictsByProvinceId(selectedProvinceId);
            const districts = districtsResponse.results;
            districtSelect.innerHTML = districts.map(renderOptionDistrict).join("");

            // Lấy district_id của đối tượng đầu tiên
            const defaultDistrictId = districts[0].district_id;

            // Cập nhật danh sách phường/xã
            const wardsResponse = await getAllWardsByDistrictId(defaultDistrictId);
            const wards = wardsResponse.results;
            wardSelect.innerHTML = wards.map(renderOptionWard).join("");
        });

        districtSelect.addEventListener("change", async () => {
            const selectedDistrictId = districtSelect.value;

            // Cập nhật danh sách phường/xã
            const wardsResponse = await getAllWardsByDistrictId(selectedDistrictId);
            const wards = wardsResponse.results;
            wardSelect.innerHTML = wards.map(renderOptionWard).join("");
        });

    } catch (error) {
        console.log(error);
    }
};


loadData();

// document.getElementById("checkoutForm").addEventListener("submit", (event) =>{
//     event.preventDefault();
//     const form = document.getElementById("checkoutForm");
//     const description = document.getElementById("floatingTextarea").value;
//     const provinceSelect = document.getElementById("provinceCre");
//     const province = provinceSelect.options[provinceSelect.selectedIndex].textContent;
//     const districtSelect = document.getElementById("districtCre");
//     const district = districtSelect.options[districtSelect.selectedIndex].textContent;
//     const wardSelect = document.getElementById("wardCre");
//     const ward = wardSelect.options[wardSelect.selectedIndex].textContent;
//     const addressDetail = document.getElementById("addressCre").value;
//     const address = addressDetail + "," + ward + "," + district + "," + province;
//     console.log(address)
//     const formData = new FormData(form);
//     const data = Object.entries(formData.entries());
//     const cart = getCart(userId);
//     const billDetail = [];
//     let billTotal = 0;
//     for(let i = 0; i < cart.length; i++){
//         const quantity = document.getElementById(`quantity-${cart[i].id}`).value;
//         const total = quantity * cart[i].price;
//         billTotal += total;
//         billDetail.push({
//             user: cart[i].user,
//             product: cart[i],
//             quantity: quantity,
//             total: total
//         })
//     }
//     data.billDetail = billDetail;
//     data.total = billTotal;
//     data.description = description;
//     data.address = address;
//
//     console.log(data)
//     $.ajax({
//         url: "http://localhost:8085/api/bills",
//         headers: {
//             'Accept': 'application/json',
//             'Content-Type': 'application/json'
//         },
//         method: 'POST',
//         data: JSON.stringify(data)
//     })
// })
document.getElementById("checkoutForm").addEventListener("submit", (event) => {
    event.preventDefault();

    const form = document.getElementById("checkoutForm");
    const description = document.getElementById("floatingTextarea").value;
    const provinceSelect = document.getElementById("provinceCre");
    const districtSelect = document.getElementById("districtCre");
    const wardSelect = document.getElementById("wardCre");
    const addressDetail = document.getElementById("addressCre").value;

    const province = provinceSelect.options[provinceSelect.selectedIndex].textContent;
    const district = districtSelect.options[districtSelect.selectedIndex].textContent;
    const ward = wardSelect.options[wardSelect.selectedIndex].textContent;
    const address = addressDetail + ", " + ward + ", " + district + ", " + province;

    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries()); // Convert FormData to an object

    const cart = getCart(userId);
    const billDetail = [];
    let billTotal = 0;

    for (let i = 0; i < cart.length; i++) {
        const quantity = document.getElementById(`quantity-${cart[i].product.id}`).value; // Change 'id' to 'product.id'
        const total = quantity * cart[i].product.price;
        billTotal += total;
        billDetail.push({
            user: cart[i].user,
            product: cart[i].product,
            quantity: quantity,
            total: total
        });
    }

    data.billDetail = billDetail;
    data.total = billTotal;
    data.description = description;
    data.shipAddress = address;

    console.log(data);

    $.ajax({
        url: "/api/bills",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: 'POST',
        data: JSON.stringify(data),
        success: function(response) {
            // Handle successful response
            console.log("Success:", response);
            const noti = document.getElementById("noti-alert");
            noti.classList.remove("d-none")
        },
        error: function(error) {
            // Handle error response
            console.log("Error:", error);
        }
    });
});

const renderTotal = (userId) => {
    const billTotal = document.getElementById("bill-total");
    billTotal.innerHTML="";
    const cart = getCart(userId);
    let total = 0;
    cart.forEach(billdetail => {
        const quantity = document.getElementById(`quantity-${billdetail.product.id}`).value;
        total += billdetail.product.price *quantity
    })
    billTotal.innerHTML= `<tr class="total-data">
                                <td><strong>Subtotal: </strong></td>
                                <td>${total}</td>
                           </tr>
                           <tr class="total-data">
                                <td><strong>Total: </strong></td>
                                <td>${total}</td>
                           </tr>`
}
renderTotal(userId);