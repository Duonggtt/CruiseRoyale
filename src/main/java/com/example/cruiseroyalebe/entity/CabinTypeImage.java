package com.example.cruiseroyalebe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "cabin_type_image")
public class CabinTypeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(name = "data", columnDefinition = "LONGBLOB")
    private byte[] data;

    @Column(name = "type")
    private String type; // image/png, image/jpg, ...

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private Date createdAt;

    @ManyToOne()
    @JoinColumn(name = "cabin_type_id")
    private CabinType cabinType;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }
}
