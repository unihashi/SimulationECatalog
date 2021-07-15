package com.simulation.simulationecatalog.presentations.views.activities;

import androidx.annotation.NonNull;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.simulation.simulationecatalog.R;
import com.simulation.simulationecatalog.classes.ObscuredSharedPreferences;
import com.simulation.simulationecatalog.cores.CoreActivity;
import com.simulation.simulationecatalog.data.components.InformationDialog;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SelectiveTask;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SimulationTask;
import com.simulation.simulationecatalog.interfaces.SplashScreenInterface;
import com.simulation.simulationecatalog.presentations.presenters.SplashScreenPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class SplashScreenActivity extends CoreActivity implements SplashScreenInterface.View {

    public static final String CATALOG_VERSION = "catalog_version";

    private ObscuredSharedPreferences preferences;
    private SplashScreenInterface.Presenter presenter;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        presenter = new SplashScreenPresenter(this);
        presenter.initUseCase();

        preferences = ObscuredSharedPreferences.getPrefs(getApplicationContext(), Settings.Secure.ANDROID_ID, Context.MODE_PRIVATE);
        firebaseFirestore = FirebaseFirestore.getInstance();

        //mocking data only for cs50 version
        presenter.onDeleteCatalog();
        presenter.onDeleteColor();
        mockUpDataSimulation();
        //check firebase version is for production version
//        onCheckStockVersion();
    }

    private void mockUpDataSimulation() {
        SimulationTask list = new SimulationTask();
        List<SimulationTask> taskList = new ArrayList<>();
        // position 0
        list.setHeaderName("white tone bedroom");
        list.setDescriptionInfo("this is white tone bedroom you can change whatever wall paper you like");
        list.setCategoryType("bedroom");
        list.setOriginalImage(R.drawable.p1);
        list.setSimulationImage(R.drawable.s1);
        taskList.add(list);
        list = new SimulationTask();
        // position 1
        list.setHeaderName("grey tone bedroom");
        list.setDescriptionInfo("this is grey tone bedroom you can change whatever wall paper you like");
        list.setCategoryType("bedroom");
        list.setOriginalImage(R.drawable.p2);
        list.setSimulationImage(R.drawable.s2);
        taskList.add(list);
        list = new SimulationTask();
        // position 2
        list.setHeaderName("bright tone bedroom");
        list.setDescriptionInfo("this is bright tone bedroom you can change whatever wall paper you like");
        list.setCategoryType("bedroom");
        list.setOriginalImage(R.drawable.p3);
        list.setSimulationImage(R.drawable.s3);
        taskList.add(list);
        list = new SimulationTask();
        // position 3
        list.setHeaderName("black & white tone bedroom");
        list.setDescriptionInfo("this is black & white tone bedroom you can change whatever wall paper you like");
        list.setCategoryType("bedroom");
        list.setOriginalImage(R.drawable.p4);
        list.setSimulationImage(R.drawable.s4);
        taskList.add(list);
        list = new SimulationTask();
        // position 4
        list.setHeaderName("cement tone bedroom");
        list.setDescriptionInfo("this is cement tone bedroom you can change whatever wall paper you like");
        list.setCategoryType("bedroom");
        list.setOriginalImage(R.drawable.p5);
        list.setSimulationImage(R.drawable.s5);
        taskList.add(list);
        list = new SimulationTask();
        // position 5
        list.setHeaderName("wood tone bedroom");
        list.setDescriptionInfo("this is wood tone bedroom you can change whatever wall paper you like");
        list.setCategoryType("bedroom");
        list.setOriginalImage(R.drawable.p6);
        list.setSimulationImage(R.drawable.s6);
        taskList.add(list);
        list = new SimulationTask();
        // position 6
        list.setHeaderName("natural tone bedroom");
        list.setDescriptionInfo("this is natural tone bedroom you can change whatever wall paper you like");
        list.setCategoryType("bedroom");
        list.setOriginalImage(R.drawable.p7);
        list.setSimulationImage(R.drawable.s7);
        taskList.add(list);
        list = new SimulationTask();
        // position 7
        list.setHeaderName("dark tone bedroom");
        list.setDescriptionInfo("this is dark tone bedroom you can change whatever wall paper you like");
        list.setCategoryType("bedroom");
        list.setOriginalImage(R.drawable.p8);
        list.setSimulationImage(R.drawable.s8);
        taskList.add(list);
        list = new SimulationTask();
        // position 8
        list.setHeaderName("wood tone kitchen");
        list.setDescriptionInfo("this is wood tone kitchen you can change whatever wall paper you like");
        list.setCategoryType("kitchen");
        list.setOriginalImage(R.drawable.p9);
        list.setSimulationImage(R.drawable.s9);
        taskList.add(list);
        list = new SimulationTask();
        // position 9
        list.setHeaderName("minimal tone kitchen");
        list.setDescriptionInfo("this is minimal tone kitchen you can change whatever wall paper you like");
        list.setCategoryType("kitchen");
        list.setOriginalImage(R.drawable.p10);
        list.setSimulationImage(R.drawable.s10);
        taskList.add(list);
        list = new SimulationTask();
        // position 10
        list.setHeaderName("wonderful tone kitchen");
        list.setDescriptionInfo("this is wonderful tone kitchen you can change whatever wall paper you like");
        list.setCategoryType("kitchen");
        list.setOriginalImage(R.drawable.p11);
        list.setSimulationImage(R.drawable.s11);
        taskList.add(list);
        list = new SimulationTask();
        // position 11
        list.setHeaderName("plain tone kitchen");
        list.setDescriptionInfo("this is plain tone kitchen you can change whatever wall paper you like");
        list.setCategoryType("kitchen");
        list.setOriginalImage(R.drawable.p12);
        list.setSimulationImage(R.drawable.s12);
        taskList.add(list);
        list = new SimulationTask();
        // position 12
        list.setHeaderName("cement tone kitchen");
        list.setDescriptionInfo("this is cement tone kitchen you can change whatever wall paper you like");
        list.setCategoryType("kitchen");
        list.setOriginalImage(R.drawable.p13);
        list.setSimulationImage(R.drawable.s13);
        taskList.add(list);
        list = new SimulationTask();
        // position 13
        list.setHeaderName("bright tone kitchen");
        list.setDescriptionInfo("this is bright tone kitchen you can change whatever wall paper you like");
        list.setCategoryType("kitchen");
        list.setOriginalImage(R.drawable.p14);
        list.setSimulationImage(R.drawable.s14);
        taskList.add(list);

        presenter.onInsertCatalogDatabase(taskList);
    }

    @Override
    public void mockUpDataSelective() {
        SelectiveTask list = new SelectiveTask();
        List<SelectiveTask> taskList = new ArrayList<>();
        list.setColorName("white");
        list.setSourceColor(R.color.white_trans);
        taskList.add(list);
        list = new SelectiveTask();

        list.setColorName("grey");
        list.setSourceColor(R.color.grey_trans);
        taskList.add(list);
        list = new SelectiveTask();

        list.setColorName("pink");
        list.setSourceColor(R.color.pink_trans);
        taskList.add(list);
        list = new SelectiveTask();

        list.setColorName("red");
        list.setSourceColor(R.color.red_trans);
        taskList.add(list);
        list = new SelectiveTask();

        list.setColorName("yellow");
        list.setSourceColor(R.color.yellow_trans);
        taskList.add(list);
        list = new SelectiveTask();

        list.setColorName("orange");
        list.setSourceColor(R.color.orange_trans);
        taskList.add(list);
        list = new SelectiveTask();

        list.setColorName("green");
        list.setSourceColor(R.color.green_trans);
        taskList.add(list);
        list = new SelectiveTask();

        list.setColorName("lawngreen");
        list.setSourceColor(R.color.lawngreen_trans);
        taskList.add(list);
        list = new SelectiveTask();

        list.setColorName("teal");
        list.setSourceColor(R.color.teal_trans);
        taskList.add(list);
        list = new SelectiveTask();

        list.setColorName("purple");
        list.setSourceColor(R.color.purple_trans);
        taskList.add(list);
        list = new SelectiveTask();

        list.setColorName("blue");
        list.setSourceColor(R.color.blue_trans);
        taskList.add(list);
        list = new SelectiveTask();

        list.setColorName("black");
        list.setSourceColor(R.color.black_trans);
        taskList.add(list);

        presenter.onInsertColorDatabase(taskList);
    }

    private void onCheckStockVersion() {
        DocumentReference docRef = firebaseFirestore.collection("catalog-list-information").document("catalog-version");
        docRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            String serverVersion = document.get("version").toString();
                            if (serverVersion != null) {
                                String localVersion = preferences.getString(CATALOG_VERSION,"0");
                                if (localVersion.equals(serverVersion)) {
                                    presenter.onGetCatalogFromDatabase();
                                } else {
                                    presenter.onDeleteCatalog();
                                    preferences.edit().putString(CATALOG_VERSION, serverVersion).apply();
                                }
                            }
                        } else {
                            onGetDataFailure();
                        }
                    }
                });
    }

    @Override
    public void onGetCatalogUpdate() {
        DocumentReference docRef = firebaseFirestore.collection("catalog-list-information").document("catalog-simulation");
        docRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            List<SimulationTask> list = (List<SimulationTask>) document.get("code");
                            if (list != null) {
                                List<SimulationTask> taskList = new ArrayList<>();
                                taskList.addAll(list);
                                presenter.onInsertCatalogDatabase(taskList);
                            }
                        } else {
                            preferences.edit().putString(CATALOG_VERSION, "0").apply();
                            onGetDataFailure();
                        }
                    }
                });
    }

    @Override
    public void onGettingStart() {
        Handler handler = new Handler();
        handler.postDelayed(r, 2000);
    }

    final Runnable r = new Runnable() {
        public void run() {
            onGoToMainActivity();
        }
    };

    private void onGoToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void onGetDataFailure() {
        InformationDialog.show(
                this,
                getResources().getString(R.string.alert_message),
                getResources().getString(R.string.txt_unknown_error),
                null);
    }


    @Override
    public Context context() {
        return this;
    }
}