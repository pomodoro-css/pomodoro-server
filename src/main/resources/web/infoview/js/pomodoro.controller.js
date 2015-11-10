angular.module('pomodoro').controller('pomodoroController', function(pomodoroService, pomodoroWebSocket, $filter, $interval, $scope) {
	var vm = this;
	    
	vm.users = pomodoroService.loadUsers();
	vm.biggestTomato = pomodoroService.loadBiggestTomato();
	
	pomodoroWebSocket.start(function (msg) {
		var response = angular.fromJson(msg);

		switch (response.method) {
		case 'addUser':
			
			$scope.$apply(function(){
				vm.users.push(response.object);
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
