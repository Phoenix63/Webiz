<#include "/header.ftl">

	<!-- Title / Description bloc -->
	<div>
 		<h1>${userList.title}</h1>
  		<h2>${userList.description}</h2>
  		<form action="./${userList.id}" method="POST">  		
  			<input type="text" name="title" value="${userList.title}">
  			<input type="text" name="description" value="${userList.description}">
  			<input type="submit" value="update"> 
  		</form>
  		<form action="./${userList.id}/remove" method="POST">
  			<input type="submit" value="definitively delete">
  		</form>
	</div>
	
	<!-- Item list bloc -->
	<div class="list-container">
		<div class="list-header">
			<h1>My items</h1>
		</div>
		<#list userList.itemList as item>
		<a href="./${userList.id}/item/${item.id}">
		<div class="list-item"><p>${item.title}</p></div>
		</a>
		</#list>
		<div class="list-item new-item">
			<form action="./${userList.id}/item" method="POST">
				<input type="text" name="title" placeholder="list name" required>
  				<input type="text" name="description" placeholder="list description" required>
  				<input type="submit" value="Add new item"> 
			</form>
		</div>
  	</div>
  	
<#include "/footer.ftl">