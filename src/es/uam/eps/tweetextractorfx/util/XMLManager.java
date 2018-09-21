package es.uam.eps.tweetextractorfx.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import es.uam.eps.tweetextractorfx.model.Constants;
import es.uam.eps.tweetextractorfx.model.User;
import es.uam.eps.tweetextractorfx.model.wrapper.UserListWrapper;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public  class XMLManager {
	

	public XMLManager() {
		// TODO Auto-generated constructor stub
	}

	public static List<User> loadUserList() {
		try {
			List<User> ret = new ArrayList<User>();
			File file = new File(Constants.persistencePath + ".auth/users.xml");
			if(!file.exists())return null;
			/* Cargamos el contexto XML */
			JAXBContext context = JAXBContext.newInstance(UserListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			// Reading XML from the file and unmarshalling.
			UserListWrapper wrapper = (UserListWrapper) um.unmarshal(file);
			ret.clear();
			ret.addAll(wrapper.getUsers());
			return ret;

		} catch (Exception e) { // catches ANY exception
			e.printStackTrace();
			showErrorLoadUsers(e.getMessage());
			return null;
		}
	}
	public static void saveUserList(List<User> userList) throws Exception {
	    try {
	    	/* Si el fichero de usuarios no existe, lo creamos */
	    	File authDir = new File (Constants.authPath);
	    	if(!authDir.exists()) {
	    		authDir.mkdirs();
				/*Ocultamos el directorio auth en entornos DOS*/
	    		String OS = System.getProperty("os.name").toLowerCase();
	    		if(OS.indexOf("win") >= 0) {
	            Path path = Paths.get(Constants.authPath);
	            Files.setAttribute(path, "dos:hidden", true);
	    		}
	    	}
			File file = new File(Constants.usersFile);
			if(!file.exists())
			file.createNewFile();
	        JAXBContext context = JAXBContext
	                .newInstance(UserListWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        // Wrapping our person data.
	        UserListWrapper wrapper = new UserListWrapper();
	        wrapper.setUsers(userList);
	        
	        // Marshalling and saving XML to the file.
	        m.marshal(wrapper, file);

	    } catch (Exception e) { // catches ANY exception
	    	e.printStackTrace();
	       throw(e);
	    }
	}
	private static void showErrorLoadUsers(String message) {
		Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText("Error de lectura de usuarios");
    	alert.setContentText("Se ha producido un error leyendo los usuarios:\n"+message);
    	alert.showAndWait();
        return;
	}

}
