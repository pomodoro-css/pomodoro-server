var dwws = dwws || {};

(function (dwws, $) {
   "use strict";

   var $status = $("#status");
   var socket = null;

   function connectSocket() {
      if(socket == null) {
         socket = new WebSocket("ws://localhost:8080/ws/");

         socket.onopen = function() {
            console.log("Connected!");
            $status.prepend("<p>Connected websocket!</p>");
         };

         socket.onclose = function() {
            console.log("Closed!");
            $status.prepend("<p>Closed websocket</p>");
         };

         socket.onmessage = function(msg) {
            console.log("Gots message", msg, this);
            $status.prepend("<p>" + msg.data + "</p>");
         };
      }
   }

   $("#broadcastBtn").click(function() {
      var msg = $("#msg").val();
      $.ajax({
         type: "POST",
         url: "/broadcast",
         data: msg,
         contentType: "text/plain"
      });
   });

   $("#jsonBtn").click(function(){
	   var msg = $("#msg").val();
	   $.ajax({
		   type:"POST",
		   url: "/broadcast",
		   data: msg,
		   contentType: "application/json"
	   });
   });
   
   $("#broadcastAddUsersBtn").click(function(){
	   var msg = [
	               "{\"method\" : \"addUser\", \"object\" : { \"nr\" : \"P12340\", \"name\" : \"John\", \"group\" : { \"name\" : \"Kontakt Management\" }, \"state\" : \"ONLINE\", \"remainingTime\" : 0, \"tomatoTime\" : 0, \"startTime\" : null, \"taskName\" : null}}"
	              ,"{\"method\" : \"addUser\", \"object\" : { \"nr\" : \"P12341\", \"name\" : \"Doe\", \"group\" : { \"name\" : \"Kontakt Management\" }, \"state\" : \"ONLINE\", \"remainingTime\" : 0, \"tomatoTime\" : 0, \"startTime\" : null, \"taskName\" : null}}"
	              ,"{\"method\" : \"addUser\", \"object\" : { \"nr\" : \"P12342\", \"name\" : \"John\", \"group\" : { \"name\" : \"Kontakt Management\" }, \"state\" : \"OFFLINE\", \"remainingTime\" : 0, \"tomatoTime\" : 0, \"startTime\" : null, \"taskName\" : null}}"
	              ,"{\"method\" : \"addUser\", \"object\" : { \"nr\" : \"P12343\", \"name\" : \"Mäge\", \"group\" : { \"name\" : \"Versicherungsabschluss CRM\" }, \"state\" : \"ONLINE\", \"remainingTime\" : 0, \"tomatoTime\" : 0, \"startTime\" : null, \"taskName\" : null}}"
	              ,"{\"method\" : \"addUser\", \"object\" : { \"nr\" : \"P12344\", \"name\" : \"Säsch\", \"group\" : { \"name\" : \"Versicherungsabschluss CRM\" }, \"state\" : \"ONLINE\", \"remainingTime\" : 0, \"tomatoTime\" : 0, \"startTime\" : null, \"taskName\" : null}}"
	   ];
	   connectSocket();
	   $.each(msg, function(i, element){
		   $.ajax({
			   type:"POST",
			   url: "/broadcast",
			   data: element,
			   contentType: "application/json"
		   });
	   });
   });
   
   $("#broadcastStartTimerBtn").click(function(){
	   var msg = [
	               "{ \"method\" : \"start\", \"object\" : { \"nr\" : \"P12340\", \"name\" : \"John\", \"group\" : { \"name\" : \"Kontakt Management\" }, \"state\" : \"BUSY\", \"remainingTime\" : 395, \"tomatoTime\" : 395, \"startTime\" : { \"millis\" : 1447795918702}, \"taskName\" : \"Ein ganz wichtiger Task\" }}"
	   ];
	   connectSocket();
	   $.each(msg, function(i, element){
		   $.ajax({
			   type:"POST",
			   url: "/broadcast",
			   data: element,
			   contentType: "application/json"
		   });
	   });
   });
   
   $("#sendBtn").click(function() {
      if(socket != null) {
         socket.send($("#msg").val());
      }
   });

   $("#connectBtn").click(connectSocket);

   $("#disconnectBtn").click(function() {
      if(socket != null) {
         socket.close();
         socket = null;
      }
   });
}(dwws, jQuery));