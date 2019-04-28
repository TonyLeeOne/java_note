
Messenger.options = {
    extraClasses: 'messenger-fixed messenger-on-top',
    theme: 'future'
}
$(function () {
    editormd("test-editormd", {
        placeholder: "输入内容...",
        width: "100%",
        height: 440,
        syncScrolling: "single",
        codeFold: true,
        // autoHeight : true,
        //你的lib目录的路径，我这边用JSP做测试的
        tocm: true, // Using [TOCM]
        tex: true, // 开启科学公式TeX语言支持，默认关闭
        flowChart: true, // 开启流程图支持，默认关闭
        path: "/editormd/lib/",
        //这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
        saveHTMLToTextarea: true,
        // toolbarIcons:function(){
        //     return ["undo", "redo", "|", "bold", "hr", "|", "preview", "watch", "|", "file", "faicon"];
        // },
        emoji:true,
        sequenceDiagram: true,
        // taskList:true,
        imageUpload: true,
        imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL: "/file/upload",
        onload: function () {
            this.width("100%");
            this.height(480);
        },
        onfullscreen: function () {
            $("header").hide();
            $("#btnGroup").hide();
            $("#test").hide();
        },
        onfullscreenExit: function () {
            $("header").show();
            $("#btnGroup").show();
            $("#test").show();
        }
    });

});

$("#submit").click(
    function () {
        var note = {};
        note.id = $("#noteId").val();
        note.noteName = $("#title").val();
        note.category = $("#category").val();
        note.content = $("#content").val();
        note.imageUrl = $("#imageUrl").val();
        note.state = '1';
        submitNote(note);
    }
)

$("#publisher").click(
    function () {
        var note = {};
        note.id = $("#noteId").val();
        note.noteName = $("#title").val();
        note.category = $("#category").val();
        note.content = $("#content").val();
        note.imageUrl = $("#imageUrl").val();
        note.state = '4';
        submitNote(note);
    }
)

$("#draft").click(
    function () {
        var note = {};
        note.id = $("#noteId").val();
        note.noteName = $("#title").val();
        note.category = $("#category").val();
        note.content = $("#content").val();
        note.imageUrl = $("#imageUrl").val();
        note.state = '2';
        submitNote(note);
    }
)

$("#personal").click(
    function () {
        var note = {};
        note.id = $("#noteId").val();
        note.noteName = $("#title").val();
        note.category = $("#category").val();
        note.content = $("#content").val();
        note.imageUrl = $("#imageUrl").val();
        note.state = '3';
        submitNote(note);
    }
)

function submitNote(note) {
    $.ajax({
        method: 'post',
        url: '/note',
        data: JSON.stringify(note),
        contentType: 'application/json;charset=utf-8',
        success: function (data) {
            Messenger().post({
                message: "保存成功！！",
                type: "info",
                showCloseButton: true
            });
            setInterval(function () {
                window.location.href = "/note/list?pageNum=1&size=10";
            }, 2000);
        },
        error: function () {
            Messenger().post({
                message: "保存失败！！",
                type: "error",
                showCloseButton: true
            });
        }
    })
}

setInterval(refresh,300000);
function refresh() {
    var note={};
    note.id=$("#noteId").val();
    note.noteName = $("#title").val();
    note.category = $("#category").val();
    note.content = $("#content").val();
    note.imageUrl = $("#imageUrl").val();
    note.state = '2';
    if(note.noteName&&note.category&&note.content){
        document.getElementById("sync").style.display = "block";
        $.ajax({
            method: 'post',
            url: '/note/sync',
            data: JSON.stringify(note),
            contentType: 'application/json;charset=utf-8',
            success:function (data) {
                document.getElementById("sync").style.display = "none";
                $("#noteId").val(data);
                Messenger().post({
                    message: "自动保存草稿成功...",
                    type: "info",
                    showCloseButton: true
                });
            },
            error:function () {
                Messenger().post({
                    message: "自动保存草稿失败...",
                    type: "error",
                    showCloseButton: true
                });
            }

        });
    }
}