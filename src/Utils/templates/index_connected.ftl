<#include "/header.ftl">

	<!-- User list bloc -->
	<div class="list-container">
		<div class="list-header">
			<h1>My lists</h1>
		</div>
    	<#list lists as list>
    	<a href="./list/${list.id}">
		<div class="list-item"><p>${list.title}</p></div>
		</a>
		</#list>
		<div class="list-item new-item">
				<form action="./list" method="POST">
					<input type="text" name="title" placeholder="Name of the new list" required>
  					<input type="text" name="description" placeholder="Description of the new list">
  					<input type="submit" value="Add"> 
				</form>
		</div>
  	</div>

<#include "/footer.ftl">