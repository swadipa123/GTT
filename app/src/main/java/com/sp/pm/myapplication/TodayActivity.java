package com.sp.pm.myapplication;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.lang.*;

public class TodayActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ProgressDialog mDialog;
    public static final String MyPREFERENCES = "MyPrefs" ;

    EditText etTotalTrip,etDate,etFuel,etToll,etIncentives,etOnline,etOtherExpense,etTotalEarning,etCashCollected,etOpeningBalance;
    TextView bankDeposited,cashInHand,closingBalance;

    SharedPreferences sharedpreferences;
    TextWatcher txtbankDeposite=null;
    TextWatcher totCashInHand = null;
    TextWatcher totClosingBalance = null;

    public ArrayList<SpinnerModel> CustomListViewValuesArr = new ArrayList<SpinnerModel>();
    CustomAdapter adapters;
    TodayActivity activity = null;
    String[] myStringArray = {"Select Driver","Uday Devlal","Swapnil Kulkarni"};

    public ArrayList<SpinnerModel> CustomListVehicleArr = new ArrayList<SpinnerModel>();
    CustomsAdapter vahicleAdapters;
    String[] myVehicalArray = {"Select Vehical","MH-15 EE 1488"};

    public ArrayList<SpinnerModel> CustomListCabArr = new ArrayList<SpinnerModel>();
    CustomssAdapter cabAdapters;
    String[] myCabArray = {"Select Operator","OLA","UBER","c3"};



    private GoogleApiClient client;
    int cashCollected = 0;
    int totalEarnings = 0;
    int tolltax = 0;
    int onlineP = 0;
    int otherExp = 0;
    int openingBal = 0;
    int cashinHands= 0;
    int fuelExx = 0;
    int cash_in_hand = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);
        etTotalTrip     =      (EditText) findViewById(R.id.etTotalTrip);
        etDate          =      (EditText) findViewById(R.id.etDate);
        etFuel          =      (EditText) findViewById(R.id.etFuel);
        etToll          =      (EditText) findViewById(R.id.etToll);
        etIncentives    =      (EditText) findViewById(R.id.etIncentives);
        etOnline        =      (EditText) findViewById(R.id.etOnline);
        etOtherExpense  =      (EditText) findViewById(R.id.etOtherExpense);
        etTotalEarning  =      (EditText) findViewById(R.id.etTotalEarning);
        etCashCollected =      (EditText) findViewById(R.id.etCashCollected);
        etOpeningBalance =     (EditText) findViewById(R.id.etOpeningBalance);

        bankDeposited    =      (TextView) findViewById(R.id.etbDeposite);
        cashInHand      =      (TextView) findViewById(R.id.cashinhand);
        closingBalance  =      (TextView) findViewById(R.id.closeBalance);




        txtbankDeposite = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ("".equals(etTotalEarning.getText().toString()))
                {
                    totalEarnings = 0;
                }
                else {
                    totalEarnings = Integer.parseInt(etTotalEarning.getText().toString());
                }
                if ("".equals(etCashCollected.getText().toString()))
                {
                    cashCollected = 0;
                }
                else {
                    cashCollected = Integer.parseInt(etCashCollected.getText().toString());
                }

                bankDeposited.setText(String.valueOf(totalEarnings - cashCollected));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        etTotalEarning.addTextChangedListener(txtbankDeposite);
        etCashCollected.addTextChangedListener(txtbankDeposite);


        totCashInHand = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ("".equals(etToll.getText().toString()))
                {
                    tolltax = 0;
                }
                else {
                    tolltax = Integer.parseInt(etToll.getText().toString());
                }
                if ("".equals(etOnline.getText().toString()))
                {
                    onlineP = 0;
                }
                else {
                    onlineP = Integer.parseInt(etOnline.getText().toString());
                }
                if ("".equals(etOtherExpense.getText().toString()))
                {
                    otherExp = 0;
                }
                else {
                    otherExp = Integer.parseInt(etOtherExpense.getText().toString());
                }
                if ("".equals(etCashCollected.getText().toString()))
                {
                    cashCollected =0;
                }
                else {
                    cashCollected = Integer.parseInt(etCashCollected.getText().toString());
                }

                cashInHand.setText(String.valueOf(cashCollected -(onlineP + otherExp + tolltax)));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        etOtherExpense.addTextChangedListener(totCashInHand);
        etToll.addTextChangedListener(totCashInHand);
        etCashCollected.addTextChangedListener(totCashInHand);
        etOnline.addTextChangedListener(totCashInHand);

        totClosingBalance = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ("".equals(etOpeningBalance.getText().toString()))
                {
                    openingBal = 0;
                }
                else {
                    openingBal = Integer.parseInt(etOpeningBalance.getText().toString());
                }

                if ("".equals(cashInHand.getText().toString()))
                {
                    cashinHands = 0;
                }
                else {
                    cashinHands = Integer.parseInt(cashInHand.getText().toString());
                }


                if ("".equals(etFuel.getText().toString()))
                {
                    fuelExx =0;
                }
                else {

                    fuelExx = Integer.parseInt(etFuel.getText().toString());
                }

                if ("".equals(etToll.getText().toString()))
                {
                    tolltax = 0;
                }
                else {
                    tolltax = Integer.parseInt(etToll.getText().toString());
                }
                if ("".equals(etOnline.getText().toString()))
                {
                    onlineP = 0;
                }
                else {
                    onlineP = Integer.parseInt(etOnline.getText().toString());
                }
                if ("".equals(etOtherExpense.getText().toString()))
                {
                    otherExp = 0;
                }
                else {
                    otherExp = Integer.parseInt(etOtherExpense.getText().toString());
                }
                if ("".equals(etCashCollected.getText().toString()))
                {
                    cashCollected =0;
                }
                else {
                    cashCollected = Integer.parseInt(etCashCollected.getText().toString());
                }

                 cash_in_hand = cashCollected -(onlineP + otherExp + tolltax);

                closingBalance.setText(String.valueOf((openingBal  + cash_in_hand) - fuelExx));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        etOpeningBalance.addTextChangedListener(totClosingBalance);
        etFuel.addTextChangedListener(totClosingBalance);
        etOtherExpense.addTextChangedListener(totClosingBalance);
        etToll.addTextChangedListener(totClosingBalance);
        etCashCollected.addTextChangedListener(totClosingBalance);
        etOnline.addTextChangedListener(totClosingBalance);

        activity  = this;
        Spinner  SpinnerExample = (Spinner)findViewById(R.id.spinner);
        setListData();
        Resources res = getResources();
        adapters = new CustomAdapter(activity, R.layout.spinner_rows, CustomListViewValuesArr,res);
        SpinnerExample.setAdapter(adapters);


        activity  = this;
        Spinner  SpinnerVehicleExample = (Spinner)findViewById(R.id.vehicleSpinner);
        setVehicleListData();
        Resources vehicleRes = getResources();
        vahicleAdapters = new CustomsAdapter(activity, R.layout.vehicle_rows, CustomListVehicleArr,vehicleRes);
        SpinnerVehicleExample.setAdapter(vahicleAdapters);

        activity  = this;
        Spinner  SpinnerCabeExample = (Spinner)findViewById(R.id.cabSpinner);
        setCabListData();
        Resources cabRes = getResources();
        cabAdapters = new CustomssAdapter(activity, R.layout.operator_rows, CustomListCabArr,cabRes);
        SpinnerCabeExample.setAdapter(cabAdapters);

        final Button bSubmit = (Button) findViewById(R.id.bSubmit);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

            /**************************************************************/
        bSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                etTotalTrip     =      (EditText) findViewById(R.id.etTotalTrip);
                etDate          =      (EditText) findViewById(R.id.etDate);
                etFuel          =      (EditText) findViewById(R.id.etFuel);
                etToll          =      (EditText) findViewById(R.id.etToll);
                etIncentives    =      (EditText) findViewById(R.id.etIncentives);
                etOnline        =      (EditText) findViewById(R.id.etOnline);
                etOtherExpense  =      (EditText) findViewById(R.id.etOtherExpense);
                etTotalEarning  =      (EditText) findViewById(R.id.etTotalEarning);
                etCashCollected =      (EditText) findViewById(R.id.etCashCollected);
                etOpeningBalance =     (EditText) findViewById(R.id.etOpeningBalance);

                TextView etDriver = (TextView) findViewById(R.id.company);
                TextView etVeh = (TextView) findViewById(R.id.etsVehicle);
                TextView etOp = (TextView) findViewById(R.id.operator);


                String date = etDate.getText().toString();
                int totalTrip = 0;
                int cashCollected = 0;
                int bankDeposite = 0;
                int fuel = 0;
                int intensives = 0;
                int toll=0;
                int openingBalance = 0;
                int totalEarning=0;
                int online=0;
                int otherExpense =0;




                if ("".equals(etTotalTrip.getText().toString()))
                {

                }
                else {
                    totalTrip = Integer.parseInt(etTotalTrip.getText().toString());
                }


                if ("".equals(etCashCollected.getText().toString()))
                {

                }
                else {
                    cashCollected = Integer.parseInt(etCashCollected.getText().toString());
                }


                if ("".equals(etFuel.getText().toString()))
                {

                }
                else {
                    fuel = Integer.parseInt(etFuel.getText().toString());
                }


                if ("".equals(etToll.getText().toString()))
                {

                }
                else {
                    toll = Integer.parseInt(etToll.getText().toString());
                }


                if ("".equals(etIncentives.getText().toString()))
                {

                }
                else {
                    intensives = Integer.parseInt(etIncentives.getText().toString());
                }


                if ("".equals(etOpeningBalance.getText().toString()))
                {

                }
                else {
                    openingBalance = Integer.parseInt(etOpeningBalance.getText().toString());
                }


                if ("".equals(etTotalEarning.getText().toString()))
                {

                }
                else {
                     totalEarning = Integer.parseInt(etTotalEarning.getText().toString());
                }


                if ("".equals(etOnline.getText().toString()))
                {

                }
                else {
                     online = Integer.parseInt(etOnline.getText().toString());
                }

                if ("".equals(etOtherExpense.getText().toString()))
                {

                }
                else {
                     otherExpense = Integer.parseInt(etOtherExpense.getText().toString());
                }



                if(!validate()) {
                    Toast.makeText(TodayActivity.this,"Submission failed",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(TodayActivity.this, UserAreaActivity.class);
                    TodayActivity.this.startActivity(intent);
                    mDialog = ProgressDialog.show(TodayActivity.this, "Loading", "Wait while loading...",true);
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {

                                    Intent intent = new Intent(TodayActivity.this, UserAreaActivity.class);
                                    TodayActivity.this.startActivity(intent);

                                } else {
                                    mDialog.dismiss();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(TodayActivity.this);
                                    builder.setMessage("Insertion of Data is Failed").setNegativeButton("Retry", null).create().show();


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };
                   // sp.setVisibility(v.GONE);
                    TodayRequest todayRequest = new TodayRequest(etVeh.getText().toString(),etDriver.getText().toString(),etOp.getText().toString(),date, totalTrip,cashCollected,bankDeposite,fuel,toll,intensives,openingBalance,totalEarning,online,otherExpense, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(TodayActivity.this);
                    queue.add(todayRequest);

                }
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void setListData()
    {

        for (int i = 0; i <=2; i++) {

            final SpinnerModel sched = new SpinnerModel();

            /******* Firstly take data in model object ******/
            sched.setCompanyName(""+myStringArray[i]);
            //sched.setImage("image"+i);
           // sched.setUrl("http:\\\\www."+i+".com");

            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add(sched);
        }

    }

    public void setVehicleListData()
    {

        for (int i = 0; i <=1; i++) {

            final SpinnerModel sched = new SpinnerModel();

            /******* Firstly take data in model object ******/
            sched.setVehicleName(""+myVehicalArray[i]);
            //sched.setImage("image"+i);
            // sched.setUrl("http:\\\\www."+i+".com");

            /******** Take Model Object in ArrayList **********/
            CustomListVehicleArr.add(sched);
        }

    }

    public void setCabListData()
    {

        for (int i = 0; i <=2; i++) {

            final SpinnerModel sched = new SpinnerModel();

            /******* Firstly take data in model object ******/
            sched.setCabName(""+myCabArray[i]);
            //sched.setImage("image"+i);
            // sched.setUrl("http:\\\\www."+i+".com");

            /******** Take Model Object in ArrayList **********/
            CustomListCabArr.add(sched);
        }

    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String sseleted = parent.getItemAtPosition(pos).toString();

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    public boolean validate(){
        boolean valid=true;
        int totalTrip = 0;
        int cashCollected = 0;
        int bankDeposite = 0;
        int fuel = 0;
        int intensives = 0;
        int totalEarning = 0;
        int toll=0;
        EditText etDate = (EditText) findViewById(R.id.etDate);
        String date = etDate.getText().toString();

        Spinner  SpinnerExample = (Spinner)findViewById(R.id.spinner);
        TextView etDriver = (TextView) findViewById(R.id.company);
        if(SpinnerExample.getSelectedItemPosition() ==0) {
            etDriver.setError("Please select Driver");
            valid=false;
        }

        Spinner  SpinnerVehicleExample = (Spinner)findViewById(R.id.vehicleSpinner);
        TextView etVeh = (TextView) findViewById(R.id.etsVehicle);
        if(SpinnerVehicleExample.getSelectedItemPosition() ==0) {
            etVeh.setError("Please select Vehical");
            valid=false;
        }


        Spinner  SpinnerCabeExample = (Spinner)findViewById(R.id.cabSpinner);
        TextView etOp = (TextView) findViewById(R.id.operator);
        if(SpinnerCabeExample.getSelectedItemPosition() ==0) {
            etOp.setError("Please select Operator");
            valid=false;
        }





        View selectedView = SpinnerExample.getSelectedView();
        if (selectedView != null && selectedView instanceof TextView) {
            TextView selectedTextView = (TextView) selectedView;
            if (!valid) {
                //String errorString = selectedTextView.getResources().getString(0);
                selectedTextView.setError("select");
            }
            else {
                selectedTextView.setError(null);
            }
        }


        /*TextView errorText = (TextView)spinner.getSelectedView();
        errorText.setError("");
        errorText.setTextColor(Color.RED);
        errorText.setError("Please select Driver");

        if ("Select Driver".equals(driverName))
        {
                valid=false;
        }

        TextView errorText1 = (TextView)mySpinner.getSelectedView();
        errorText1.setError("Select Vehicle");
        errorText1.setTextColor(Color.RED);
        errorText1.setError("Please select Vehicle");
        if ("Select Vehicle".equals(vehicalNo))
        {
            valid=false;
        }

        TextView errorText2 = (TextView)operatureSp.getSelectedView();
        errorText2.setError("Select Operature");
        errorText2.setTextColor(Color.RED);
        errorText2.setError("Please select Operator");
        if ("Select Operator".equals(operature))
        {
            valid=false;
        }
        */


        EditText etTotalTrip = (EditText) findViewById(R.id.etTotalTrip);
        if ("".equals(etTotalTrip.getText().toString()))
        {

        }
        else {
             totalTrip = Integer.parseInt(etTotalTrip.getText().toString());
        }

        EditText etCashCollected = (EditText) findViewById(R.id.etCashCollected);
        if ("".equals(etCashCollected.getText().toString()))
        {

        }
        else {
             cashCollected = Integer.parseInt(etCashCollected.getText().toString());
        }

       EditText etFuel = (EditText) findViewById(R.id.etFuel);
        if ("".equals(etFuel.getText().toString()))
        {

        }
        else {
            fuel = Integer.parseInt(etFuel.getText().toString());
        }
        EditText etToll = (EditText) findViewById(R.id.etToll);
        if ("".equals(etToll.getText().toString()))
        {

        }
        else {
            toll = Integer.parseInt(etToll.getText().toString());
        }
        EditText etIncentives = (EditText) findViewById(R.id.etIncentives);
        if ("".equals(etIncentives.getText().toString()))
        {

        }
        else {
             intensives = Integer.parseInt(etIncentives.getText().toString());
        }



        if(date.isEmpty()) {
            etDate.setError("please enter valid Date");
            valid=false;
        }

        if(totalTrip==0) {
            etTotalTrip.setError("please enter valid Total trip");
            valid=false;
        }
        if(cashCollected ==0) {
            etCashCollected.setError("please enter valid collected cash");
            valid=false;
        }



       /* if(fuel==0) {
            etFuel.setError("please enter valid fuel Expense");
            valid=false;
        }*/

       /* if(intensives==0) {
            etIncentives.setError("please enter valid Intensives");
            valid=false;
        }*/



        return valid;

    }
    @Override
    public void onStart() {
        super.onStart();
        EditText txtDate = (EditText) findViewById(R.id.etDate);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v ,boolean hasFocus){
                if(hasFocus){
                    DateDialog dialog = new DateDialog();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft,"DatePicker");

                }
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Register Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.sp.pm.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Register Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.sp.pm.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


 }

