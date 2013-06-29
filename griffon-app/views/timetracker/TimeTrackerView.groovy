package timetracker


import javafx.beans.*
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue

import javafx.fxml.FXMLLoader

import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.table.TableColumn
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Callback;

def fxmlLoader = new FXMLLoader(app.getResourceAsURL('TimeTracker2.fxml'))
def fxmlPane = fxmlLoader.load()

application(title: 'TimeTracker', sizeToScene: true, centerOnScreen: true) {
	scene=scene(fill: lightgray, width: 900, height: 700) {
		node(fxmlPane)

		
	}
	noparent {
		loadDataButton=fxmlLoader.getNamespace().get("loadDataButton")
		loadDataButton.onAction(controller.loadData)
		infoTable=fxmlLoader.getNamespace().get("infoTable")
		nameColumn=fxmlLoader.getNamespace().get("nameCol")
		titleColumn=fxmlLoader.getNamespace().get("titleCol")
		dateCreatedColumn=fxmlLoader.getNamespace().get("dateCreatedCol")
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<ActiveWindowInfo, String>("name"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<ActiveWindowInfo, String>("title"));
		dateCreatedColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ActiveWindowInfo, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<ActiveWindowInfo, String> param) {
				// The creation date will never change...
				ActiveWindowInfo awi = param.getValue();
				
				return new SimpleStringProperty(awi.dateCreated);
			}
		});
		model.activeWindowInfoList.addListener({
			infoTable.items.clear()
			infoTable.items.addAll(model.activeWindowInfoList)
		} as InvalidationListener)
	}
}

