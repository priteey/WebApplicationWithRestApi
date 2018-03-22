<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script type="text/javascript"> 

$(function(){
$("#deleteButton").on("click", function(){
	var data = JSON.stringify(this.dataset);
	
	$.ajax({
        url: "http://localhost:8080/KsquareFurniture/user/delete/userId=candicef&furnitureId=1",
         method: 'DELETE', 
        success: function(xml) {
        	debugger;
            console.log(xml);
                    alert("Deleted the user with id "+userId);
            /*
                    console.log(id);
                });
            });*/
           
        }
    });
   return true;
 })
 
});
</script>
</head>
<body>
<div class="content">
   
 <form>
<table border="1px"  style = "color:black" cellspacing="0" width="75%" align="center" id="myTable">
                                            	<thead>
                                                <tr bgcolor="blue" style="color:white">
                                                    <th >UserId</th>
                                                    <th >Product Name</th>
                                                    <th>Quantity</th>
                                                    <th>Action</th>
                                                </tr>
											</thead>
											<tbody>
                                            <c:forEach var="o" items="${orderDetails}">
                                                <tr>
                                                    <td><c:out value="${o.userId}"/></td>
                                                    <td><c:out value="${o.furnitureId}" /></td>
                                                    <td><c:out value="${o.quantity}" /></td>
                                                    
                                          			<td><a id="deleteButton"  data-userId="${o.userId}" data-furnitureId="${o.furnitureId}" >Delete</a></td>
                                           </tr>
                                                </c:forEach>
                                        </tbody>
                                        </table>
                                        </form>
 
  </div>

</body>
</html>