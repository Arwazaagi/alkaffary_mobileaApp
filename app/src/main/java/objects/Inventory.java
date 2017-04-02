package objects;

/**
 * Created by AZeaage on 4/2/2017.
 */

public class Inventory {
    private int ProductCode;
    private int BranchCode;
    private int InventoryQty;

    public Inventory(int productCode, int branchCode, int inventoryQty) {
        ProductCode = productCode;
        BranchCode = branchCode;
        InventoryQty = inventoryQty;
    }

    public int getProductCode() {
        return ProductCode;
    }

    public void setProductCode(int productCode) {
        ProductCode = productCode;
    }

    public int getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(int branchCode) {
        BranchCode = branchCode;
    }

    public int getInventoryQty() {
        return InventoryQty;
    }

    public void setInventoryQty(int inventoryQty) {
        InventoryQty = inventoryQty;
    }
}

