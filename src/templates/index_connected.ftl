<html>
<head>
  <title>${title}
</head>
<body>
	<!-- User list bloc -->
	<div>
  		<ul>
    		<#list lists as list>
      		<li>${list_index + 1}. ${list.title}</li>
			</#list>
			<li class="newItem">Add new list</li>
  		</ul>
  	</div>

</body>
</html>