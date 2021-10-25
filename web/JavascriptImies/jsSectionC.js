function ecf (){
    this.id="";
    this.name="";
    this.date="";
};

var ecf = new ecf();

function addEcf(){
    ecf.name = $("#ecf_name").val();
    ecf.date = $("#ecf_date").val();
    $.ajax({
        type:"POST",
        url:"resources/ecf",
        data: JSON.stringify(ecf),
        dataType: 'json',
        contentType: 'application/json'
    });
    ecf.name = $("#ecf_name").val("");
    ecf.date = $("#ecf_date").val("");
    
}
    
$("#btn_addecf").on('click', addEcf);
