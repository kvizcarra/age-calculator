package com.kevin.agecalculator;

    import android.app.DatePickerDialog;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.DatePicker;
    import android.widget.RelativeLayout;
    import android.widget.TextView;
    import android.widget.Toast;

    import org.threeten.bp.LocalDate;
    import org.threeten.bp.Period;
    import org.threeten.bp.temporal.ChronoUnit;

    import java.util.Calendar;

    import butterknife.BindView;
    import butterknife.ButterKnife;
    import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    @BindView(R.id.button_birthday) Button buttonBirthday;
    @BindView(R.id.text_view_container) RelativeLayout container;
    @BindView(R.id.text_view_years) TextView textViewYears;
    @BindView(R.id.text_view_months) TextView textViewMonths;
    @BindView(R.id.text_view_days) TextView textViewDays;

    LocalDate now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_birthday)
    void onClick() {
        // Use the current date as the default date in the picker
        now = LocalDate.now();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, this, now.getYear(), now.getMonthValue()-1, now.getDayOfMonth());
        datePickerDialog.show();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        buttonBirthday.setText(Integer.toString(year) + "-" + Integer.toString(month+1) + "-" + Integer.toString(dayOfMonth));

        Period period = Period.between(LocalDate.of(year, month,dayOfMonth), now);
        textViewYears.setText(Integer.toString(period.getYears()));
        textViewMonths.setText(Integer.toString(period.getMonths()));
        textViewDays.setText(Integer.toString(period.getDays()));

        container.setVisibility(View.VISIBLE);
    }
}
