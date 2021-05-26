package exercise.android.reemh.todo_items;

import java.util.Comparator;

public class sortTodoList implements Comparator<TodoItem> {

    @Override
    public int compare(TodoItem o1, TodoItem o2) {
        int status = Boolean.compare(o2.getItemStatus(), o1.getItemStatus());
        if (status == 0) {
            return Long.compare(o1.getItemCreationTime(),o2.getItemCreationTime());
        }
        return status;
    }
}
