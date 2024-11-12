package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private double percentage;

    @Override
	public String toString() {
		return "Discount [id=" + id + ", code=" + code + ", percentage=" + percentage + "]";
	}

	public Discount(Long id, String code, double percentage) {
		super();
		this.id = id;
		this.code = code;
		this.percentage = percentage;
	}

	public Discount() {
    }

    public Discount(String code, double percentage) {
        this.code = code;
        this.percentage = percentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
