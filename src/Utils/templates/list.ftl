<html>
<head>
  <title>${title}</title>
</head>
<body>
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
	<div>
  		<ul>
    		<#list userList.itemList as item>
			<a href="./${userList.id}/item/${item.id}"><li>${item_index + 1}. ${item.title}</li></a>
			</#list>
			<li class="newItem">
				<form action="./${userList.id}/item" method="POST">
					<input type="text" name="title" placeholder="list name" required>
  					<input type="text" name="description" placeholder="list description" required>
  					<input type="submit" value="Add new item"> 
				</form>
			</li>
  		</ul>
  	</div>
  	

</body>
</html>