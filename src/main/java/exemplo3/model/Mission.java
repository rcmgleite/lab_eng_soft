package exemplo3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import utils.ProjectEnums;

/**
 * 
 * 	Entidade do banco que possui os dados das Missões
 * 
 * 	dev-status - OK
 * 
 **/

@Entity
@Table(name = "Missao")
public class Mission implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9041687330141206715L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idMissao")
	private Long id;
	
	@Column(name="Descricao")
	private String description;
	
	@Column(name="Status")
	private Long status;
	
	@Column(name="Prioridade")
	private Long priority;
	
	/* -- Relacionamentos -- */

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Acidente_idAcidente")
	private Accident accident;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="mission")
	private List<Resource> resources = new ArrayList<Resource>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Usuario_idUsuario")
	private User chefeMissao;
	/* -- Relacionamentos -- */

	/* -- Transient -- */
	@Transient
	private String statusAlias;
	
	@Transient
	private String priorityAlias;
	/* -- Transient -- */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public Accident getAccident() {
		return accident;
	}

	public void setAccident(Accident accident) {
		this.accident = accident;
	}
	
	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public String getStatusAlias() {
		return statusAlias;
	}
	
	public User getChefeMissao() {
		return chefeMissao;
	}

	public void setChefeMissao(User chefeMissao) {
		this.chefeMissao = chefeMissao;
	}

	public void setStatusAlias() {
		ProjectEnums.MissionStatus _status = ProjectEnums.getMissionStatusByInt(Integer.parseInt(this.status.toString()));
		switch(_status){
			case REQUESTING:
				this.statusAlias = "Solicitado";
				break;
			
			case IN_EXECUTION:
				this.statusAlias = "Em execução";
				break;
			
			case CONCLUDED:
				this.statusAlias = "Concluído";
				break;
		}
	}

	public String getPriorityAlias() {
		return priorityAlias;
	}

	public void setPriorityAlias() {
		ProjectEnums.MissionPriority _priority = ProjectEnums.getMissionPriorityByInt(Integer.parseInt(this.priority.toString()));
		switch(_priority){
			case LOW:
				this.priorityAlias = "baixa";
				break;
			case MEDIUM:
				this.priorityAlias = "média";
				break;
			case HIGH:
				this.priorityAlias = "alta";
				break;
		}
	}
}
