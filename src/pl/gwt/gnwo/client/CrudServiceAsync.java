package pl.gwt.gnwo.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CrudServiceAsync {

	void getItems(AsyncCallback<List<String>> callback);
	void updateItem(int index, String newName, AsyncCallback<List<String>> callback);
	void removeItem(int index, AsyncCallback<List<String>> callback);
	void addItem(String newName, AsyncCallback<List<String>> callback);

}
