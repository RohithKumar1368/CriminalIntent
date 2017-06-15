package rohith.criminalintent;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import java.util.UUID;


public class CrimeFragment extends Fragment {

    private Crime mCrime ;
    private EditText mTitleField ;
    private Button mDateButton ;
    private CheckBox mSolvedCheckBox ;

    public static final String EXTRA_CRIME_ID = "com.rohith.criminalintent.crime_id" ;
    private static final String DIALOG_DATE = "date" ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID) ;
        // Progress : 8:20PM 14-06-2017 mCrime object is null, we aren't receiving a crime object!!!
        // Update : 9:07PM 14-06-2017 Resolved the NullPointerException.

        /*
         * It seems like the crime id retrieved using the intent extra doesn't belong to any crime,
         * which is weird because it's the id of the crime in crimelist.
         * Theoretically no new UUID's are generated i.e. to say that every UUID belongs to some crime
         * so we should get a hit results. But here in practise we aren't getting any hit results.
         * Something is off, and i've to find it.
         *
         * Solution: Found the culprit to be == to check equality
         * Remedy was to use .euqals() method to check if the UUID's are same, and the app works.
         * Two hours well spent on a simple thing. Bravo
         */
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId) ;
    }



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup parent,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime,parent,false) ;

        //Wiring up the widgets
        mTitleField = (EditText) v.findViewById(R.id.crime_title) ;
        mDateButton = (Button) v.findViewById(R.id.crime_date) ;
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved) ;

        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // We do not need to oberride this method now
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // We do not need to override this one too
            }
        });

        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager() ;
                DatePickerFragment dialog = new DatePickerFragment() ;
                dialog.show(fm,DIALOG_DATE);
            }
        });

        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // set the crime's solved property
                mCrime.setSolved(b);
            }
        });

        /*
         * This doesn't work for some reason
         */
        getActivity().setTitle(mTitleField.getText());

        return v ;
    }

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle() ;
        args.putSerializable(EXTRA_CRIME_ID , crimeId);

        CrimeFragment fragment = new CrimeFragment() ;
        fragment.setArguments(args) ;

        return fragment ;
    }
}