<html>
<head>
  <title>${title}
</head>
<body>
	<!-- Title / Description bloc -->
	<div>
 		<h1>${userList.title}</h1>
  		<h2>${userList.description}</h2>
	</div>

	<!-- Item list bloc -->
  	<ul>
    	<#list items as item>
      	<li>${item_index + 1}. ${item.title}</li>
		</#list>
		<li class="newItem">Add new item</li>
  	</ul>

</body>
</html>