angular.module('pomodoro').controller('pomodoroController', function(pomodoroService, pomodoroWebSocket, $filter, $interval, $scope) {
	var vm = this;
	
	$scope.pomodoroWebSocket = pomodoroWebSocket;	
    
	$scope.users = pomodoroService.loadUsers();
	$scope.biggestTomato = pomodoroService.loadBiggestTomato();
	
	vm.users = pomodoroWebSocket.users;
	vm.biggestTomato = pomodoroWebSocket.biggestTomato;
	
	pomodoroWebSocket.start(function (msg) {
		var response = angular.fromJson(msg);

		switch (response.method) {
		case 'addUser':
			
			$scope.$apply(function(){
				$scope.users.push(response.object);
		     });
			break;
		}
		console.log("Got message", msg, this);
    });
	
	var refreshData = function() {
		vm.users = pomodoroService.loadUsers();
		vm.biggestTomato = pomodoroService.loadBiggestTomato();
	};

	//var promise = $interval(refreshData, 4000);

	// Cancel interval on page changes
	$scope.$on('$destroy', function(){
	    if (angular.isDefined(promise)) {
	        $interval.cancel(promise);
	        promise = undefined;
	    }
	});
});
