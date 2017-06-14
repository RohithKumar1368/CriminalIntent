package rohith.criminalintent;


import android.app.Fragment;
import android.os.Bundle;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID) ;
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
        // Disable the button so that users cannot click it
        mDateButton.setEnabled(false) ;

        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // set the crime's solved property
                mCrime.setSolved(b);
            }
        });

        return v ;
    }
}