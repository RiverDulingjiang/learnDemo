/*
 * 在iframe层中打开新界面
 * @param url
 */
function openWindow(url){
	layer.open({
		type : 2,
		title : false,
		resize : false,
		closeBtn : 0,
		area : [ '100%', '100%' ],
		content : url
	});
}
/*
 * 关闭界面
 */
function closeWindow(){
	var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
	parent.layer.close(index);
}