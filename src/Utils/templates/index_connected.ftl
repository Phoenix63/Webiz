<#include "/header.ftl">

	<!-- User list bloc -->
	<div>
  		<ul>
    		<#list lists as list>
      		<a href="./list/${list.id}"><li>${list.title}</li></a>
			</#list>
			<li class="newItem">
				<form action="./list" method="POST">
					<input type="text" name="title" placeholder="list name" required>
  					<input type="text" name="description" placeholder="list description" required>
  					<input type="submit" value="Add new item"> 
				</form>
			</li>
  		</ul>
  	</div>

<#include "/footer.ftl">