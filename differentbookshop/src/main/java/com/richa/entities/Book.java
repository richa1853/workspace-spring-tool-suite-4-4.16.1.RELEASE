package com.richa.entities;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor

@Entity
@Table(name = "book")
@Builder
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Length(min=9, max = 13)
    @Column(name="isbn",length=13,unique=true)
    private String isbn;

    @NotEmpty
    @Length(max = 140)
    @Column(length = 140)
    private String title;

    @DecimalMax("199.99")
    @DecimalMin("0.0")
    private Double price;

    @NotEmpty
    @Length(max = 155)
    @Column(length = 155)
    private String author;

    @Min(1)
    private Integer pages;

    @NotEmpty
    @Length(max = 140)
    @Column(length = 140)
    private String provider;
    
  
	public Book() {
		super();
	}

	public Book(Long id, @NotEmpty @Length(min = 9, max = 13) String isbn, @NotEmpty @Length(max = 140) String title,
			@DecimalMax("199.99") @DecimalMin("0.0") Double price, @NotEmpty @Length(max = 155) String author,
			@Min(1) Integer pages, @NotEmpty @Length(max = 140) String provider) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.price = price;
		this.author = author;
		this.pages = pages;
		this.provider = provider;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", price=" + price + ", author=" + author
				+ ", pages=" + pages + ", provider=" + provider + "]";
	}
	
    
    

}