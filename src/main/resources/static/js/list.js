Messenger.options = {
    extraClasses: 'messenger-fixed messenger-on-top',
    theme: 'flat'
}

$("[id='delete']").each(function () {
        $(this).click(function () {
            $.ajax({
                method: 'delete',
                url: '/note?id=' + $(this).attr('value'),
                success: function (data) {
                    Messenger().post({
                        message: "删除成功！！",
                        type: "info",
                        showCloseButton: true
                    });
                    setInterval(function () {
                        location.reload()
                    }, 2000);
                },
                error: function () {
                    Messenger().post({
                        message: "删除失败！！",
                        type: "error",
                        showCloseButton: true
                    });
                }
            });
        });
    }
);

$("#search1").click(function () {
    var noteName = $("#noteName").val();
    var category = $("#category").val();
    var state = $("#state").val();
    window.location.href = "/note/search?noteName=" + noteName + "&category=" + category + "&state=" + state + "&pageNum=1&size=10";
});
