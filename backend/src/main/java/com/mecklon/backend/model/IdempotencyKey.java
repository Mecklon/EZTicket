package com.mecklon.backend.model;


import com.mecklon.backend.model.type.IdempotencyStatus;
import io.lettuce.core.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Entity
@Table(name = "idempotency_keys")
@Getter
@Setter
public class IdempotencyKey {

    @Id
    private String key;

    @Enumerated(EnumType.STRING)
    private IdempotencyStatus status;

    private String response;

    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
