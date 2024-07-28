package com.anhvu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "orderId")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId")
	private Bills orderId;

	@Column(name = "amount")
	@NotNull
	private Long amount;

	@Column(name = "vnpBankCode")
	@NotNull
	@Size(min = 1, max = 255)
	private String vnpBankCode;

	@Column(name = "vnpResponseCode")
	@NotNull
	@Size(min = 1, max = 255)
	private String vnpResponseCode;

	@Column(name = "vnpTransCreateDate")
	@NotNull
	@Size(min = 1, max = 255)
	private String vnpTransDate;


	

}
