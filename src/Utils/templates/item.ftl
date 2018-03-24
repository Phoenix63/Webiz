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
  				last modification the ${item.lastModificationDate?string('dd.MM.yyyy HH:mm')} | 
  				created the ${item.creationDate?string('dd.MM.yyyy HH:mm')}
  			</span>
  		</div>
	</div>

</body>
</html>