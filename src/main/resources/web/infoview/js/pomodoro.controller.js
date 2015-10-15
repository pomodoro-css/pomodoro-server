angular.module('pomodoro').controller('pomodoroController', function(pomodoroService, $filter, $interval, $scope) {
	var vm = this;
	vm.users = pomodoroService.loadUsers();
	vm.biggestTomato = pomodoroService.loadBiggestTomato();
	
	var refreshData = function() {
		vm.users = pomodoroService.loadUsers();
		vm.biggestTomato = pomodoroService.loadBiggestTomato();
	};

	var promise = $interval(refreshData, 4000);

	// Cancel interval on page changes
	$scope.$on('$destroy', function(){
	    if (angular.isDefined(promise)) {
	        $interval.cancel(promise);
	        promise = undefined;
	    }
	});
});
