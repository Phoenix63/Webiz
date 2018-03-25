<html>
<head>
  <title>${title}</title>
</head>
<body>
	<!-- Title / Description bloc -->
	<div>
 		<h1>${item.title}</h1>
  		<h2>${item.description}</h2>
  		<div>
  			<span>
  				last modification the ${item.lastModificationDate?string('dd.MM.yyyy hh:mm')} | 
  				created the ${item.creationDate?string('dd.MM.yyyy hh:mm')}
  			</span>
  		</div>
  		<form action="./${item.id}" method="POST">  		
  			<input type="text" name="title" value="${item.title}">
  			<input type="text" name="description" value="${item.description}">
  			<input type="submit" value="update"> 
  		</form>
  		<form action="./${item.id}/remove" method="POST">
  			<input type="submit" value="definitively delete">
  		</form>
	</div>

</body>
</html>