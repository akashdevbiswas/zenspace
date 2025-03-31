package com.cromxt.zenspaceserver.auth;

import com.cromxt.zenspaceserver.entity.PlatformPermissions;
import com.cromxt.zenspaceserver.entity.UserEntity;
import com.cromxt.zenspaceserver.entity.UserRole;
import com.cromxt.zenspaceserver.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JWTService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        Set<PlatformPermissions> grantedAuthorities = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractSubject(token);
            grantedAuthorities = jwtService.extractAuthorities(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null && grantedAuthorities != null) {
            UserDetails userDetails = UserEntity.builder()
                    .username(username)
                    .password("")
                    .userRole(UserRole.builder()
                            .permissions(grantedAuthorities)
                            .build())
                    .build();
            if (jwtService.isTokenValid(token)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);

        String userId = jwtService.extractSubject(token);
        request.setAttribute("userId", userId);
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
