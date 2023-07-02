package com.ecommerce.openapi.admin.auth.service.impl;

import com.ecommerce.openapi.admin.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    SessionRepository sessionRepository;

    @Override
    public boolean checkSession(String sessionId) {
        Session session = sessionRepository.findById(sessionId);
        if (null != session) {
            return session.isExpired();
        }
        return false;
    }
}
