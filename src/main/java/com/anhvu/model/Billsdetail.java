package com.anhvu.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "billsdetail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"bill","product"})
public class Billsdetail implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@Column(name = "quatity")
    private int quatity;

	@Column(name = "total")
    private double total;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_bill", referencedColumnName = "id")
    private Bills bill;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_products")
    private Products product;


}
