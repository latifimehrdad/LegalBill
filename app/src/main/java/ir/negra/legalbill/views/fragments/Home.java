package ir.negra.legalbill.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.negra.legalbill.R;
import ir.negra.legalbill.databinding.FragmentHomeBinding;
import ir.negra.legalbill.viewmodels.fragments.VM_Home;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    private Context context;
    private View view;
    private VM_Home vm_home;
    private NavController navController;

    @BindView(R.id.LinearLayoutLegalBill)
    LinearLayout LinearLayoutLegalBill;

    @BindView(R.id.LinearLayoutTrafficController)
    LinearLayout LinearLayoutTrafficController;

    @BindView(R.id.LinearLayoutLeaveRequest)
    LinearLayout LinearLayoutLeaveRequest;

    public Home() {//_______________________________________________________________________________ Start Home
    }//_____________________________________________________________________________________________ End Home


    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {//__________________________________________________________ Start onCreateView
        context = getActivity();
        vm_home = new VM_Home(context);
        FragmentHomeBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home,container,false
        );
        binding.setHome(vm_home);
        view = binding.getRoot();
        ButterKnife.bind(this, view);
        return view;
    }//_____________________________________________________________________________________________ End onCreateView


    @Override
    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        navController = Navigation.findNavController(view);
        SetOnclick();
    }//_____________________________________________________________________________________________ End onStart


    private void SetOnclick() {//___________________________________________________________________ Start SetOnclick

        LinearLayoutLegalBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_home_to_legalBill);
            }
        });

        LinearLayoutTrafficController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_home_to_trafficController);
            }
        });

        LinearLayoutLeaveRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_home_to_leaveRequest);
            }
        });

    }//_____________________________________________________________________________________________ End SetOnclick


}
