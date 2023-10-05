package com.example.assignment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.assignment1.databinding.FragmentFirstBinding;

public class CalculatorFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        init the interactable widgets

        EditText mortgageAmountInput = (EditText) getView().findViewById(R.id.mortgageAmountInput);
        EditText interestInput = (EditText) getView().findViewById(R.id.interestRateInput);
        EditText amortizationInput = (EditText) getView().findViewById(R.id.amortizationPeriodInput);
        TextView resultTextView = (TextView) getView().findViewById(R.id.resultTextView);

        final Button calculateButton = (Button) getView().findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                get values for calculation

                double mortgage = Double.parseDouble(mortgageAmountInput.getText().toString());
                double interest = Double.parseDouble(interestInput.getText().toString());
                double amortization = Double.parseDouble(amortizationInput.getText().toString());

                double result = calculatePayment(mortgage, interest, amortization);

                resultTextView.setText(String.format("%.2f", result));
            }
        });
    }

    public double calculatePayment(double mortgage, double interest, double amortization) {
        double monthlyInterest = (interest / 100) / 12;
        double amortizationMonths = amortization * 12;

        return mortgage * (monthlyInterest * Math.pow(1 + monthlyInterest, amortizationMonths)) / ((Math.pow(1 + monthlyInterest, amortizationMonths)) - 1);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}