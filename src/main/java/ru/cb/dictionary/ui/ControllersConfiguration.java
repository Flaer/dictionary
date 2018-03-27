package ru.cb.dictionary.ui;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by libragimov on 25.03.2018.
 */
@Configuration
public class ControllersConfiguration {


    @Bean(name = "mainView")
    @Qualifier("mainView")
    public ViewHolder getMainView() throws IOException {
        return new ViewInitializer().loadView("fxml/main.fxml");
    }

    @Bean(name = "actionView")
    @Qualifier("actionView")
    public ViewHolder getActionView() throws IOException {
        return new ViewInitializer().loadView("fxml/popup.fxml");
    }

    /**
     * Именно благодаря этому методу мы добавили контроллер в контекст спринга,
     * и заставили его сделать произвести все необходимые инъекции.
     */
    @Bean
    public MainController getMainController() throws IOException {
        return (MainController) getMainView().getController();
    }

    @Bean
    public ActionController getActionController() throws IOException {
        return (ActionController) getActionView().getController();
    }
}
