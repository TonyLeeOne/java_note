var i = 0;
$(function () {
    var view=$("#test-editormd-view").find("div:first");
    // 生成左侧边目录
    // view.wrap("<div class='col-sm-5'></div>")
    view.addClass("markdown-toc editormd-markdown-toc col-sm-2 col-sm-offset-7 col-md-2 col-md-offset-7");
    view.css({
        "position": "fixed",
        "padding-left": "1%",
        "padding-right": "1%",
        "margin-top": "1%"
    });
    $("ul").css({"list-style": "none"});

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
            $("#half").text(" "+data.recommend);
            $("#eye").text("　" + data.count);
        },
        error: function () {
            alert("error");
        }
    });

    $.ajax({
        method: "get",
        url: "/user?username=" + $("#uname").text(),
        sync: false,
        success: function (data) {
            if (data) {
                $("#img").attr("src", data.avatar);
                $("#git").attr("href", data.gitUrl);
            }
        },
        error: function () {
            console.log("error");
        }
    });
    $("#test-editormd-view").find("img").each(function(){
        $(this).css("width", "80%");
        $(this).css("height", "80%");
        $(this).wrap("<div align='center'></div>");
    })
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
    $("#empty").toggleClass("icon-star").toggleClass("icon-star-empty");
    $(this).toggleClass("button button-raised button-glow button-inverse button-sm");
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

