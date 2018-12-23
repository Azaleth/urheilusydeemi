package com.example.tonir.urheilusuoritesydeemi.UI.Adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tonir.urheilusuoritesydeemi.Entities.BaseExercise;
import com.example.tonir.urheilusuoritesydeemi.Enums.ButtonTag;
import com.example.tonir.urheilusuoritesydeemi.R;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.BaseButton;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.ButtonBuilder;
import com.example.tonir.urheilusuoritesydeemi.UI.Buttons.ButtonParameters;

import java.util.List;

public class ExerciseListAdapter<Y extends BaseExercise> extends ArrayAdapter<Y> {
    private static final String TAG = ExerciseListAdapter.class.getSimpleName();
    private Context context;
    private List<Y> entities;
    private BaseButton.ButtonListener clickCallback;
    private ButtonTag tag;
    ViewGroup parent;

    public ExerciseListAdapter(Context context, List<Y> entities, BaseButton.ButtonListener clickCallback, @Nullable ButtonTag tag) {
        super(context, 0, entities);
        this.context = context;
        this.entities = entities;
        this.clickCallback = clickCallback;
        this.tag = tag;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (this.parent == null) {
            this.parent = parent;
        }
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.exercise_list_item, parent, false);
        }
        BaseExercise entity = entities.get(position);
        InitView(listItem, entity);

        listItem.setBackgroundResource(R.drawable.list_item_background);

        return listItem;
    }

    //region private
    private void InitView(View listItem, BaseExercise entity) {
        InitExerciseView(listItem, entity);
    }

    private void InitExerciseView(View listItem, BaseExercise exercise) {
        TextView name = listItem.findViewById(R.id.entity_name);
        name.setText(exercise.getName());

        TextView type = listItem.findViewById(R.id.entity_type);
        type.setText(exercise.getType());

        LinearLayout buttonPlaceholder = listItem.findViewById(R.id.entity_button);
        if (tag != null) {
            ButtonParameters parameters = new ButtonParameters();
            parameters.setOwner(exercise);
            parameters.setButtonTag(tag);
            parameters.setIdentifier(exercise.getId());
            ButtonBuilder.getButton(context, parameters, buttonPlaceholder, clickCallback);
        } else {
            buttonPlaceholder.setVisibility(View.GONE);
        }
    }
    //endregion
}
