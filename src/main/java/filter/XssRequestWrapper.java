package filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.xml.ws.RequestWrapper;

@Slf4j
public class XssRequestWrapper extends HttpServletRequestWrapper {

    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String param) {
        String[] val =  super.getParameterValues(param);

        if(val == null){
            return null;
        }

        int length = val.length;
        String[] encodedVal = new String[length];

        for (int idx = 0; idx < length; idx++) {
            encodedVal[idx] = removeXSS(val[idx]);
        }
        return encodedVal;
    }

    @Override
    public String getParameter(String param) {
        String val = super.getParameter(param);

        if(val == null)
            return null;

        return removeXSS(val);
    }

    private String removeXSS(String value) {

        log.info("xss filter before : {}",value);
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        log.info("xss filter after : {}",value);
        return value;
    }


}
