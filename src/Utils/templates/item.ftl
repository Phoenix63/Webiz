<#include "/header.ftl">

	<a href="../" class="back-button"></a>
	
	<!-- Title / Description bloc -->
	<div class="item-container">
	
		<div class="item-action-wrapper">
			<div class="item-action-valid hide" onclick="actionValid();">
				<!-- button valid modification -->
			</div>
			<div class="item-action-reject hide" onclick="actionReject();">
				<!-- button reject modification -->
			</div>
			<div class="item-action-edit" onclick="actionEdit();">
				<!-- button edit -->
			</div>
		</div>
		
		<div class="item-information">
	 		<h1>${item.title}</h1>
	  		<h2>${item.description}</h2>
  		</div>
  		
		<div class="item-modification hide">
	  		<form action="./${item.id}" method="POST">  		
	  			<input type="text" name="title" value="${item.title}">
	  			<input type="text" name="description" value="${item.description}">
	  		</form>
	  	</div>
	  	
	</div>
	
	<div class="item-information-date">
  		<span>
  			last modification the ${item.lastModificationDate?string('dd.MM.yyyy hh:mm')} | 
  			created the ${item.creationDate?string('dd.MM.yyyy hh:mm')}
  		</span>
  	</div>
		  	
  	<div class="item-deletion hide">  		
  		<form action="./${item.id}/remove" method="POST">
  			<input type="submit" value="Remove">
  		</form>
	</div>

<#include "/footer.ftl">