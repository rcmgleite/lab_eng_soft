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

import org.hibernate.annotations.ForeignKey;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idAcidente")
	@ForeignKey(name="Acidente_idAcidente")
//	@Column(name="Acidente_idAcidente")
	private Accident accident;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="mission")
	private List<Resource> resources = new ArrayList<Resource>();
	
	/* -- Relacionamentos -- */

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
	
}
