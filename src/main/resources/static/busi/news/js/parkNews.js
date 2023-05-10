//栏目分类
function getNewsTable() {
    $.ajax({
        url: "../../busi/queryAllNewsTable",
        async: false,
        success: function (result) {
            var htmls = ""
            var tablelist = result;
            for (var i = 0; i < tablelist.length; i++) {
                htmls = htmls
                    + "<li><a href=\"javascript:void();\">"
                    + tablelist[i].newsHead
                    + "</a></li>"

            }
            $("#tablelist").html(htmls);
        }
    })
}

getNewsTable();

//分页
var pagesNumber = 1;
var totalPage = 0;

function getNewsCont(pageNo) {
        $.ajax({
            url: "../../busi/querNewsCont",
            data: {
                pageSize: 3,
                pageNo: pageNo
            },
            async: false,
            success: function (result) {
                var htmls = "";
                totalPage = parseInt(result.total / 3) + 1;
                $("#currentPage").text(pageNo);
                $("#totalPage").text(totalPage);
                var newslist = result.rows;
              
                for (var i = 0; i < newslist.length; i++) {
                    htmls = htmls
                        + "<li class=\"wow flipInX\" data-wow-delay=\"0.6s\"> <a link="
                        + "\"news/parkNews_content.html?newcontId="+ result.rows[i].newcontId+"\"><img class=\"case_list_img\" src=\"../../busi/picture/" + result.rows[i].picture + "\" width=\"246\" height=\"154\"></a>\n"
                        + "              <div class=\"txt\">\n"
                        + "                <div class=\"txt1\"> <a class=\"sl\" link="
                        + "\"news/parkNews_content.html?newcontId="+ result.rows[i].newcontId+"\">"
                        + result.rows[i].newsName
                        + "</a>\n"
                        + "                  <div class=\"rq\">"+result.rows[i].dateDay  + "</div>\n"
                        + "                </div>\n"
                        + "                <div class=\"txt2 sl3\">"
                        + result.rows[i].content
                        + "</div>\n"
                        + "              </div>\n"
                        + "              <div class=\"clear\"></div>\n"
                        + "               <input type=\"hidden\" id=\"hiddenId\" value=\" " + result.rows[i].newcontId + "\" /> "
                    "            </li>";                   
                }         
                $("#news_list").html(htmls);
            }
        })
}

getNewsCont(1);
$("#getFirstPage").click(function () {
    getNewsCont(1);
});
$("#getBottomPage").click(function () {
    getNewsCont(totalPage);
});
$("#getLastPage").click(function () {
    if (pagesNumber > 1) {
        pagesNumber--;
        getNewsCont(pagesNumber);
    }
});
$("#getNextPage").click(function () {
    if (pagesNumber < totalPage) {
        pagesNumber++;
        getNewsCont(pagesNumber);
    }
});


//
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


$('#maincontext a').unbind();
$("#maincontext a").click(function (){
    let url=$(this).attr("link");
    var str=url.split("?");
 
    console.log(str[1]);  
    $("#maincontext").empty();
    sessionStorage.setItem("id", str[1]);
    $("#maincontext").load(url);
});