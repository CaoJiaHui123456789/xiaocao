jQuery("#i_fullSlide1").slide({
    titCell: ".hd ul",
    mainCell: ".bd ul",
    effect: "fold",
    autoPlay: true,
    autoPage: true,
    trigger: "click"
});
$.post("../../busi/getAllPark",{},function (data) {
    $("#content").attr("srcdoc",data[0].parkIntro);
});