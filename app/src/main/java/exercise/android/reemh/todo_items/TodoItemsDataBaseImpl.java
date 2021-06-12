package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoItemsDataBaseImpl implements TodoItemsDataBase {

  private final ArrayList<TodoItem> todoItemList;
  private final Context context;
  private final SharedPreferences sp;

  TodoItemsDataBaseImpl(Context context){
    this.todoItemList = new ArrayList<TodoItem>();
    this.context = context;
    this.sp = context.getSharedPreferences("local_db_todoitems", Context.MODE_PRIVATE);
  }

  @Override
  public ArrayList<TodoItem> getCurrentItems() { return todoItemList; }

  @Override
  public void addNewInProgressItem(String description) {
    TodoItem newItem = new TodoItem(description);
    todoItemList.add(newItem);
    sendBroadcastDbChanged();
  }

  @Override
  public void markItemDone(TodoItem item) {
    item.setItemStatus(TodoItem.DONE);
    item.setItemColor(TodoItem.DONE_COLOR);
    Collections.sort(this.todoItemList, new sortTodoList());
    sendBroadcastDbChanged();
  }

  @Override
  public void markItemInProgress(TodoItem item) {
    item.setItemStatus(TodoItem.IN_PROGRESS);
    item.setItemColor(Color.WHITE);
    Collections.sort(this.todoItemList, new sortTodoList());
    sendBroadcastDbChanged();
  }

  @Override
  public void deleteItem(TodoItem item) {
    todoItemList.remove(item);
    // TODO: MAYBE? Collections.sort(this.todoItemList, new sortTodoList());
    sendBroadcastDbChanged();
  }

  @Override
  public int getSize() {
    return getCurrentItems().size();
  }

  @Override
  public TodoItem getItemByIndex(int i){
    return this.todoItemList.get(i);
  }

  private void sendBroadcastDbChanged(){
    Intent broadcast = new Intent("db_changed");
    broadcast.putExtra("new_list", getCurrentItems());
    context.sendBroadcast(broadcast);
  }
}
