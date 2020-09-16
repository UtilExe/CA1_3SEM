let getAllMembersBtn = document.getElementById("getAllMembersBtn");
getAllMembersBtn.addEventListener('click', (event) => {
    event.preventDefault();
    fetchAllMembers();
});


function fetchAllMembers() {
    let url = 'https://work.emucoach.com/CA1/api/groupmembers/all';
    let membersTable = document.getElementById("membersTable");
    fetch(url, {mode:'cors'})
        .then(res => res.json())
        .then(data => {
            let newArray = data.map(x => `<tr><td>${x.name}</td><td>${x.studentID}</td><td>${x.favoriteTVSeries}</td></tr>`)
            membersTable.innerHTML =
                `<table class="table table-striped tableStyle">
                    ${getTableHeader()}
                    ${newArray.join("")}
                </table>`
        });
}

function getTableHeader(){
    return `<thead><th>Name</th><th>Student ID</th><th>Favorite TV-series</th></thead>`;
}

function getTableRow(){
    return `<tr><td> </td><td> </td><td> </td></tr>`;
}
