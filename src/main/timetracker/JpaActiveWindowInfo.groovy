package timetracker

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column
import javax.persistence.Table;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import java.sql.Timestamp
import java.util.jar.Attributes.Name;


@Entity
@Table(name="ActiveWindowInfo")
class JpaActiveWindowInfo implements ActiveWindowInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id
	String name
	String title
	int updatedCount = 0


	@Column(insertable=true, updatable=false)
	Timestamp dateCreated;
	@Column(insertable=false, updatable=true)
	Timestamp dateUpdated;

	@Override
	public String toString() {
		return "ActiveWindowInfo [name=" + name + ", title=" + title + "]";
	}
	@PrePersist
	void onCreate() {
		this.setDateCreated(new Timestamp((new Date()).getTime()));
	}
	@PreUpdate
	void onPersist() {
		this.setDateUpdated(new Timestamp((new Date()).getTime()));
	}
}
