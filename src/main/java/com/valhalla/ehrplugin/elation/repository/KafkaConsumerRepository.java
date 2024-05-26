package com.valhalla.ehrplugin.elation.repository;

import com.valhalla.ehrplugin.elation.domain.KafkaConsumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaConsumerRepository extends JpaRepository<KafkaConsumer, Long> {
    // You can define custom query methods here if needed
}
