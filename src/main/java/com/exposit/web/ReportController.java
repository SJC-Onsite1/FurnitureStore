package com.exposit.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.exposit.domain.model.Provider;
import com.exposit.domain.model.Shipment;
import com.exposit.domain.service.PriceService;
import com.exposit.domain.service.RequestService;
import com.exposit.domain.service.ShipmentService;
import com.exposit.domain.service.UserService;
import com.exposit.domain.service.WaybillService;

@Controller
@RequestMapping(value = "/reports", method = RequestMethod.GET)
public class ReportController {
	@Autowired
	private RequestService requestService;
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private PriceService priceService;
	@Autowired
	private ShipmentService shipmentService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "", "/" }, method = { RequestMethod.GET })
	public String showReportsPanel() {

		return "report-management-page";
	}

	
	@RequestMapping(value = { "/filtered" }, method = { RequestMethod.GET })
	public ModelAndView showFilteredReports(@RequestParam(value = "daterange",
			required = true) String daterange, Principal principal) {

		Provider provider = (Provider) userService
				.findUserByName(((UserDetails) ((Authentication) principal)
						.getPrincipal()).getUsername());
		ModelAndView mav = new ModelAndView();

		List<Shipment> shipments = shipmentService.getConfirmedShipments(
				daterange, provider);
		mav.addObject("shipments",
				shipmentService.getConfirmedShipments(daterange, provider));
		mav.addObject("gain", priceService.calculateGain(shipments));
		mav.setViewName("report-management-page");

		return mav;
	}

}
