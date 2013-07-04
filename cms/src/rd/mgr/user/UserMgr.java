package rd.mgr.user;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class UserMgr implements IUserMgr {

	@Override
	public Group[] getAllGroups(EntityManager eMgr) {
		TypedQuery<Group> qry = eMgr.createNamedQuery("Group.findAll", Group.class);
		List<Group> grps = qry.getResultList();
		Group[] result = new Group[grps.size()];
		Iterator<Group> it = grps.iterator();
		for(int i = 0; it.hasNext(); i++){
			result[i] = it.next();
		}
		return result;
	}

	@Override
	public Group[] saveGroups(EntityManager eMgr, Group[] groups) {
		for (int i = 0; i < groups.length; i++) {
			Group grp = groups[i];
			if(grp.getId() <= 0){
				eMgr.persist(grp);
			}else{
				eMgr.merge(grp);
			}
		}
		return groups;
	}

	@Override
	public User getUserByID(EntityManager eMgr, long id) {
		return eMgr.find(User.class, id);
	}

	@Override
	public void deleteUser(EntityManager eMgr, long id) {
		User u = getUserByID(eMgr, id);
		eMgr.remove(u);
	}

	@Override
	public User[] getAllUsers(EntityManager eMgr) {
		
		TypedQuery<User> qry = eMgr.createNamedQuery("User.findAll", User.class);
		List<User> usrs = qry.getResultList();
		User[] result = new User[usrs.size()];
		Iterator<User> it = usrs.iterator();
		for(int i = 0; it.hasNext(); i++){
			result[i] = it.next();
		}
		return result;
	}

	@Override
	public User saveUser(EntityManager eMgr, User usr) {
		return saveUsers(eMgr, new User[]{usr})[0];
	}

	@Override
	public User[] saveUsers(EntityManager eMgr, User[] usrs) {
		for (int i = 0; i < usrs.length; i++) {
			User usr = usrs[i];
			if(usr.getId() <= 0){
				eMgr.persist(usr);
			}else{
				eMgr.merge(usr);
			}
		}
		
		return usrs;
	}
}
