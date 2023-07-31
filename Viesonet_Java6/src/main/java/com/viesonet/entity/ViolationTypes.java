package com.viesonet.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ViolationTypes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ViolationTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer violationTypeId;
    
    private String violationDescription;
    @OneToMany(mappedBy = "violationType")
    private List<Violations> violations;
}

