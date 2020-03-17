package ir.negra.legalbill.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.negra.legalbill.R;
import ir.negra.legalbill.databinding.ActivityMainBinding;
import ir.negra.legalbill.viewmodels.activity.VM_Main;

public class MainActivity extends AppCompatActivity {

    private VM_Main vm_main;

    @BindView(R.id.TextView_Main_Footer)
    TextView TextView_Main_Footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//__________________________________________ Start onCreate
        super.onCreate(savedInstanceState);
        vm_main = new VM_Main(this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMain(vm_main);
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

}
