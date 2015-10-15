angular.module('pomodoro').controller('pomodoroController', function(pomodoroService) {
	var vm = this;
	vm.users = pomodoroService.loadUsers();
});
