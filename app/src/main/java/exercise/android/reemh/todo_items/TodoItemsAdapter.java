package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoItemsAdapter extends RecyclerView.Adapter<TodoItemViewHolder> {

    Context context;
    TodoItemsDataBaseImpl dataBase;
    LayoutInflater layoutInflater;



    TodoItemsAdapter(TodoItemsDataBaseImpl dataBase){
        this.dataBase = dataBase;
    }

    @NonNull
    @Override
    public TodoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        this.layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_todo_item, parent, false);
        return new TodoItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoItemViewHolder holder, int position) {
        TodoItem itemAtPosition = this.dataBase.getItemByIndex(position);
        boolean itemStatus = itemAtPosition.getItemStatus();
        holder.itemCheckBox.setChecked(itemStatus);
        holder.itemDescription.setText(itemAtPosition.getItemDescription());
        holder.itemCreationTime.setText(itemAtPosition.getItemCreationTimeFormatted());
        holder.itemRowLayout.setBackgroundColor(itemAtPosition.getItemColor());
        holder.itemCheckBox.setOnClickListener(v -> {
            if (holder.itemCheckBox.isChecked()){
                this.dataBase.markItemDone(itemAtPosition);
            }
            else {
                this.dataBase.markItemInProgress(itemAtPosition);
            }
            holder.itemRowLayout.setBackgroundColor(itemAtPosition.getItemColor());
            this.notifyDataSetChanged();
        });
        holder.itemDeleteButton.setOnClickListener(v -> {
            this.dataBase.deleteItem(itemAtPosition);
            this.notifyDataSetChanged();
        });
        holder.itemEditButton.setOnClickListener(v -> {
            Intent intent = new Intent(this.context, EditTodoItemActivity.class);
            intent.putExtra("edit_todo_item", itemAtPosition.getItemId());
            this.context.startActivity(intent);
            // v.getContext().startActivity(intent);
            // TODO: maybe not the same db as in editActivity, maybe this row is a problem
            // dataBase = TodoItemsApplication.getInstance().getDB();
            // holder.itemRowLayout.setBackgroundColor(this.dataBase.getItemByIndex(position).getItemColor());
            // this.notifyDataSetChanged();

        });
    }

    @Override
    public int getItemCount() {
        return this.dataBase.getSize();
    }


}
