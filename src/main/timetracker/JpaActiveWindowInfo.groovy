package timetracker

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column
import javax.persistence.Table;

import java.sql.Timestamp
import java.util.jar.Attributes.Name;


@Entity
@Table(name="ActiveWindowInfo")
class JpaActiveWindowInfo implements ActiveWindowInfo{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id
	String name
	String title

	
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable=false)
	Timestamp dateCreated;
	
	@Override
	public String toString() {
		return "ActiveWindowInfo [name=" + name + ", title=" + title + "]";
	}

}
