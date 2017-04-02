package objects;

import java.sql.Date;

/**
 * Created by AZeaage on 3/30/2017.
 */

public class SalesOrderStatusTra {
    private int RecordID;
    private String Comments;
    private Date StatusDateTime;
    private String StatusUpdatedBy;
    private SalesOrderStatuses salesOrderStatuses;

    public SalesOrderStatuses getSalesOrderStatuses() {
        return salesOrderStatuses;
    }

    public void setSalesOrderStatuses(SalesOrderStatuses salesOrderStatuses) {
        this.salesOrderStatuses = salesOrderStatuses;
    }

    public int getRecordID() {
        return RecordID;
    }

    public void setRecordID(int recordID) {
        RecordID = recordID;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public Date getStatusDateTime() {
        return StatusDateTime;
    }

    public void setStatusDateTime(Date statusDateTime) {
        StatusDateTime = statusDateTime;
    }

    public String getStatusUpdatedBy() {
        return StatusUpdatedBy;
    }

    public void setStatusUpdatedBy(String statusUpdatedBy) {
        StatusUpdatedBy = statusUpdatedBy;
    }
}
