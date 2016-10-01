package rhynix.eliud.sharedpreffernce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import javax.xml.parsers.SAXParser;

public class MainActivity extends AppCompatActivity {

    UserSessionManager session;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new UserSessionManager(getApplicationContext());

        TextView lblName = (TextView)findViewById(R.id.lblName);
        TextView lblEmail = (TextView)findViewById(R.id.lblEmail);

        btnLogout = (Button)findViewById(R.id.btnLogout);

        Toast.makeText(getApplicationContext(),"User Login Status"+session.isUserLoggedIn(),Toast.LENGTH_LONG).show();

        if (session.checkedLogin())
            finish();

        HashMap<String ,String> user = session.getUserDetails();
        String name  = user.get(UserSessionManager.KEY_NAME);
        String email = user.get(UserSessionManager.KEY_EMAIL);

        lblName.setText(Html.fromHtml("Name:<b>"+name+"<b>"));
        lblEmail.setText(Html.fromHtml("Email:<b>"+email+"<b>"));

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
            }
        });
    }
}
