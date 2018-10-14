package com.sp.pm.myapplication;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by m on 05-Mar-17.
 */
public class TodayRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL ="https://puranikprashant3.000webhostapp.com/addData.php";

    private Map<String, String> params;

    public TodayRequest(String vehicalNo, String driverName,String operator, String date,int totalTrip,int cashCollected,int bankDeposite,int fuel,int toll,int intensives,int openingBalance,int totalEarning,int online,int otherExpense, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("vehicalNo", vehicalNo);
        params.put("driverName", driverName);
        params.put("operator", operator);
        params.put("date", date);
        params.put("totalTrip", totalTrip + "");
        params.put("fuel", fuel + "");
        params.put("toll", toll + "");
        params.put("cashCollected", cashCollected + "");
        params.put("bankDeposite", bankDeposite + "");
        params.put("intensives", intensives + "");
        params.put("openingBalance", openingBalance + "");
        params.put("totalEarning", totalEarning + "");
        params.put("online", online + "");
        params.put("otherExpense", otherExpense + "");



    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
