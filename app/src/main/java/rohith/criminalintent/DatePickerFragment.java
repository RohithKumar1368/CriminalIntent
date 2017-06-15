package rohith.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE = "com.rohith.criminalintent.date" ;
    private Date mDate ;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mDate =(Date) getArguments().getSerializable(EXTRA_DATE) ;

        //Create a calendar to get the year, month and day
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(mDate);
        final int year = calendar.get(Calendar.YEAR) ;
        final int month = calendar.get(Calendar.MONTH) ;
        final int day = calendar.get(Calendar.DAY_OF_MONTH) ;

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null) ;

        DatePicker datePicker = (DatePicker) v.findViewById(R.id.dialog_date_datePicker) ;
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                //Translate year, month, day into a Date object using a calendar
                mDate = new GregorianCalendar(year,month,day).getTime() ;

                //Update argument to preserve selected value on rotation
                getArguments().putSerializable(EXTRA_DATE , mDate);
            }
        }) ;

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .create() ;
    }

    public static DatePickerFragment newInstance(Date date){
        Bundle args = new Bundle() ;
        args.putSerializable(EXTRA_DATE , date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);

        return fragment ;
    }

    private void sendResult(int ResultCode){
        if (getTargetFragment() == null){
            return ;
        }

        Intent i = new Intent() ;
        i.putExtra(EXTRA_DATE,mDate) ;

        getTargetFragment().onActivityResult(getTargetRequestCode(),ResultCode,i);
    }
}