//栏目分类

function getNewsTable() {
      $.ajax({
          url: "../../busi/queryAllNewsTable",
          async:false,
          success: function (result) {
              var htmls = ""
              var tablelist = result;
              for (var i = 0; i < tablelist.length ; i++) {
                  htmls = htmls + "<li><a href=\"javascript:void();\">" + tablelist[i].newsHead + "</a></li>"

              }
              $("#tablelist").html(htmls);
          }
      })
  }
getNewsTable();

jQuery("#i_fullSlide1").slide({
        titCell: ".hd ul",
        mainCell: ".bd ul",
        effect: "fold",
        autoPlay: true,
        autoPage: true,
        trigger: "click"
      });
      
      var newcontId=sessionStorage.getItem("id");
      console.log(newcontId);
	
 	 $.post("../../busi/queryById",newcontId,function (data) {
      console.log(data.rows.newsName);
      $("#newsName").html(data.rows.newsName);
      $("#subtitleName").html(data.rows.subtitleName);
      $("#dateDay").html(data.rows.dateDay);
      $("#author").html(data.rows.author);
      $("#source").html(data.rows.source);
      $("#content").html(data.rows.content);
    });
 
        
//


	var wow = new WOW({
    boxClass: 'wow',
    animateClass: 'animated',
    offset:0,//100
    mobile: true,
    live: true
});
wow.init();


//从URL中获取问号后的参数




