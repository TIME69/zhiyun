define("app", [], function() {
	/**
	 * 设置路由相关信息，同时加载相关js
	 * @param url [模块相对路径]
	 * @param ctrl [模块控制器名称]
	 * @param reqJs [模块对应的控制器JS文件]
	 */
	var setRoute = function(url,ctrl,reqJs){
		var route = {};
		if (url == ''){
			route.template = "";
		}else{
			route.templateUrl = "views"+url+".html?_ran="+Math.random();
		}
		route.controller =  ctrl;
		route.resolve = {
				load:['$q','$rootScope',
				      function($q,$rootScope){
						var defer = $q.defer();
						require(["controller/"+reqJs],function(){
							defer.resolve();
							$rootScope.$apply();
						});
						return defer.promise;
					  }]
		};
		return route;
	}
	
	var app = angular.module("zhiyun", [ 'ngRoute' ]);
	app.config(['$controllerProvider',function($controllerProvider){
		app.register = {
			controller:$controllerProvider.register
		};
	}]);
	
	app.config([ '$routeProvider', function($routeProvider) {
		$routeProvider
		.when('/',setRoute("/home/index","homeIndexCtrl","home/index"))
		.when('/download',setRoute("/download/index","downloadIndexCtrl","download/index"))
        .when('/computers',{template:'这是电脑分类页面'})
        .when('/printers',{template:'这是打印机页面'})
        .otherwise({redirectTo:'/'});

	} ]);
	
	return app;
});