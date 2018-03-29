define(['app'],function(app){
	console.log(app.register);
	app.register.controller("homeIndexCtrl",['$scope',function($scope){
		$scope.userList = [
			                   {
			                	   'id':1,
			                	   'name':'tony'
			                   },
			                   {
			                	   'id':2,
			                	   'name':'tom'
			                   }
		                   ];
		console.log('home index controller');
	}]);
	
});