package com.richa.entities;

import com.richa.entities.enums.PaymentMethod;
//import lombok.AllArgsConstructor;
import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "purchase")
@Builder
public class Purchase extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Double price;

//    @Valid
    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "book_id")
    private long bookId;

    @NotNull
    private PaymentMethod paymentMethod;

    private String currency;

	public Purchase() {
		super();
	}

	public Purchase(Long id, @Valid Customer customer, Double price,long book,
			@NotNull PaymentMethod paymentMethod, String currency) {
		super();
		this.id = id;
		this.customer = customer;
		this.price = price;
		this.bookId = book;
		this.paymentMethod = paymentMethod;
		this.currency = currency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public long getBook() {
		return bookId;
	}

	public void setBook(long book) {
		this.bookId = book;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public java.lang.String toString() {
		return "Purchase [id=" + id + ", customer=" + customer + ", price=" + price + ", book=" + bookId
				+ ", paymentMethod=" + paymentMethod + ", currency=" + currency + "]";
	}
    

}