var i = 0;
$(function () {

    $(window).scroll(function () {
        if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
            document.getElementById("myBtn").style.display = "block";
        } else {
            document.getElementById("myBtn").style.display = "none";
        }
    });

    $.ajax({
        method: "get",
        url: "/info?nid=" + $("#empty").attr("value"),
        success: function (data) {
            $("#empty").text("　"+data.recommend);
        },
        error: function () {
            alert("error");
        }
    });

});

testEditormdView2 = editormd.markdownToHTML("test-editormd-view", {
    htmlDecode: "style,script,iframe",  // you can filter tags decode
    emoji: true,
    taskList: true,
    tex: true,  // 默认不解析
    flowChart: true,  // 默认不解析
    sequenceDiagram: true  // 默认不解析
});



$("#test").click(function () {
    $("#empty").toggleClass("icon-thumbs-down").toggleClass("icon-thumbs-up");
    if (i == 0) {
        ++i;
        $.ajax({
            method: "get",
            url: "/info/star?id=" + $("#empty").attr("value"),
            success: function (data) {
                $("#empty").text("　" + data);
            },
            error: function () {
                console.log("请求失败");
            }
        });
    } else {
        --i;
        $.ajax({
            method: "get",
            url: "/info/unStar?id=" + $("#empty").attr("value"),
            success: function (data) {
                $("#empty").text("　" + data);
            },
            error: function () {
                console.log("请求失败");
            }
        });
    }
});

function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

