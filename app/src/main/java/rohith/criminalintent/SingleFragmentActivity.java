package rohith.criminalintent;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public abstract class SingleFragmentActivity extends Activity{

    protected abstract Fragment createFragment() ;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getFragmentManager() ;
        Fragment fragment = fm.findFragmentById(R.id.fragemtContainer) ;

        if(fragment == null){
            fragment =  createFragment() ;
            fm.beginTransaction().add(R.id.fragemtContainer,fragment).commit() ;
        }
    }
}