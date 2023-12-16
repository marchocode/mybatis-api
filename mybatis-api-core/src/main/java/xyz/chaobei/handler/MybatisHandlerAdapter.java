package xyz.chaobei.handler;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import xyz.chaobei.MybatisApiContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MybatisHandlerAdapter implements HandlerAdapter, Ordered {

    private final MybatisApiContext context;

    public MybatisHandlerAdapter(MybatisApiContext context) {
        this.context = context;
    }

    @Override
    public boolean supports(Object handler) {
        return handler instanceof MybatisHandlerMethod;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ((MybatisHandlerMethod) handler).handle(request,response);
        return null;
    }

    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        return -1L;
    }

    @Override
    public int getOrder() {
        return 3;
    }
}
