package com.example.vk.services.audit;

import com.example.vk.entities.Audit;
import com.example.vk.repositories.AuditRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditService{
    private final AuditRepository auditRepository;

    @Transactional
    public void save(Audit audit) {
        auditRepository.save(audit);
    }
}
