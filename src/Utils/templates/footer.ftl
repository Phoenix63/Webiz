	<!-- end of page-content div -->
	</div>
</body>

<script type="text/javascript">
	
	function actionValid() {	
		console.log("Action Valid clicked");
		var divModification = document.getElementsByClassName("item-modification")[0];
		var form = divModification.getElementsByTagName("form")[0];
		form.submit();
	}
	
	function actionReject() {
		console.log("Action Reject clicked");
		location.reload();
	}
	
	function actionEdit() {
		console.log("Action Edit clicked");
		var divValid = document.getElementsByClassName("item-action-valid")[0];
		var divReject = document.getElementsByClassName("item-action-reject")[0];
		var divEdit = document.getElementsByClassName("item-action-edit")[0];
		var divShare = document.getElementsByClassName("item-action-share")[0];
		var divInformation = document.getElementsByClassName("item-information")[0];		
		var divModification = document.getElementsByClassName("item-modification")[0];
		var divDeletion = document.getElementsByClassName("item-deletion")[0];
		
		if (divValid) divValid.classList.remove("hide");
		if (divReject) divReject.classList.remove("hide");
		if (divEdit) divEdit.classList.add("hide");
		if (divShare) divShare.classList.add("hide");
		if (divInformation) divInformation.classList.add("hide");
		if (divModification) divModification.classList.remove("hide");
		if (divDeletion) divDeletion.classList.remove("hide");		
	}
	
	function actionShare() {
		console.log("Action Share clicked");
	}
		
</script>

</html>