package rd.mgr.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="user_accounts")
@NamedQuery(name="User.findAll",query="SELECT u FROM User u")
public class User {

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private int id;
	private String email;
	private String password;
	private boolean active = false;
//	private String groups = ""; // json encoded string
	private String activationKey;
	private String extras;
	
	@ManyToMany
    @JoinTable(name="USER2GROUP",
               joinColumns={@JoinColumn(name="USER_ID")},
               inverseJoinColumns={@JoinColumn(name="GROUP_ID")})
	private Set<Group> groups;
	
	public User(){}
	
	public User(String email){
		this.email = email;
	}
	
	public User(String email, String pass){
		this(email);
		this.password = pass;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return password;
	}
	public void setPass(String pass) {
		this.password = pass;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getActivationKey() {
		return activationKey;
	}
	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}
	public String getExtras() {
		return extras;
	}
	public void setExtras(String extras) {
		this.extras = extras;
	}
	public void addGroup(Group g){
		if(g == null)
			return;
		if(groups == null){
			groups = new HashSet<Group>();
		}else{
			groups.contains(g);
		}
		groups.add(g);
	}
    public Set<Group> getGroups( ) { 
		return groups; 
	}
    public void setGroups(Set<Group> groups) { 
    	this.groups = groups; 
    	}
}
