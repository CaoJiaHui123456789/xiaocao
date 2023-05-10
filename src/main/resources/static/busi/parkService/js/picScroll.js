jQuery("#picScroll1").slide({ mainCell:"ul",autoPlay:false,effect:"left", vis:5, scroll:1, autoPage:true, pnLoop:true });
$('.picScroll_case li img').hover(function(){
    $('.c_i_img').attr("src",$(this).attr("lang"));
    $('.c_i_img').attr("lang",$(this).parent().attr("lang"));
    $('.picScroll_case li').removeClass("on");
    $(this).parent().addClass("on");
});
$('.c_i_bt_l').click(function(){
    if($('.c_i_img').attr("lang")!="0"){
        on_i=parseInt($('.c_i_img').attr("lang"))-1;
        $('.c_i_img').attr("src",$('.picScroll_case li:eq('+on_i+') img').attr("lang"));
        $('.c_i_img').attr("lang",$('.picScroll_case li:eq('+on_i+') img').parent().attr("lang"));
        $('.picScroll_case li').removeClass("on");
        $('.picScroll_case li:eq('+on_i+') img').parent().addClass("on");
    }
});
$('.c_i_bt_r').click(function(){
    if($('.c_i_img').attr("lang")!="4"){
        on_i=parseInt($('.c_i_img').attr("lang"))+1;
        $('.c_i_img').attr("src",$('.picScroll_case li:eq('+on_i+') img').attr("lang"));
        $('.c_i_img').attr("lang",$('.picScroll_case li:eq('+on_i+') img').parent().attr("lang"));
        $('.picScroll_case li').removeClass("on");
        $('.picScroll_case li:eq('+on_i+') img').parent().addClass("on");
    }
});