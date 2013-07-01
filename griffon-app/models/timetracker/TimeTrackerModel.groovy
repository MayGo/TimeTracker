package timetracker

import java.util.List;

import groovyx.javafx.beans.FXBindable
import griffon.util.GriffonNameUtils
import javafx.collections.FXCollections

class TimeTrackerModel {
	
	List<ActiveWindowInfo> activeWindowInfoList = FXCollections.observableList([])
	List<String> chartCategoriesList = FXCollections.observableList([])
	
	
	//List<String> chartDataList = FXCollections.observableList([])

    void mvcGroupInit(Map args) {
    }
}
