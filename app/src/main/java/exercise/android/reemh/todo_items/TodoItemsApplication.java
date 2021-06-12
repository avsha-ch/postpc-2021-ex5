package exercise.android.reemh.todo_items;

import android.app.Application;

public class TodoItemsApplication extends Application {

    private TodoItemsDataBaseImpl todoItemsDataBase;
    private static TodoItemsApplication instance = null;


    public TodoItemsDataBaseImpl getDB() {
        return todoItemsDataBase;
    }

    public static TodoItemsApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        todoItemsDataBase = new TodoItemsDataBaseImpl(this);
        instance = this;
    }
}
