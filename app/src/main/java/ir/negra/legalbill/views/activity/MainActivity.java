package ir.negra.legalbill.views.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.negra.legalbill.R;
import ir.negra.legalbill.databinding.ActivityMainBinding;
import ir.negra.legalbill.viewmodels.activity.VM_Main;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private VM_Main vm_main;
    private NavController navController;
    private boolean doubleBackToExitPressedOnce = false;
    private AppBarConfiguration appBarConfiguration;
    private boolean MenuOpen = false;

    @BindView(R.id.TextView_Main_Footer)
    TextView TextView_Main_Footer;

    @BindView(R.id.LinearLayoutMain)
    LinearLayout LinearLayoutMain;

    @BindView(R.id.RelativeLayoutHeader)
    RelativeLayout RelativeLayoutHeader;

    @BindView(R.id.LinearLayoutFooter)
    LinearLayout LinearLayoutFooter;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
//
//    @BindView(R.id.nvView)
//    NavigationView nvView;

    @BindView(R.id.ImageViewMenu)
    ImageView ImageViewMenu;

    @BindView(R.id.LinearLayoutMenuHome)
    LinearLayout LinearLayoutMenuHome;

    @BindView(R.id.LinearLayoutMenuLegal)
    LinearLayout LinearLayoutMenuLegal;

    @BindView(R.id.LinearLayoutMenuTraffic)
    LinearLayout LinearLayoutMenuTraffic;

    @BindView(R.id.LinearLayoutLeave)
    LinearLayout LinearLayoutLeave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {//__________________________________________ Start onCreate
        super.onCreate(savedInstanceState);
        vm_main = new VM_Main(this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMain(vm_main);
        ButterKnife.bind(this);
        SetMenu();
        SetFooterVersion();
        SetListener();
    }//_____________________________________________________________________________________________ End onCreate


    private void SetMenu() {//______________________________________________________________________ Start onCreate

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph())
                        .setDrawerLayout(mDrawer)
                        .build();
//        NavigationUI.setupWithNavController(nvView, navController);
//        nvView.setNavigationItemSelectedListener(this);

        ImageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.openDrawer(Gravity.RIGHT, true);
            }
        });

        mDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                MenuOpen = true;
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                MenuOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        SetMenuClick();

    }//_____________________________________________________________________________________________ End onCreate


    private void SetMenuClick() {//_________________________________________________________________ Start SetMenuClick

        LinearLayoutMenuHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.home);
            }
        });

        LinearLayoutMenuLegal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.legalBill);
            }
        });

        LinearLayoutMenuTraffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.trafficController);
            }
        });

        LinearLayoutLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.leaveRequest);
            }
        });


    }//_____________________________________________________________________________________________ End SetMenuClick


    private void SetListener() {//__________________________________________________________________ Start onCreate

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(
                    @NonNull NavController controller,
                    @NonNull NavDestination destination,
                    @Nullable Bundle arguments) {

                mDrawer.closeDrawer(Gravity.RIGHT);

                String fragment = destination.getLabel().toString();
                if ((!fragment.equalsIgnoreCase("Splash")) &&
                        (!fragment.equalsIgnoreCase("Login"))) {
                    RelativeLayoutHeader.setVisibility(View.VISIBLE);
                    LinearLayoutFooter.setVisibility(View.VISIBLE);
                    LinearLayoutMain.setBackgroundResource(R.color.ML_White);
                } else {
                    RelativeLayoutHeader.setVisibility(View.INVISIBLE);
                    LinearLayoutFooter.setVisibility(View.INVISIBLE);
                    LinearLayoutMain.setBackgroundResource(R.color.ML_LoginBack);
                }

            }
        });
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {//_________________________ Start attachBaseContext

        menuItem.setChecked(true);
        mDrawer.closeDrawers();

        int id = menuItem.getItemId();

        switch (id) {

            case R.id.home:
                navController.navigate(R.id.home);
                break;

            case R.id.legalBill:
                navController.navigate(R.id.legalBill);
                break;

            case R.id.trafficController:
                navController.navigate(R.id.trafficController);
                break;

            case R.id.leaveRequest:
                navController.navigate(R.id.leaveRequest);
                break;


        }
        return true;

    }//_____________________________________________________________________________________________ End attachBaseContext


    @Override
    public void onBackPressed() {//_________________________________________________________________ Start onBackPressed

        if(MenuOpen) {
            mDrawer.closeDrawer(Gravity.RIGHT);
            return;
        }

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
