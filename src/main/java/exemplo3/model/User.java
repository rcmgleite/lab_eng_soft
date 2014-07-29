package exemplo3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import utils.ProjectEnums;

/**
 * 
 * 	Entidade do banco que possui os dados dos usuários
 * 
 * 	dev-status - TODO
 * 
 **/

@Entity
@Table(name = "Usuario")
public class User implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4739247824555444574L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idUsuario")
	private Long id;

	@Column(name="Username")
	private String username;

	@Column(name="Password")
	private String password;
	
	@Column(name="Role")
	private Long role;

	/* -- Relacionamentos -- */
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="chefeMissao")
	private List<Mission> missions = new ArrayList<Mission>();
	/* -- Relacionamentos -- */
	
	/*Transients*/
	@Transient
	private String roleAlias;
	/*Transients*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getRole() {
		return role;
	}

	public List<Mission> getMissions() {
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public String getRoleAlias() {
		return roleAlias;
	}

	public void setRoleAlias() {
		ProjectEnums.UserRoles _role = ProjectEnums.getUserRoleByInt(Integer.parseInt(this.role.toString()));
		switch(_role){
			case ADMIN:
				this.roleAlias = "Administrador";
				break;
			case COORD:
				this.roleAlias  = "Coordenador";
				break;
			case ESPEC:
				this.roleAlias  = "Especialista";
				break;
			case CHEFE_MISSAO:
				this.roleAlias = "Chefe de missão";	
		}
	}

}
