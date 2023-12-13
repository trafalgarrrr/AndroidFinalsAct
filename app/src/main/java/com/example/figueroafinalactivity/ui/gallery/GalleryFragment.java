package com.example.figueroafinalactivity.ui.gallery;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.figueroafinalactivity.DashboardAdapter;
import com.example.figueroafinalactivity.R;

import java.util.ArrayList;
import java.util.List;

import com.example.figueroafinalactivity.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String BACKGROUND_COLOR_KEY = "BackgroundColor";
    private static final String TEXT_COLOR_KEY = "TextColor";

    private ConstraintLayout parentLayout; // Reference to the parent ConstraintLayout
    private RecyclerView recyclerView;
    private DashboardAdapter adapter;
    private SharedPreferences sharedPreferences;

    private FragmentGalleryBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        parentLayout = root.findViewById(R.id.parentLayout);
        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        List<String> titles = new ArrayList<>();
        titles.add("Vision");
        titles.add("Mission");

        List<String> descriptions = new ArrayList<>();
        descriptions.add("Laguna University shall be a socially responsive educational institution of choice providing holistically developed individuals in the Asia-Pacific region");
        descriptions.add("Laguna University is committed to produce academically prepared and technically skilled individuals who are socially and morally upright");

        adapter = new DashboardAdapter(titles, descriptions);
        recyclerView.setAdapter(adapter);

        Button colorPickerButton = root.findViewById(R.id.colorPickerButton);
        colorPickerButton.setOnClickListener(v -> showColorPicker());

        Button textColorPickerButton = root.findViewById(R.id.textColorPickerButton);
        textColorPickerButton.setOnClickListener(v -> showTextColorPicker());

        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, 0);
        int savedBackgroundColor = sharedPreferences.getInt(BACKGROUND_COLOR_KEY, Color.WHITE);
        int savedTextColor = sharedPreferences.getInt(TEXT_COLOR_KEY, Color.BLACK);

        parentLayout.setBackgroundColor(savedBackgroundColor); // Set the saved background color
        adapter.setTextColor(savedTextColor); // Set the saved text color for RecyclerView

        return root;
    }

    private void showColorPicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Select Background Color");

        String[] colors = {"Yellow", "Cyan", "White", "Light Gray", "Magenta", "Black",}; // Add more colors as needed
        final int[] colorValues = {Color.YELLOW, Color.CYAN, Color.WHITE, Color.LTGRAY, Color.MAGENTA, Color.BLACK}; // Corresponding color values
        builder.setItems(colors, (dialog, which) -> {
            int color = colorValues[which];
            parentLayout.setBackgroundColor(color);
            saveColorToPreferences(color, BACKGROUND_COLOR_KEY);
        });

        builder.show();
    }

    private void showTextColorPicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Select Text Color");

        String[] colors = {"Black", "Red", "Green", "Yellow", "Blue", "Cyan"}; // Add more colors as needed
        final int[] colorValues = {Color.BLACK, Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.CYAN}; // Corresponding color values
        builder.setItems(colors, (dialog, which) -> {
            int color = colorValues[which];
            adapter.setTextColor(color);
            saveColorToPreferences(color, TEXT_COLOR_KEY);
        });

        builder.show();
    }

    private void saveColorToPreferences(int color, String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, color);
        editor.apply();
    }
}
