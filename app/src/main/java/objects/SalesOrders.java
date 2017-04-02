package objects;

import com.google.android.gms.maps.model.LatLng;

import java.sql.Date;

/**
 * Created by AZeaage on 3/30/2017.
 */

public class SalesOrders {
    private int OrderId;
    private Date OrderDate;
    private String ShippingAddress;
    private String ShippingCity;
    private LatLng locationCoordinate;
    private double OrderTotal;
    private int SAPInvoiceNo;
    private String CompletionCode;
    private boolean isCancelled;
    private Date CancellationDate;
    private String CreatedBy;
    private String LastModifiedBy;
    private Date LastModificationDate;
    private Customer customer;
    private SalesOrderDetails salesOrderDetails;
    private SalesOrderStatusTra salesOrderStatusTra;

    public SalesOrderDetails getSalesOrderDetails() {
        return salesOrderDetails;
    }

    public void setSalesOrderDetails(SalesOrderDetails salesOrderDetails) {
        this.salesOrderDetails = salesOrderDetails;
    }

    public SalesOrderStatusTra getSalesOrderStatusTra() {
        return salesOrderStatusTra;
    }

    public void setSalesOrderStatusTra(SalesOrderStatusTra salesOrderStatusTra) {
        this.salesOrderStatusTra = salesOrderStatusTra;
    }

    public SalesOrders(int orderId, Date orderDate, String shippingAddress, String shippingCity, LatLng locationCoordinate,
                       double orderTotal, int SAPInvoiceNo, String completionCode, boolean isCancelled, String createdBy
                       ) {
        OrderId = orderId;
        OrderDate = orderDate;
        ShippingAddress = shippingAddress;
        ShippingCity = shippingCity;
        this.locationCoordinate = locationCoordinate;
        OrderTotal = orderTotal;
        this.SAPInvoiceNo = SAPInvoiceNo;
        CompletionCode = completionCode;
        this.isCancelled = isCancelled;
        CreatedBy = createdBy;

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getSAPInvoiceNo() {
        return SAPInvoiceNo;
    }

    public void setSAPInvoiceNo(int SAPInvoiceNo) {
        this.SAPInvoiceNo = SAPInvoiceNo;
    }

    public String getCompletionCode() {
        return CompletionCode;
    }

    public void setCompletionCode(String completionCode) {
        CompletionCode = completionCode;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    public String getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        ShippingAddress = shippingAddress;
    }

    public String getShippingCity() {
        return ShippingCity;
    }

    public void setShippingCity(String shippingCity) {
        ShippingCity = shippingCity;
    }

    public LatLng getLocationCoordinate() {
        return locationCoordinate;
    }

    public void setLocationCoordinate(LatLng locationCoordinate) {
        this.locationCoordinate = locationCoordinate;
    }

    public double getOrderTotal() {
        return OrderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        OrderTotal = orderTotal;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public Date getCancellationDate() {
        return CancellationDate;
    }

    public void setCancellationDate(Date cancellationDate) {
        CancellationDate = cancellationDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getLastModifiedBy() {
        return LastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        LastModifiedBy = lastModifiedBy;
    }

    public Date getLastModificationDate() {
        return LastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        LastModificationDate = lastModificationDate;
    }
}
