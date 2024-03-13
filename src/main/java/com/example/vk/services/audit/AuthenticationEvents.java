package com.example.vk.services.audit;


import com.example.vk.entities.Audit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;


@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationEvents{

    private final AuditService auditService;


    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        // При повышении rps может начать сильно проседать
        auditService.save(createAudit(failures));
    }

    private Audit createAudit(AbstractAuthenticationFailureEvent failures) {
        Audit audit = new Audit();
        audit.setUsername(((UsernamePasswordAuthenticationToken) failures.getSource()).getPrincipal().toString());
        audit.setMessage(failures.getException().getMessage());
        audit.setIsAvailable(false);
        audit.setData(Instant.now());
        log.info("Audit event: {}", audit);
        return audit;
    }
}

