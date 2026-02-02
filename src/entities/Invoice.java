package entities;

public class Invoice {

    private Double basicPayment;
    private Double tax;

    public Invoice(Double basicPayment, Double tax) {
        this.basicPayment = basicPayment;
        this.tax = tax;
    }

    public Double getBasicPayment() {
        return basicPayment;
    }
    public void setBasicPayment(Double basicPayment) {
        this.basicPayment = basicPayment;
    }

    public Double getTax() {
        return tax;
    }
    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTotalPayment() {
        return getBasicPayment() + tax;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Pagamento basico: ");
        sb.append(String.format("%.2f \n", basicPayment));
        sb.append("Imposto: ");
        sb.append(String.format("%.2f \n", tax));
        sb.append("Pagamento total: ");
        sb.append(String.format("%.2f", getTotalPayment()));

        return sb.toString();
    }
}
