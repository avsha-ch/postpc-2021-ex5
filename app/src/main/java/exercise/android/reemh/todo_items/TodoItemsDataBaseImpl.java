package exercise.android.reemh.todo_items;

import java.util.Collections;
import java.util.List;

public class TodoItemsDataBaseImpl implements TodoItemsDataBase {

  private List<TodoItem> todoItemList;

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
  public int getNumberOfCurrentItems() {
    return todoItemList.size();
  }
}
