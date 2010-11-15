(function() {

	function log(txt) {
		$('#log').append(document.createTextNode(txt));
		$('#log').append('<br />');
	}
	
	if (!window.WebSocket) {
		log('WebSockets not natively supported, falling back to Flash');

		WEB_SOCKET_SWF_LOCATION = 'WebSocketMain.swf';
		WEB_SOCKET_DEBUG = true;

		$('head').append('<script type="text/javascript" src="/js/web_socket.js"></script>');
	} else {
		log('Using browser\'s native WebSocket implementation');
	}
	
	
	var ws;

	$('#uriForm').submit(function(e) {
		e.preventDefault();
		ws = new WebSocket($('#uri').val());
		ws.onopen = function() {
			log('Connected');
		}
		ws.onmessage = function(e) {
			log(e.data);
			handle(JSON.parse(e.data));
		}
		ws.onclose = function() {
			log('Disconnected');
			$('#uri, #connect').removeAttr('disabled');
			$('#disconnect').attr('disabled', 'disabled');
			ws = null;
		}
		$('#uri, #connect').attr('disabled', 'disabled');
		$('#disconnect').removeAttr('disabled');
	});

	$('#sendForm').submit(function(e) {
		e.preventDefault();
		if (ws) {
			var textField = $('#textField');
			log('Sending: ' + textField.val());
			ws.send(textField.val());
			textField.val('');
			textField.focus();
		}
	});

	$("#disconnect").click(function(e) {
		e.preventDefault();
		if (ws) {
			ws.close();
		}
	});
	
	
	var canvas = document.getElementById('gameCanvas');
	var ctx = canvas.getContext('2d');
    
    function handle(input) {
    	if(input.field != null) {
    		ctx.fillStyle = '#660000';
    		for(var i=0; i<input.field.length; i++) {
    			ctx.fillRect (i, canvas.height, 1, -input.field[i]);
    		}
    	}
    }
    
})();
