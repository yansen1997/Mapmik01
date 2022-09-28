package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class InsertVessel extends AppCompatActivity {

    private EditText user, id, name, builtBy, numberPhone1, numberPhone2, numberFax, numberTelex,
    maxTeusContainer, maxTeusReefer, wtInHold, wtInDeck, hold, hatche, hatchesSize, officialNo, callSign, imoNumber,
    shipLoa, shipLbp, shipBreadth, shipMaxHeight, shipFwa, shipDepth, shipDraft, shipDwt, shipDispl, gross, net, lightShip, serviceSpeed,
    foTank, doTank, fwTank, blstTank, idOwner, idManagement, idOperator, vesselType, vesselClass;
    private Button PostVessel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_vessel);

        user = findViewById(R.id.user);
        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        builtBy = findViewById(R.id.Built_by);
        numberPhone1 = findViewById(R.id.inmarsat_number_phone_1);
        numberPhone2 = findViewById(R.id.inmarsat_number_phone_2);
        numberFax = findViewById(R.id.inmarsat_number_fax);
        numberTelex = findViewById(R.id.inmarsat_telex);
        maxTeusContainer = findViewById(R.id.max_teus_container_capacity);
        maxTeusReefer = findViewById(R.id.max_teus_reefer_capacity);
        wtInHold = findViewById(R.id.stack_wt_in_hold);
        wtInDeck = findViewById(R.id.stack_wt_in_deck);
        hold = findViewById(R.id.holds);
        hatche = findViewById(R.id.hatches);
        hatchesSize = findViewById(R.id.hatches_size);
        officialNo = findViewById(R.id.official_no);
        callSign = findViewById(R.id.call_sign);
        imoNumber = findViewById(R.id.imo_number);
        shipLoa = findViewById(R.id.ship_loa);
        shipLbp = findViewById(R.id.ship_lbp);
        shipBreadth = findViewById(R.id.ship_breadth);
        shipMaxHeight = findViewById(R.id.ship_max_height);
        shipFwa = findViewById(R.id.ship_fwa);
        shipDepth = findViewById(R.id.ship_depth);
        shipDraft = findViewById(R.id.ship_draft_summer);
        shipDwt = findViewById(R.id.ship_dwt_summer);
        shipDispl = findViewById(R.id.ship_displ_summer);
        gross = findViewById(R.id.gross_tonnage);
        net = findViewById(R.id.net_tonnage);
        lightShip = findViewById(R.id.light_ship);
        serviceSpeed = findViewById(R.id.service_speed);
        foTank = findViewById(R.id.fo_tank_capacity);
        doTank = findViewById(R.id.do_tank_capacity);
        fwTank = findViewById(R.id.fw_tank_capacity);
        blstTank = findViewById(R.id.blst_tank_capacity);
        idOwner = findViewById(R.id.id_owner);
        idManagement = findViewById(R.id.id_management);
        idOperator = findViewById(R.id.id_operator);
        vesselType = findViewById(R.id.vessel_type);
        vesselClass = findViewById(R.id.vessel_class);
        PostVessel = findViewById(R.id.idBtnPost);

        PostVessel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDataUsingVolley(user.getText().toString(), id.getText().toString(), name.getText().toString(), builtBy.getText().toString(),
                numberPhone1.getText().toString(), numberPhone2.getText().toString(), numberFax.getText().toString(), numberTelex.getText().toString(),
                        maxTeusContainer.getText().toString(), maxTeusReefer.getText().toString(), wtInHold.getText().toString(), wtInDeck.getText().toString(),
                        hold.getText().toString(), hatche.getText().toString(), hatchesSize.getText().toString(), officialNo.getText().toString(), callSign.getText().toString(),
                        imoNumber.getText().toString(), shipLoa.getText().toString(), shipLbp.getText().toString(), shipBreadth.getText().toString(), shipBreadth.getText().toString(),
                        shipMaxHeight.getText().toString(), shipFwa.getText().toString(), shipDepth.getText().toString(), shipDraft.getText().toString(), shipDwt.getText().toString(),
                        shipDispl.getText().toString(), gross.getText().toString(), net.getText().toString(), lightShip.getText().toString(), serviceSpeed.getText().toString(), foTank.getText().toString(),
                        doTank.getText().toString(), fwTank.getText().toString(), blstTank.getText().toString(), idOwner.getText().toString(), idManagement.getText().toString(), idOperator,
                        vesselType.getText().toString(), vesselClass.getText().toString());
            }
        });


    }

    private void postDataUsingVolley(String toString, String toString1, String toString2, String toString3, String toString4, String toString5, String toString6, String toString7, String toString8, String toString9, String toString10, String toString11, String toString12, String toString13, String toString14, String toString15, String toString16, String toString17, String toString18, String toString19, String toString20, String toString21, String toString22, String toString23, String toString24, String toString25, String toString26, String toString27, String toString28, String toString29, String toString30, String toString31, String toString32, String toString33, String toString34, String toString35, String toString36, String toString37, EditText idOperator, String toString38, String toString39) {

        String url = "http://192.168.1.119:7001/api/insertNewVessel";

        RequestQueue queue = Volley.newRequestQueue(InsertVessel.this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(InsertVessel.this, "asd"+response, Toast.LENGTH_SHORT).show();
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){



        };
    }
}