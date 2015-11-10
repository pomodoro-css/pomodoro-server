angular.module('pomodoro').factory('pomodoroWebSocket', function($websocket) {

	return {
		start : function(callback) {
			var ws = $websocket.$new({
				url : 'ws://localhost:8080/ws', // instance of ngWebsocket,
												// handled by
				// $websocket service
				reconnect : true,
				reconnectInterval : 500
				// it will reconnect after 0.5 seconds
			});

			ws.$on('$open', function() {
				console.log('WebSocket is open!');
			});

			ws.$on('$close', function() {
				console.log('WebSocket is closed!');
			});

			ws.$on('$message', function(msg) {
				callback(msg);
			});
		}
	}
});
