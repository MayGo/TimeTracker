package timetracker

import java.sql.Timestamp

interface ActiveWindowInfo {
	Long id
	String name
	String title
	Timestamp dateCreated;
}
