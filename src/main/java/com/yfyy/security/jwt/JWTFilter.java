package com.yfyy.security.jwt;

import cn.hutool.core.util.StrUtil;
import com.yfyy.config.ApplicationProperties;
import com.yfyy.domain.JWTKey;
import com.yfyy.domain.JwtEnum;
import com.yfyy.domain.JwtPropEnum;
import com.yfyy.domain.JwtUser;
import com.yfyy.service.JWTKeyService;
import com.yfyy.util.JSONUtils;
import com.yfyy.util.ResultVO;
import com.yfyy.util.context.IContextInfoProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: zhanglei
 * @DateTime: 2022/5/12 17:27
 */
public class JWTFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String AUTHORIZATION_HEADER = "V-Token";

    public static final String ORGANIZATION = "organization";

    public static long EIGHT_HOURS_SECONDS = 28800L;

    private TokenProvider tokenProvider;

    @Autowired
    private JWTKeyService jwtKeyService;

    @Autowired
    private ApplicationProperties applicationProperties;

    public JWTFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
        log.info("ExclusiveJWTFilter--实例化");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        servletRequest.setCharacterEncoding("utf-8");
        String jwt = resolveToken(request);
        String organization = resolveSysTenantId(request);
        ResultVO failureMessage = new ResultVO(JwtEnum.NORMAL.getCode(), "");
        if(StrUtil.isNotEmpty(jwt)) {
            if(tokenProvider.isDebugMode(jwt, organization)) {
                try {
                    Authentication debugAuthentication = tokenProvider.getDebugAuthentication();
                    SecurityContextHolder.getContext().setAuthentication(debugAuthentication);
                    IContextInfoProxy.getInstance().setCI("userId", JwtPropEnum.DEFAULT_USER_ID.getCode());
                    IContextInfoProxy.getInstance().setCI("user_id", JwtPropEnum.DEFAULT_USER_ID.getCode());
                    IContextInfoProxy.getInstance().setCI("organization", JwtPropEnum.DEFAULT_ORGANIZATION.getCode());
                    filterChain.doFilter(servletRequest, servletResponse);
                }catch (Exception e) {
                    failureMessage.setMsg(e.getMessage());
                    failureMessage.setStatus(JwtEnum.TOKEN_FAIL.getCode());
                    printfAlertMsg(servletResponse, failureMessage);
                }
            }else {
                JWTKey jwtKey = this.jwtKeyService.getJwtKeyByOrganization(organization);
                if (jwtKey == null){
                    failureMessage.setMsg("key不存在");
                    failureMessage.setStatus(JwtEnum.KEY.getCode());
                    printfAlertMsg(servletResponse, failureMessage);
                    return;
                }
                Date tmpDate = Date.from(jwtKey.getCreateTime().minusSeconds(EIGHT_HOURS_SECONDS));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                log.info("====jwt===pk===priKey===time： " + jwt + "====" + jwtKey.getPublicKey() + "=====" + jwtKey.getPrivateKey() + "=====" + formatter.format(tmpDate));
                // 校验token是否过期
                if (tokenProvider.validateKeyExpire(jwtKey.getCreateTime())) {
                    // 校验通过解析token获取用户信息，并将用户信息设置到上下文环境
                    Authentication authentication;
                    try {
                        authentication = this.tokenProvider.getAuthentication(jwt, jwtKey.getPublicKey());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        JwtUser user = (JwtUser) authentication.getPrincipal();
                        IContextInfoProxy.getInstance().setCI("role", user.getRole());
                        IContextInfoProxy.getInstance().setCI("userId", user.getUserId());
                        IContextInfoProxy.getInstance().setCI("user_id", user.getUserId());
                        IContextInfoProxy.getInstance().setCI("userName", user.getFirstName());
                        IContextInfoProxy.getInstance().setCI("organization", user.getOrganization());
                        filterChain.doFilter(servletRequest, servletResponse);
                    } catch (Exception e) {
                        log.error("ip: "+ request.getRemoteAddr() + ", 解析Token失败", e);
                        failureMessage.setMsg("解析Token失败");
                        failureMessage.setStatus(JwtEnum.TOKEN_FAIL.getCode());
                        printfAlertMsg(servletResponse, failureMessage);
                    }
                } else {
                    failureMessage.setMsg("key时间过期，请重新获取");
                    failureMessage.setStatus(JwtEnum.KEY.getCode());
                    printfAlertMsg(servletResponse, failureMessage);
                }
            }
        }else {
            failureMessage.setMsg("用户尚未登录");
            failureMessage.setStatus(JwtEnum.TOKEN_FAIL.getCode());
            printfAlertMsg(servletResponse, failureMessage);
        }
    }

    @Override
    public void destroy() {

    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        String parameterToken = request.getParameter(AUTHORIZATION_HEADER);
        if (StrUtil.isNotEmpty(bearerToken)) {
            return bearerToken;
        } else if (StrUtil.isNotEmpty(parameterToken)) {
            return parameterToken;
        }
        return null;
    }

    private String resolveSysTenantId(HttpServletRequest request) {
        String tenantId = request.getHeader(ORGANIZATION);
        String parameterTenantId = request.getParameter(ORGANIZATION);
        return StrUtil.isNotEmpty(tenantId) ? tenantId : (StrUtil.isNotEmpty(parameterTenantId) ? parameterTenantId : null);
    }

    private void printfAlertMsg(ServletResponse servletResponse, ResultVO failureMessage) throws IOException {
        // 将校验信息直接返回
        String serialized = JSONUtils.gsonString(failureMessage);
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = servletResponse.getWriter();
        writer.print(serialized);
        writer.flush();
        writer.close();
    }
}
