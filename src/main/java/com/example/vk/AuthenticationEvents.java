package com.example.vk;


import com.example.vk.entities.Audit;
import com.example.vk.services.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;


@Component
@RequiredArgsConstructor
public class AuthenticationEvents {

    private final AuditService auditService;


    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        // При повышении rps может начать сильно проседать
        auditService.save(createAudit(failures));
    }

    private Audit createAudit(AbstractAuthenticationFailureEvent failures){
        Audit audit = new Audit();
        audit.setUsername(((UsernamePasswordAuthenticationToken) failures.getSource()).getPrincipal().toString());
        audit.setMessage(failures.getException().getMessage());
        audit.setIsAvailable(false);
        audit.setData(Instant.now());
        return audit;
    }
}

