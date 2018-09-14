var root ="";

function requestGet(url, data, callbackfun, async,dataType){
	commitData(url,data,callbackfun,async,"get",dataType);
}
function requestPost(url, data, callbackfun, async,dataType){
	contentType="application/json;charset=utf-8"
	postCommitData(url,data,callbackfun,async,"post",dataType,contentType);
}
function requestPut(url, data, callbackfun, async,dataType){
	commitData(url,data,callbackfun,async,"put",dataType);
}
function requestDelete(url, data, callbackfun, async,dataType){
	commitData(url,data,callbackfun,async,"delete",dataType);
}
/**
 * ajax调用
 * @param url 链接地址
 * @param data 数据
 * @param callbackfun 回调函数
 * @param async，是否异步
 * @param method 调用方法，默认为post
 * @param dataType 返回参数类型,默认为json
 */
function commitData(url, data, callbackfun, async, method, dataType) {
	if (method == null||method ==undefined) {method = "post";}
	if (dataType == null||dataType ==undefined) {dataType = "json";}
	if (async == undefined) {async = true;}
	if (callbackfun.error == null) {callbackfun.error = function(data) {alert("错误");};} 
	$.ajax({
		url : url,
		dataType : dataType,
		type : method,
		data : data,
		async : async,
		crossDomain : true,
		headers : {"returntype" : "ajax/json"},
		traditional : true,
		success : function(data) {
			if(callbackfun.success) {
				callbackfun.success(data);
			}else{
				if(data.code==1000){
					top.location.href = "./index.html"
				}
			}
		},
		error : callbackfun.error
	});
}
//
function postCommitData(url, data, callbackfun, async, method, dataType,contentType) {
	if (method == null||method ==undefined) {method = "post";}
	if (dataType == null||dataType ==undefined) {dataType = "json";}
	if (async == undefined) {async = true;}
	if (callbackfun.error == null) {callbackfun.error = function(data) {alert("错误");};} 
	$.ajax({
		url : url,
		dataType : dataType,
		type : method,
		data : data,
		async : async,
		contentType:contentType,
		crossDomain : true,
		headers : {"returntype" : "ajax/json"},
		traditional : true,
		success : function(data) {
			if(callbackfun.success) {
				callbackfun.success(data);
			}else{
				if(data.code==1000){
					top.location.href = "./index.html"
				}
			}
		},
		error : callbackfun.error
	});
}