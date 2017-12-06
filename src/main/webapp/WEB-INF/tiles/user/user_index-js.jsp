<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
  $(function () {
    $('#example1').DataTable()
    $('#example2').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
     
    })
  })

</script>
<script>
  $(function () {
	$('#example3').DataTable({
			"searching" : false, //去掉搜索框方法一
			"bSort": false,  //禁止排序
			"paging": false,   //禁止分页
			"info": false,   //去掉底部文字
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
				 {"data":"stopTime"},
	    	  	{"data":"zt"},
	      ],
	      
	});
		
	
  })
</script>

