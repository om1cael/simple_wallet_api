package com.om1cael.simple.wallet.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "senderId", referencedColumnName = "id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiverId", referencedColumnName = "id")
    private User receiver;

    private BigInteger value;
}
