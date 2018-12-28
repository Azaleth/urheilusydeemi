package com.example.tonir.urheilusuoritesydeemi.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.tonir.urheilusuoritesydeemi.Entities.BaseExercise;
import com.example.tonir.urheilusuoritesydeemi.Entities.ExerciseSeries;
import com.example.tonir.urheilusuoritesydeemi.Entities.ExerciseType;
import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.Enums.FragmentType;
import com.example.tonir.urheilusuoritesydeemi.Events.DialogEvent;
import com.example.tonir.urheilusuoritesydeemi.Events.GeneralEventListener;
import com.example.tonir.urheilusuoritesydeemi.Handler.FireBaseHandler;
import com.example.tonir.urheilusuoritesydeemi.Fragments.FragmentBase;
import com.example.tonir.urheilusuoritesydeemi.Fragments.FragmentParameterHandler;
import com.example.tonir.urheilusuoritesydeemi.Fragments.FragmentParameters;
import com.example.tonir.urheilusuoritesydeemi.Handler.FragmentHandler;
import com.example.tonir.urheilusuoritesydeemi.R;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.ButtonBarBase;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.ButtonBarBuilder;
import com.example.tonir.urheilusuoritesydeemi.UI.ButtonBar.ButtonBarParameters;
import com.example.tonir.urheilusuoritesydeemi.UI.Dialogs.ExerciseSeriesSelectionDialog;
import com.example.tonir.urheilusuoritesydeemi.UI.Dialogs.ExerciseTypeSelectionDialog;
import com.example.tonir.urheilusuoritesydeemi.Events.BaseEvent;
import com.example.tonir.urheilusuoritesydeemi.Events.ButtonClickEvent;
import com.example.tonir.urheilusuoritesydeemi.UI.NoEditStringField;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag.GYM;

public class MainActivity extends AppCompatActivity
        implements
        FragmentBase.FragmentListener,
        GeneralEventListener {

    private static final ButtonTag[] buttonBarButtons = new ButtonTag[]{GYM, ButtonTag.RUN};
    private static final String TAG = MainActivity.class.getSimpleName();
    private FragmentHandler fragmentHandler;
    private List<String> headerString;
    private LinearLayout headerField;
    private List<UUID> visibleFragments;
    private FireBaseHandler fireBaseHandler;
    private ExerciseType exerciseType;
    private ExerciseSeries exerciseSeries;

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
        parameters.setButtonInfos(buttonBarButtons);
        ButtonBarBase buttonBarBase = ButtonBarBuilder.GetButtonBar(this, this, parameters);
        if (buttonBarBase != null && buttonBarBase.getLayout() != null) {
            ((LinearLayout) findViewById(R.id.bottom_button_bar)).addView(buttonBarBase.getLayout());
        }
    }


    @Override
    public void listItemSelected(BaseExercise exercise) {

    }

    public void requestDestroy(UUID identifier) {
        fragmentHandler.destroyFragment(identifier);
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
            headerField.addView(field);
        }
    }

    public GeneralEventListener getButtonListener() {
        return this;
    }

    //region listeners
    @Override
    public void Event(Object sender, BaseEvent event) {
        try {
            if (event instanceof ButtonClickEvent) {
                ButtonTag tag = ((ButtonClickEvent) event).getButtonParameters().getButtonTag();
                if (tag != null) {
                    try {
                        switch (tag) {
                            case GYM:
                                ExerciseSeriesSelectionDialog dialog = new ExerciseSeriesSelectionDialog(this, this, fireBaseHandler.getExerciseSeries());
                                dialog.show();
                                break;
                            case SAVE:
                                if (sender instanceof BaseExercise) {
                                    ((BaseExercise)sender).save();
                                }
                                break;
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "onButtonBarButtonClicked: ", e);
                    }
                }
            } else if (event instanceof DialogEvent) {
                if (sender instanceof ExerciseSeriesSelectionDialog) {
                    this.exerciseSeries = ((ExerciseSeries) ((DialogEvent) event).getSelection());
                    ExerciseTypeSelectionDialog dialog = new ExerciseTypeSelectionDialog(this, this, fireBaseHandler.getExerciseTypes(exerciseSeries.getExerciseSeries()));
                    dialog.show();
                    addToViewHeader(exerciseSeries.getExerciseSeries());
                } else if (sender instanceof ExerciseTypeSelectionDialog) {
                    fragmentHandler.hideAll();
                    visibleFragments.clear();

                    this.exerciseType = ((ExerciseType) ((DialogEvent) event).getSelection());
                    addToViewHeader(exerciseType.getExerciseType());
                    FragmentParameters parameters = FragmentParameterHandler.getFragmentParameters(FragmentType.NEW_GYM_EXERCISE, this.exerciseType, this.exerciseSeries);
                    visibleFragments.add(parameters.getIdentifier());
                    fragmentHandler.show(parameters, this);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Event: ", e);
        }
    }

    //endregion
}
