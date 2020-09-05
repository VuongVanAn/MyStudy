$(document).ready(function() {	
	$('.nBtn, .eBtn').click(function(e) {
		e.preventDefault();
		var text = $(this).text();
		var href = $(this).attr('href');
		
		if (text == 'Add Exercise') {
			$('.myForm #id').val('');
			$('.myForm #title').val('');
			$('.myForm #shortDescription').val('');
			$('.myForm #amount').val('');	
			$('.myForm #userId').val('');	
		} else {
			$.get(href, function(exercise, status) {
				$('.myForm #id').val(exercise.id);
				$('.myForm #title').val(exercise.title);
				$('.myForm #shortDescription').val(exercise.shortDescription);
				$('.myForm #amount').val(exercise.amount);	
				$('.myForm #userId').val(exercise.userId);		
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