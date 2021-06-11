
//Funct
async function updateList(self){
    await updateListReq(self.id)
}

async function updateListReq(bean){
    const xhr = new XMLHttpRequest();
    xhr.open('GET', 'listf?side='+bean, true);xhr.onload = function () {
        document.getElementsByClassName("gridLeft")[0].innerHTML = xhr.responseText
    };
    xhr.send(null);
    await getFormReq(bean, null);
}

async function getForm(self){
    await getFormReq(self.parentNode.id, self.id)
}

async function getFormReq(side,id){
    const xhr = new XMLHttpRequest();
    let url = 'form?side=' + side;
    if(id != null)url+='&id='+id;
    xhr.open('GET', url, true);xhr.onload = function () {
        document.getElementsByClassName("gridRight")[0].innerHTML = xhr.responseText
    };
    xhr.send(null);
}

async function edit(){
    const xhr = new XMLHttpRequest();
    const nameInput = document.getElementsByClassName("Placename")[0];
    const url = 'JPA?action=mere&name=' + nameInput.value + '&id=' + nameInput.id;
    xhr.open('GET', url, true);xhr.onload = function () {
        getFormReq("Place",nameInput.id);
        updateListReq("Place");
    };
    xhr.send(null);
}

async function remove(){
    const xhr = new XMLHttpRequest();
    let nameInput;
    let side;
    if (document.getElementsByClassName("Placename")[0] != null){
        nameInput = document.getElementsByClassName("Placename")[0];
        side = "Place";
    } else {
        nameInput = document.getElementsByClassName("Tripname")[0];
        side = "Trip";
    }
    const url = 'JPA?action=remv&side=' + side + '&id=' + nameInput.id;
    xhr.open('GET', url, true);xhr.onload = function () {
        getFormReq(side,nameInput.id);
        updateListReq(side);
    };
    xhr.send(null);
}

async function create(self){
    const xhr = new XMLHttpRequest();
    let url = 'JPA?action=pers';
    if(self.className === "TriptabTitle"){
        let name = document.getElementsByClassName("Tripname")[0].value;
        let depa = document.getElementsByClassName("departure")[0].value;
        let term = document.getElementsByClassName("terminal")[0].value;
        let cost = document.getElementsByClassName("cost")[0].value;
        if(name !== "" && depa !== "" && term !== "" && cost !== ""){
            url += "&depa=" + depa;
            url += "&term=" + term;
            url += "&cost=" + cost;
            url += "&nameTrip=" + name;
            xhr.open('GET', url, true);xhr.onload = function () {
                getFormReq("Trip",null);
                updateListReq("Trip");
            };
        }
    } else {
        if(document.getElementsByClassName("Placename")[0].value !== "") {
            url += "&name=" + document.getElementsByClassName("Placename")[0].value;
            xhr.open('GET', url, true);
            xhr.onload = function () {
                getFormReq("Place", null);
                updateListReq("Place");
            };
        }
    }
    xhr.send(null);
}
