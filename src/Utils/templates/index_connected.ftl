<html>
<head>
  <title>${title}</title>
</head>
<body>
	<!-- User list bloc -->
	<div>
  		<ul>
    		<#list lists as list>
      		<a href="./list/${list.id}"><li>${list.title}</li></a>
			</#list>
			<li class="newItem">Add new list</li>
  		</ul>
  	</div>

</body>
</html>