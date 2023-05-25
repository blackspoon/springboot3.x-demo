$(function(){
  $("#usageCategoryList tbody").sortable({
    cursor: "move",
    handle: ".sortArrow",
  });
  $("#usageCategoryList tbody").disableSelection();

  $("#updateBtn").click(function(){
    if(confirm("表示順番を変更してよろしいですか？")) {
      var form = $("#sortForm");
      $("<input>", {type: 'hidden', name: 'oneTimeToken', value: $("#oneTimeToken").val()}).appendTo(form);
      form.attr("action","/recipe-tool/adminUsageCategory/sort/update/");
      form.submit();
    } else {
      return false;
    }
  });
});
