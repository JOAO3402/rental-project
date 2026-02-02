package model.services;

import entities.CarRental;
import entities.Invoice;

import java.time.Duration;

public class RentalService {

    private Double pricePerHour;
    private Double pricePerDay;
    private BrazilTaxService taxService;

    public RentalService(Double pricePerHour, Double pricePerDay, BrazilTaxService taxService) {
        this.taxService = taxService;
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }
    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }
    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public BrazilTaxService getTaxService() {
        return taxService;
    }
    public void setTaxService(BrazilTaxService taxService) {
        this.taxService = taxService;
    }

    public void processInvoice(CarRental carRental){

        double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
        double hours = minutes / 60.0;

        double basicPayment;

        if(carRental.getFinish().isBefore(carRental.getStart())){
            throw new IllegalArgumentException("Finish date must be after start date");
        }
        if(carRental.getFinish().isEqual(carRental.getStart())){
            throw new IllegalArgumentException("Finish date must be different from start date");
        }

        if(hours <= 12.0){
            basicPayment = Math.ceil(hours) * pricePerHour;
        }
        else{
            basicPayment = Math.ceil(hours / 24.0) * pricePerDay;
        }

        double tax = taxService.tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
}
