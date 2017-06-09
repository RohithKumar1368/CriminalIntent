package rohith.criminalintent;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CrimeListFragment extends ListFragment {

    private ArrayList<Crime> mCrimes ;
    private static String TAG = "CrimeListFragment" ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        mCrimes = CrimeLab.get(getActivity()).getCrimes() ;

        ArrayAdapter<Crime> adapter = new ArrayAdapter<Crime>(getActivity(),
                android.R.layout.simple_list_item_1,
                mCrimes);

        setListAdapter(adapter) ;
    }

    @Override
    public void onListItemClick(ListView l, View v, int positon, long id){
        Crime c = (Crime) (getListAdapter()).getItem(positon) ;
        Log.d(TAG,c.getTitle() + " was clicked") ;
    }
}
