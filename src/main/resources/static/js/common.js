$(function () {
    //存储url数量和背景色
    if (!localStorage) {
        alert("浏览器不支持localstorage");
    } else {
        var navColor = localStorage.getItem("nav");
        var bgColor = localStorage.getItem("bodyColor");
        if (navColor) {
            $("#navigation").attr("class",navColor);
        }
        if (bgColor) {
            document.body.style.backgroundColor = bgColor;
        }
    }
})

$("#change").click(function () {
    $("#navigation").toggleClass("navbar navbar-inverse navbar-fixed-top").toggleClass("navbar navbar-default navbar-fixed-top");
    if (!localStorage) {
        alert("浏览器不支持localstorage");
    } else {
        localStorage.setItem("nav", $("#navigation").attr("class"));
    }
});

$("#search").click(function () {
    location.href="/note/find?pageNum=1&size=10&content="+$("#searchContent").val();
});

//设置网页背景色
$(".input-sm").each(function () {
    $(this).click(function () {
        document.body.style.backgroundColor = $(this).attr('value');
        if (!localStorage) {
            alert("浏览器不支持localstorage");
        } else {
            localStorage.setItem("bodyColor", $(this).attr('value'));
        }
    });
});