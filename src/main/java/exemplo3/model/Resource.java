package exemplo3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * 
 * 	Entidade do banco que possui os dados dos Recursos
 * 
 * 	dev-status - OK
 * 
 **/

@Entity
@Table(name = "Recurso")
public class Resource  implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3416814943980308062L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRecurso")
	private Long id;
	
	@Column(name="Quantidade")
	private Long quantity;
	
	/* -- Relacionamentos -- */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Missao_idMissao")	
	private Mission mission;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoDeRecurso_idTipoDeRecurso")
	private ResourceType resourceType;

	/* -- Relacionamentos -- */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public Mission getMisson() {
		return mission;
	}

	public void setMisson(Mission misson) {
		this.mission = misson;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	
}
