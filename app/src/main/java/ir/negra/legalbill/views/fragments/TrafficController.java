package ir.negra.legalbill.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.negra.legalbill.R;
import ir.negra.legalbill.databinding.AdabterTerafficsBinding;
import ir.negra.legalbill.databinding.FragmentTrafficControllerBinding;
import ir.negra.legalbill.viewmodels.fragments.VM_TrafficController;
import ir.negra.legalbill.views.adabters.AB_TrafficController;

public class TrafficController extends Fragment {

    private Context context;
    private View view;
    private VM_TrafficController vm_trafficController;
    private AB_TrafficController ab_trafficController;

    @BindView(R.id.RecyclerViewTraffics)
    RecyclerView RecyclerViewTraffics;


    public TrafficController() {//__________________________________________________________________ Start TrafficController
    }//_____________________________________________________________________________________________ End TrafficController


    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {//__________________________________________________________ Start onCreateView
        context = getActivity();
        vm_trafficController = new VM_TrafficController(context);
        FragmentTrafficControllerBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_traffic_controller, container, false
        );
        binding.setTraffic(vm_trafficController);
        view = binding.getRoot();
        ButterKnife.bind(this, view);
        return view;
    }//_____________________________________________________________________________________________ End onCreateView


    @Override
    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        ab_trafficController = new AB_TrafficController();
        RecyclerViewTraffics.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        RecyclerViewTraffics.setAdapter(ab_trafficController);
    }//_____________________________________________________________________________________________ End onStart


}
