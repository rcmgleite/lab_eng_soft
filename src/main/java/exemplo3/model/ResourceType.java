package exemplo3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * 	Entidade do banco que possui os dados dos tipos de recurso
 * 
 * 	dev-status - OK
 * 
 **/

@Entity
@Table(name = "TipoDeRecurso")
public class ResourceType implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3276198400985784900L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idTipoDeRecurso")
	private Long id;
	
	@Column(name="Nome")
	private String name;
	
	@Column(name="Descricao")
	private String description;
	
	@Column(name="recursoExterno")
	private Boolean external;

	/* -- Relacionamentos -- */
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="resourceType")
	List<Resource> resources = new ArrayList<Resource>();
	
	/* -- Relacionamentos -- */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getExternal() {
		return external;
	}

	public void setExternal(Boolean external) {
		this.external = external;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
}
