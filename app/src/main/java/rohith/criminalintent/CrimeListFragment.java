package rohith.criminalintent;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

public class CrimeListFragment extends ListFragment {

    private ArrayList<Crime> mCrimes ;
    private static String TAG = "CrimeListFragment" ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        mCrimes = CrimeLab.get(getActivity()).getCrimes() ;

        CrimeAdapter adapter = new CrimeAdapter(mCrimes);

        setListAdapter(adapter) ;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        Crime c = ((CrimeAdapter) getListAdapter() ).getItem(position) ;
        Log.d(TAG,c.getTitle() + " was clicked") ;
    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {

        public CrimeAdapter(ArrayList<Crime> crimes){
            super(getActivity(),0,crimes) ;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // If we weren't given a view inflate this one
            if(convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime,null) ;
            }

            //Configure the view for this crime
            Crime c = getItem(position) ;

            TextView titleTextView = (TextView) convertView.findViewById(R.id.crime_list_item_titleTextView) ;
            titleTextView.setText(c.getTitle());

            TextView dateTextView = (TextView) convertView.findViewById(R.id.crime_list_item_dateTextView) ;
            dateTextView.setText(c.getDate().toString());

            CheckBox solvedCheckBox = (CheckBox) convertView.findViewById(R.id.crime_list_item_solvedCheckBox) ;
            solvedCheckBox.setChecked(c.isSolved());

            return convertView ;
        }
    }
}
