var pageNum = 1;
var total = 2;
$(function () {
    $("#loading").hide();
    $("#loadingOver").hide();
    $(window).scroll(function () {
        //$(window).scrollTop()这个方法是当前滚动条滚动的距离
        //$(window).height()获取当前窗体的高度
        //$(document).height()获取当前文档的高度
        if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
            document.getElementById("myBtn").style.display = "block";
        } else {
            document.getElementById("myBtn").style.display = "none";
        }
        if (($(window).scrollTop()) >= ($(document).height() - $(window).height())) {
            ++pageNum;
            if (pageNum <= total) {
                $("#loading").show();
                $.ajax({
                    method: "post",
                    url: "/note/more?pageNum=" + pageNum + "&size=10",
                    success: function (data) {
                        total = data.pageCount;
                        $("#loading").hide();
                        console.log(data);
                        appendData(data);
                    },
                    error: function () {
                    }
                });
            } else {
                $("#loadingOver").show();
            }
        }
    });
});

function appendData(data) {
    if (data.records != null) {
        $.each(data.records, function (i, record) {
            $("#article").append("<a href='/note/view?id="+record.id +
                "'               style=\"text-decoration: none;text-decoration-line: none;color: black\">\n" +
                "                <div class=\"jumbotron\">\n" +
                "                    <div class=\"row\">\n" +
                "                        <div class=\"col-sm-8\">\n" +
                "                            <h3>"+record.noteName+"</h3>\n" +
                "                            <i class=\"icon-user-md\"></i> <span>"+record.creator+"</span>　\n" +
                "                            <i class=\"icon-asterisk\"></i> <span>"+record.category+"</span>　\n" +
                "                            <i class=\"icon-edit\" th:if=\"${record.modifyDate}!=null\"></i> <span\n" +
                "                                >"+record.modifyDate+"</span>\n" +
                "                        </div>\n" +
                "                        <div class=\"col-sm-4\">\n" +
                "                            <img src='"+record.imageUrl+"' class=\"img img-responsive img-rounded\"\n" +
                "                                 style=\"width: 80%;height: 80%\"\n" +
                "                                 th:if=\"${record.imageUrl}!=null\"/>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </a>"
            );
        });
    }
}

function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}