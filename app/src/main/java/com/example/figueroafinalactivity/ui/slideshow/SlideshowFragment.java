package com.example.figueroafinalactivity.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.figueroafinalactivity.R;
import com.example.figueroafinalactivity.databinding.FragmentSlideshowBinding;

import java.util.Random;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private TextView notificationTextView;
    private EditText userInputEditText;
    private int randomNumber;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        notificationTextView = root.findViewById(R.id.text_notifications);
        userInputEditText = root.findViewById(R.id.editText_userInput);

        // Generate a random number between 1 to 100
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;

        root.findViewById(R.id.button_check).setOnClickListener(view -> checkGuess());

        return root;
    }

    private void checkGuess() {
        String userInputString = userInputEditText.getText().toString();

        if (!userInputString.isEmpty()) {
            int userGuess = Integer.parseInt(userInputString);

            if (userGuess == randomNumber) {
                notificationTextView.setText("You Got it!");
            } else if (userGuess < randomNumber) {
                notificationTextView.setText("Try Higher");
            } else {
                notificationTextView.setText("Try Lower");
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}