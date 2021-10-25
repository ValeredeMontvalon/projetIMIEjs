    var promotionsArray = new Array();
    var formationsArray = new Array();
    var schoolsArray = new Array();
loadFromDataBase();
function loadFromDataBase(){
    $.ajax({
           type:"GET",
           url:"/ProjetImie/resources/formation/dataFormation",
           dataType:"json",
           contentType: 'application/json',
           success:function(data){
                formationsArray = data;
                loadSelectFormation();
           
            }
});
    $.ajax({
           type:"GET",
           url:"/ProjetImie/resources/school/dataSchool",
           dataType:"json",
           contentType: 'application/json',
           success:function(data){
                schoolsArray = data;
                loadPromotionSelectCityName();
           
            }
});
   $.ajax({
           type:"GET",
           url:"/ProjetImie/resources/promotion/dataPromotion",
           dataType:"json",
           contentType: 'application/json',
           success:function(data){
                promotionsArray = data;
                loadPromotionSelectDates();
                loadPromotionTable();

                }
});
};
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/







function loadPromotionTable(){
    var selectedPromotionsArray = new Array();
    var selectedSubstituteArray = new Array();
    var j = 0;
    $('#sectionAPromotionTable').find('tr').remove();

    if($('#selectSearchPromotionFormation').val() !== ""){
        j = 0;
        for(var i = 0; i < promotionsArray.length; i++){
            if(promotionsArray[i].idFormation.formationName === $('#selectSearchPromotionFormation').val()){
                selectedPromotionsArray[j] = promotionsArray[i];
                j++;
            }
        }
    }else{
        selectedPromotionsArray = promotionsArray;
    }

    if($('#selectSearchPromotionVille').val() !== ""){
        j = 0;
        for(var i = 0; i < selectedPromotionsArray.length; i++){
            if(selectedPromotionsArray[i].idSchool.schoolCity === $('#selectSearchPromotionVille').val()){
                selectedSubstituteArray[j] = selectedPromotionsArray[i];
                j++;
            }
        }
        selectedPromotionsArray = selectedSubstituteArray;
    }
    
    if($('#selectSearchPromotionDateBegin').val() !== ""){
        selectedSubstituteArray = new Array();
        j = 0;
        for(var i = 0; i < selectedPromotionsArray.length; i++){
            if(selectedPromotionsArray[i].promotionDateBegin === $('#selectSearchPromotionDateBegin').val()){
                selectedSubstituteArray[j] = selectedPromotionsArray[i];
                j++;
            }
            
        }
        selectedPromotionsArray = selectedSubstituteArray;
    }
    
    if($('#selectSearchPromotionDateEnd').val() !== ""){
        selectedSubstituteArray = new Array();
        j = 0;
        for(var i = 0; i < selectedPromotionsArray.length; i++){
            if(selectedPromotionsArray[i].promotionDateEnd === $('#selectSearchPromotionDateEnd').val()){
                selectedSubstituteArray[j] = selectedPromotionsArray[i];
                j++;
            }
            
        }
        selectedPromotionsArray = selectedSubstituteArray;
    }
    
    var options = [];
    options.push('<tr><th>Nom</th><th>Formation(nom)</th><th>Ecole(ville)</th><th>Date début</th> <th>Date fin</th></tr>');
    for(var i = 0; i < selectedPromotionsArray.length; i++){
	options.push( '<tr id="row'+selectedPromotionsArray[i].promotionId+'" onClick='+"loadtabStudent("+selectedPromotionsArray[i].promotionId+")" +' style="cursor:pointer"> <td>'+selectedPromotionsArray[i].promotionName+'</td><td>'+selectedPromotionsArray[i].idFormation.formationName+'</td><td>'+selectedPromotionsArray[i].idSchool.schoolName+'</td><td>'+selectedPromotionsArray[i].promotionDateBegin+'</td><td>'+selectedPromotionsArray[i].promotionDateEnd+'</td></tr>');

    }
    $("#sectionAPromotionTable").html(options.join(''));
};
function loadtabStudent(idPromo){
    $("#choosePromotionSelect").val(idPromo);
    $('.nav-tabs > .active').next('li').find('a').trigger('click');
    loadListStudent ();
};

function loadPromotionSelectDates(){
    var optionsDateBegin = [];
    var optionsDateEnd = [];
    optionsDateBegin.push('<option id="selectSearchPromotionFormationEmpty"></option>');
    optionsDateEnd.push('<option id="selectSearchPromotionFormationEmpty"></option>');
    for(var i = 0; i < promotionsArray.length; i++){
	optionsDateBegin.push('<option id="selectSearchPromotionDateBegin'+promotionsArray[i].promotionId+'">'+ promotionsArray[i].promotionDateBegin +'</option>');
        optionsDateEnd.push('<option id="selectSearchPromotionDateEnd'+promotionsArray[i].promotionId+'">'+ promotionsArray[i].promotionDateEnd +'</option>');
    }
    $("#selectSearchPromotionDateBegin").html(optionsDateBegin.join(''));
    $("#selectSearchPromotionDateEnd").html(optionsDateEnd.join(''));
    
};

function loadPromotionSelectCityName(){
    var options = [];
    options.push('<option id="selectSearchPromotionFormationEmpty"></option>');
    for(var i = 0; i < schoolsArray.list.length; i++){
        options.push('<option id="'+schoolsArray.list[i]+'">'+ schoolsArray.list[i]+'</option>');
    }
    $("#selectSearchPromotionVille").html(options.join(''));
    }
function loadSelectFormation(){
    var options = [];
    options.push('<option id="selectSearchPromotionFormationEmpty"></option>');
    for(var i = 0; i < formationsArray.length; i++){
        options.push('<option id="selectSearchPromotionFormation'+formationsArray[i].formationId+'">'+ formationsArray[i].formationName +'</option>');
    }
    $("#selectSearchPromotionFormation").html(options.join(''));
}

$('#selectSearchPromotionFormation').on('change',loadPromotionTable);
$('#selectSearchPromotionVille').on('change',loadPromotionTable);
$('#selectSearchPromotionDateBegin').on('change',loadPromotionTable);
$('#selectSearchPromotionDateEnd').on('change',loadPromotionTable);

