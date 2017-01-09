package com.kevin.agecalculator;

    import android.app.DatePickerDialog;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.widget.Button;
    import android.widget.DatePicker;
    import android.widget.Toast;

    import java.util.Calendar;

    import butterknife.BindView;
    import butterknife.ButterKnife;
    import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    @BindView(R.id.button_birthday) Button buttonBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_birthday)
    void onClick() {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, this, year, month, day);
        datePickerDialog.show();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        buttonBirthday.setText(Integer.toString(year) + "-" + Integer.toString(month+1) + "-" + Integer.toString(dayOfMonth));
    }
}
