package rohith.criminalintent;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CrimeListFragment extends ListFragment {

    private ArrayList<Crime> mCrimes ;
    private static String TAG = "CrimeListFragment" ;
    private Context context = getActivity() ;

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
        Intent i = new Intent(getActivity(),CrimeActivity.class) ;
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID,c.getId()) ;
        startActivity(i) ;
    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {

        public CrimeAdapter(ArrayList<Crime> crimes){
            super(getActivity(),0,crimes) ;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // If we weren't given a view inflate this one
            // Check to see if a recycled view was passed in.
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
