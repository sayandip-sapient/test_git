// package com.bank_customer.bank_customer.Model;



// import com.fasterxml.jackson.annotation.JsonIgnore;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Entity
// @Getter @Setter
// @AllArgsConstructor
// @NoArgsConstructor
// public class Transaction {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer transactionId;

//     private String transactionType;

//     private float transactionAmount;

//     @ManyToOne
//     @JoinColumn(name="aadharNo")
//     @JsonIgnore
//     Customer customer;
// }

package com.bank_customer.bank_customer.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date; // Import the Date class
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    private String transactionType;

    private float transactionAmount;

    @Temporal(TemporalType.TIMESTAMP) // Specify the temporal type
    @Column(name = "transactionTimestamp") // Specify the column name
    private Date transactionTimestamp; // Add timestamp field

    @ManyToOne
    @JoinColumn(name="aadharNo")
    @JsonIgnore
    Customer customer;

    // Constructor that sets the timestamp automatically
    public Transaction(String transactionType, float transactionAmount, Customer customer) {
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.customer = customer;
        this.transactionTimestamp = new Date(); // Set the timestamp to the current date and time
    }
}

