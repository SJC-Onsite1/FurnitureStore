package com.exposit.domain.service.dobrilko;

import java.util.Date;
import java.util.List;

import com.exposit.domain.model.dobrilko.Shipment;
import com.exposit.domain.model.dobrilko.ShipmentUnit;
import com.exposit.domain.model.dobrilko.Waybill;

public interface ShipmentService {

	public Integer saveShipment(Shipment shipment);

	public Integer saveShipmentUnit(ShipmentUnit shipmentUnit);

	public Shipment getShipmentByWaybill(Waybill waybill);

	public Shipment getShipmentById(int id);

	public List<Shipment> getShipments();

	public List<Shipment> getShipments(Date beginningDate, Date endDate);

	public List<Shipment> getConfirmedShipments(Date beginningDate, Date endDate);

	public List<Shipment> getConfirmedShipments();

	public void acceptShipment(Shipment shipment, Date confirmantionDate);

	public Shipment getShipment(Waybill waybill);

	public List<Date> parseDateRangeValue(String dateRangeValue);

	public List<Shipment> getShipments(String dateRangeValue);

	public List<Shipment> getConfirmedShipments(String dateRangeValue);

	public void updateShipment(Shipment shipment);

	public List<ShipmentUnit> getShipmentUnitsByShipment(Shipment shipment);
}
