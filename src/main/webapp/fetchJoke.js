let getRandomJokeBtn = document.getElementById("getRandomJokeBtn");
getRandomJokeBtn.addEventListener('click', (event) => {
    event.preventDefault();
    getRandomJoke();
});

let buttonFetch = document.getElementById("buttonFetch");
buttonFetch.addEventListener('click', (event) => {
    event.preventDefault();
    var id = document.getElementById("fetch").value;
    getJokeByID(id);
});

let myJoke = document.getElementById("myJoke");


function getRandomJoke() {
    let url = 'http://localhost:8080/CA1/api/jokes/random';
    fetch(url)
            .then(res => res.json())
            .then(data => {
                myJoke.innerHTML = `${data.joke}`;
    });
}

function getJokeByID(id) {
    let url = 'http://localhost:8080/CA1/api/jokes/id/' + id;
    fetch(url)
            .then(res => res.json())
            .then(data => {
                myJoke.innerHTML = `${data.joke}`;
    });
}