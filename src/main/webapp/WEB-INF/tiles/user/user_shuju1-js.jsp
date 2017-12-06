<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
  $(function () {
	var t =	$('#example1').DataTable({
		 "ajax"		: {
	    	  "url"		: "<%=request.getContextPath()%>/shuju_1.shtml",
	    	  "method"	: "POST",
	    	  "headers" : {"Content-Type": "application/json"},
	    	  "data"	: function(d){
	    		  return JSON.stringify(d);
	    	  }
	      },
	      "columns": [
	    	  {"data": "office",
	    	 "defaultContent": "1"},
	    	  {"data":"startTime"},
	    	  {"data":"stopTime"},
	    	  {"data":"zt"},
	    	  {"data": function(data, type, row) { return '<button onclick="pop()" type="button" class="btn btn-primary btn-lg results" data-toggle="modal" data-target="#myModal2" data-whatever="@jason" style="width: 70px;height: 20px;font-size: 10px;line-height: 0px">查看结果</button>'; } },
	      ],
	      
	});
		t.on('order.dt search.dt',
		 function() {
   		       t.column(0, {
   		              search: 'applied',
   		              order: 'applied',
   		        }).nodes().each(function(cell, i) {
   		              cell.innerHTML = i + 1;
   		  	  
   		  	   })        	 	
   		  	   }).draw();	
	
  })
</script>
<script type="text/javascript">
   	function pop(){
   	  $(".results").attr("data-toggle","modal");
      $(".results").attr("data-target","#myModal")
      $(".results").attr("id","identifier");
      $('#myModal').modal({
        show:false,
        backdrop:"static",
        keyboard:false
   	})
   	}
    
</script>