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
import ir.negra.legalbill.databinding.FragmentLeaveRequestBinding;
import ir.negra.legalbill.viewmodels.fragments.VM_LeaveRequest;

public class LeaveRequest extends Fragment {

    private Context context;
    private View view;
    private VM_LeaveRequest vm_leaveRequest;
    private NavController navController;



    public LeaveRequest() {//_______________________________________________________________________ Start LeaveRequest
    }//_____________________________________________________________________________________________ End LeaveRequest


    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {//__________________________________________________________ Start onCreateView
        context = getActivity();
        vm_leaveRequest = new VM_LeaveRequest(context);
        FragmentLeaveRequestBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_leave_request, container,false
        );
        binding.setLeave(vm_leaveRequest);
        view = binding.getRoot();
        ButterKnife.bind(this,view);
        return view;
    }//_____________________________________________________________________________________________ End onCreateView


    @Override
    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        navController = Navigation.findNavController(view);
        SetOnClick();
    }//_____________________________________________________________________________________________ End onStart


    private void SetOnClick() {//___________________________________________________________________ Start SetOnClick

    }//_____________________________________________________________________________________________ End SetOnClick

}
