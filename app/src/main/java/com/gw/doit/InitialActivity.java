package com.gw.doit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gw.doit.adapters.SingleTimesAdapter;
import com.gw.doit.model.Plan;
import com.gw.doit.model.SingleTime;
import com.gw.doit.service.DownCounterService;

import io.realm.Realm;

public class InitialActivity extends AppCompatActivity {

    private RecyclerView rvItens = null;
    private RecyclerView.Adapter mAdapter = null;
    private RecyclerView.LayoutManager mLayoutManager = null;
    private FloatingActionButton fab;
    private Realm realm = Realm.getDefaultInstance();

    private Plan myPlan = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initPlan();

        rvItens = (RecyclerView) findViewById(R.id.rvItens);
        rvItens.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(mLayoutManager);
        mAdapter = new SingleTimesAdapter(myPlan.getTimes());
        rvItens.setAdapter(mAdapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
                Snackbar.make(view, "Iniciando service", Snackbar.LENGTH_SHORT)
                        .show();
            Intent i = new Intent(this, DownCounterService.class);
            startService(i);
        });
    }

    private void initPlan() {
        try {
            if(myPlan == null)
                myPlan = realm.where(Plan.class).findFirst();
            if(myPlan == null) {
                myPlan = getDefaultPlan();
                myPlan.setId(2L);
                realm.beginTransaction();
                realm.copyToRealm(myPlan);
                realm.commitTransaction();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Plan getDefaultPlan(){
        Plan myPlan = new Plan("default");
        myPlan.addTime(new SingleTime(1L,240));
        myPlan.addTime(new SingleTime(2L,60));
        myPlan.addTime(new SingleTime(3L,240));
        myPlan.addTime(new SingleTime(4L,60));
        myPlan.addTime(new SingleTime(5L,180));
        myPlan.addTime(new SingleTime(6L,240));
        myPlan.addTime(new SingleTime(7L,180));
        myPlan.addTime(new SingleTime(8L,240));
        myPlan.addTime(new SingleTime(9L,180));
        myPlan.addTime(new SingleTime(10L,240));
        myPlan.addTime(new SingleTime(12L,180));
        myPlan.addTime(new SingleTime(13L,240));
        myPlan.addTime(new SingleTime(14L,180));
        myPlan.addTime(new SingleTime(15L,240));

        myPlan.getTimes().stream();

        return myPlan;
    }
}
