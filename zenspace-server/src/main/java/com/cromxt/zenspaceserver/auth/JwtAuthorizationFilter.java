package com.cromxt.zenspaceserver.auth;

import com.cromxt.zenspaceserver.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {


    private final JWTService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String requestPath = request.getRequestURI();


        if(requestPath.startsWith("/api/v1/auth")) {
            if(requestPath.equals("/api/v1/auth/refresh")) {
                Cookie[] cookies = request.getCookies();
                if(cookies==null) {
                    buildResponse(response, "Invalid cookie.", HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
                Optional<Cookie> refreshToken = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("refreshToken")).findFirst();
                if(refreshToken.isEmpty()) {
                    buildResponse(response, "No refresh token found.", HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
                String refreshTokenValue = refreshToken.get().getValue();
                if(refreshTokenValue.isEmpty()) {
                    buildResponse(response, "You have an empty refresh token .", HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
                request.setAttribute("refreshToken", refreshTokenValue);
                filterChain.doFilter(request, response);
                return;
            }
            filterChain.doFilter(request, response);
            return;
        }

        Optional<String> userIdFromTheRequest = getTheUserIdFromTheRequest(requestPath);

        if(userIdFromTheRequest.isEmpty()){
            buildResponse(response, "Invalid request.", HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String userId = userIdFromTheRequest.get();

        String token = request.getHeader("Authorization");
        if(token == null || !token.startsWith("Bearer ")) {
            buildResponse(response, "You are not allowed to use this endpoint.", HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String jwtToken = token.substring(7);

        if(!jwtService.isTokenValid(jwtToken, userId)) {
            buildResponse(response, "Unauthorized", HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> getTheUserIdFromTheRequest(String requestPath){
        int index = requestPath.lastIndexOf("/");
        if(index == -1) {
            return Optional.empty();
        }
        String userId = requestPath.substring(index + 1);
        return Optional.of(userId);
    }
    private void buildResponse(HttpServletResponse response, String message, int status) throws IOException {
        response.setStatus(status);
        response.setHeader("Content-Type", "application/json");
        PrintWriter writer = response.getWriter();
        writer.write(String.format("{\"message\":\"%s\"}", message));
        writer.flush();
    }
}
