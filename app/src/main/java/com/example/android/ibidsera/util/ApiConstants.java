package com.example.android.ibidsera.util;

/**
 * Created by Fahmi Hakim on 07/03/2018.
 * for SERA
 */

public class ApiConstants { // http://alpha.ibid.astra.co.id/backend/service/taksasi/pdi/
    //public static final String HOST = "http://alpha.ibid.astra.co.id/";

    //public static final String BASEURL = "http://103.107.100.67/";
    public static final String BASEURL = "https://ibid.astra.co.id/";
    
    public static final String DEV_URL = "http://api.ibid.co.id/checklist/";
    public static final String AZURE_STOCK_URL = "http://ibidadmsdevservicestock.azurewebsites.net/index.php/pdi/";
    public static final String AZURE_TAKSASI_URL = "http://ibidadmsdevservicetaksasi.azurewebsites.net/index.php/pdi/";

    public static final String ALPHA_TAKSASI_URL = BASEURL + "backend/service/taksasi/";
    public static final String ALPHA_STOK_URL = BASEURL + "backend/service/stok/";

    public static final String PRINT_IN_HOST = BASEURL + "backend/adms/pdi/prints/index/6/";
    public static final String PRINT_OUT_HOST = BASEURL + "backend/adms/pdi/printout/index/6/";
}
