package com.levik.googleadsense.common;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class IdentityResponse {
    private Long identity;
    private List<KeyWord> keyWords;
    private String provider;

    public IdentityResponse() {
    }

    public IdentityResponse(Long identity, List<KeyWord> keyWords, String provider) {
        this.identity = identity;
        this.keyWords = keyWords;
        this.provider = provider;
    }

    public Long getIdentity() {
        return identity;
    }

    public void setIdentity(Long identity) {
        this.identity = identity;
    }

    public List<KeyWord> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<KeyWord> keyWords) {
        this.keyWords = keyWords;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentityResponse that = (IdentityResponse) o;
        return Objects.equals(getIdentity(), that.getIdentity()) &&
                Objects.equals(getKeyWords(), that.getKeyWords()) &&
                Objects.equals(getProvider(), that.getProvider());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentity(), getKeyWords(), getProvider());
    }

    @Override
    public String toString() {
        return "IdentityResponse{" +
                "identity=" + identity +
                ", keyWords=" + keyWords +
                ", provider='" + provider + '\'' +
                '}';
    }
}
