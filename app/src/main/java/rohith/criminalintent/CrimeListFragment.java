package rohith.criminalintent;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class CrimeListFragment extends ListFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
    }
}
