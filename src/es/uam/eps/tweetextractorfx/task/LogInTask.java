/**
 * 
 */
package es.uam.eps.tweetextractorfx.task;

import java.util.Date;

import org.hibernate.HibernateException;
import org.mindrot.jbcrypt.BCrypt;

import es.uam.eps.tweetextractorfx.dao.service.UserService;
import es.uam.eps.tweetextractorfx.error.ErrorDialog;
import es.uam.eps.tweetextractorfx.model.Constants;
import es.uam.eps.tweetextractorfx.model.LoginState;
import es.uam.eps.tweetextractorfx.model.User;
import javafx.concurrent.Task;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class LogInTask extends Task<LoginState>{
	String username;
	String password;
	/**
	 * 
	 */
	public LogInTask(String username,String password) {
		this.username=username;
		this.password=password;
	}

	@Override
	protected LoginState call() throws Exception {
		if(username==null||password==null)return null;
		UserService userService=new UserService();
		LoginState ret= new LoginState();
		User userLogged = null;
		if(username.isEmpty()) {
			ret.setState(Constants.EMPTY_USER_LOGIN_ERROR);
			ret.setUser(null);
			return ret;
		}
		String pass = password.trim();
		if(pass.isEmpty()) {
			ret.setState(Constants.EMPTY_PASSWORD_LOGIN_ERROR);
			ret.setUser(null);
			return ret;
		}
		try {
			userLogged =userService.findByNickname(username);
			if(userLogged==null) {
				ret.setState(Constants.EXIST_USER_LOGIN_ERROR);
				ret.setUser(null);
				return ret;
			}
		}catch(HibernateException e) {
			e.printStackTrace();
			return null;
		}
		boolean passOK = BCrypt.checkpw(pass, userLogged.getPassword());
		if(passOK) {
			ret.setState(Constants.SUCCESS_LOGIN);
			userLogged.setLastConnectionDate(new Date());
			userService.update(userLogged);
			ret.setUser(userLogged);
			return ret;
		}else {
			ret.setState(Constants.INCORRECT_PASSWORD_LOGIN_ERROR);
			ret.setUser(null);
			return ret;
		}
	}

}
