

function formation (){
    this.id="";
    this.name="";
};

var formation = new formation();

function addFormation(){
    formation.name = $("#formation_name").val();
    $.ajax({
        type:"POST",
        url:"resources/formation",
        data: JSON.stringify(formation),
        dataType: 'json',
        contentType: 'application/json'
    });
    $("#formation_name").val("");
}
        
$("#bnt_addformation").on('click', addFormation);
$("#bnt_addformation").on('click', loadFromDataBase);
