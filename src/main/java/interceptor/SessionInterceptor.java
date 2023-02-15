package interceptor;

import data.dto.SessionInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import service.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
@RequiredArgsConstructor
public class SessionInterceptor implements HandlerInterceptor {

    private final SessionUtil sessionUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession httpSession = request.getSession(false);

        if(httpSession!=null){
            SessionInfo sessionInfo = sessionUtil.getSessionInfo(request);
            if(sessionInfo!=null) {
                return true;
            }
        }

        log.info("SESSION REQUIRED::REDIRECT TO AUTHENTICATION");
        response.sendRedirect(request.getContextPath()+"/user/authentication");
        return false;
    }

}
