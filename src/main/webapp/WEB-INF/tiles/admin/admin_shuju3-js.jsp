<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- page script -->
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
   /* $("#myModal2").modal("show");  
   $("#myModal2").modal({  
       keyboard:true  
   });  
   $("#myModal2").modal({  
       backdrop:false  
   });   */
    /*$("#myModal2").on("show.bs.modal",function(e){  
        var button=$(e.relatedTarget);  
        var recipient=button.data("whatever");  
        var modal=$(this);  
        modal.find(".modal-title").text("hello"+recipient);  
        modal.find(".modal-body input[name='username']").val(recipient);  
    });  */
</script>   