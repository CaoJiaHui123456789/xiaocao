 //栏目分类
 function getNewsTable() {
          $.ajax({
            url: "../../sysset/queryAllNewsTable",
            async:false
            ,success: function (result) {
              var htmls = ""
              var tablelist = result;
              for (var i = 0; i < tablelist.length ; i++) {
                htmls = htmls + "<li><a link=\"index39.html\">" + tablelist[i].newsHead + "</a></li>"

              }
              $("#tablelist").html(htmls);
            }
          })
        }

        getNewsTable();
        
//分页
var pagesNumber=1;
      var totalPage=0;
      function getNewsCont(pageNo) {
        $.ajax({
          url:"../../newsCont/querNewsCont",
          data:{pageSize:3,pageNo:pageNo},
          async:false,
          success:function (result) {
            var htmls="";
            totalPage=parseInt(result.total/3)+1;
            $("#currentPage").text(pageNo);
            $("#totalPage").text(totalPage);
            var newslist = result.rows;
            console.log(newslist);
            for(var i=0;i<newslist.length;i++){
              htmls=htmls+"<li class=\"wow flipInX\" data-wow-delay=\"0.6s\"> <a link=\"news/index35.html\"><img class=\"case_list_img\" src=\"../../newsCont/picture/"+result.rows[i].picture+"\" width=\"246\" height=\"154\"></a>\n" +
                      "              <div class=\"txt\">\n" +
                      "                <div class=\"txt1\"> <a class=\"sl\" link=\"news/index35.html\">"+result.rows[i].newsName+"</a>\n" +
                      "                  <div class=\"rq\">2022-12-20</div>\n" +
                      "                </div>\n" +
                      "                <div class=\"txt2 sl3\">"+result.rows[i].subtitleName+"</div>\n" +
                      "              </div>\n" +
                      "              <div class=\"clear\"></div>\n" +
                      "            </li>";
              console.log(htmls);
            }
            console.log(htmls);
            $("#news_list").html(htmls);
          }
        })
      }
      /*
      function getTitle() {
          $.post("demo_ajax_gethint.html",{suggest:txt},function(result){
              $("span").html(result);
          });
      }
       */
      getNewsCont(1);
      $("#getFirstPage").click(function () {
        getNewsCont(1);
      });
      $("#getBottomPage").click(function () {
        getNewsCont(totalPage);
      });
      $("#getLastPage").click(function (){
        if(pagesNumber>1) {
          pagesNumber--;
          getNewsCont(pagesNumber);
        }
      });
      $("#getNextPage").click(function (){
        if (pagesNumber<totalPage) {
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