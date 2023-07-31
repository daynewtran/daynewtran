package com.viesonet.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Violations")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Violations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int violationId;
    
    @ManyToOne
    @JoinColumn(name ="postId")
    private Posts post;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    @ManyToOne
    @JoinColumn(name= "violationType")
    private ViolationTypes violationType;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportDate;
    

}

