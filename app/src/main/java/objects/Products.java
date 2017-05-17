package objects;

import java.io.Serializable;

/**
 * Created by AZeaage on 4/2/2017.
 */

public class Products implements Serializable {
    private int ProductCode;
    private String productName;
    private String ProductName_Ar;
    private String ProductName_En;
    private String ProductGroup;//EX : ورق جدران , باركية
    private String ProductType ;
    private double Price;
    private boolean isActive;
    private Double priceBeforeDisc;
    private Double priceAfterDisc;
    private String UOM;
    private Double qty;
    private String lineTotal;

    public Products(int productCode, String productName_Ar, String productName_En, String productGroup, String productType, double price, boolean isActive) {
        ProductCode = productCode;
        ProductName_Ar = productName_Ar;
        ProductName_En = productName_En;
        ProductGroup = productGroup;
        ProductType = productType;
        Price = price;
        this.isActive = isActive;
    }

    public Products(String productName, Double priceBeforeDisc, Double priceAfterDisc, String UOM, Double qty,String lineTotal) {
        this.productName = productName;
        this.priceBeforeDisc = priceBeforeDisc;
        this.priceAfterDisc = priceAfterDisc;
        this.UOM = UOM;
        this.qty = qty;
        this.lineTotal=lineTotal;
    }

    public String getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(String lineTotal) {
        this.lineTotal = lineTotal;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPriceBeforeDisc() {
        return priceBeforeDisc;
    }

    public void setPriceBeforeDisc(Double priceBeforeDisc) {
        this.priceBeforeDisc = priceBeforeDisc;
    }

    public Double getPriceAfterDisc() {
        return priceAfterDisc;
    }

    public void setPriceAfterDisc(Double priceAfterDisc) {
        this.priceAfterDisc = priceAfterDisc;
    }

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public int getProductCode() {
        return ProductCode;
    }

    public void setProductCode(int productCode) {
        ProductCode = productCode;
    }

    public String getProductName_Ar() {
        return ProductName_Ar;
    }

    public void setProductName_Ar(String productName_Ar) {
        ProductName_Ar = productName_Ar;
    }

    public String getProductName_En() {
        return ProductName_En;
    }

    public void setProductName_En(String productName_En) {
        ProductName_En = productName_En;
    }

    public String getProductGroup() {
        return ProductGroup;
    }

    public void setProductGroup(String productGroup) {
        ProductGroup = productGroup;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Products{" +
                "ProductCode=" + ProductCode +
                ", productName='" + productName + '\'' +
                ", ProductName_Ar='" + ProductName_Ar + '\'' +
                ", ProductName_En='" + ProductName_En + '\'' +
                ", ProductGroup='" + ProductGroup + '\'' +
                ", ProductType='" + ProductType + '\'' +
                ", Price=" + Price +
                ", isActive=" + isActive +
                ", priceBeforeDisc=" + priceBeforeDisc +
                ", priceAfterDisc=" + priceAfterDisc +
                ", UOM='" + UOM + '\'' +
                ", qty=" + qty +
                '}';
    }
}

