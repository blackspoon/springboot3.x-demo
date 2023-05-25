$(function() {
	function checkInput() {
		var message = [];
		var categoryName = $('input[name="categoryName"]').val().trim();
		if (categoryName === '') {
			message.push('カテゴリ名が設定されていません')
		} else if (categoryName.length > 50) {
			message.push('カテゴリ名は50文字までです')
		}
		return message;
	}

	$('#editButton').on("click", function() {
		var form = $('#editForm');
		var message = checkInput();
		if (message.length != 0) {
			alert(message.join('\n'));
		} else {
			if (confirm("使い方 カテゴリを更新してよろしいですか？")) {
				$("<input>", {
					type : 'hidden',
					name : 'oneTimeToken',
					value : $("#oneTimeToken").val()
				}).appendTo(form);
				form.attr('action', $(this).data('action'));
				form.submit();
			}
		}
	});

	$('#newButton').on("click", function() {
		var form = $('#editForm');
		var message = checkInput();
		if (message.length != 0) {
			alert(message.join('\n'));
		} else {
			if (confirm("使い方 カテゴリを登録してよろしいですか？")) {
				$("<input>", {
					type : 'hidden',
					name : 'oneTimeToken',
					value : $("#oneTimeToken").val()
				}).appendTo(form);
				form.attr('action', $(this).data('action'));
				form.submit();
			}
		}
	});

	$('#deleteButton').on("click", function() {
		var form = $('#editForm');
		if (confirm("使い方 カテゴリを削除してよろしいですか？")) {
			$("<input>", {
				type : 'hidden',
				name : 'oneTimeToken',
				value : $("#oneTimeToken").val()
			}).appendTo(form);
			form.attr('action', $(this).data('action'));
			form.submit();
		}
	});
});
