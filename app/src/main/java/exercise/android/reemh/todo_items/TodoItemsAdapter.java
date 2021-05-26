package exercise.android.reemh.todo_items;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class TodoItemsAdapter extends RecyclerView.Adapter<TodoItemHolder> {

    Context context;
    TodoItemsDataBase todoItemsDB;
    LayoutInflater layoutInflater;


    TodoItemsAdapter(TodoItemsDataBase todoItemsDB){
        this.todoItemsDB = todoItemsDB;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public TodoItemHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        this.layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_todo_item, parent, false);
        return new TodoItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull TodoItemHolder holder, int position) {
        TodoItem itemAtPosition = this.todoItemsDB.getItemByIndex(position);
        boolean itemStatus = itemAtPosition.getItemStatus();
        holder.itemCheckBox.setChecked(itemStatus);
        holder.itemDescription.setText(itemAtPosition.getItemDescription());
        holder.itemCheckBox.setOnClickListener(v -> {
            if (itemStatus){
                this.todoItemsDB.markItemDone(itemAtPosition);
            }
            else {
                this.todoItemsDB.markItemInProgress(itemAtPosition);
            }
        });
        // TODO: see if this is needed
//        if (itemStatus){
//            holder.itemDescription.setBackgroundColor(Color.GREEN);
//        }
//        else {
//            holder.itemDescription.setBackgroundColor(Color.LTGRAY);
//        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
