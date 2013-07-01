package timetracker


import java.text.SimpleDateFormat
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
import javafx.collections.FXCollections

def fxmlLoader = new FXMLLoader(app.getResourceAsURL('TimeTracker.fxml'))
def fxmlPane = fxmlLoader.load()


application(title: 'TimeTracker', sizeToScene: true, centerOnScreen: true) {
	scene=scene(fill: lightgray, width: 900, height: 700) { node(fxmlPane) }
	noparent {
		[
			"loadDataButton",
			"infoTable",
			"nameCol",
			"titleCol",
			"dateCreatedCol",
			"dateUpdatedCol",
			"appChartLocation"
			
		].each{name->
			this."$name"=fxmlLoader.getNamespace().get(name);
		}
		loadDataButton.onAction(controller.loadData)

		nameCol.setCellValueFactory(new PropertyValueFactory<ActiveWindowInfo, String>("name"));
		titleCol.setCellValueFactory(new PropertyValueFactory<ActiveWindowInfo, String>("title"));
		dateCreatedCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ActiveWindowInfo, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<ActiveWindowInfo, String> param) {
						// The creation date will never change...
						ActiveWindowInfo awi = param.getValue();
						String formatted=""
						if(awi.dateCreated){
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
							formatted=dateFormat.format( awi.dateCreated)
						}
						return new SimpleStringProperty(formatted);
					}
				});
		dateUpdatedCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ActiveWindowInfo, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<ActiveWindowInfo, String> param) {
						// The creation date will never change...
						ActiveWindowInfo awi = param.getValue();
						String formatted=""
						if(awi.dateUpdated){
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
							formatted=dateFormat.format( awi.dateUpdated)
						}
						return new SimpleStringProperty(formatted);
					}
				});
		model.activeWindowInfoList.addListener({
			infoTable.items.clear()
			infoTable.items.addAll(model.activeWindowInfoList)
		} as InvalidationListener)
		appChart=stackedBarChart(height:100, xAxis: numberAxis(label: ""), yAxis: categoryAxis(label: "",tickMarkVisible:false,tickLabelsVisible:false, categories:["Applications"]),
		data:[
			series(name: 'Firefox', data: [
				[100, "Applications"]
			]),
			series(name: 'Firefox222', data: [
				[110, "Applications"]
			]),                             
			series(name: 'Firefox3', data: [
				[50, "Applications"]
			])
		]) {

		}
		appChartLocation.getChildren().addAll(appChart);

	}
}

