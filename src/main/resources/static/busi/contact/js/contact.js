//信息内容
jQuery("#i_fullSlide1").slide({
            titCell: ".hd ul",
            mainCell: ".bd ul",
            effect: "fold",
            autoPlay: true,
            autoPage: true,
            trigger: "click"
          });
        $.post("../../busi/getAllPark",{},function (data) {
          $("#phone").html(data[0].parkPhone);
          $("#mailbox").html(data[0].parkMailbox);
          $("#siteurl").html(data[0].parkSiteurl);
          $("#parkname").html(data[0].parkName);
          $("#address").html(data[0].parkAddress);
        });
        
        //
        function loadwow() {
	        var wow = new WOW({
			    boxClass: 'wow',
			    animateClass: 'animated',
			    offset:0,//100
			    mobile: true,
			    live: true
			});
			wow.init();
		}