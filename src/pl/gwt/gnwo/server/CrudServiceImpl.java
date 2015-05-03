package pl.gwt.gnwo.server;

import java.util.ArrayList;
import java.util.List;

import pl.gwt.gnwo.client.CrudService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CrudServiceImpl extends RemoteServiceServlet implements CrudService{
	private static final long serialVersionUID = 1L;
	private List<String> data = new ArrayList<String>();
	
	public List<String> getItems() {
		return data;
	}
	
	public List<String> updateItem(int index, String newName){
		data.set(index, newName);
		return data;
	}
	public List<String> removeItem(int index){
		data.remove(index);
		return data;
	}
	public List<String> addItem(String newName){
		data.add(newName);
		return data;
	}

}
