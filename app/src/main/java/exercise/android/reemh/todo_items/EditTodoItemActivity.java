package exercise.android.reemh.todo_items;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class EditTodoItemActivity extends AppCompatActivity {
    private static final String CREATION_TIME_STR = "TODO creation time: %s";
    private static final String MODIFIED_TIME_STR = "TODO last modified %s";
    private static final String EMPTY_STRING = "";
    private TodoItemsDataBaseImpl dataBase = null;
    private BroadcastReceiver receiverDBChanged;
    private String editItemId;
    private TodoItem editTodoItem;
    private static final Gson gson = new Gson();
    private TextView showCreationTime;
    private TextView showModifiedTime;
    private CheckBox editStatusCheckBox;
    private EditText editDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo_item);

        if (dataBase == null){
            Log.d("DB", "1");
            dataBase = TodoItemsApplication.getInstance().getDB();
        }
        Intent intent = getIntent();
        editItemId = intent.getStringExtra("edit_todo_item");
        editTodoItem = this.dataBase.getItemById(editItemId);
        showCreationTime = findViewById(R.id.showCreationTime);
        showModifiedTime = findViewById(R.id.showModifiedTime);
        editStatusCheckBox = findViewById(R.id.editStatus);
        editDescription = findViewById(R.id.editDescription);

        String creationTime = String.format(CREATION_TIME_STR, editTodoItem.getItemCreationTimeFormatted());
        String modifiedTime = String.format(MODIFIED_TIME_STR, editTodoItem.getItemModifiedTimeFormatted());

        showCreationTime.setText(creationTime);
        showModifiedTime.setText(modifiedTime);
        editStatusCheckBox.setChecked(editTodoItem.getItemStatus());
        editDescription.setText(editTodoItem.getItemDescription());
        editStatusCheckBox.setOnClickListener(v -> {
            if (editStatusCheckBox.isChecked()){
                this.dataBase.markItemDone(editTodoItem);
            }
            else {
                this.dataBase.markItemInProgress(editTodoItem);
            }
        });

        editDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (editDescription.getText().toString().equals(EMPTY_STRING)){
                    return;
                }
                dataBase.setItemDescription(editItemId, editDescription.getText().toString());
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("creation_time", showCreationTime.toString());
        outState.putString("modified_time", showModifiedTime.toString());
        outState.putBoolean("status_check_box", editStatusCheckBox.isChecked());
        outState.putString("edit_description", editDescription.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        showCreationTime.setText(savedInstanceState.getString("creation_time"));
        showModifiedTime.setText(savedInstanceState.getString("modified_time"));
        editStatusCheckBox.setChecked(savedInstanceState.getBoolean("status_check_box"));
        editDescription.setText(savedInstanceState.getString("edit_description"));
    }
}
