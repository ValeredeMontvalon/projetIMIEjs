function former(){
    this.id ="";
    this.lastname="";
    this.firstname="";
    this.lesson="";
    this.login="";
    this.password="";
}

var f = new former();

function addFormer(){
    f.lastname = $("#formerLastname").val();
    f.firstname = $("#formerFirstname").val();
    f.lesson = $("#formerLesson").val();
    f.login = $("#formerLogin").val();
    f.password = $("#formerPassword").val();
    var confPassword = $("#formerConfPassword").val();
    if (f.password === confPassword){
        $.ajax({
            type:"POST",
            url:"resources/former",
            data: JSON.stringify(f),
            dataType: 'json',
            contentType: 'application/json'
        });
    }else{
        alert('La confirmation du mot de passe est incorrect.');
    }
}

$("#btnConfAddFormer").on('click', addFormer);


