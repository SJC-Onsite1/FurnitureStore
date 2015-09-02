package com.exposit.domain.service.dobrilko;

import java.util.List;

import com.exposit.domain.model.dobrilko.Provider;
import com.exposit.domain.model.dobrilko.Shipment;
import com.exposit.domain.model.dobrilko.ShipmentUnit;
import com.exposit.domain.model.dobrilko.StorageModuleUnit;

public interface PriceService {

	public double calculateStorageModuleUnitMargin(ShipmentUnit shipmentUnit);

	double calculateFullStorageModuleUnitPrice(
			StorageModuleUnit storageModuleUnit);

	public double calculateGain(List<Shipment> shipments);

	public List<Double> calculateYearGain(Provider provider);

	public double calculateGain(Shipment shipment);



}
