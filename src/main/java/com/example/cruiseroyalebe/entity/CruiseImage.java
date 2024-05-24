package com.example.cruiseroyalebe.entity;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "cruise_image")
public class CruiseImage {
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
    @JoinColumn(name = "crusie_id")
    private Cruise cruise;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }
}
