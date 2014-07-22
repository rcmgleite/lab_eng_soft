package exemplo3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public void setRole(Long role) {
		this.role = role;
	}


}
