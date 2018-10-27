/**
 * 
 */
package es.uam.eps.tweetextractorfx.dao.service;

import java.util.List;

import es.uam.eps.tweetextractorfx.dao.UserDAO;
import es.uam.eps.tweetextractorfx.model.User;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class UserService {

	private static UserDAO userDAO;

	public UserService() {
		userDAO = new UserDAO();
	}

	public void persist(User entity) {
		userDAO.openCurrentSessionwithTransaction();
		userDAO.persist(entity);
		userDAO.closeCurrentSessionwithTransaction();
	}

	public void update(User entity) {
		userDAO.openCurrentSessionwithTransaction();
		userDAO.update(entity);
		userDAO.closeCurrentSessionwithTransaction();
	}

	public User findById(String id) {
		userDAO.openCurrentSession();
		User user = userDAO.findById(id);
		userDAO.closeCurrentSession();
		return user;
	}

	public void delete(String id) {
		userDAO.openCurrentSessionwithTransaction();
		User user = userDAO.findById(id);
		userDAO.delete(user);
		userDAO.closeCurrentSessionwithTransaction();
	}
	public boolean existsUser(String nickname) {
		if(findByNickname(nickname)==null)return false;
		return true;	}
	public User findByNickname(String nickname) {
		userDAO.openCurrentSessionwithTransaction();
		User ret=userDAO.findByNickname(nickname);
		userDAO.closeCurrentSessionwithTransaction();
		return ret;
	}

	public List<User> findAll() {
		userDAO.openCurrentSession();
		List<User> users = userDAO.findAll();
		userDAO.closeCurrentSession();
		return users;
	}

	public void deleteAll() {
		userDAO.openCurrentSessionwithTransaction();
		userDAO.deleteAll();
		userDAO.closeCurrentSessionwithTransaction();
	}

	public UserDAO userDAO() {
		return userDAO;
	}

}
