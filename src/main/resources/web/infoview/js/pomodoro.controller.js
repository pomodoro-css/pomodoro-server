angular.module('pomodoro').controller('pomodoroController', function(pomodoroService, $filter) {
	var vm = this;
	vm.users = pomodoroService.loadUsers();
});
