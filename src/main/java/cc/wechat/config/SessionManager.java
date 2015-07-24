package cc.wechat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

public class SessionManager {
	private static Session session = null;
	
	@Autowired(required=true)
	private SessionRepository<?> sessionRepo;
	
	public Session getSession() {
		
		if(session == null) {
			session = sessionRepo.createSession();
		}
		return session;
	}
}
