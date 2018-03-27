package ru.cb.dictionary;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import ru.cb.dictionary.ui.ViewHolder;

//@Lazy
@SpringBootApplication
@EnableCaching
public class DictionaryApplication extends AbstractJavaFxApplicationSupport {

	@Qualifier("mainView")
	@Autowired
	private ViewHolder mainView;

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("dictionary");
	}

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
