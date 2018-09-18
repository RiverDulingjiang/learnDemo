var root ="";

/**
 * get获取请求
 * */
function requestGet(url, data, callbackfun, async,dataType){
	commitData(url,data,callbackfun,async,"get",dataType,contentType);
}
/**
 * post新增请求
 * */
function requestPost(url, data, callbackfun, async,dataType){
	contentType="application/json;charset=utf-8"
	commitData(url,data,callbackfun,async,"post",dataType,contentType);
}
/**
 * put修改请求
 * */
function requestPut(url, data, callbackfun, async,dataType){
	commitData(url,data,callbackfun,async,"put",dataType,contentType);
}
/**
 * delete删除请求
 * */
function requestDelete(url, data, callbackfun, async,dataType){
	commitData(url,data,callbackfun,async,"delete",dataType,contentType);
}
/**
 * ajax调用
 * @param url 链接地址
 * @param data 数据
 * @param callbackfun 回调函数
 * @param async，是否异步
 * @param method 调用方法，默认为post
 * @param dataType 返回参数类型,默认为json
 * @param contentType 特别重要，默认application/x-www-form-urlencoded
 */
function commitData(url, data, callbackfun, async, method, dataType,contentType) {
	if (async == undefined) {async = true;}
	if (method == null||method ==undefined) {method = "post";}
	if (dataType == null||dataType ==undefined) {dataType = "json";}
	if (contentType == null||contentType ==undefined) {contentType = "application/x-www-form-urlencoded";}	
	if (callbackfun.error == null) {callbackfun.error = function(data) {alert("错误");};} 
	$.ajax({
		url : url,
		data : data,
		async : async,
		type : method,
		dataType : dataType,		
		contentType:contentType,
		crossDomain : true,
		headers : {"returntype" : "ajax/json"},
		traditional : true,
		success : function(data) {
			if(callbackfun.success) {
				callbackfun.success(data);
			}else{
				if(data.code==1000){
					top.location.href = "/index.html"
				}
			}
		},
		error : callbackfun.error
	});
}
/**
 * 获取url地址的参数值
 * @param field 参数字段
 */
function getUrlField(field) {
	return getFieldByUrl(window.location.search, field);
}
/**
 * 正则表达式获取参数
 * @param url 链接地址
 * @param field 参数字段
 */
function getFieldByUrl(url, field) {
	var reg = new RegExp("(^|&|\\?)" + field + "=([^&]*)(&|$)", "i");
	var r = url.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return "";
}