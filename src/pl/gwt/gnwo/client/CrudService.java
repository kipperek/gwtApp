package pl.gwt.gnwo.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("crud")
public interface CrudService extends RemoteService{
	
	List<String> getItems();
	List<String> updateItem(int index, String newName);
	List<String> removeItem(int index);
	List<String> addItem(String newName);
}
