define(['app'],function(app){
	app.controller("indexCtrl",['$scope',function($scope){
		$scope.username = 'tony';
		console.log('index controller');
		$scope.initData = {
            "menus": [
                { "remark": "首页", "url": "#" },
                { "remark": "前端", "url": "#" },
                { "remark": "下载中心", "url": "#!/download" },
                { "remark": "工具", "url": "#" }
            ]
		};
	}]);
});