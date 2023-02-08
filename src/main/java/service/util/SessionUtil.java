package service.util;

import data.dto.SessionInfo;

import javax.servlet.http.HttpServletRequest;

public interface SessionUtil {

    SessionInfo getSessionInfo(HttpServletRequest request);
}
