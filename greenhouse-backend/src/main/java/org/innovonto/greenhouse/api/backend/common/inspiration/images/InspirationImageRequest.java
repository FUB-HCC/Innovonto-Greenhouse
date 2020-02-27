package org.innovonto.greenhouse.api.backend.common.inspiration.images;

import java.util.UUID;

public class InspirationImageRequest {
    private UUID inspirationId;
    private String data;

    public InspirationImageRequest() {
    }

    public InspirationImageRequest(UUID inspirationId, String data) {
        this.inspirationId = inspirationId;
        this.data = data;
    }

    public UUID getInspirationId() {
        return inspirationId;
    }

    public void setInspirationId(UUID inspirationId) {
        this.inspirationId = inspirationId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

