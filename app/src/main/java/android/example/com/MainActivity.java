package android.example.com;

/* It is a track lap counter with display of the number of kilometers traveled and the number of calories burned. Distances are in meters*/


import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private static final String TAG ="";
    int nbOfLaps = 0;
    int distance = 0;
    int calories = 0;
    private Chronometer mChronometer;
    private long pauseOffset;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChronometer = findViewById(R.id.chronometer_test);
        mChronometer.setFormat("%S");
        mChronometer.setBase(SystemClock.elapsedRealtime());

        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer mChronometer) {

            }
        });
        numberOfLaps(0);
    }

    public void start_button(View v) {
        if (!running) {
            mChronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            mChronometer.start();
            running = true;
        }
    }

    public void stop_button(View v) {
        if (running) {
            mChronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - mChronometer.getBase();
            running = false;
        }
    }

    // counter +
    public void plusNumberOfLaps(View v) {
        nbOfLaps += 1;
        numberOfLaps(nbOfLaps);
    }

    // counter -
    public void minusNumberOfLaps(View v) {
        nbOfLaps -= 1;
        numberOfLaps(nbOfLaps);
    }

    //displays the number of laps
    public void numberOfLaps(int nbOfLaps) {
        TextView number_of_laps = (TextView) findViewById(R.id.number_of_laps);
        number_of_laps.setText(String.valueOf(nbOfLaps));
    }

    //calculates the total distance traveled
    public void total_distance(View v) {
        distance = nbOfLaps * 400;
        displayDistance(+distance + " m");
    }

    // displays the total distance traveled
    private void displayDistance(String distanceTot) {
        TextView totalDistance = (TextView) findViewById(R.id.total_distance);
        totalDistance.setText(distanceTot);
    }

    //calculates the number of calories burned
    public void total_calories(View v) {
        calories = 75 * distance / 1000;
        displayCalories(+calories + " kcal");
    }

    //displays the number of calories burned
    private void displayCalories(String caloriesTot) {
        TextView totalCalories = (TextView) findViewById(R.id.total_calories_kcal);
        totalCalories.setText(caloriesTot);
    }

    // reset button
    public void reset_btn(View v) {
        mChronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
        nbOfLaps = 0;
        numberOfLaps(nbOfLaps);
        displayDistance("0");
        displayCalories("0");
    }

}
