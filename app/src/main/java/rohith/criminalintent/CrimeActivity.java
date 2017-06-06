package rohith.criminalintent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CrimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        FragmentManager fm = getFragmentManager() ;
        Fragment fragment = fm.findFragmentById(R.id.fragemtContainer) ;

        if(fragment == null){
            fragment = new CrimeFragment() ;
            fm.beginTransaction().add(R.id.fragemtContainer,fragment).commit() ;
        }
    }
}
