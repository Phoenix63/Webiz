<#include "/header.ftl">

	<!-- Title / Description bloc -->
	<div class="item-container">
		<div class="item-information">
			<h1>${userList.title}</h1>
  			<h2>${userList.description}</h2>
		</div>
		<div class="item-modification">
			<form action="./${userList.id}" method="POST">  		
  				<input type="text" name="title" value="${userList.title}">
  				<input type="text" name="description" value="${userList.description}">
  				<input type="submit" value="update"> 
  			</form>
		</div>
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
				<input type="text" name="title" placeholder="Name of the new item" required>
  				<input type="text" name="description" placeholder="Description of the new item" required>
  				<input type="submit" value="Add new item"> 
			</form>
		</div>
  	</div>
  	
  	<div class="item-deletion">  		
  		<form action="./${userList.id}/remove" method="POST">
  			<input type="submit" value="Remove">
  		</form>
	</div>
  	
<#include "/footer.ftl">