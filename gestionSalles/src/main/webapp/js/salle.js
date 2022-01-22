$(document).ready(function () {
	var submitEtat="add";
	var idSelected;
    $.ajax({
        url: "SalleController",
        data: {op: "load"},
        type: 'POST',
        success: function (data, textStatus, jqXHR) {
            remplir(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus);
        }
    });
    $("#add").click(function () {
        var code = $("#code").val();
        var capacite = $("#capacite").val();
        var type = $("#type").val();
        
        
        console.log(code);
        console.log(capacite);
        console.log(type);
        if(submitEtat=="add"){
	 console.log(submitEtat);
        $.ajax({
            url: "SalleController",
            data: {code: code, capacite: capacite, type: type},
            type: 'POST',
            success: function (data, textStatus, jqXHR) {
                remplir(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus);
            }
        });
	
}else{
	 console.log(submitEtat);
        $.ajax({
            url: "SalleController",
            data: {op: "update",id: idSelected,code: code, capacite: capacite, type: type},
            type: 'POST',
            success: function (data, textStatus, jqXHR) {
                remplir(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus);
            }
        });
}
       

    });
    $("#content").on("click", ".delete", function () {
	console.log("d5uuuuul l detele");
       // alert($(this).attr('val'));
       
        var id = $(this).closest('tr').find('td').eq(0).text();
        $.ajax({
        url: "SalleController",
        data: {op: "delete", id:id},
        type: 'POST',
        success: function (data, textStatus, jqXHR) {
            remplir(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus);
        }
    });

    });
    $("#content").on("click", ".update", function () {
        //alert($(this).attr('val'));
          
         idSelected = $(this).closest('tr').find('td').eq(0).text();
        var code = $(this).closest('tr').find('td').eq(1).text();
        var capacite = $(this).closest('tr').find('td').eq(2).text();
        var type = $(this).closest('tr').find('td').eq(3).text();
        
        console.log(idSelected);
        console.log(code);
        console.log(capacite);
        console.log(type);
        $("#code").val(code);
    	$("#capacite").val(capacite);
    	$("#type").val(type);
	submitEtat="update";
		
		
		/*   $("#add").click(function () {
        
        $.ajax({
            url: "SalleController",
            data: {op: "update",id: id,code: code, capacite: capacite, type: type},
            type: 'POST',
            success: function (data, textStatus, jqXHR) {
	console.log("**after**");
	console.log(id);
	console.log(code);
	console.log(capacite);
	console.log(type);
                remplir(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus);
            }
        });

    });*/
     

    });
    function remplir(data) {
        var ligne = "";
        for (var i = 0; i < data.length; i++) {
            ligne += "<tr><td>" + data[i].id + "</td><td>" + data[i].code + "</td><td>" + data[i].capacite + "</td><td>" + data[i].type + "</td><td><button class='delete btn btn-danger' val='"+ data[i].id +"'>supprimer</button></td><td><button class='update btn btn-success' val='"+ data[i].id +"'>modifier</button></td></tr>";
        }
        $("#content").html(ligne);
    }
});

