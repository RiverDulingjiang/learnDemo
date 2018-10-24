var root ="";
var ctx ="";
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
	if (method == null||method ==undefined) {
		// 默认post方式
		method = "post";
	}
	if (dataType == null||dataType ==undefined) {
		// 默认json数据类型
		dataType = "json";
	}
	if (async == undefined) {
		async = true;
	}
	if (callbackfun.error == null) {
		// 默认输出错误日志
		callbackfun.error = function(data) {
			alert("错误");
		};
	}
	ctx = getRootPath();
	$.ajax({
		url : ctx+url,
		dataType : dataType,
		type : method,
		data : data,
		async : async,
		crossDomain : true,
		headers : {
			"returntype" : "ajax/json"
		},
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
/**
 * 获取项目根路径
 */
function getRootPath() {  
    var pathName = window.location.pathname.substring(1);  
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    var path =  window.location.protocol + '//' + window.location.host + '/' + webName;
    return path;  
} 