package com.mecklon.backend.repo;

import com.mecklon.backend.model.IdempotencyKey;
import com.mecklon.backend.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdempotencyRepository extends JpaRepository<IdempotencyKey, String> {

    @Modifying
    @Query(value = """
        insert into idempotency_keys (key, status)
        VALUES (:key, 'PROCESSING')
        on conflict do nothing
        """, nativeQuery = true)
    int tryInsert(@Param("key") String key);

    @Query("select i from IdempotencyKey i WHERE i.key = :key")
    Optional<IdempotencyKey> find(@Param("key") String key);

    @Query("""
            update IdempotencyKey i
            set
            i.payment = :payment
            where i.key = :key
            """)
    void attachPayment(Payment payment, String key);
}
