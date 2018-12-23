package com.example.tonir.urheilusuoritesydeemi.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.tonir.urheilusuoritesydeemi.Entities.BaseExercise;
import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.Enums.FragmentType;
import com.example.tonir.urheilusuoritesydeemi.FireBaseService.FireBaseHandler;
import com.example.tonir.urheilusuoritesydeemi.Fragments.FragmentBase;
import com.example.tonir.urheilusuoritesydeemi.Fragments.FragmentParameters;
import com.example.tonir.urheilusuoritesydeemi.Handler.FragmentHandler;
import com.example.tonir.urheilusuoritesydeemi.R;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.ButtonBarBase;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.ButtonBarParameters;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.BaseButton;
import com.example.tonir.urheilusuoritesydeemi.UI.Dialogs.ExerciseSeriesSelectionDialog;
import com.example.tonir.urheilusuoritesydeemi.UI.Dialogs.ExerciseTypeSelectionDialog;
import com.example.tonir.urheilusuoritesydeemi.UI.Events.BaseEventArgs;
import com.example.tonir.urheilusuoritesydeemi.UI.Events.ButtonClickEventArgs;
import com.example.tonir.urheilusuoritesydeemi.UI.NoEditStringField;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag.GYM;

public class MainActivity extends AppCompatActivity
        implements
        FragmentBase.FragmentListener,
        BaseExercise.BaseExerciseListener,
        View.OnClickListener,
        ExerciseTypeSelectionDialog.OnExerciseSelectedListener,
        ExerciseSeriesSelectionDialog.OnExerciseSeriesSelectedListener,
        BaseButton.ButtonListener {

    private static final ButtonTag[] buttonBarButtons = new ButtonTag[]{GYM, ButtonTag.RUN};
    private static final String TAG = MainActivity.class.getSimpleName();
    private FragmentHandler fragmentHandler;
    private List<String> headerString;
    private LinearLayout headerField;
    private List<UUID> visibleFragments;
    private FireBaseHandler fireBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentHandler = new FragmentHandler(TAG, getSupportFragmentManager(), R.id.fragment_container);
        visibleFragments = new ArrayList<>();

        InitButtonBar();
        headerString = new ArrayList<>();
        headerField = findViewById(R.id.main_view_header);
        fireBaseHandler = FireBaseHandler.getInstance();
        setViewHeader("Main");
    }

    private void InitButtonBar() {
        ButtonBarParameters parameters = new ButtonBarParameters();
        parameters.setSingleSelection(true);
        parameters.setButtonTags(buttonBarButtons);
        ButtonBarBase buttonBarBase = new ButtonBarBase(this, parameters);
        ((LinearLayout) findViewById(R.id.bottom_button_bar)).addView(buttonBarBase.getLayout());
    }




    @Override
    public void listItemSelected(BaseExercise exercise) {

    }

    public void requestDestroy(UUID identifier) {
        fragmentHandler.destroyFragment(identifier);
    }

    @Override
    public void callback(BaseExercise caller, FragmentType type) {

    }

    public BaseExercise.BaseExerciseListener getExerciseListener() {
        return this;
    }


    public void setViewHeader(String header) {
        this.headerString.clear();
        this.headerString.add(header);
        parseHeader();
    }

    public void addToViewHeader(String header) {
        this.headerString.add(header);
        parseHeader();
    }

    public void removeFromViewHeader(String header) {
        this.headerString.remove(header);
        parseHeader();
    }

    private void parseHeader() {
        headerField.removeAllViews();
        for (int i = 0; headerString.size() > i; i++) {
            String str = headerString.get(i);
            if (i + 1 != headerString.size()) {
                str += " > ";
            }
            NoEditStringField field = new NoEditStringField(getApplicationContext(), str);
            field.editText.setText(str);
            field.setOnClickListener(this);
            headerField.addView(field);
        }
    }

    @Override
    public void onClick(View v) {
        //TODO
    }

    private FragmentParameters getFragmentParameters(ButtonTag tag) {
        FragmentParameters fragmentParameters = new FragmentParameters();
        fragmentParameters.setButtonTag(tag);
        fragmentParameters.setFragmentType(tag.getRelatedFragmentType());
        fragmentParameters.setIdentifier(UUID.randomUUID());
        visibleFragments.add(fragmentParameters.getIdentifier());

        return fragmentParameters;
    }

    private FragmentParameters getFragmentParameters(ButtonTag tag, String exerciseType) {
        FragmentParameters fragmentParameters = new FragmentParameters();
        fragmentParameters.setButtonTag(tag);
        fragmentParameters.setFragmentType(tag.getRelatedFragmentType());
        fragmentParameters.setIdentifier(UUID.randomUUID());
        fragmentParameters.setExerciseType(exerciseType);
        visibleFragments.add(fragmentParameters.getIdentifier());

        return fragmentParameters;
    }

    private FragmentParameters getFragmentParameters(FragmentType tag) {
        FragmentParameters fragmentParameters = new FragmentParameters();
        fragmentParameters.setFragmentType(tag);
        fragmentParameters.setIdentifier(UUID.randomUUID());
        visibleFragments.add(fragmentParameters.getIdentifier());

        return fragmentParameters;
    }

    private FragmentParameters getFragmentParameters(FragmentType tag, String exerciseType) {
        FragmentParameters fragmentParameters = new FragmentParameters();
        fragmentParameters.setFragmentType(tag);
        fragmentParameters.setIdentifier(UUID.randomUUID());
        fragmentParameters.setExerciseType(exerciseType);
        visibleFragments.add(fragmentParameters.getIdentifier());

        return fragmentParameters;
    }
    @Override
    public void OnExerciseTypeSelected(String exerciseType) {
        try {
            addToViewHeader(exerciseType);
            fragmentHandler.show(getFragmentParameters(FragmentType.NEW_GYM_EXERCISE), this);
        } catch (Exception e) {
            Log.e(TAG, "OnExerciseTypeSelected: ", e);
        }
    }

    public BaseButton.ButtonListener getButtonListener(){
        return  this;
    }

    @Override
    public void OnExerciseSeriesTypeSelected(String exerciseSeriesType) {
        fragmentHandler.hideAll();
        visibleFragments.clear();
        try {
            addToViewHeader(exerciseSeriesType);
            ExerciseTypeSelectionDialog dialog = new ExerciseTypeSelectionDialog(this, this, fireBaseHandler.getExerciseTypes());
            dialog.show();
        } catch (Exception e) {
            Log.e(TAG, "OnExerciseSeriesTypeSelected: ", e);
        }
    }

    @Override
    public void ClickEvent(Object sender, BaseEventArgs args) {
        if(args instanceof ButtonClickEventArgs){
            ButtonTag tag = ((ButtonClickEventArgs)args).getButtonParameters().getButtonTag();
            if (tag != null) {
                try {
                    switch (tag) {
                        case GYM:
                            ExerciseSeriesSelectionDialog dialog = new ExerciseSeriesSelectionDialog(this, this, fireBaseHandler.getExerciseSeries());
                            dialog.show();
                            break;
                    }
                } catch (Exception e) {
                    Log.e(TAG, "onButtonBarButtonClicked: ", e);
                }
            }
        }
    }

    //region listeners

    //endregion
}
