package ir.negra.legalbill.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.negra.legalbill.R;
import ir.negra.legalbill.databinding.ActivityMainBinding;
import ir.negra.legalbill.viewmodels.activity.VM_Main;

public class MainActivity extends AppCompatActivity {

    private VM_Main vm_main;
    private NavController navController;
    private boolean doubleBackToExitPressedOnce = false;

    @BindView(R.id.TextView_Main_Footer)
    TextView TextView_Main_Footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//__________________________________________ Start onCreate
        super.onCreate(savedInstanceState);
        vm_main = new VM_Main(this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMain(vm_main);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        ButterKnife.bind(this);
        SetFooterVersion();
    }//_____________________________________________________________________________________________ End onCreate


    private void SetFooterVersion() {//_____________________________________________________________ Start SetFooterVersion

        PackageInfo pInfo = null;
        String Version;
        try {
            pInfo = getPackageManager().getPackageInfo(this.getPackageName(), 0);
            Version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Version = "0.0";
        }
        StringBuilder sp = new StringBuilder();
        sp.append(getResources().getString(R.string.Application));
        sp.append(" ");
        sp.append(getResources().getString(R.string.app_name));
        sp.append(" ");
        sp.append(getResources().getString(R.string.Version));
        sp.append(" ");
        sp.append(getResources().getString(R.string.FakeVersion));
        //sp.append(Version);
        TextView_Main_Footer.setText(sp.toString());


    }//_____________________________________________________________________________________________ End SetFooterVersion


    public void attachBaseContext(Context newBase) {//______________________________________________ Start attachBaseContext
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }//_____________________________________________________________________________________________ End attachBaseContext



    @Override
    public void onBackPressed() {//_________________________________________________________________ Start onBackPressed

        NavDestination navDestination = navController.getCurrentDestination();
        String fragment = navDestination.getLabel().toString();
        if ((!fragment.equalsIgnoreCase("Home")) &&
                (!fragment.equalsIgnoreCase("Login"))) {
            super.onBackPressed();
            return;
        }


        if (doubleBackToExitPressedOnce) {
            System.exit(1);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "برای خروج 2 بار کلیک کنید", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }//_____________________________________________________________________________________________ End onBackPressed


}
