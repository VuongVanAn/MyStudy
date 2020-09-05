$(document).ready(function() {	
	$('.nBtn, .eBtn').click(function(e) {
		e.preventDefault();
		var text = $(this).text();
		var href = $(this).attr('href');
		
		if (text == 'Add User') {
			$('.myForm #id').val('');
			$('.myForm #userName').val('');
			$('.myForm #fullName').val('');
			$('.myForm #password').val('');
			$('.myForm #email').val('');
			$('.myForm #roleId').val('');		
		} else {
			$.get(href, function(user, status) {
				$('.myForm #id').val(user.id);
				$('.myForm #userName').val(user.userName);
				$('.myForm #fullName').val(user.fullName);
				$('.myForm #password').val(user.password);
				$('.myForm #email').val(user.email);
				$('.myForm #roleId').val(user.roleId);
			});
		}
		$('.myForm #exampleModal').modal();
	});
	
	$('.delBtn').click(function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		$('.myModal #delRef').attr('href', href);
		$('.myModal #exampleModalLong').modal();
	});
});