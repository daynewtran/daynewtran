package com.viesonet.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Images")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Images {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int imageId;

@ManyToOne
@JoinColumn(name = "postId")
private Posts post;
private String ImageUrl;
}
