package pl.gwt.gnwo.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;



public class GwtProjekt implements EntryPoint {
	
	Button myBtn = new Button("Add element");
	Button removeBtn = new Button("Remove element");
	Button editBtn = new Button("Edit element");
	

	TextBox txtField = new TextBox();
	TextBox editField = new TextBox();
	CellList<String> cellList = new CellList<String>(new TextCell());
	private CrudServiceAsync crudSvc = GWT.create(CrudService.class);
	
    MyDataProvider dataProvider = new MyDataProvider();
    static List<String> myData = new ArrayList<String>();
	
	 private static class MyDataProvider extends AsyncDataProvider<String> {
		    @Override
		    protected void onRangeChanged(HasData<String> display) {
		    	updateRowData(0, myData);
		    }		
		  
	 }
	 
	 private void refreshItemsList() {
		    if (crudSvc == null) {
		    	crudSvc = GWT.create(CrudService.class);
		    }
		    
		    AsyncCallback<List<String>> callback = new AsyncCallback<List<String>>() {
		      public void onFailure(Throwable caught) {
		        // TODO: Do something with errors.
		      }

		      public void onSuccess(List<String> result) {
		    	  myData = result;
		    	  dataProvider.updateRowData(0, result);
		    	  dataProvider.updateRowCount(myData.size(), true);
		      }
		    };

		     // Make the call to the stock price service.
		    crudSvc.getItems(callback);
		}
	 
	 private void addItem(String item) {
		    if (crudSvc == null) {
		    	crudSvc = GWT.create(CrudService.class);
		    }
		    
		    AsyncCallback<List<String>> callback = new AsyncCallback<List<String>>() {
		      public void onFailure(Throwable caught) {
		        // TODO: Do something with errors.
		      }

		      public void onSuccess(List<String> result) {
		    	  myData = result;
		    	  dataProvider.updateRowData(0, result);
		    	  dataProvider.updateRowCount(myData.size(), true);
		      }
		    };

		     // Make the call to the stock price service.
		    crudSvc.addItem(item,callback);
		}
	 
	 private void removeItem(int index) {
		    if (crudSvc == null) {
		    	crudSvc = GWT.create(CrudService.class);
		    }
		    
		    AsyncCallback<List<String>> callback = new AsyncCallback<List<String>>() {
		      public void onFailure(Throwable caught) {
		        // TODO: Do something with errors.
		      }

		      public void onSuccess(List<String> result) {
		    	  myData = result;
		    	  dataProvider.updateRowData(0, result);
		    	  dataProvider.updateRowCount(myData.size(), true);
		      }
		    };

		     // Make the call to the stock price service.
		    crudSvc.removeItem(index,callback);
		}
	 
	 private void updateItem(int index, String item) {
		    if (crudSvc == null) {
		    	crudSvc = GWT.create(CrudService.class);
		    }
		    
		    AsyncCallback<List<String>> callback = new AsyncCallback<List<String>>() {
		      public void onFailure(Throwable caught) {
		        // TODO: Do something with errors.
		      }

		      public void onSuccess(List<String> result) {
		    	  myData = result;
		    	  dataProvider.updateRowData(0, result);
		    	  dataProvider.updateRowCount(myData.size(), true);
		      }
		    };

		     // Make the call to the stock price service.
		    crudSvc.updateItem(index, item,callback);
		}
	 
	public void onModuleLoad() {
		
	    dataProvider.addDataDisplay(cellList);
	    SimplePager pager = new SimplePager();
	    pager.setDisplay(cellList);	 
	    
	    final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
	    cellList.setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	      public void onSelectionChange(SelectionChangeEvent event) {
	        String selected = selectionModel.getSelectedObject();
	        if (selected != null) {
	        	editField.setText(selected);
	        }else{
	        	editField.setText("");
	        }
	      }
	    });
	    
	    VerticalPanel vPanel = new VerticalPanel();
	    vPanel.add(pager);
	    vPanel.add(cellList);
	    RootPanel.get().add(vPanel);
	    
		RootPanel.get("textFieldContainer").add(txtField);
		RootPanel.get("sendButtonContainer").add(myBtn);
	
		RootPanel.get("editFieldContainer").add(editField);
		RootPanel.get("removeBtnContainer").add(removeBtn);
		RootPanel.get("editBtnContainer").add(editBtn);
		
		refreshItemsList();
		
		myBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addItem(txtField.getText());
				txtField.setText("");
			}
		});
		
		removeBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int index = cellList.getKeyboardSelectedRow();
				removeItem(index);
				selectionModel.clear();
			}
		});
		
		editBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int index = cellList.getKeyboardSelectedRow();
				updateItem(index, editField.getText());
				selectionModel.clear();
			}
		});
		
	}
}
