package interceptor;

import data.dto.SessionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession httpSession = request.getSession(false);

        if(httpSession!=null){
            SessionInfo sessionInfo = (SessionInfo) httpSession.getAttribute("session");
            if(sessionInfo!=null) {
                return true;
            }
        }

        log.info("SESSION REQUIRED::REDIRECT TO AUTHENTICATION");
        response.sendRedirect(request.getContextPath()+"/user/authentication");
        return false;
    }

}
