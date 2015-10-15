angular.module('pomodoro').factory('pomodoroService', function($resource) {

	var service = {
		loadUsers : loadUsers
	};
	return service;

	function loadUsers() {
		return users().query();
	}
	
	function users() {
		return $resource('users', {}, {
			query : {
				method : 'GET',
				isArray : true
			}
		});
	}

});