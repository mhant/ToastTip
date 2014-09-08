package com.androiddev101.sampletoasttip;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androiddev101.ToastTip;
import com.androiddev101.structs.*;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
           
            return rootView;
        }

        @Override
        public void onResume() {
        	super.onResume();
        	View rootView = getView();

        	TextView text1Above = (TextView) rootView.findViewById(R.id.text1_above);
        	ToastTip.attachToViewShort(text1Above, "Text 1 above the view that it is attached to asdf as df asfd asdf a sdf asd f asdf as fd asfd a sf asd f", ShowType.ABOVE_VIEW);
        	TextView text1Below = (TextView) rootView.findViewById(R.id.text1_below);
        	ToastTip.attachToViewShort(text1Below, "Text 1 below the view it is attached to sdfsdfsd fsd fsd f sdf sd f sd f sdf sd f sf d ", ShowType.BELOW_VIEW);
        	TextView text2 = (TextView) rootView.findViewById(R.id.text2);
        	ToastTip.attachToViewShort(text2, "Text 2, this one is on a normal click really long, and when I say really long I mean really long");
        	TextView text3 = (TextView) rootView.findViewById(R.id.text3);
        	ToastTip.attachToViewShort(text3, "Text 3");
        	TextView text4 = (TextView) rootView.findViewById(R.id.text4);
        	ToastTip.attachToView(text4, "Text 4, ok another really long hint cause that's how we roll.  When we say roll we mean rollin text like we just don't care...throw them vowels up in the air...like we still don't care.");
        	TextView text5 = (TextView) rootView.findViewById(R.id.text5);
        	ToastTip.attachToView(text5, "Text 5, We mean rollin text like we just don't care...throw them vowels up in the air...like we still don't care.", ShowType.BOTTOM);
        	TextView text6 = (TextView) rootView.findViewById(R.id.text6);
        	ToastTip.attachToView(text6, "Text 6, abbra kadabra bakala mazuza", ShowType.ABOVE_VIEW);
        	TextView text7 = (TextView) rootView.findViewById(R.id.text7);
        	ToastTip.attachToView(text7, "Text 7, Woogy boogy hookie pookie", ShowType.BELOW_VIEW);


        }
    }

}
