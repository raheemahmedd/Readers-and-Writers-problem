package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class MainSceneController implements Initializable {
	Path path = new Path();
	@FXML
	private Text parisTickets;

	@FXML
	private TextField ticketNo;

	// Event Listener on Button.onAction
	@FXML
	public void submitBtn(ActionEvent event) {

		Platform.runLater(() -> {

			try {
				Reader read = new Reader();
				Thread thread2 = new Thread(read);

				thread2.start();
				thread2.join();

				if (read.getParis() == 0 | (read.getParis() - Integer.parseInt(ticketNo.getText())) < 0) {
					throw new IllegalArgumentException("Not enough tickets");
				}

				int ticketsNo = Integer.parseInt(ticketNo.getText());

				Book book = new Book();
				book.setTicketsNo(ticketsNo);
				Thread thread = new Thread(book);

				thread.start();
				thread.join();

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("SUCCESS");
				alert.setContentText("Ticket booked successfully");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image(path.images() + "\\check.png"));
				alert.showAndWait();

			} catch (InterruptedException e) {
				e.printStackTrace();

			} catch (NumberFormatException e) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("WARNING");
				alert.setContentText("Please enter a valid number");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image(path.images() + "\\warning.png"));
				alert.showAndWait();

			} catch (IllegalArgumentException e) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("WARNING");
				alert.setContentText(String.valueOf(e).split(":")[1]);
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image(path.images() + "\\warning.png"));
				alert.showAndWait();

			}
		});
	}

	// Event Listener on Button.onAction
	@FXML
	public void removeBtn(ActionEvent event) {
		try {
			Reader read = new Reader();
			Thread thread = new Thread(read);
			
			thread.start();
			thread.join();

			if (ticketNo.getText() == "" | read.getParis() + Integer.parseInt(ticketNo.getText()) > 200) {
				throw new IllegalArgumentException("Please enter a valid number of ticket(s) to be removed");
			}

			if (read.getParis() == 200) {
				throw new IllegalArgumentException("There are no tickets booked for this flight");
			}
			
			UnBook unbook = new UnBook();
			unbook.setTicketsNo(Integer.parseInt(ticketNo.getText()));
			unbook.run();

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("SUCCESS");
			alert.setContentText("Ticket(s) removed successfully");

			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(path.images() + "\\check.png"));
			alert.showAndWait();

		} catch (IllegalArgumentException e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setContentText(String.valueOf(e).split(":")[1]);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(path.images() + "\\warning.png"));
			alert.showAndWait();

		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("FAILED");
			alert.setContentText("Ticket was not removed successfully due to an Error occured");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(path.images() + "\\cancel.png"));
			alert.showAndWait();

		}

	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		new Thread() {
			public void run() {
				try {
					while (true) {
						Reader read = new Reader();
						Thread tickets = new Thread(read);

						tickets.start();
						tickets.join();

						parisTickets.setText(Integer.toString(read.getParis()));

						Thread.sleep(100);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
