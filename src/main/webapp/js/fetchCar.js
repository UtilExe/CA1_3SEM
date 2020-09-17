fetchAllCars();
let carsTable = document.getElementById("carsTable");

var buttonFilterMake = document.getElementById("buttonFilterMake");
buttonFilterMake.addEventListener('click', (event) => {
    event.preventDefault();
    getCarsByMake();
});

var buttonSortPrice = document.getElementById("buttonSortPrice");
buttonSortPrice.addEventListener('click', (event) => {
    event.preventDefault();
    sortCarsByPrice();
});

var buttonSortYear = document.getElementById("buttonSortYear");
buttonSortYear.addEventListener('click', (event) => {
    event.preventDefault();
    sortCarsByYear();
});

var buttonRecommended = document.getElementById("buttonRecommended");
buttonRecommended.addEventListener('click', (event) => {
    event.preventDefault();
    getRecommendedCar();
})

function getRecommendedCar() {
    let url = '../CA1/api/cars/all';
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let newArray = data.sort((b,a) => a.year-b.year);
                let recommended = newArray[0];
                console.log(recommended);
                carsTable.innerHTML =
                `<table class="table table-striped tableStyle">
                    ${getTableHeader()}
                    <tr><td>${recommended.id}</td><td>${recommended.year}</td><td>${recommended.make}</td><td>${recommended.model}</td><td>${recommended.price}</td></tr>
                </table>`
    })
}

function sortCarsByPrice() {
    let url = '../CA1/api/cars/all';
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let newArray = data.sort((b,a) => a.price-b.price);
                newArray = newArray.map(x => `<tr><td>${x.id}</td><td>${x.year}</td><td>${x.make}</td><td>${x.model}</td><td>${x.price}</td></tr>`);
                carsTable.innerHTML =
                        `<table class="table table-striped tableStyle">
                            ${getTableHeader()}
                            ${newArray.join('')}
                         </table>`
    });
}

function sortCarsByYear() {
    let url = '../CA1/api/cars/all';
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let newArray = data.sort((b,a) => a.year-b.year);
                newArray = newArray.map(x => `<tr><td>${x.id}</td><td>${x.year}</td><td>${x.make}</td><td>${x.model}</td><td>${x.price}</td></tr>`);
                carsTable.innerHTML =
                        `<table class="table table-striped tableStyle">
                            ${getTableHeader()}
                            ${newArray.join('')}
                         </table>`
    });
}

//Perhaps make it possible to search with lowercase and not full make e.g. 'volk' for Volkswagen
function getCarsByMake() {
    let url = '../CA1/api/cars/all';
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let newArray = data.filter(x => x.make.includes(document.getElementById("inputFilterMake").value));
                newArray = newArray.map(x => `<tr><td>${x.id}</td><td>${x.year}</td><td>${x.make}</td><td>${x.model}</td><td>${x.price}</td></tr>`);
                carsTable.innerHTML =
                        `<table class="table table-striped tableStyle">
                            ${getTableHeader()}
                            ${newArray.join('')}
                         </table>`
    })
}

function fetchAllCars() {
    let url = '../CA1/api/cars/all';
    fetch(url)
            .then(res => res.json())
            .then(data => {
                let newArray = data.map(x => `<tr><td>${x.id}</td><td>${x.year}</td><td>${x.make}</td><td>${x.model}</td><td>${x.price}</td></tr>`);
                carsTable.innerHTML =
                        `<table class="table table-striped tableStyle">
                            ${getTableHeader()}
                            ${newArray.join('')}
                         </table>`
    });
}

function getTableHeader(){
    return `<thead><th>ID</th><th>Year</th><th>Make</th><th>Model</th><th>Price</th></thead>`;
}

function getTableRow(){
    return `<tr><td> </td><td> </td><td> </td></tr>`;
}