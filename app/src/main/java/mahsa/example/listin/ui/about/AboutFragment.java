package mahsa.example.listin.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import mahsa.example.listin.R;

public class AboutFragment extends Fragment {

    private AboutViewModel aboutViewModel;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aboutViewModel =
                ViewModelProviders.of(this).get(AboutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_about, container, false);
        //final TextView textView = root.findViewById(R.id.text_about1);
        aboutViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
        //        textView.setText(s);
            }
        });
        return root;
    }
}