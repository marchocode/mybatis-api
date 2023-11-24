package chaobei.xyz.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class MyHandlerMapping extends AbstractHandlerMapping implements InitializingBean {

    @Resource
    private MyHandler myHandler;

    @Override
    protected Object getHandlerInternal(HttpServletRequest request) throws Exception {
        return myHandler;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setOrder(3);
    }
}
