angular.module('pomodoro').controller('pomodoroController', function(pomodoroService, $filter, $interval, $scope) {
	var vm = this;
	vm.users = pomodoroService.loadUsers();
	
	var refreshData = function() {
		vm.users = pomodoroService.loadUsers();
	};

	var promise = $interval(refreshData, 1000);

	// Cancel interval on page changes
	$scope.$on('$destroy', function(){
	    if (angular.isDefined(promise)) {
	        $interval.cancel(promise);
	        promise = undefined;
	    }
	});
});
