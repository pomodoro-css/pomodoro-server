angular.module('pomodoro').factory('pomodoroWebSocket', function($websocket, $location) {

	return {
		start : function(callback) {
			var ws = $websocket.$new({
				url : 'ws://' +$location.host() + ':' + $location.port()+'/ws', // instance of ngWebsocket,
												// handled by
				// $websocket service
				reconnect : true,
				reconnectInterval : 500
				// it will reconnect after 0.5 seconds
			});

			ws.$on('$open', function() {
				console.log('WebSocket is open!');
				
				var open_data = { method: 'open' };
				callback(open_data);
			});

			ws.$on('$close', function() {
				console.log('WebSocket is closed!');
			});

			ws.$on('$message', function(msg) {
				callback(msg);
			});
			
			ws.$on('$error', function(msg){
				var error_data = {
				        method: 'error'
				    };
				
				callback(error_data);
			});
		}
	}
});
