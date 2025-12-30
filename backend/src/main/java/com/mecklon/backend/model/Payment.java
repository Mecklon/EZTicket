package com.mecklon.backend.model;


import com.mecklon.backend.model.type.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        indexes = {
                @Index(name = "idx_payment_status_created", columnList = "status, creation_time_stamp")
        }
)

public class Payment {
    @Id
    private String id;
    private PaymentStatus status;
    private LocalDateTime creationTimeStamp;
}
