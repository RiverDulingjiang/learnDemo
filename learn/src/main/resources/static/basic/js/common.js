var root ="";
var ctx ="";
/**
 * get获取请求
 * */
function requestGet(url, data, callbackfun, async,dataType){
	commitData(url,data,callbackfun,async,"GET",dataType);
}
/**
 * post新增请求
 * */
function requestPost(url, data, callbackfun, async,dataType){
	contentType="application/json;charset=utf-8";
	commitData(url,data,callbackfun,async,"POST",dataType,contentType);
}
/**
 * put修改请求
 * */
function requestPut(url, data, callbackfun, async,dataType){
	contentType="application/json;charset=utf-8";
	commitData(url,data,callbackfun,async,"PUT",dataType,contentType);
}
/**
 * delete删除请求
 * */
function requestDelete(url, data, callbackfun, async,dataType){
	commitData(url,data,callbackfun,async,"DELETE",dataType);
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
	if (method == null||method ==undefined) {method = "POST";}
	if (dataType == null||dataType ==undefined) {dataType = "json";}
	if (contentType == null||contentType ==undefined) {contentType = "application/x-www-form-urlencoded";}	
	if (callbackfun.error == null) {callbackfun.error = function(data) {alert("请求发生错误");};}
	ctx = getRootPath();
	$.ajax({
		url : ctx +url,
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
				if(data.code==1000){//跳转到登陆页面
					top.location.href = ctx + "/login.html";
				}else{
					callbackfun.success(data);
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
/**
 * 获取项目根路径
 */
function getRootPath() {  
    var pathName = window.location.pathname.substring(1);  
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    var path =  window.location.protocol + '//' + window.location.host + '/' + webName;
    return path;  
}
