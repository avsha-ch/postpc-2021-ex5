package exercise.android.reemh.todo_items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoItemsDataBaseImpl implements TodoItemsDataBase {

  private final List<TodoItem> todoItemList;

  TodoItemsDataBaseImpl(){
    this.todoItemList = new ArrayList<TodoItem>();
  }

  @Override
  public List<TodoItem> getCurrentItems() { return todoItemList; }

  @Override
  public void addNewInProgressItem(String description) {
    TodoItem newItem = new TodoItem(description);
    todoItemList.add(newItem);
  }

  @Override
  public void markItemDone(TodoItem item) {
    item.setItemStatus(TodoItem.DONE);
    item.setItemColor(TodoItem.DONE_COLOR);
    Collections.sort(this.todoItemList, new sortTodoList());
  }

  @Override
  public void markItemInProgress(TodoItem item) {
    item.setItemStatus(TodoItem.IN_PROGRESS);
    Collections.sort(this.todoItemList, new sortTodoList());
  }

  @Override
  public void deleteItem(TodoItem item) {
    todoItemList.remove(item);
    // TODO: MAYBE? Collections.sort(this.todoItemList, new sortTodoList());
  }

  @Override
  public int getSize() {
    return getCurrentItems().size();
  }

  @Override
  public TodoItem getItemByIndex(int i){
    return this.todoItemList.get(i);
  }
}
