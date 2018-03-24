<html>
<head>
  <title>${title}</title>
</head>
<body>
	<!-- Title / Description bloc -->
	<div>
 		<h1>${userList.title}</h1>
  		<h2>${userList.description}</h2>
	</div>
	
	<!-- Item list bloc -->
	<div>
  		<ul>
    		<#list userList.itemList as item>
			<a href="./${userList.id}/item/${item.id}"><li>${item_index + 1}. ${item.title}</li></a>
			</#list>
			<li class="newItem">Add new item</li>
  		</ul>
  	</div>

</body>
</html>