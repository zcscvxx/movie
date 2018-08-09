package movie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MovieMain extends Application {

   @Override
   
//   이전 start() 메소드
//   public void start(Stage stage) throws Exception {
//      BorderPane root = FXMLLoader.load(getClass().getResource("movie.fxml"));
//      Scene scene = new Scene(root);
//      stage.setScene(scene);
//      stage.setTitle("영화 예매 프로그램");
//      stage.show();
   
   
   
   //   primaryStage는 메인 클래스의 start() 파라미터로 전달되기 때문에 start() 메소드에서 컨트롤러로 primaryStage를 전달하면 됩니다.
    //   FXML 루트 태그의 fx:controller 속성에 지정된 컨트롤러 클래스는 FXMLLoader가 FXML을 로딩할 때 객체로 생성됩니다.
    //   FXMLLoader는 생성된 컨트롤러를 리턴하는 getController() 메소드를 제공하고 있습니다.
    //   그러나 이 메소드는 인스턴스 메소드이기 때문에 FXMLLoader 객체가 필요합니다.
    //   그래서 FXMLLoader의 정적 메소드 load() 호출 코드는 다음과 같이 인스턴스 메소드 load() 호출 코드로 변경해야 합니다.
   
   public void start(Stage primaryStage) throws Exception {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
      Parent root = loader.load();

      MovieControl2 controller = loader.getController();
      controller.setPrimaryStage(primaryStage);
        Scene scene = new Scene(root);

        primaryStage.setTitle("영화 예매 프로그램");
        primaryStage.setScene(scene);
        primaryStage.show();


   }

   public static void main(String[] args) {
      launch();
      
   }

}