package com.thekan.check;

public interface Builder {

    Builder setTitle(String title);
    Builder setStoreName(String storeName);
    Builder setStoreAddress(String storeAddress);
    Builder setStoreTelephone(String storeTelephone);
    Builder setIdCashier(String idCashier);
    Builder setColumnName(String[] columnName);
    Builder setSeparator(String separator);
    Check checkBuild();

}
