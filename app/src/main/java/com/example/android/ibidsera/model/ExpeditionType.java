package com.example.android.ibidsera.model;

import com.example.android.ibidsera.util.ErrorHandler;

/**
 * Created by Randi Dwi Nandra on 08/11/2017.
 * randi.dwinandra@gmail.com
 */

public class ExpeditionType {

    private String ExpeditionTypeId;
    private String CompanyId;
    private String AuctionCity;
    private String OriginCity;
    private String Distance;
    private String CarFee;
    private String CraneFeeFourTire;
    private String CarryingFeeFourTire;
    private String TruckFee;
    private String CraneFeeSixTire;
    private String CarryingFeeSixTire;
    private String Desc;
    private String CreateDate;
    private String CreateUser;
    private String ModifyDate;
    private String ModifyUser;
    private String DeleteDate;
    private String DeleteUser;
    private String StsDeleted;

    public String getExpeditionTypeId() {
        return ErrorHandler.nullString(ExpeditionTypeId);
    }

    public void setExpeditionTypeId(String expeditionTypeId) {
        this.ExpeditionTypeId = expeditionTypeId;
    }

    public String getCompanyId() {
        return ErrorHandler.nullString(CompanyId);
    }

    public void setCompanyId(String companyId) {
        this.CompanyId = companyId;
    }

    public String getAuctionCity() {
        return ErrorHandler.nullString(AuctionCity);
    }

    public void setAuctionCity(String auctionCity) {
        this.AuctionCity = auctionCity;
    }

    public String getOriginCity() {
        return ErrorHandler.nullString(OriginCity);
    }

    public void setOriginCity(String originCity) {
        this.OriginCity = originCity;
    }

    public String getDistance() {
        return ErrorHandler.nullString(Distance);
    }

    public void setDistance(String distance) {
        this.Distance = distance;
    }

    public String getCarFee() {
        return ErrorHandler.nullString(CarFee);
    }

    public void setCarFee(String carFee) {
        this.CarFee = carFee;
    }

    public String getCraneFeeFourTire() {
        return ErrorHandler.nullString(CraneFeeFourTire);
    }

    public void setCraneFeeFourTire(String craneFeeFourTire) {
        this.CraneFeeFourTire = craneFeeFourTire;
    }

    public String getCarryingFeeFourTire() {
        return ErrorHandler.nullString(CarryingFeeFourTire);
    }

    public void setCarryingFeeFourTire(String carryingFeeFourTire) {
        this.CarryingFeeFourTire = carryingFeeFourTire;
    }

    public String getTruckFee() {
        return ErrorHandler.nullString(TruckFee);
    }

    public void setTruckFee(String truckFee) {
        this.TruckFee = truckFee;
    }

    public String getCraneFeeSixTire() {
        return ErrorHandler.nullString(CraneFeeSixTire);
    }

    public void setCraneFeeSixTire(String craneFeeSixTire) {
        this.CraneFeeSixTire = craneFeeSixTire;
    }

    public String getCarryingFeeSixTire() {
        return ErrorHandler.nullString(CarryingFeeSixTire);
    }

    public void setCarryingFeeSixTire(String carryingFeeSixTire) {
        this.CarryingFeeSixTire = carryingFeeSixTire;
    }

    public String getDesc() {
        return ErrorHandler.nullString(Desc);
    }

    public void setDesc(String desc) {
        this.Desc = desc;
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

}
