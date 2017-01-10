package com.kevin.agecalculator;

    import android.app.DatePickerDialog;
    import android.support.design.widget.FloatingActionButton;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.DatePicker;
    import android.widget.RelativeLayout;
    import android.widget.TextView;
    import android.widget.Toast;

    import org.threeten.bp.LocalDate;
    import org.threeten.bp.Period;
    import org.threeten.bp.format.DateTimeFormatter;
    import org.threeten.bp.temporal.ChronoUnit;

    import java.util.Calendar;
    import java.util.Locale;

    import butterknife.BindView;
    import butterknife.ButterKnife;
    import butterknife.OnClick;
    import icepick.Icepick;
    import icepick.State;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    @BindView(R.id.text_view_container) ViewGroup container;
    @BindView(R.id.text_view_birthday) TextView textViewBirthday;
    @BindView(R.id.text_view_years) TextView textViewYears;
    @BindView(R.id.text_view_months) TextView textViewMonths;
    @BindView(R.id.text_view_days) TextView textViewDays;
    @BindView(R.id.fab_birthday) FloatingActionButton fabBirthday;

    @State LocalDate birthday;
    @State LocalDate now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (now == null) {
            now = LocalDate.now();
        }

        if (birthday != null) {
            this.calculateAge(birthday);
        }
    }

    private void calculateAge(LocalDate birthday) {
        this.birthday = birthday;

        // Set birthday text
        textViewBirthday.setText(birthday.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")));

        // Set years, months, days text
        Period period = Period.between(birthday, now);
        textViewYears.setText(String.format(Locale.getDefault(), "%d", period.getYears()));
        textViewMonths.setText(String.format(Locale.getDefault(), "%d", period.getMonths()));
        textViewDays.setText(String.format(Locale.getDefault(), "%d", period.getDays()));

        container.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @OnClick(R.id.fab_birthday)
    void onFABClick() {
        // Use the current date as the default date in the picker
        LocalDate date = birthday != null ? birthday : now;

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, this, date.getYear(), date.getMonthValue()-1, date.getDayOfMonth());

        // Show date picker
        datePickerDialog.show();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.calculateAge(LocalDate.of(year, month+1, dayOfMonth));
    }
}
