requirejs.config({baseUrl:"/scripts/"});
require([
         "app",
         "controller/index"
         ],
         function(){
			angular.bootstrap(document,["zhiyun"]);
		});