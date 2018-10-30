/**
 * 
 */
package es.uam.eps.tweetextractorfx.error;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * @author plysk
 *
 */
public class ErrorDialog {
	public static void showErrorLoadUsers(String message) {
		Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText("Error reading users");
    	alert.setContentText("An error ocurred while reading the users:"+message);
    	alert.showAndWait();
        return;
	}
	public static void showErrorLoginFailed() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("Incorrect password");
		alert.setContentText("The password you entered is not correct. Please try again.");
		alert.showAndWait();
		return;
	}

	public static void showErrorExistsUSer() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("Unexisting user");
		alert.setContentText("There's no account registered with that nickname. Please, try again.");
		alert.showAndWait();
		return;
	}

	public static void showErrorPassEmpty() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("Empty password");
		alert.setContentText("Plese, enter the password for this account.");
		alert.showAndWait();
		return;
	}

	public static void showErrorUserEmpty() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("Empty username");
		alert.setContentText("Please, enter the username of the account to log in.");
		alert.showAndWait();
		return;
	}
	public static void showErrorSaveUser(String message) {
		Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText("Error writing users");
    	alert.setContentText("An error has ocurred while saving the new user:\n"+message);
    	alert.showAndWait();
        return;
	}
	public static void showSuccessCreateUser() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("User created");
		alert.setContentText("The new user account has been succesfully created.");
		alert.showAndWait();
		return;	
	}
	public static void showErrorPasswordCheck() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("Unsafe password");
		alert.setContentText("Passwords must have between 6 and 16 characters.\n They also must contain a lower case, an upper case and a number.");
		alert.showAndWait();
		return;		
	}
	public static void showErrorExistingUser() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("User already exists");
		alert.setContentText("This user account already exists. Please, introduce another name for the account or go to the log in screen.");
		alert.showAndWait();
		return;
	}
	public static void showErrorEmptyUser() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("Invalid account name");
		alert.setContentText("The user name for the account must have at least 3 characters.");
		alert.showAndWait();
		return;
	}
	public static void showErrorPasswordMismatch() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Informatin");
		alert.setHeaderText("PAssword mismatch");
		alert.setContentText("Las contraseñas introducidas no coinciden. Por favor, inténtalo de nuevo.");
		alert.showAndWait();
		return;
	}
	public static void showErrorSaveCredentials(String message) {
		Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("Error");
    	alert.setHeaderText("Error al guardar los credenciales");
    	alert.setContentText("Se ha producido un error desconocido al guardar los credenciales para la cuenta. ERROR:\n"+message);
    	alert.showAndWait();
        return;	
	}
	public static void showErrorExistingCredentials() {
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Credenciales ya existentes");
    	alert.setContentText("Esta cuenta ya es propietaria de esos credenciales, introduce unos nuevos para añadirlos a la cuenta.");
    	alert.showAndWait();
        return;	
	}
	public static void showErrorEmptyCredentials() {
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Campos vacíos");
    	alert.setContentText("Por favor, introduzca los tokens de la nueva cuenta de Twitter");
    	alert.showAndWait();
        return;		
	}
	public static void showErrorEmptyNickname() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Ningún nombre de usuario seleccionado");
    	alert.setContentText("Por favor, seleccione un nombre de usuario como origen de los tweets.");
    	alert.showAndWait();
        return;
    }
	public static void showErrorEmptyUrl() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Ninguna url seleccionada");
    	alert.setContentText("Por favor, seleccione la URL que quiere que contengan los tweets.");
    	alert.showAndWait();
        return;
    }
	public static void showErrorWrongValues() {
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Alguno de los campos es incoherente");
    	alert.setContentText("Recuerde que las cuentas de Twitter y los nombres de listas se componen de una sola expresión sin espacios.");
    	alert.showAndWait();
        return;
	}
	public static void showErrorEmptyFields() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Alguno de los campos está vacío");
    	alert.setContentText("Por favor, seleccione una cuenta de Twitter y un nombre de lista existente en esa cuenta.");
    	alert.showAndWait();
        return;
    }
	public static void showErrorSelectDateSince() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Ninguna fecha seleccionada");
    	alert.setContentText("Por favor, seleccione la fecha desde la que realizar la extracción.");
    	alert.showAndWait();
        return;
    }
	public static void showErrorEmptyNicknameTo() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Ningún nombre de usuario seleccionado");
    	alert.setContentText("Por favor, seleccione un nombre de usuario como destino de los tweets.");
    	alert.showAndWait();
        return;
    }
	public static void showErrorSelectDateTo() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Ninguna fecha seleccionada");
    	alert.setContentText("Por favor, seleccione la fecha hasta la que realizar la extracción.");
    	alert.showAndWait();
        return;
    }
	public static void showErrorEmptyNicknameFrom() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Ningún nombre de usuario seleccionado");
    	alert.setContentText("Por favor, seleccione un nombre de usuario como origen de los tweets.");
    	alert.showAndWait();
        return;
    }
	public static void showErrorSelectFilterRemove() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Ningún filtro seleccionado");
		alert.setContentText("Por favor, seleccione un filtro para eliminar de la lista de la derecha");
		alert.showAndWait();
		return;
	}

	public static void showErrorNotEnoughFilters() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Operaciones Lógicas");
		alert.setContentText(
				"Por favor, seleccione al menos dos filtros aplicados para poder aplicar la operación OR lógica.");
		alert.showAndWait();
		return;
	}

	public static void showErrorNotEnoughFiltersNot() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Operaciones Lógicas");
		alert.setContentText(
				"Por favor, seleccione al menos un filtro aplicado para poder aplicar la operación NOT lógica.");
		alert.showAndWait();
		return;
	}

	public static void showErrorUndoLogic() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Operaciones Lógicas");
		alert.setContentText("Por favor, seleccione filtros de tipo lógico para deshacerlos.");
		alert.showAndWait();
		return;
	}

	public static void showErrorSelectFilterAdd() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Ningún filtro seleccionado");
		alert.setContentText("Por favor, seleccione un filtro para añadir de la lista de la izquierda");
		alert.showAndWait();
		return;
	}
	public static void showSuccessUpdatePassword() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Contraseña actualizada");
		alert.setContentText("La contraseña se ha cambiado satisfactoriamente.");
		alert.showAndWait();
		return;
	}
	public static void showErrorNoCredentials() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("No hay credenciales");
		alert.setContentText("Este usuario no tiene credenciales para la API de Twitter.\nAñada unos credenciales desde el menú principal.");
		alert.showAndWait();
		return;		
	}
	public static void showErrorNoSelectedCredentials() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("No hay credenciales seleccionados");
		alert.setContentText("Selecciona unos credenciales para llevar a cabo esta acción");
		alert.showAndWait();
		return;	
	}
	public static void showErrorLoadExtraction(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Atención");
		alert.setHeaderText("Error de lectura");
		alert.setContentText("Se ha producido un error desconocido cargando una exxtracción:\n"+message);
		alert.showAndWait();
		return;
	}
	public static void showErrorNoSelectedExtraction() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Ninguna extracción seleccionada");
		alert.setContentText("Selecciona una extracción para llevar a cabo esta acción");
		alert.showAndWait();
		return;	
	}
	public static void showErrorTwitterExecution(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Error de búsqueda");
		alert.setHeaderText("Error de API de Twitter");
		alert.setContentText(message);
		alert.showAndWait();
		return;	
	}
	public static void showErrorDB(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error connecting to database");
		alert.setHeaderText("Error connecting to database");
		alert.setContentText("An error has ocurred contacting the database:\n\n"+message+"\n\nPlease try again.");
		alert.showAndWait();
		return;	
	}
	public static Alert showUpdateQueryResults(int added) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Actualización de extracción terminada");
		alert.setContentText("Se han encontrado "+added+" tweet(s) nuevos para la extracción");
		return alert;	
	}
	public static void showErrorEmptyExtraction() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("Empty extraction");
		alert.setContentText("Add at least one filter to the extraction");
		alert.showAndWait();
		return ;
	}
	
}
