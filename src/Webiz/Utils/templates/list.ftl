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
			<div class="item-action-share" onclick="actionShare();">
				<!-- button share -->
			</div>
		</div>
		
		<div class="item-information">
			<h1>${userList.title}</h1>
  			<h2>${userList.description}</h2>
		</div>
		
		<div class="item-modification hide">
			<form action="./${userList.id}" method="POST">  		
  				<input type="text" name="title" value="${userList.title}">
  				<input type="text" name="description" value="${userList.description}">
  			</form>
		</div>
		
	</div>
	
	<hr/>
		
	<!-- Item list bloc -->
	<div class="list-container">
		<div class="list-header">
			<h1>My items</h1>
		</div>
		<#list userList.itemList as item>
		<a href="./${userList.id}/item/${item.id}">
		<div class="list-item">
		<#if item.state == "DONE"> 
			<del>${item.title}</del>
		</#if>
		<#if item.state != "DONE"> 
			<p>${item.title}</p>
		</#if>
		</div>
		</a>
		</#list>
		<div class="list-item new-item">
			<form action="./${userList.id}/item" method="POST">
				<input type="text" name="title" placeholder="Name of the new item" required>
  				<input type="text" name="description" placeholder="Description of the new item">
  				<input type="submit" value="Add"> 
			</form>
		</div>
  	</div>
  	
  	<div class="item-deletion hide">  		
  		<form action="./${userList.id}/remove" method="POST">
  			<input type="submit" value="Remove">
  		</form>
	</div>
  	
<#include "/footer.ftl">