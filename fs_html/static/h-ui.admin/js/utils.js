/**
 * 初始调用方法
 */
var utils = function(){};

common.dictOption = "";

utils.loadDictOption = function(type){
	var optHtml = '<option value="" >请选择</option>';
	if (!type) {
		return optHtml;
	}
	var url = "/dict/loadByType?type="+type;
	common.getData(url,function(data){
		var list = data.result;
		var optHtml = '<option value="" >请选择</option>';
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			optHtml+='<option value="'+obj.id+'">'+obj.name+'</option>';
		}
		common.dictOption=optHtml;
	});
	return optHtml;
}

utils.loadShopDictOption = function(type){
	var optHtml = '<option value="" >请选择</option>';
	if (!type) {
		return optHtml;
	}
	var url = "/shopDict/loadByType?type="+type;
	common.getData(url,function(data){
		var list = data.result;
		var optHtml = '<option value="" >请选择</option>';
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			optHtml+='<option value="'+obj.id+'">'+obj.name+'</option>';
		}
		common.dictOption=optHtml;
	});
	return optHtml;
}

