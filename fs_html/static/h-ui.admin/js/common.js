/**
 * 初始调用方法
 */
var common = function(){};

common.basePath = "http://192.168.1.102:8888";
common.htmlPath = "";
common.cookieKey = "token";
common.cookieParams = "params";
common.currentMember = {};
common.v = new Date().getTime();

common.initCurrentMember = function() {
	var key = "currentMember";
	var currentMemberStr = $.cookie(key); 
	var currentMember =  $.parseJSON(currentMemberStr?currentMemberStr:"{}");
	if(!currentMember.wxId){
		var url = "/member/getMemberUUID";
		common.getData(url,function(data){
			common.currentMember["wxId"]=data.result.wxId;
			common.currentMember["name"]=data.result.name;
			$.cookie(key, JSON.stringify(common.currentMember));
		});
	}else if(!common.currentMember.wxId){
		common.currentMember["wxId"]=currentMember.wxId;
		common.currentMember["name"]=currentMember.name;
	}
}

common.formatDate = function(timestamp){
	if(!timestamp){
		return "";
	}
	var time = new Date(timestamp);
	var year = time.getFullYear();
	var month = time.getMonth()+1;
	var date = time.getDate();
	var hours = time.getHours();
	var minutes = time.getMinutes();
	var seconds = time.getSeconds();
	return year+'-'+add0(month)+'-'+add0(date)+' '+add0(hours)+':'+add0(minutes)+':'+add0(seconds);
}
common.alertX = function(msg){
	alert(msg);
}
common.msg = function(msg){
	layer.msg(msg,{icon:1,time:1000});
}
common.msg = function(msg,times){
	layer.msg(msg,{icon:1,time:times});
}
common.required = function(val,msg){
	if(!val){
		if(msg){
			common.alertX(msg);
		}else{
			common.alertX("存在必填信息未填写");
		}
		return true;
	}
	return false;
}
function add0(m){
	return m<10?'0'+m:m;
}
/**
 * 公共的异步请求方法(GET)
 */
common.getData =function(url, callback) {
	if (!url) {
		return;
	}
	url = common.basePath+url;
	var data={token:$.cookie(common.cookieKey)};
	$.ajax({
		url : url,
		type : "GET",
		cache : false,
		timeout : 30000,
		async:false,
		dataType : "json",
		data: data,
		success : function(data) {
			if(data.code!=9000){
				common.alertX(data.msg);
				if(data.code==9001006){
					window.location.href=common.htmlPath+"/login.html";
				}
			}else{
				callback(data);
			}
		},
		error : function(data) {
			common.alertX("请求失败");
		}
	});
}

/**
 * 公共的异步请求方法(POST)
 */
common.postData =function(url, data, callback) {
	if (!url) {
		return;
	}
	data['token']=$.cookie(common.cookieKey);
	url = common.basePath+url;
	$.ajax({
		type : "POST",
		url : url,
		timeout : 30000,
		data : data,
		dataType : "json",
		success : function(data) {
			if(data.code!=9000){
				common.alertX(data.msg);
			}else{
				callback(data);
			}
		},
		error : function(data) {
			common.alertX("请求失败");
		}
	});
}

/**
 * 同步get方法
 * @param url
 * @param callback
 */
common.syncGet =function(url,callback) {
	if (!url) {
		return;
	}
	url = common.basePath+url;
	$.ajax({
		url : url,
		type : "GET",
		cache : false,
		timeout : 30000,
		async:false,
		dataType : "json",
		success : function(data) {
			if(data.code!=9000){
				common.alertX(data.msg);
			}else{
				callback(data);
			}
		},
		error : function(data) {
			common.alertX("请求失败");
		}
	});
}

/**
 * 公共的异步请求方法(post)
 */
common.postAsyncData =function(url, data, callback) {
	if (!url) {
		return;
	}
	url = common.basePath+url;
	$.ajax({
		url : url,
		type : "POST",
		cache : false,
		timeout : 30000,
		async:false,
		dataType : "json",
		data: data,
		traditional: true,
		success : function(data) {
			if(data.code!=9000){
				common.alertX(data.msg);
			}else{
				callback(data);
			}
		},
		error : function(data) {
			common.alertX("请求失败");
		}
	});
}

common.addParams = function(params){
	$.cookie(common.cookieParams, JSON.stringify(params)); 
}

common.getParams = function(){
	if(!$.cookie(common.cookieParams)){
		return {};
	}
	var params = jQuery.parseJSON($.cookie(common.cookieParams)); 
	return params;
}

common.back = function(url){
	if(url){
		window.location.href=common.htmlPath+url;
	}
}
/**
 * 获取当前页面路径
 */
$(function(){ 
　　var curWwwPath=window.document.location.href;  
	var pathName=window.document.location.pathname;  
	var pos=curWwwPath.indexOf(pathName);  
	common.htmlPath=curWwwPath.substring(0,pos);  
	common.initCurrentMember();
}); 


