var totalAsientos = 0;
var pFinal = $("#precioFinal");

$("input[type='checkbox']").prop("checked", false);

$("input[type='checkbox']").click(function(e){
	
	if(totalAsientos == 4){
		if($(this).is(":not(:checked)")){
			totalAsientos--;
			if(totalAsientos){				
				pFinal.text("Precio: $" + precioFinal * totalAsientos +".00");
				pFinal.val(precioFinal * totalAsientos);
			}
			else{
				pFinal.text("");
			}
		}
		else{			
			e.preventDefault();
			e.stopPropagation();
		}
	}
	else{		
		if($(this).is(":checked")){
			totalAsientos++;
			pFinal.text("Precio: $" + precioFinal * totalAsientos +".00");
			pFinal.val(precioFinal * totalAsientos);
		}
		else if($(this).is(":not(:checked)")){
			totalAsientos--;
			if(totalAsientos){				
				pFinal.text("Precio: $" + precioFinal * totalAsientos +".00");
				pFinal.val(precioFinal * totalAsientos);
			}
			else{
				pFinal.text("");
			}
		}
	}
	
});