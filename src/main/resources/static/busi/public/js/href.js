$('#maincontext a').unbind();
$("#maincontext a").click(function (){

    let url=$(this).attr("link");
    console.log(url);
    $("#maincontext").empty();
    $("#maincontext").load(url);
});