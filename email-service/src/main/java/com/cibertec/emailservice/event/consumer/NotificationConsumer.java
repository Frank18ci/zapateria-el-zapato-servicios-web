package com.cibertec.emailservice.event.consumer;

import com.cibertec.emailservice.event.consumer.dto.ProductVariantResponse;
import com.cibertec.emailservice.event.consumer.dto.ProductVariantResponseK;
import com.cibertec.emailservice.service.MailManagerPersonalizado;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {
    private final MailManagerPersonalizado mailManager;
    @KafkaListener(
            topics = "notification-topic",
            groupId = "notification-group"
    )
    @Transactional
    public void listeningProductVariantResponse(ProductVariantResponseK productVariantResponseK) {
        ProductVariantResponse p = productVariantResponseK.productVariantResponse();
        String body = String.format(MailManagerPersonalizado.HTML_TEMPLATE,
                p.id(),
                p.modelColor().model().name(),
                p.modelColor().model().description(),
                p.modelColor().color().hex(),
                p.modelColor().color().name(),
                p.size().code(),
                p.width().code(),
                p.skuCode(),
                p.status(),
                java.time.LocalDate.now().getYear()
        );

        mailManager.sendMessage(
                productVariantResponseK.email(),
                "Detalle actualizado del producto " + p.modelColor().model().name(),
                body
        );
    }
}
