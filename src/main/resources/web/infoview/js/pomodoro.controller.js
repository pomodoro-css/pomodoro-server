angular.module('pomodoro').controller('pomodoroController', function(pomodoroService, pomodoroWebSocket, $filter, $interval, $scope) {
	var vm = this;
	    
	pomodoroWebSocket.start(function (msg) {
		var response = angular.fromJson(msg);

		switch (response.method) {
		case 'open':
			$scope.$apply(function(){
				vm.users = pomodoroService.loadUsers();
				vm.biggestTomato = pomodoroService.loadBiggestTomato();
			});
			break;
		case 'addUser':
			$scope.$apply(function(){
				vm.users.push(response.object);
		     });
			break;
		case 'deleteUser':
			removeUser(response.object);
			break;
		case 'newBiggestTomato':
			$scope.$apply(function(){
				vm.biggestTomato = response.object;
			});
			break;
		case 'stop':
		case 'start':
		case 'offline':
		case 'online':
			updateUser(response.object);
			break;
		case 'error':
			$scope.$apply(function(){
				vm.users = {};
				vm.biggestTomato = {};
		    });
			break;
		}
		console.log("Got message", msg, this);
    });
	
	var refreshData = function() {
		vm.users = pomodoroService.loadUsers();
		vm.biggestTomato = pomodoroService.loadBiggestTomato();
	};

	var updateUser = function(user){
		vm.users = $.grep(vm.users, function(e){ 
		     return e.nr != user.nr; 
		});
		
		$scope.$apply(function(){
			vm.users.push(user);
	    });
	};
	
	var removeUser = function(user){
		$scope.$apply(function(){
			vm.users = $.grep(vm.users, function(e){ 
			     return e.nr != user.nr; 
			});
	    });
	};
});
