/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function Student (){
    this.lastname="";
    this.firstname="";
    this.mail="";
    this.age="";
    this.retard="";
    this.commentary="";
    this.telephone="";
    this.image="";
    this.promotion="";
}

var student = new Student();
var Students;
var Promotions;
function loadListStudent () {
	var idPromo = $('#choosePromotionSelect').val();
	            var ajaxresp = $.ajax({
                                    type: "GET",
                                    contentType : "application/json",
                                    url: "resources/promotion/dataPromotion",
                                    async: false
                                    });
                                        
	Promotions = ajaxresp.responseJSON;
        for (i = 0; i < Promotions.length; i++) { 
            if (Promotions[i].promotionId == idPromo ){
                $("#choosePromotionNumberFormation").text(Promotions[i].promotionNumber);
                $("#choosePromotionDateBegin").text(Promotions[i].promotionDateBegin.substr(0,10));
                $("#choosePromotionDateEnd").text(Promotions[i].promotionDateEnd.substr(0,10));
                $("#choosePromotionSchool").text(Promotions[i].idSchool.schoolName);
                $("#choosePromotionCity").text(Promotions[i].idSchool.schoolCity);
            }if(idPromo == 0){
                $("#choosePromotionNumberFormation").text("");
                $("#choosePromotionDateBegin").text("");
                $("#choosePromotionDateEnd").text("");
                $("#choosePromotionSchool").text("");
                $("#choosePromotionCity").text("");
            }
        }
        var table = $("#tableListStudent");
	$('#tableListStudent').find('tr').remove();
        if (Students == undefined){
            var ajaxresp = $.ajax({
                                        type: "GET",
                                        contentType : "application/json",
                                        url: "resources/students",
                                        async: false
                                        });

            Students = ajaxresp.responseJSON;
        }
	table.append('<tr><th class="col-md-5">Prénom</th><th class="col-md-2">Nom</th><th class="col-md-2">Mail</th><th class="col-md-2">Age</th><th class="col-md-2">N°Tel</th><th class="col-md-2">Retards</th><th class="col-md-2">Promotion</th></tr>');
        var nbStudent = 0;
        for (i = 0; i < Students.length; i++) { 
            if (Students[i].idPromotion.promotionId == idPromo || idPromo == 0){
                table.append('<tr><td class="col-md-5">'+Students[i].studentFirstName+'</td><td class="col-md-2">'+Students[i].studentLastName+'</td><td class="col-md-2">'+Students[i].studentMail+'</td><td class="col-md-2">'+Students[i].studentAge+'</td><td class="col-md-2">'+Students[i].studentTelephonNumber+'</td><td class="col-md-2">'+Students[i].studentRetard+'</td><td class="col-md-2">'+Students[i].idPromotion.promotionName+'</td></tr>');
                nbStudent = nbStudent+1;
            }
        }
	$("#choosePromotionNumberStudent").text(nbStudent);
}

function loadListPromotion () {
            var ajaxresp = $.ajax({
                                    type: "GET",
                                    contentType : "application/json",
                                    url: "resources/promotion/dataPromotion",
                                    async: false
                                    });
                                        
	Promotions = ajaxresp.responseJSON;
    $('#choosePromotionSelect')
         .append($("<option></option>")
         .attr("value",0)
         .text("")); 
    
    $.each(Promotions, function(key, value) {   
    $('#choosePromotionSelect')
         .append($("<option></option>")
         .attr("value",value.promotionId)
         .text(value.promotionName)); 
    });
	
}

function loadListPromotionAddStudent () {
            var ajaxresp = $.ajax({
                                    type: "GET",
                                    contentType : "application/json",
                                    url: "resources/promotion/dataPromotion",
                                    async: false
                                    });
                                        
	Promotions = ajaxresp.responseJSON;
    $('#choosePromotionSelectAddStudent')
         .append($("<option></option>")
         .attr("value",0)
         .text("")); 
    
    $.each(Promotions, function(key, value) {   
    $('#choosePromotionSelectAddStudent')
         .append($("<option></option>")
         .attr("value",value.promotionId)
         .text(value.promotionName)); 
    });
	
}

function confAddStudent (){
        
    student.lastname = $("#studentLastname").val();
    student.firstname = $("#studentFirstname").val();
    student.age = $("#studentAge").val();
    student.mail = $("#studentMail").val();
    student.telephone = $("#studentTelephone").val();
    student.image = $("#studentImage").val();
    student.promotion = $("#choosePromotionSelectAddStudent").val();
    $.ajax({
        type:"POST",
        url:"resources/students",
        data: JSON.stringify(student),
        dataType: 'json',
        contentType: 'application/json'
    });
}

loadListPromotion ();
loadListStudent ();


$('#choosePromotionSelect').on('change',loadListStudent);
$('#btnChoosePromotionAddStudent').on('click', loadListPromotionAddStudent);
$('#btnConfAddStudent').on('click', confAddStudent);