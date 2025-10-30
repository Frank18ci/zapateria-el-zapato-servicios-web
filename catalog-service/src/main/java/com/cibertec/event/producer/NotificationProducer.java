package com.cibertec.event.producer;

import com.cibertec.dto.ProductVariantResponse;
import com.cibertec.event.producer.dto.ProductVariantResponseK;
import com.cibertec.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducer {
    private final ProductVariantService productVariantService;
    private final KafkaTemplate<String, ProductVariantResponseK> kafkaTemplate;
    private static final String TOPIC = "notification-topic";
    public void sendProductVariantNotification(String email, Long productVariantId) {
        ProductVariantResponse productVariant = productVariantService.getProductVariantById(productVariantId);
        kafkaTemplate.send(TOPIC, new ProductVariantResponseK(email, productVariant));
    }
}
