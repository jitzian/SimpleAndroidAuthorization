package nee.wal.com.raian.com.org.simpleauthorization;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nee.wal.com.raian.com.org.simpleauthorization.model.Result;
import nee.wal.com.raian.com.org.simpleauthorization.providers.RetrofitProviders;
import nee.wal.com.raian.com.org.simpleauthorization.rest.RestAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mUser;
    private EditText mPasswordView;
    private Button mButtonLogin;

    //Rest Implementation
    RestAPIService restAPIService = RetrofitProviders.getInstance().providesRetrofit().create(RestAPIService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        mPasswordView = findViewById(R.id.password);
        mUser = findViewById(R.id.mUser);

        mButtonLogin = findViewById(R.id.mButtonLogin);
        mButtonLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                executeLogin();
            }
        });
    }

    private void executeLogin(){
        String username = mUser.getText().toString();
        String password = mPasswordView.getText().toString();

        String base = username + ":" + password;

        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
        Call<Result> resultResponse = restAPIService.getResponse(authHeader);

        resultResponse.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d(TAG, "onResponse: " + response.message() + ":" + response.code());
                Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() + ":" + t.getCause() + ":" + t.toString());
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}

