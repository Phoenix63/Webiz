<html>
<head>
  <title>${title}
</head>
<body>
  <h1>${userList.title}</h1>
  <h2>${userList.description}</h2>

  <ul>
    <#list items as item>
      <li>${item_index + 1}. ${item.title}</li>
	</#list>
  </ul>

</body>
</html>