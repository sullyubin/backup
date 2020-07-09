$(function(){
	$(".accept").on("click",function(){
		var id = $(this).attr("id");
		location.href="/admin/petaccept?id="+id;
	})
	
	$(".cencel").on("click",function(){
		var id = $(this).attr("id");
		location.href="/admin/cencel?id="+id;
	})
})