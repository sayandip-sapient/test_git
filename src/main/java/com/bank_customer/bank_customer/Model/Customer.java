package com.bank_customer.bank_customer.Model;



import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
public class Customer {
    
	@Id
	@Column(nullable = false, updatable = false)
	@NotBlank(message = "Aadhar Number cannot be blank.")
	private String aadharNo;
	
	@Column @NotBlank(message = "Name cannot be blank.")
	private String name; 

	@Column @NotBlank(message = "Address cannot be blank.")
	private String address;

	@Column @Pattern(regexp = "^\\+?[0-9]{1,15}$", message = "Invalid phone number format.")
	private String phone;

	@Column @Pattern(regexp = "^(M|F)$", message = "Gender must be 'M' or 'F'")
	private String gender;

	@Column @Email
	private String email;

	@Column @NotNull(message = "Balance cannot be null")
	private float balance;
    @Column @NotNull(message = "unique val cannot be null")
	private int uniq_val=0;
    

	@JsonBackReference
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Transaction> transactions;

}
