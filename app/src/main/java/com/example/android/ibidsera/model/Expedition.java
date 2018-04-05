package com.example.android.ibidsera.model;

import com.example.android.ibidsera.util.ErrorHandler;

/**
 * Created by Randi Dwi Nandra on 08/11/2017.
 * randi.dwinandra@gmail.com
 */

public class Expedition {

    private String ExpeditionOrderId;
    private String ExpeditionOrderCode;
    private String ExpeditionTypeId;
    private ExpeditionType ExpeditionType;


    /*private String UserId;
    private String Amount;
    private String ExpeditionDate;
    private String AssignmentUserId;
    private String IsDone;
    private String CreateDate;
    private String CreateUser;
    private String ModifyDate;
    private String ModifyUser;
    private String DeleteDate;
    private String DeleteUser;
    private String StsDeleted;
    private String WorkOrderId;
    private String AuctionItemId;
    private String IsPickUp;
    private String StsDelete;*/

    public String getExpeditionOrderId() {
        return ErrorHandler.nullString(ExpeditionOrderId);
    }

    public void setExpeditionOrderId(String expeditionOrderId) {
        if (expeditionOrderId == null) throw new NullPointerException("expeditionOrderId");
        this.ExpeditionOrderId = expeditionOrderId;
    }

    public String getExpeditionOrderCode() {
        return ErrorHandler.nullString(ExpeditionOrderCode);
    }

    public void setExpeditionOrderCode(String expeditionOrderCode) {
        if (expeditionOrderCode == null) throw new NullPointerException("expeditionOrderCode");
        this.ExpeditionOrderCode = expeditionOrderCode;
    }

    public String getExpeditionTypeId() {
        return ErrorHandler.nullString(ExpeditionTypeId);
    }

    public void setExpeditionTypeId(String expeditionTypeId) {
        if (expeditionTypeId == null) throw new NullPointerException("expeditionTypeId");
        this.ExpeditionTypeId = expeditionTypeId;
    }

/*    public String getUserId() {
        return ErrorHandler.nullString(UserId);
    }

    public void setUserId(String userId) {
        this.UserId = userId;
    }

    public String getAmount() {
        return ErrorHandler.nullString(Amount);
    }

    public void setAmount(String amount) {
        this.Amount = amount;
    }

    public String getExpeditionDate() {
        return ErrorHandler.nullString(ExpeditionDate);
    }

    public void setExpeditionDate(String expeditionDate) {
        this.ExpeditionDate = expeditionDate;
    }

    public String getAssignmentUserId() {
        return ErrorHandler.nullString(AssignmentUserId);
    }

    public void setAssignmentUserId(String assignmentUserId) {
        this.AssignmentUserId = assignmentUserId;
    }

    public String getIsDone() {
        return ErrorHandler.nullString(IsDone);
    }

    public void setIsDone(String isDone) {
        this.IsDone = isDone;
    }

    public String getCreateDate() {
        return ErrorHandler.nullString(CreateDate);
    }

    public void setCreateDate(String createDate) {
        this.CreateDate = createDate;
    }

    public String getCreateUser() {
        return ErrorHandler.nullString(CreateUser);
    }

    public void setCreateUser(String createUser) {
        this.CreateUser = createUser;
    }

    public String getModifyDate() {
        return ErrorHandler.nullString(ModifyDate);
    }

    public void setModifyDate(String modifyDate) {
        this.ModifyDate = modifyDate;
    }

    public String getModifyUser() {
        return ErrorHandler.nullString(ModifyUser);
    }

    public void setModifyUser(String modifyUser) {
        this.ModifyUser = modifyUser;
    }

    public String getDeleteDate() {
        return ErrorHandler.nullString(DeleteDate);
    }

    public void setDeleteDate(String deleteDate) {
        this.DeleteDate = deleteDate;
    }

    public String getDeleteUser() {
        return ErrorHandler.nullString(DeleteUser);
    }

    public void setDeleteUser(String deleteUser) {
        this.DeleteUser = deleteUser;
    }

    public String getStsDeleted() {
        return ErrorHandler.nullString(StsDeleted);
    }

    public void setStsDeleted(String stsDeleted) {
        this.StsDeleted = stsDeleted;
    }

    public String getWorkOrderId() {
        return ErrorHandler.nullString(WorkOrderId);
    }

    public void setWorkOrderId(String workOrderId) {
        this.WorkOrderId = workOrderId;
    }

    public String getAuctionItemId() {
        return ErrorHandler.nullString(AuctionItemId);
    }

    public void setAuctionItemId(String auctionItemId) {
        this.AuctionItemId = auctionItemId;
    }

    public String getIsPickUp() {
        return ErrorHandler.nullString(IsPickUp);
    }

    public void setIsPickUp(String isPickUp) {
        this.IsPickUp = isPickUp;
    }

    public String getStsDelete() {
        return ErrorHandler.nullString(StsDelete);
    }

    public void setStsDelete(String stsDelete) {
        this.StsDelete = stsDelete;
    }*/

    public ExpeditionType getExpeditionType() {
        return ExpeditionType;
    }

    public void setExpeditionType(ExpeditionType expeditionType) {
        if (expeditionType == null) throw new NullPointerException("expedition");
        this.ExpeditionType = expeditionType;
    }

}

