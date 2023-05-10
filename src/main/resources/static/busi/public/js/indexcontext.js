/**
 * 
 */

 
function getPictures() {
    $.ajax({
        url: "../../busi/getImgList",
        success: function (result) {
            console.log(result);
            var pathlist = result;
            for (let i = 0; i <= pathlist.length - 1; i++) {
                var bdhtml = $(".bd ul").html();
                $(".bd ul").html(bdhtml +
                    "<li style=\"background:url(../../busi/picture/" + pathlist[i] + ") #fff center 0 no-repeat;width: 100%;" +
                    "background-repeat: no-repeat;\n" +
                    "\tbackground-size: cover;\n" +
                    "\t-webkit-background-size: cover;\n" +
                    "\t-o-background-size: cover;\n" +
                    "\tbackground-position: center 0;\"><a href=\"#\" target=\"_blank\"></a>\n" +
                    "</li>")
            }
            jQuery(".fullSlide").slide({
                titCell: ".hd ul",
                mainCell: ".bd ul",
                effect: "fold",
                autoPlay: true,
                autoPage: true,
                trigger: "click"
            });
        }
    });
}

function i_d6_list_c() {
    jQuery(".i_d6_list_c").slide({
        titCell: ".hd ol",
        mainCell: ".i_d6_list",
        autoPlay: true,
        effect: "left",
        vis: 4,
        scroll: 1,
        autoPage: true,
        pnLoop: true
    });
}

$(document).ready(function () {
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

    function loadwow() {
        var wow = new WOW({
            boxClass: 'wow',
            animateClass: 'animated',
            offset: 0,//100
            mobile: true,
            live: true
        });
        wow.init();
    }
    loadwow();

});



