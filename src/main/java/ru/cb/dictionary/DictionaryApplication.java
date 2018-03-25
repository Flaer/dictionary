package ru.cb.dictionary;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import ru.cb.dictionary.ui.MainController;
import ru.cb.dictionary.ui.ViewHolder;

@Lazy
@SpringBootApplication
public class DictionaryApplication extends AbstractJavaFxApplicationSupport {

	@Qualifier("mainView")
	@Autowired
	private ViewHolder mainView;

	@Autowired
	private MainController mainController;

	@Override
	public void start(Stage stage) throws Exception {

		notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));

		stage.setTitle("Справочник БИК");
		stage.setScene(new Scene(mainView.getView()));
		stage.setResizable(true);
		stage.centerOnScreen();
		stage.show();
	}

	public static void main(String[] args) {
		launchApp(DictionaryApplication.class, args);
	}
}
