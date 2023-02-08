package service.util.impl;

import data.dto.SessionInfo;
import org.springframework.stereotype.Service;
import service.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class SessionUtilImpl implements SessionUtil {

    @Override
    public SessionInfo getSessionInfo(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);

        return (SessionInfo) httpSession.getAttribute("session");
    }
}
