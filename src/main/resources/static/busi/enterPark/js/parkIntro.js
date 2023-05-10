$(document).ready(function () {
    base_url = document.location.href.substring(0, document.location.href.indexOf('parkService/parkService.html'), 0);
    $(".lightbox-2").lightbox({
        fitToScreen: false
    });
    $(".i_d2_nav li").hover(function () {
        $(this).parent().find("li").removeClass("on");
        $(this).addClass("on");
        $(".i_d2_list_c").css("display", "none");
        $(".i_d2_list_c:eq(" + $(this).index() + ")").css("display", "block");
    });
    $(".i_d2_list li").hover(function () {
        $(this).parent().find("li").removeClass("on");
        $(this).addClass("on");
    });
});