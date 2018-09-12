var root ="";

function requestGet(url, data, callbackfun, async,dataType){
	commitData(url,data,callbackfun,async,"get",dataType);
}
function requestPost(url, data, callbackfun, async,dataType){
	commitData(url,data,callbackfun,async,"post",dataType);
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
	$.ajax({
		url : url,
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