package com.yfyy.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhanglei
 * @DateTime: 2022/5/12 17:31
 */
@Entity
@Table(name = "jwt_key")
public class JWTKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 36)
    private String id;

    @Column(name = "organization")
    private String organization;

    @Column(name = "public_key", columnDefinition = "text")
    private String publicKey;

    @Column(name = "private_key", columnDefinition = "text")
    private String privateKey;

    @Column(name = "create_time")
    private Instant createTime = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));

    public JWTKey() {
    }

    public JWTKey(String id, String organization, String publicKey, String privateKey, Instant createTime) {
        this.id = id;
        this.organization = organization;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.createTime = createTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "JWTKey{" +
                "id='" + id + '\'' +
                ", organization='" + organization + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
