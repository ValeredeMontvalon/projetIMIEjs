/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var formerArray = new Array();
function loadModal(){
    $("#ModalConnectFormer").modal('show');
    $("#btnModalConnexion").on('click',loadFormers);
}
loadModal();

function loadFormers(){
    if(formerArray.length === 0){
        $.ajax({
               type:"GET",
               url:"/ProjetImie/resources/former/dataFormer",
               dataType:"json",
               contentType: 'application/json',
               success:function(data){

                    formerArray = data;
                    verificationConnexion();

                }
        });   
    }else{
        verificationConnexion();
    }
}

function verificationConnexion(){
    var confirmationLoginPwd = false;
    for(var i = 0; i < formerArray.length; i++){
            if($("#inputLogin").val() === formerArray[i].formerLogin && $("#inputPassword").val() === formerArray[i].formerPassword){
                $("#firstNameUser").text(formerArray[i].formerFirstName);
                $("#lastNameUser").text(formerArray[i].formerLastName);
                $("#userLesson").text(formerArray[i].formerLesson);
                $("#ModalConnectFormer").modal('hide');
                confirmationLoginPwd = true;
            }
    }
    if(confirmationLoginPwd == false){
        alert("login ou mot de passe incorrect");
    }
}