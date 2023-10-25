package com.richa.entities;

//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "customer")
@Builder
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Length(max = 140)
    @Column(length = 140)
    private String name;

    @NotEmpty
    @Length(max = 140)
    @Column(length = 140)
    private String surname;

    @NotEmpty
    @Length(max = 300)
    @Column(length = 300)
    private String address;

    @NotEmpty
    @Email
    @Length(max = 150)
    @Column(length = 150, unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Purchase> purchases;
    

	public Customer() {
		super();
	}

	public Customer(Long id, @NotEmpty @Length(max = 140) String name, @NotEmpty @Length(max = 140) String surname,
			@NotEmpty @Length(max = 300) String address, @NotEmpty @Email @Length(max = 150) String email,
		List<Purchase> purchases) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.email = email;
	this.purchases = purchases;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", surname=" + surname + ", address=" + address + ", email="
				+ email + "]";
	}
	
    
    

}
