/*
 * 左侧导航栏列表
 */
var navs = [{
	"title" : "后台首页",
	"icon" : "icon-computer",
	"href" : "page/main.html",
	"spread" : false
},{
	"title" : "组织管理",
	"icon" : "icon-text",
	"href" : "",
	"spread" : false,
	"children" : [
		{
			"title" : "部门管理",
			"icon" : "icon-text",
			"href" : "",
			"spread" : false
		},
		{
			"title" : "人员管理",
			"icon" : "icon-text",
			"href" : "./page/rbac/userList.html",
			"spread" : false
		}
	]
},{
	"title" : "权限管理",
	"icon" : "icon-text",
	"href" : "",
	"spread" : false,
	"children" : [
		{
			"title" : "角色管理",
			"icon" : "icon-text",
			"href" : "",
			"spread" : false
		},
		{
			"title" : "功能管理",
			"icon" : "icon-text",
			"href" : "",
			"spread" : false
		},
		{
			"title" : "权限分配",
			"icon" : "icon-text",
			"href" : "",
			"spread" : false
		}
	]
},{
	"title" : "数据库管理",
	"icon" : "icon-text",
	"href" : "",
	"spread" : false
}]