package com.learning.model;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="rate")
public class Rate {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int rateId;
		
		private String rateDescription;
		private Date rateEffectiveDate, rateExpirationDate;
		private int amount;
		
		

		public Rate() {
			super();
		}

		public Rate(int i, String rateDescription, Date rateEffectiveDate, Date rateExpirationDate, int amount) {
			super();
			this.rateId = i;
			this.rateDescription = rateDescription;
			this.rateEffectiveDate = rateEffectiveDate;
			this.rateExpirationDate = rateExpirationDate;
			this.amount = amount;
		}


		@NotNull
		@Column(name="rate_id")
		public int getRateId() {
			return rateId;
		}

		public void setRateId(int rateId) {
			this.rateId = rateId;
		}

		@Column(name="rate_description")
		public String getRateDescription() {
			return rateDescription;
		}

		public void setRateDescription(String rateDescription) {
			this.rateDescription = rateDescription;
		}

		@NotNull
		@Column(name="rate_effective_date")
		public Date getRateEffectiveDate() {
			return rateEffectiveDate;
		}

		public void setRateEffectiveDate(Date rateEffectiveDate) {
			this.rateEffectiveDate = rateEffectiveDate;
		}

		@NotNull
		@Column(name="rate_expiration_date")
		public Date getRateExpirationDate() {
			return rateExpirationDate;
		}

		public void setRateExpirationDate(Date rateExpirationDate) {
			this.rateExpirationDate = rateExpirationDate;
		}

		@NotNull
		@Column(name="amount")
		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}


		@Override
		public String toString() {
			return "Rate [rateId=" + rateId + ", rateDescription=" + rateDescription + ", rateEffectiveDate="
					+ rateEffectiveDate + ", rateExpirationDate=" + rateExpirationDate + ", amount=" + amount + "]";
		}
		
		
}
