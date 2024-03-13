package com.example.vk.services.audit;

import com.example.vk.entities.Audit;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Principal;
import java.time.Instant;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CorrelationIdFilter extends OncePerRequestFilter{

    private final AuditService auditService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        // При повышении rps может начать сильно проседать
        auditService.save(createAudit(request));
        filterChain.doFilter(request, response);
    }

    public Audit createAudit(HttpServletRequest request) {
        Audit audit = new Audit();
        audit.setUsername(Optional.ofNullable(request.getUserPrincipal())
                .map(Principal::getName)
                .orElse("unauthorized user"));
        audit.setUrl(request.getRequestURL().toString());
        audit.setMethod(request.getMethod());
        audit.setIsAvailable(true);
        audit.setData(Instant.now());
        log.info("Audit event: {}", audit);
        return audit;
    }

}

