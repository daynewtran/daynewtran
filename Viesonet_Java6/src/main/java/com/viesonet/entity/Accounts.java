package com.viesonet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Accounts")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accounts {
    @Id
    private String phoneNumber;
    private String password;
    private String email;
    
    @OneToOne
    @JoinColumn(name = "userId")
    private Users user;
    
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Roles role;
    
    @ManyToOne
    @JoinColumn(name = "StatusId")
    private AccountStatus accountStatus;

}
