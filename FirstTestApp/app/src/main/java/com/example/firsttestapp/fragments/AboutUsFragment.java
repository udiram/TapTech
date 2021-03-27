package com.example.firsttestapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.firsttestapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {

    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_about_us, container, false);


        Button more = view.findViewById(R.id.moreBtn);
        more.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {

                /*Intent more = new Intent(getActivity(), BrandsActivity.class);
               startActivity(more);*/
               //Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_brands);
               Navigation.findNavController(v).navigate(R.id.nav_brands);
            }
        });

        return view;

    }




}

//}
//    public void more(View view)
//    {
//        TextView tv= view.findViewById(R.id.more);
//
//        //alter text of textview widget
//        tv.setText("This text view is clicked");
//
//        //assign the textview forecolor
//        tv.setTextColor(Color.GREEN);
//    }
//}


