console.clear();

$(document).ready(function(){
    var path=getRootPath();
    console.log(path);
    console.log("111");
    addBuildingOption();
    
        let selectDom = document.querySelector('#selectResouce'); //获取下拉框元素
    	selectDom.onchange = function() {
            let index = this.options.selectedIndex;
            let value = this.options[index].value;
            if(value=="引进资金"){
                document.getElementById("resouceAreaLabel").style.display="inline";
                document.getElementById("resouceAreaInput").style.visibility="visible"          
            }   
        } 



        let selectBuilding = document.querySelector('#building'); //选择园区后加载对应的房屋
    	selectBuilding.onchange = function() {
            let index = this.options.selectedIndex;
            let value = this.options[index].value;
            console.log(value);
            addHouseOption(value);

        } 
    
    
    
    
 function addBuildingOption(){
    //根据id查找对象，
    var obj=document.getElementById('building');
    $.ajax({
        url:path+"/busi/getAllBuilding",
        
        type: "post",
		async: true,
		success:function(data){
            let newarr=[];    
            data.forEach((element,index) => { 
                obj.options[index]=new Option(element.buildingName,element.buildingId);            
            })		
		},
    })
}   

function addHouseOption(buildingId){
	
    //根据id查找对象，
    var obj=document.getElementById('house');
    $.ajax({
        url:path+"/busi/queryHouse",
        type: "post",
        data:{ "buildingId" : buildingId },
		async: true,
		success:function(data){
			console.log(data);
            data.forEach((element,index) => { 
                obj.options[index]=new Option(element.houseCode,element.houseId);            
            })		
		},
    })
}  
    
    
})













// other option input
