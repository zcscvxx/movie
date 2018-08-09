package movie;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import movie.common.ConnFactory;
import movie.dao.CustomerDao;
import movie.dao.GenreDao;
import movie.dao.MovieDao;
import movie.vo.Customer;

public class MovieControl2 implements Initializable {

	@FXML
	private TextField loginName1;

	@FXML
	private Button btnLogin1;

	@FXML
	private TextField loginAge1;

	@FXML
	private TextField loginGender1;

	@FXML
	private ComboBox<Customer> cusList1;

	@FXML
	private TextField loginTel1;

	@FXML
	private Button btnLogin2;

	@FXML
	private Button btnLogin3;

	@FXML
	private TabPane tab13213;

	@FXML
	private TextField loginGender2;

	@FXML
	private TextField loginName2;

	@FXML
	private TextField loginTel2;

	@FXML
	private TextField loginAge2;

	@FXML
	private ComboBox<Customer> cusList2;

	@FXML
	void login3(ActionEvent event) {
		Customer vo = new Customer();
		CustomerDao dao = new CustomerDao();
		vo.setCus_id(getCustid(loginName2.getText()));
		vo.setCus_name(loginName2.getText());
		vo.setCus_age(Integer.parseInt(loginAge2.getText()));
		vo.setCus_gender(loginGender2.getText());
		vo.setCus_tel(loginTel2.getText());
		dao.update(vo);

		cusList.getItems().clear();
		// cusList1.getItems().clear();
		// cusList2.getItems().clear();

		cusList.getItems().addAll(customerDao.selectAll());
		// cusList1.getItems().addAll(customerDao.selectAll());
		// cusList2.getItems().addAll(customerDao.selectAll());

		SingleSelectionModel<Tab> selectionModel = tab13213.getSelectionModel();
		selectionModel.select(0); // select by index starting with 0

	}

	@FXML
	void cusList2(ActionEvent event) {
		Customer a = cusList2.getSelectionModel().getSelectedItem();
		// System.out.println(a);
		loginName2.setText(a.getCus_name());
		loginAge2.setText(a.getCus_age() + "");
		loginGender2.setText(a.getCus_gender());
		loginTel2.setText(a.getCus_tel());
		btnLogin3.setDisable(false);
	}

	@FXML
	void login2(ActionEvent event) {
		CustomerDao dao = new CustomerDao();

		int index = getCustid(loginName1.getText());
		Customer vo = dao.select(index);
		dao.delete(index);

		loginName1.clear();
		loginAge1.clear();
		loginGender1.clear();
		loginTel1.clear();

		cusList.getItems().clear();
		// cusList1.getItems().clear();
		// cusList2.getItems().clear();

		cusList.getItems().addAll(customerDao.selectAll());
		// cusList1.getItems().addAll(customerDao.selectAll());
		// cusList2.getItems().addAll(customerDao.selectAll());

		SingleSelectionModel<Tab> selectionModel = tab13213.getSelectionModel();
		selectionModel.select(0); // select by index starting with 0
	}

	@FXML
	void login1(ActionEvent event) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("회원탈퇴");
		alert.setHeaderText("정말로 탈퇴하시겠습니까?");
		alert.showAndWait();

		btnLogin2.setDisable(false);

	}

	public int getCustid(String custName) {
		String sql = "SELECT CUS_ID FROM CUSTOMER " + "WHERE CUS_NAME =? ";
		int num = 0;
		try {
			Connection conn = ConnFactory.getConnection("movie.config.oracle");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, custName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	@FXML
	void cusList1(ActionEvent event) {
		Customer a = cusList1.getSelectionModel().getSelectedItem();
		// System.out.println(a);
		loginName1.setText(a.getCus_name());
		loginAge1.setText(a.getCus_age() + "");
		loginGender1.setText(a.getCus_gender());
		loginTel1.setText(a.getCus_tel());
		btnLogin1.setDisable(false);

	}

	@FXML
	private Button btnJoin;

	@FXML
	private TextField txtGender;

	@FXML
	private TextField loginTel;
	public static TextField xxxTel;

	@FXML
	private Button btnLogin;

	@FXML
	private TextField loginGender;

	@FXML
	private TextField joinName;

	@FXML
	private RadioButton btnFemale;

	@FXML
	private TextField loginAge;

	@FXML
	private TextField loginName;
	public static TextField xxxName;
	@FXML
	private ComboBox<Customer> cusList;

	@FXML
	private TextField joinTel;

	@FXML
	private TextField joinAge;

	@FXML
	private RadioButton btnMale;

	@FXML
	void cusList(ActionEvent event) {
		Customer a = cusList.getSelectionModel().getSelectedItem();
		// System.out.println(a);
		loginName.setText(a.getCus_name());
		loginAge.setText(a.getCus_age() + "");
		loginGender.setText(a.getCus_gender());
		loginTel.setText(a.getCus_tel());
		btnLogin.setDisable(false);
	}

	private Stage primaryStage; // 컨트롤러에서 primaryStage 사용하는 방법

	public void setPrimaryStage(Stage primaryStage) { // 컨트롤러에서 primaryStage 사용하는 방법
		this.primaryStage = primaryStage;
		// primaryStage는 메인 클래스의 start() 파라미터로 전달되기 때문에 start() 메소드에서 컨트롤러로
		// primaryStage를 전달하면 됩니다.
		// FXML 루트 태그의 fx:controller 속성에 지정된 컨트롤러 클래스는 FXMLLoader가 FXML을 로딩할 때 객체로
		// 생성됩니다.
		// FXMLLoader는 생성된 컨트롤러를 리턴하는 getController() 메소드를 제공하고 있습니다.
		// 그러나 이 메소드는 인스턴스 메소드이기 때문에 FXMLLoader 객체가 필요합니다.
		// 그래서 FXMLLoader의 정적 메소드 load() 호출 코드는 다음과 같이 인스턴스 메소드 load() 호출 코드로 변경해야 합니다.

	}

	@FXML
	void login(ActionEvent event) throws IOException {

		// primaryStage.close(); //꺼지지만 좌석선택안됨

		// Platform.exit(); //다꺼짐

		Stage dialog = new Stage();
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(primaryStage);
		dialog.setTitle("영화 예매 프로그램");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("movie.fxml"));
		Parent root = loader.load();
		MovieControl controller = loader.getController();
		controller.setPrimaryStage(primaryStage);
		Scene scene = new Scene(root);

		dialog.setScene(scene);
		dialog.setResizable(false);
		dialog.show();

		// BorderPane root = FXMLLoader.load(getClass().getResource("movie.fxml"));
		// Scene scene = new Scene(root);

		// Stage primaryStage = new Stage();
		// Parent root = FXMLLoader.load(getClass().getResource("movie.fxml"));
		// Scene scene = new Scene(root);
		//
		// primaryStage.setScene(scene);
		// primaryStage.show();

	}

	@FXML
	void male(ActionEvent event) {
		txtGender.clear();
		txtGender.setText("남자");
	}

	@FXML
	void female(ActionEvent event) {
		txtGender.clear();
		txtGender.setText("여자");
	}

	@FXML
	void join(ActionEvent event) {
		Customer vo = new Customer();
		vo.setCus_name(joinName.getText());
		vo.setCus_age(Integer.parseInt(joinAge.getText()));
		vo.setCus_gender(txtGender.getText());
		vo.setCus_tel(joinTel.getText());
		CustomerDao dao = new CustomerDao();
		dao.insert(vo);

		cusList.getItems().clear();
		customerDao = new CustomerDao();
		cusList.getItems().addAll(customerDao.selectAll());

		cusList1.getItems().clear();
		customerDao = new CustomerDao();
		cusList1.getItems().addAll(customerDao.selectAll());

		cusList2.getItems().clear();
		customerDao = new CustomerDao();
		cusList2.getItems().addAll(customerDao.selectAll());
		
		SingleSelectionModel<Tab> selectionModel = tab13213.getSelectionModel();
		selectionModel.select(0); // select by index starting with 0
	}

	public static CustomerDao customerDao;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		xxxName = loginName;
		xxxTel = loginTel;
		customerDao = new CustomerDao();
		cusList.getItems().addAll(customerDao.selectAll());
		cusList1.getItems().addAll(customerDao.selectAll());
		cusList2.getItems().addAll(customerDao.selectAll());
	}

}
