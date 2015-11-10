package ch.css.pomodoro.service.websocket;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class BroadcastServlet extends WebSocketServlet {

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(60 * 10000); // WebSocket Timeout
		factory.register(BroadcastSocket.class);
	}
}
