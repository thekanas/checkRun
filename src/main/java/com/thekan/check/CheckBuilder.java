package com.thekan.check;

import com.thekan.LoadData;

public class CheckBuilder implements Builder {
    private String title;
    private String storeName;
    private String storeAddress;
    private String storeTelephone;
    private String idCashier;
    private String[] columnName;
    private String separator;
    private LoadData loadData;

    public CheckBuilder(LoadData loadData) {
        this.loadData = loadData;
    }

    @Override
    public Builder setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public Builder setStoreName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    @Override
    public Builder setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
        return this;
    }

    @Override
    public Builder setStoreTelephone(String storeTelephone) {
        this.storeTelephone = storeTelephone;
        return this;
    }

    @Override
    public Builder setIdCashier(String idCashier) {
        this.idCashier = idCashier;
        return this;
    }

    @Override
    public Builder setColumnName(String[] columnName) {
        this.columnName = columnName;
        return this;
    }

    @Override
    public Builder setSeparator(String separator) {
        this.separator = separator;
        return this;
    }

    public Check checkBuild() {
        return new Check(title, storeName, storeAddress, storeTelephone, idCashier, columnName, separator, loadData);
    }
}
