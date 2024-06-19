package com.example.cruiseroyalebe.modal.request;

import com.example.cruiseroyalebe.entity.CabinTypeImage;
import com.example.cruiseroyalebe.entity.Tag;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpsertCabinTypeRequest {
    private String name;
    private int roomSize;
    private int maxGuests;
    private String description;
    private int roomQuantity;
    private BigDecimal price;
    private List<Integer> cabinTypeImageIds;
    private Collection<Integer> tagIds;
}
