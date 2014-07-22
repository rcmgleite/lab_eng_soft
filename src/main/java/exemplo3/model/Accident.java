package exemplo3.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * 	Entidade do banco que possui os dados do acidente
 * 
 * 	dev-status - OK
 * 
 **/

@Entity
@Table(name = "Acidente")
public class Accident implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4076769060202798299L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idAcidente")
	private Long id;
	
	@Column(name="data")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="localizacao")
	private String location;
	
	@Column(name="nVitimas")
	private Long numVictims;
	
	@Column(name="descricao")
	private String description;
	
	@Column(name="tipo")
	private Long type;
	
	@Column(name="status")
	private Long status;
	
	/* -- Relacionamentos -- */

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accident")
	private List<Mission> missions = new ArrayList<Mission>();
	
	/* -- Relacionamentos -- */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getNumVictims() {
		return numVictims;
	}

	public void setNumVictims(Long numVictims) {
		this.numVictims = numVictims;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public List<Mission> getMissions() {
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}
}
