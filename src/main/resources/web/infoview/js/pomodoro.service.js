angular.module('pomodoro').factory('pomodoroService', function($resource) {

	var service = {
		loadUsers : loadUsers,
		loadBiggestTomato : loadBiggestTomato
	};
	return service;

	function loadUsers() {
		return users().query();
	}
	
	function loadBiggestTomato(){
		return biggestTomato().query();
	}
	
	function users() {
		return $resource('users', {}, {
			query : {
				method : 'GET',
				isArray : true
			}
		});
	}
	
	function biggestTomato(){
		return $resource('statistic/biggest', {}, {
			query : {
				method : 'GET',
				isArray : true
			}
		});
	}

});