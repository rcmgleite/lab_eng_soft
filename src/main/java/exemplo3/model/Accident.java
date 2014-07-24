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
import javax.persistence.Transient;

import utils.ProjectEnums;

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

	
	/*Transients(significa que não são colunas do banco)*/
	@Transient
	private String typeAlias;
	
	@Transient
	private String statusAlias;
	/*Transients(significa que não são colunas do banco)*/

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

	public String getTypeAlias() {
		return typeAlias;
	}

	public void setTypeAlias() {
		ProjectEnums.AccidentStatus _status = ProjectEnums.getStatusByInt(Integer.parseInt(this.status.toString()));
		switch(_status){
			case OPEN:
				this.statusAlias = "em aberto";
				break;
			case IN_PROGRESS:
				this.statusAlias = "em progesso";
				break;
			
			case CONCLUDED:
				this.statusAlias = "concluído";
				break;
		}
	}

	public String getStatusAlias() {
		return statusAlias;
	}

	public void setStatusAlias() {
		ProjectEnums.AccidentType _type = ProjectEnums.getTypeByInt(Integer.parseInt(this.type.toString()));
		switch(_type){
			case VHEICLE_VHEICLE_COLLISION:
				this.typeAlias = "Colisão entre veículos";
				break;
			case VHEICLE_OBSTACLE_COLLISION:
				this.typeAlias = "Colisão entre veículo e obstáculo";
				break;
			case RUN_OVER:
				this.typeAlias = "Atropelamento";
				break;
			case BROKEN_VHEICLE:
				this.typeAlias = "Veículo quebrado";
				break;
			case ROLLOVER:
				this.typeAlias = "Capotamento";
				break;
		}
	}
}
