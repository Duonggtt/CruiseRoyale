package com.example.cruiseroyalebe.modal.respone;

import com.example.cruiseroyalebe.entity.CabinType;
import com.example.cruiseroyalebe.entity.CabinTypeImage;
import com.example.cruiseroyalebe.entity.Cruise;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CabinResponse {
    private Integer id;
    private CabinType cabinType;
}
