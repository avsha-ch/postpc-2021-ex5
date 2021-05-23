package exercise.android.reemh.todo_items;

import java.util.Comparator;

public class sortTodoList implements Comparator<TodoItem> {

    @Override
    public int compare(TodoItem o1, TodoItem o2) {
        int status = o1.getItemStatus() - o2.getItemStatus();
        if (status == 0) {
            return (int) (o2.getItemCreationTime() - o1.getItemCreationTime());
        }
        return status;
    }
}
