package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvDirection;
    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDirection=findViewById(R.id.tv_direction);
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        gyroscopeEventListener= new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
            long x,y,z;
            x=Math.round(Math.toDegrees(sensorEvent.values[0]));
            y=Math.round(Math.toDegrees(sensorEvent.values[1]));
            z=Math.round(Math.toDegrees(sensorEvent.values[2]));
            if(y>10){
                tvDirection.setText("Adelante");
            }else if(y<-10){
                tvDirection.setText("Atras");
            } else if (x>10) {
                tvDirection.setText("Derecha");
            }else if (x<-10){
                tvDirection.setText("Izquierda");
            }else{
                tvDirection.setText("Alto");
            }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(gyroscopeEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);


    }
}