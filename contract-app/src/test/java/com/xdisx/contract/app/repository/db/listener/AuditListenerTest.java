package com.xdisx.contract.app.repository.db.listener;

import com.xdisx.contract.app.repository.db.entity.BaseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AuditListenerTest {

    private AuditListener auditListener;
    private BaseEntity mockEntity;

    @BeforeEach
    void setUp() {
        auditListener = new AuditListener();
        mockEntity = Mockito.mock(BaseEntity.class);
    }

    @Test
    void onCreate_shouldSetCreatedAndModifiedDates() {
        auditListener.onCreate(mockEntity);
        verify(mockEntity, times(1)).setCreated(any(LocalDateTime.class));
        verify(mockEntity, times(1)).setModified(any(LocalDateTime.class));
    }

    @Test
    void onUpdate_shouldSetModifiedDate() {
        auditListener.onUpdate(mockEntity);
        verify(mockEntity, times(1)).setModified(any(LocalDateTime.class));
    }
}
