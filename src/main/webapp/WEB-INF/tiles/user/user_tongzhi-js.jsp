<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- <script type="text/javascript">
          
              $("#check_1 tr").find("td:eq(0)").css("width","100px")
              $("#check_1 tr").find("td:eq(0)").css("text-align","center")
              
              $("#check_1 tr").find("td:eq(1)").css("width","500px")
            
              $("#check_1 tr").find("td:eq(2)").css("text-align","center")     
              $("#check_1 tr").find("td:eq(2)").mouseenter(function(){
                $(this).css("cursor","pointer");
              });
            
              $("#check_1 tr").find("td:eq(1)").mouseenter(function(){
                $(this).css("cursor","pointer");
              });
              $("#check_1 tr").find("td:eq(1)").click(function(){
              	window.location.href="./#"
              })
            </script>
           <script type="text/javascript">
          		function pop1(){
          			window.location.href="./admin_tongzhi3.shtml"
          		}
           </script>
           
<script type="text/javascript">
   
    $("#check_1 tr").find("td:eq(4)").click(function(){    
    $("#check_1 tr").find("td:eq(4)").attr("data-toggle","modal");
    $("#check_1 tr").find("td:eq(4)").attr("data-target","#myModal")
    $("#check_1 tr").find("td:eq(4)").attr("id","identifier");
    $('#myModal').modal({
      show:false,
      backdrop:"static",
      keyboard:false
    }
    
    ); 
     });
  
</script>
<script>
  $(function () {
	var t =	$('#example1').DataTable({
		 "ajax"		: {
	    	  "url"		: "<%=request.getContextPath()%>/dt_lists3.shtml",
	    	  "method"	: "POST",
	    	  "headers" : {"Content-Type": "application/json"},
	    	  "data"	: function(d){
	    		  return JSON.stringify(d);
	    	  }
	      },
	      "columns": [
	    	  {"data": "office",
	    	 "defaultContent": "1"},
	    	  {"data":"title"},
	    	  {"data": function(data, type, row) { return '&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<button type="button" class="btn btn-primary btn-lg update_btn" data-toggle="modal" data-target="#myModal2" data-whatever="@jason" style="width: 70px;height: 20px;font-size: 10px;line-height: 0px" onclick="pop1()">编辑</button>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<button type="button" class="btn btn-primary btn-lg delete_btn" data-toggle="modal" data-target="#myModal2" data-whatever="@jason" style="width: 70px;height: 20px;font-size: 10px;line-height: 0px" onclick="pop2()">删除</button>'; } },
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
<script>
  $(function () {
    // Replace the <textarea id="editor1"> with a CKEditor
    // instance, using default configuration.
    CKEDITOR.replace('editor1')
    //bootstrap WYSIHTML5 - text editor
    $('.textarea').wysihtml5()
  })
</script>
 --%>