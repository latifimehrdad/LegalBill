package ir.negra.legalbill.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.negra.legalbill.R;
import ir.negra.legalbill.databinding.FragmentSplashBinding;
import ir.negra.legalbill.viewmodels.fragments.VM_Splash;

/**
 * A simple {@link Fragment} subclass.
 */
public class Splash extends Fragment {

    private View view;
    private VM_Splash vm_splash;
    private Context context;
    private NavController navController;

    @BindView(R.id.imgLoading)
    ImageView imgLoading;


    public Splash() {//_____________________________________________________________________________ Start Splash
    }//_____________________________________________________________________________________________ End Splash



    @Override
    public View onCreateView(//_____________________________________________________________________ Start onCreateView
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        context = getActivity();
        vm_splash = new VM_Splash(context);
        FragmentSplashBinding binding = DataBindingUtil.inflate(
                inflater,R.layout.fragment_splash,container,false
        );
        binding.setSplash(vm_splash);
        view = binding.getRoot();
        ButterKnife.bind(this, view);
        return view;
    }//_____________________________________________________________________________________________ End onCreateView


    @Override
    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        navController = Navigation.findNavController(view);
        Animation fade = AnimationUtils.loadAnimation(context, R.anim.fade_in_repeat);
        imgLoading.setAnimation(fade);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navController.navigate(R.id.action_splash_to_login);
            }
        }, 5000);


    }//_____________________________________________________________________________________________ End onStart
}
