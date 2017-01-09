package com.kevin.agecalculator;

    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.widget.Button;
    import android.widget.Toast;

    import butterknife.BindView;
    import butterknife.ButterKnife;
    import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
        @BindView(R.id.button_birthday) Button buttonBirthday;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);
        }

        @OnClick(R.id.button_birthday)
        void onClick() {
            Toast.makeText(this, R.string.button_clicked, Toast.LENGTH_SHORT).show();
        }
}
