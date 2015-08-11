package com.exposit.domain.model.dobrilko;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "shipment")
public class Shipment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "shipment_id")
	private int id;

	@Column(name = "provider_margin_precent")
	private int providerMarginPercent;

	@Column(name = "is_processed")
	private boolean isProcessed;

	@OneToOne
	@JoinColumn(name = "way_bill_id")
	private Waybill waybill;

	@ManyToOne
	@JoinColumn(name = "provider_id", nullable = false)
	private Provider provider;

	@OneToMany(mappedBy = "shipment", orphanRemoval = true)
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	private List<ShipmentUnit> shipmentUnits;

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getProviderMarginPercent() {
		return providerMarginPercent;
	}

	public void setProviderMarginPercent(int providerMarginPercent) {
		this.providerMarginPercent = providerMarginPercent;
	}

	public void setWaybill(Waybill waybill) {
		this.waybill = waybill;
	}

	public void setShipmentUnits(List<ShipmentUnit> shipmentUnits) {
		this.shipmentUnits = shipmentUnits;
	}

	public boolean isProcessed() {
		return isProcessed;
	}

	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

}
