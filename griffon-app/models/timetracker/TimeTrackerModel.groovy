package timetracker

import java.util.List;

import groovyx.javafx.beans.FXBindable
import griffon.util.GriffonNameUtils
import javafx.collections.FXCollections

class TimeTrackerModel {
	
	List<FxActiveWindowInfo> activeWindowInfoList = FXCollections.observableList([])

    void mvcGroupInit(Map args) {
    }
}
