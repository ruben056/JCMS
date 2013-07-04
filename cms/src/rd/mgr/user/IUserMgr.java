package rd.mgr.user;

import javax.persistence.EntityManager;


public interface IUserMgr {

	public Group[] getAllGroups(EntityManager eMgr);
	public Group[] saveGroups(EntityManager eMgr, Group[] groups);
	
	public User getUserByID(EntityManager eMgr, long id);	
	public void deleteUser(EntityManager eMgr, long id);
	
	public User[] getAllUsers(EntityManager eMgr);
	public User saveUser(EntityManager eMgr, User usr);
	public User[] saveUsers(EntityManager eMgr, User[] usrs);
}
