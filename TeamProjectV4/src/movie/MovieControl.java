package movie;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import movie.common.ConnFactory;
import movie.dao.GenreDao;
import movie.dao.GradeDao;
import movie.dao.MovieDao;
import movie.dao.Orders2Dao;
import movie.dao.OrdersDao;
import movie.vo.Customer;
import movie.vo.Genre;
import movie.vo.Grade;
import movie.vo.Movie;
import movie.vo.Orders;
import movie.vo.Orders2;

public class MovieControl implements Initializable {

	private Connection conn = ConnFactory.getConnection("movie.config.oracle");

	@FXML
	private TextField txtName;
	
	@FXML
	private Button btnClear;

	@FXML
	private TableColumn<Orders2, Integer> colId;

	@FXML
	private TableColumn<Orders2, String> colName;

	@FXML
	private TableColumn<Orders2, String> colTel;

	@FXML
	private TableColumn<Orders2, String> colTitle;

	@FXML
	private TableColumn<Orders2, Integer> colAmount;

	@FXML
	private TableColumn<Orders2, String> colSeat;

	@FXML
	private TableColumn<Orders2, String> colPrice;

	@FXML
	private TableColumn<Orders2, String> colDate;

	@FXML
	private TableColumn<Orders2, String> colTime;

	@FXML
	private ComboBox<Genre> cmbGenre;

	@FXML
	private TextField txtDate;

	@FXML
	private TextField txtTel;

	@FXML
	private ComboBox<String> cmbAmount;

	@FXML
	private TextField txtSeat1;
	@FXML
	private TextField txtSeat2;
	@FXML
	private TextField txtSeat3;
	@FXML
	private TextField txtSeat4;

	@FXML
	private ImageView imgSeat;

	@FXML
	private TextField txtAmount;

	@FXML
	private DatePicker datePicker;

	@FXML
	private ComboBox<Grade> cmbGrade;

	@FXML
	private Button btnCheck;

	@FXML
	private ComboBox<String> cmbTime;

	@FXML
	private TextField txtSeat;

	@FXML
	private ListView<Movie> listMovie;

	@FXML
	private ImageView imgPoster;

	@FXML
	private Button btnSerch;

	@FXML
	private TextArea txtContent;

	@FXML
	private TextField txtPrice;

	@FXML
	private Button btnReserve;

	@FXML
	private TableView<Orders2> table;

	@FXML
	private TextField txtTitle;

	@FXML
	private TextField txtTime;

	public List<Customer> login() {
		String sql = "SELECT CUS_NAME " + "FROM CUSTOMER";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Customer vo = null;
		List<Customer> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new Customer();
				//vo.setCus_id(rs.getInt("CUS_ID"));
				vo.setCus_name(rs.getString("CUS_NAME"));
				//vo.setCus_age(rs.getInt("CUS_AGE"));
				//vo.setCus_gender(rs.getString("CUS_GENDER"));
				//vo.setCus_tel(rs.getString("CUS_TEL"));
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	@FXML
	void check(ActionEvent event) throws FileNotFoundException {
		
		
		
		if (login().toString().contains(txtName.getText())) {
			btnReserve.setDisable(false);
		} else {
			btnReserve.setDisable(true);
		}
		
		

	}

	@FXML
	void genre(ActionEvent event) {
	
	}

	@FXML
	void grade(ActionEvent event) {

	}

	@FXML
	void serch(ActionEvent event) {
		if(!cmbGenre.getSelectionModel().isEmpty() &&
				cmbGrade.getSelectionModel().isEmpty()) {
			listMovie.getItems().clear();
			List<Movie> list = new ArrayList<>();
			list.addAll(movieDao.selectt(getGenreid(cmbGenre.getValue().toString())));
			listMovie.getItems().addAll(list);		
		}else if(cmbGenre.getSelectionModel().isEmpty() &&
				!cmbGrade.getSelectionModel().isEmpty()){
			listMovie.getItems().clear();
			List<Movie> list = new ArrayList<>();
			list.addAll(movieDao.selectGrade(getGradeid(cmbGrade.getValue().toString())));
			listMovie.getItems().addAll(list);
		}else if(!cmbGenre.getSelectionModel().isEmpty() &&
				!cmbGrade.getSelectionModel().isEmpty()){
			listMovie.getItems().clear();
			List<Movie> list = new ArrayList<>();
			list.addAll(movieDao.selectGG(getGenreid(cmbGenre.getValue().toString()),getGradeid(cmbGrade.getValue().toString())));
			listMovie.getItems().addAll(list);
		}
	}
	
	@FXML
	void clear(ActionEvent event) {
		listMovie.getItems().clear();
		listMovie.getItems().addAll(movieDao.selectAll());
		
		cmbGenre.getItems().clear();
		genreDao = new GenreDao();
		cmbGenre.getItems().addAll(genreDao.selectAll());
		
		cmbGrade.getItems().clear();
		gradeDao = new GradeDao();
		cmbGrade.getItems().addAll(gradeDao.selectAll());
		
	}

	@FXML
	void poster(MouseEvent event) {

	}

	@FXML
	void time(ActionEvent event) {
		String time = cmbTime.getValue();
		txtTime.setText(time);
	}

	@FXML
	void amount(ActionEvent event) {
		
		
		String amount = cmbAmount.getValue();
		txtAmount.setText(amount);

//		String x = cmbAmount.getValue();
//		String y = (Integer.parseInt(x.substring(0, 1)) * 7000) + "원";
//		txtPrice.setText(y);
		
		String x = txtAmount.getText();
		String y = (Integer.parseInt(x.substring(0, 1)) * 7000) + "원";
		txtPrice.setText(y);

		////////////////////////////////////
		////////////////////////////////////
		////////////////////////////////////
		////////////////////////////////////
		
		if(cmbAmount.getValue() == "1장")
		   {
			   seat1P.setDisable(false);
			   seat2P.setDisable(true);
			   seat3P.setDisable(true);
			   seat4P.setDisable(true);
			   seat5P.setDisable(false);
			   seatClear.setDisable(false);
				txtSeat1.setText(null);
				txtSeat2.setText(null);
				txtSeat3.setText(null);
				txtSeat4.setText(null);
				seatFirst1 = " ";
				seatFirst2 = " ";
				seatFirst3 = " ";
				seatFirst4 = " ";
				txtAmount.setText("1");
				
		   }else if(cmbAmount.getValue() == "2장")
		   {
			   seat1P.setDisable(false);
			   seat2P.setDisable(false);
			   seat3P.setDisable(true);
			   seat4P.setDisable(true);
			   seat5P.setDisable(false);
			   seatClear.setDisable(false);
				txtSeat1.setText(null);
				txtSeat2.setText(null);
				txtSeat3.setText(null);
				txtSeat4.setText(null);
				seatFirst1 = " ";
				seatFirst2 = " ";
				seatFirst3 = " ";
				seatFirst4 = " ";
				txtAmount.setText("2");
		   }else if(cmbAmount.getValue() == "3장")
		   {
			   seat1P.setDisable(false);
			   seat2P.setDisable(false);
			   seat3P.setDisable(false);
			   seat4P.setDisable(true);
			   seat5P.setDisable(false);
			   seatClear.setDisable(false);
				txtSeat1.setText(null);
				txtSeat2.setText(null);
				txtSeat3.setText(null);
				txtSeat4.setText(null);
				seatFirst1 = " ";
				seatFirst2 = " ";
				seatFirst3 = " ";
				seatFirst4 = " ";
				txtAmount.setText("3");
		   }else if(cmbAmount.getValue() == "4장")
		   {
			   seat1P.setDisable(false);
			   seat2P.setDisable(false);
			   seat3P.setDisable(false);
			   seat4P.setDisable(false);
			   seat5P.setDisable(false);
			   seatClear.setDisable(false);
				txtSeat1.setText(null);
				txtSeat2.setText(null);
				txtSeat3.setText(null);
				txtSeat4.setText(null);
				seatFirst1 = " ";
				seatFirst2 = " ";
				seatFirst3 = " ";
				seatFirst4 = " ";
				txtAmount.setText("4");
		   }

		////////////////////////////////////
		////////////////////////////////////
		////////////////////////////////////
		////////////////////////////////////
		////////////////////////////////////
		////////////////////////////////////
		
	}

	@FXML
	void reserve(ActionEvent event) {

		// 테이블에 데이터 입력
		Orders vo = new Orders();
		vo.setCus_id(getCustid(MovieControl2.xxxName.getText()));
		vo.setCus_tel(MovieControl2.xxxTel.getText());
		vo.setMovie_id(getMovieid(txtTitle.getText()));
		vo.setOrder_date(txtDate.getText());
		vo.setOrder_time(txtTime.getText());
		vo.setOrder_amount(Integer.parseInt(txtAmount.getText()));
		vo.setOrder_seat(txtSeat.getText());
		OrdersDao dao = new OrdersDao();
		dao.insert(vo);

		// 테이블에 데이터 입력
		Orders2 vo2 = new Orders2();
		Orders2Dao dao2 = new Orders2Dao();

		// 테이블뷰에 출력
		vo2.setCus_name(MovieControl2.xxxName.getText());
		vo2.setCus_tel(MovieControl2.xxxTel.getText());
		vo2.setMovie_name(txtTitle.getText());
		vo2.setOrder_date(txtDate.getText());
		vo2.setOrder_time(txtTime.getText());
		vo2.setOrder_amount(Integer.parseInt(txtAmount.getText()));
		vo2.setOrder_seat(txtSeat.getText());
		vo2.setMovie_price(txtPrice.getText());
		dao2.insert(vo2);
		int currval2 = 0;
		try {
			String sql = "SELECT MAX(ORDER_ID) FROM ORDERS";
			Connection conn = ConnFactory.getConnection("movie.config.oracle");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			currval2 = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vo2.setOrder_id(currval2);
		table.getItems().add(vo2);
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
//////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
	
	
	
	
	@FXML
	private Button seat1P;
	@FXML
	private Button seat2P;
	@FXML
	private Button seat3P;
	@FXML
	private Button seat4P;
	@FXML
	private Button seat5P;
	@FXML
	private Button seatClear;	
	@FXML
	void seatClearA(ActionEvent event) throws Exception {
		txtSeat1.setText(null);
		txtSeat2.setText(null);
		txtSeat3.setText(null);
		txtSeat4.setText(null);
		seatFirst1 = " ";
		seatFirst2 = " ";
		seatFirst3 = " ";
		seatFirst4 = " ";
		txtSeat.setText(null);
	}
	
	private String seatFirst1 = " ";
	private String seatFirst2 = " ";
	private String seatFirst3 = " ";
	private String seatFirst4 = " ";
	
	private String seatFinal = " ";
	
	@FXML
	void seat1PA(ActionEvent event) throws Exception {
		Popup popup = new Popup();
		AnchorPane hbox = (AnchorPane) FXMLLoader.load(getClass().getResource("popup.fxml"));
		
		popup.getContent().add(hbox);
		popup.show(primaryStage);
		
		

		
////////////////A//////////////
 Button a1 = (Button) hbox.lookup("#a1");
 Button a2 = (Button) hbox.lookup("#a2");
 Button a3 = (Button) hbox.lookup("#a3");
 Button a4 = (Button) hbox.lookup("#a4");
 Button a5 = (Button) hbox.lookup("#a5");
 Button a6 = (Button) hbox.lookup("#a6");
 Button a7 = (Button) hbox.lookup("#a7");
 Button a8 = (Button) hbox.lookup("#a8");
 Button a9 = (Button) hbox.lookup("#a9");
 Button a10 = (Button) hbox.lookup("#a10");
 Button a11  =(Button) hbox.lookup("#a11");
 Button a12 = (Button) hbox.lookup("#a12");
 Button a13 = (Button) hbox.lookup("#a13");	  
 
 /////////////////B////////////////////
 Button a14 = (Button) hbox.lookup("#a14");
 Button a21 = (Button) hbox.lookup("#a21");
 Button a31 = (Button) hbox.lookup("#a31");
 Button a41 = (Button) hbox.lookup("#a41");
 Button a51 = (Button) hbox.lookup("#a51");
 Button a61 = (Button) hbox.lookup("#a61");
 Button a71 = (Button) hbox.lookup("#a71");
 Button a81 = (Button) hbox.lookup("#a81");
 Button a91 = (Button) hbox.lookup("#a91");
 Button a101 = (Button) hbox.lookup("#a101");
 Button a111 = (Button) hbox.lookup("#a111");
 Button a121 = (Button) hbox.lookup("#a121");
 Button a131 = (Button) hbox.lookup("#a131");	
 
 //////////////C///////////////////////////
 Button a15 = (Button) hbox.lookup("#a15");
 Button a22 = (Button) hbox.lookup("#a22");
 Button a32 = (Button) hbox.lookup("#a32");
 Button a42 = (Button) hbox.lookup("#a42");
 Button a52 = (Button) hbox.lookup("#a52");
 Button a62 = (Button) hbox.lookup("#a62");
 Button a72 = (Button) hbox.lookup("#a72");
 Button a82 = (Button) hbox.lookup("#a82");
 Button a92 = (Button) hbox.lookup("#a92");
 Button a102 = (Button) hbox.lookup("#a102");
 Button a112 = (Button) hbox.lookup("#a112");
 Button a122 = (Button) hbox.lookup("#a122");
 Button a132 = (Button) hbox.lookup("#a132");	
 
 //////////////////D////////////////////////
 Button a16 = (Button) hbox.lookup("#a16");
 Button a23 = (Button) hbox.lookup("#a23");
 Button a33 = (Button) hbox.lookup("#a33");
 Button a43 = (Button) hbox.lookup("#a43");
 Button a53 = (Button) hbox.lookup("#a53");
 Button a63 = (Button) hbox.lookup("#a63");
 Button a73 = (Button) hbox.lookup("#a73");
 Button a83 = (Button) hbox.lookup("#a83");
 Button a93 = (Button) hbox.lookup("#a93");
 Button a103 = (Button) hbox.lookup("#a103");
 Button a113 = (Button) hbox.lookup("#a113");
 Button a123 = (Button) hbox.lookup("#a123");
 Button a133 = (Button) hbox.lookup("#a133");	
 
 //////////////E////////////////////////////
 Button a17 = (Button) hbox.lookup("#a17");
 Button a24 = (Button) hbox.lookup("#a24");
 Button a34 = (Button) hbox.lookup("#a34");
 Button a44 = (Button) hbox.lookup("#a44");
 Button a54 = (Button) hbox.lookup("#a54");
 Button a64 = (Button) hbox.lookup("#a64");
 Button a74 = (Button) hbox.lookup("#a74");
 Button a84 = (Button) hbox.lookup("#a84");
 Button a94 = (Button) hbox.lookup("#a94");
 Button a104 = (Button) hbox.lookup("#a104");
 Button a114 = (Button) hbox.lookup("#a114");
 Button a124 = (Button) hbox.lookup("#a124");
 Button a134 = (Button) hbox.lookup("#a134");	
 
 ////////////F///////////////////////////////
 Button a18 = (Button) hbox.lookup("#a18");
 Button a25 = (Button) hbox.lookup("#a25");
 Button a35 = (Button) hbox.lookup("#a35");
 Button a45 = (Button) hbox.lookup("#a45");
 Button a55 = (Button) hbox.lookup("#a55");
 Button a65 = (Button) hbox.lookup("#a65");
 Button a75 = (Button) hbox.lookup("#a75");
 Button a85 = (Button) hbox.lookup("#a85");
 Button a95 = (Button) hbox.lookup("#a95");
 Button a105 = (Button) hbox.lookup("#a105");
 Button a115 = (Button) hbox.lookup("#a115");
 Button a125 = (Button) hbox.lookup("#a125");
 Button a135 = (Button) hbox.lookup("#a135");	
 
 ////////////G//////////////////////////////
 Button a19 = (Button) hbox.lookup("#a19");
 Button a26 = (Button) hbox.lookup("#a26");
 Button a36 = (Button) hbox.lookup("#a36");
 Button a46 = (Button) hbox.lookup("#a46");
 Button a56 = (Button) hbox.lookup("#a56");
 Button a66 = (Button) hbox.lookup("#a66");
 Button a76 = (Button) hbox.lookup("#a76");
 Button a86 = (Button) hbox.lookup("#a86");
 Button a96 = (Button) hbox.lookup("#a96");
 Button a106 = (Button) hbox.lookup("#a106");
 Button a116 = (Button) hbox.lookup("#a116");
 Button a126 = (Button) hbox.lookup("#a126");
 Button a136 = (Button) hbox.lookup("#a136");	
 
 ////////////////////H/////////////////////////
 Button a110 = (Button) hbox.lookup("#a110");
 Button a27 = (Button) hbox.lookup("#a27");
 Button a37 = (Button) hbox.lookup("#a37");
 Button a47 = (Button) hbox.lookup("#a47");
 Button a57 = (Button) hbox.lookup("#a57");
 Button a67 = (Button) hbox.lookup("#a67");
 Button a77 = (Button) hbox.lookup("#a77");
 Button a87 = (Button) hbox.lookup("#a87");
 Button a97 = (Button) hbox.lookup("#a97");
 Button a107 = (Button) hbox.lookup("#a107");
 Button a117 = (Button) hbox.lookup("#a117");
 Button a127 = (Button) hbox.lookup("#a127");
 Button a137 = (Button) hbox.lookup("#a137");	
 
 /////////////////I//////////////////////////
 Button a1100 = (Button) hbox.lookup("#a1100");
 Button a28 = (Button) hbox.lookup("#a28");
 Button a38 = (Button) hbox.lookup("#a38");
 Button a48 = (Button) hbox.lookup("#a48");
 Button a58 = (Button) hbox.lookup("#a58");
 Button a68 = (Button) hbox.lookup("#a68");
 Button a78 = (Button) hbox.lookup("#a78");
 Button a88 = (Button) hbox.lookup("#a88");
 Button a98 = (Button) hbox.lookup("#a98");
 Button a108 = (Button) hbox.lookup("#a108");
 Button a118 = (Button) hbox.lookup("#a118");
 Button a128 = (Button) hbox.lookup("#a128");
 Button a138 = (Button) hbox.lookup("#a138");
 
 //////////////J///////////////////////////////
 Button a1101 = (Button) hbox.lookup("#a1101");
 Button a29 = (Button) hbox.lookup("#a29");
 Button a39 = (Button) hbox.lookup("#a39");
 Button a49 = (Button) hbox.lookup("#a49");
 Button a59 = (Button) hbox.lookup("#a59");
 Button a69 = (Button) hbox.lookup("#a69");
 Button a79 = (Button) hbox.lookup("#a79");
 Button a89 = (Button) hbox.lookup("#a89");
 Button a99 = (Button) hbox.lookup("#a99");
 Button a109 = (Button) hbox.lookup("#a109");
 Button a119 = (Button) hbox.lookup("#a119");
 Button a129 = (Button) hbox.lookup("#a129");
 Button a139 = (Button) hbox.lookup("#a139");
 
 //////////////K///////////////////////////////
 Button a1102 = (Button) hbox.lookup("#a1102");
 Button a210 = (Button) hbox.lookup("#a210");
 Button a310 = (Button) hbox.lookup("#a310");
 Button a410 = (Button) hbox.lookup("#a410");
 Button a510 = (Button) hbox.lookup("#a510");
 Button a610 = (Button) hbox.lookup("#a610");
 Button a710 = (Button) hbox.lookup("#a710");
 Button a810 = (Button) hbox.lookup("#a810");
 Button a910 = (Button) hbox.lookup("#a910");
 Button a1010 = (Button) hbox.lookup("#a1010");
 Button a1110 = (Button) hbox.lookup("#a1110");
 Button a1210 = (Button) hbox.lookup("#a1210");
 Button a1310 = (Button) hbox.lookup("#a1310");
 
 //////////////L///////////////////////////////
 Button a1103 = (Button) hbox.lookup("#a1103");
 Button a211 = (Button) hbox.lookup("#a211");
 Button a311 = (Button) hbox.lookup("#a311");
 Button a411 = (Button) hbox.lookup("#a411");
 Button a511 = (Button) hbox.lookup("#a511");
 Button a611 = (Button) hbox.lookup("#a611");
 Button a711 = (Button) hbox.lookup("#a711");
 Button a811 = (Button) hbox.lookup("#a811");
 Button a911 = (Button) hbox.lookup("#a911");
 Button a1011 = (Button) hbox.lookup("#a1011");
 Button a1111 = (Button) hbox.lookup("#a1111");
 Button a1211 = (Button) hbox.lookup("#a1211");
 Button a1311 = (Button) hbox.lookup("#a1311");	     
 
 ////////////////A/////////////////////////
 a1.setOnAction(even -> 
 {
	 seatFirst1 = "A1";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a2.setOnAction(even -> 
 {
	 seatFirst1 = "A2";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a3.setOnAction(even -> 
 {
	 seatFirst1 = "A3";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a4.setOnAction(even -> 
 {
	 seatFirst1 = "A4";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a5.setOnAction(even -> 
 {
	 seatFirst1 = "A5";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a6.setOnAction(even -> 
 {
	 seatFirst1 = "A6";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a7.setOnAction(even -> 
 {
	 seatFirst1 = "A7";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a8.setOnAction(even -> 
 {
	 seatFirst1 = "A8";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a9.setOnAction(even -> 
 {
	 seatFirst1 = "A9";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a10.setOnAction(even -> 
 {
	 seatFirst1 = "A10";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a11.setOnAction(even -> 
 {
	 seatFirst1 = "A11";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a12.setOnAction(even -> 
 {
	 seatFirst1 = "A12";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a13.setOnAction(even -> 
 {
	 seatFirst1 = "A13";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });

 
 //////////////////////B////////////////////////
 a14.setOnAction(even -> 
 {
	 seatFirst1 = "B1";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a21.setOnAction(even -> 
 {
	 seatFirst1 = "B2";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a31.setOnAction(even -> 
 {
	 seatFirst1 = "B3";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a41.setOnAction(even -> 
 {
	 seatFirst1 = "B4";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a51.setOnAction(even -> 
 {
	 seatFirst1 = "B5";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a61.setOnAction(even -> 
 {
	 seatFirst1 = "B6";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a71.setOnAction(even -> 
 {
	 seatFirst1 = "B7";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a81.setOnAction(even -> 
 {
	 seatFirst1 = "B8";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a91.setOnAction(even -> 
 {
	 seatFirst1 = "B9";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a101.setOnAction(even -> 
 {
	 seatFirst1 = "B10";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a111.setOnAction(even -> 
 {
	 seatFirst1 = "B11";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a121.setOnAction(even -> 
 {
	 seatFirst1 = "B12";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a131.setOnAction(even -> 
 {
	 seatFirst1 = "B13";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 ////////////////C///////////////////////
 a15.setOnAction(even -> 
 {
	 seatFirst1 = "C1";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a22.setOnAction(even -> 
 {
	 seatFirst1 = "C2";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a32.setOnAction(even -> 
 {
	 seatFirst1 = "C3";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a42.setOnAction(even -> 
 {
	 seatFirst1 = "C4";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a52.setOnAction(even -> 
 {
	 seatFirst1 = "C5";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a62.setOnAction(even -> 
 {
	 seatFirst1 = "C6";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a72.setOnAction(even -> 
 {
	 seatFirst1 = "C7";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a82.setOnAction(even -> 
 {
	 seatFirst1 = "C8";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a92.setOnAction(even -> 
 {
	 seatFirst1 = "C9";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a102.setOnAction(even -> 
 {
	 seatFirst1 = "C10";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a112.setOnAction(even -> 
 {
	 seatFirst1 = "C11";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a122.setOnAction(even -> 
 {
	 seatFirst1 = "C12";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a132.setOnAction(even -> 
 {
	 seatFirst1 = "C13";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 
 
 /////D///////////////////
 
 a16.setOnAction(even -> 
 {
	 seatFirst1 = "D1";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a23.setOnAction(even -> 
 {
	 seatFirst1 = "D2";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a33.setOnAction(even -> 
 {
	 seatFirst1 = "D3";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a43.setOnAction(even -> 
 {
	 seatFirst1 = "D4";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a53.setOnAction(even -> 
 {
	 seatFirst1 = "D5";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a63.setOnAction(even -> 
 {
	 seatFirst1 = "D6";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a73.setOnAction(even -> 
 {
	 seatFirst1 = "D7";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a83.setOnAction(even -> 
 {
	 seatFirst1 = "D8";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a93.setOnAction(even -> 
 {
	 seatFirst1 = "D9";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a103.setOnAction(even -> 
 {
	 seatFirst1 = "D10";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a113.setOnAction(even -> 
 {
	 seatFirst1 = "D11";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a123.setOnAction(even -> 
 {
	 seatFirst1 = "D12";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a133.setOnAction(even -> 
 {
	 seatFirst1 = "D13";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 
 a17.setOnAction(even -> 
 {
	 seatFirst1 = "E1";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a24.setOnAction(even -> 
 {
	 seatFirst1 = "E2";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a34.setOnAction(even -> 
 {
	 seatFirst1 = "E3";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a44.setOnAction(even -> 
 {
	 seatFirst1 = "E4";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a54.setOnAction(even -> 
 {
	 seatFirst1 = "E5";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a64.setOnAction(even -> 
 {
	 seatFirst1 = "E6";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a74.setOnAction(even -> 
 {
	 seatFirst1 = "E7";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a84.setOnAction(even -> 
 {
	 seatFirst1 = "E8";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a94.setOnAction(even -> 
 {
	 seatFirst1 = "E9";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a104.setOnAction(even -> 
 {
	 seatFirst1 = "E10";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a114.setOnAction(even -> 
 {
	 seatFirst1 = "E11";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a124.setOnAction(even -> 
 {
	 seatFirst1 = "E12";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a134.setOnAction(even -> 
 {
	 seatFirst1 = "E13";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 
 
 //////////F//////////////
 a18.setOnAction(even -> 
 {
	 seatFirst1 = "F1";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a25.setOnAction(even -> 
 {
	 seatFirst1 = "F2";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a35.setOnAction(even -> 
 {
	 seatFirst1 = "F3";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a45.setOnAction(even -> 
 {
	 seatFirst1 = "F4";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a55.setOnAction(even -> 
 {
	 seatFirst1 = "F5";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a65.setOnAction(even -> 
 {
	 seatFirst1 = "F6";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a75.setOnAction(even -> 
 {
	 seatFirst1 = "F7";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a85.setOnAction(even -> 
 {
	 seatFirst1 = "F8";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a95.setOnAction(even -> 
 {
	 seatFirst1 = "F9";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a105.setOnAction(even -> 
 {
	 seatFirst1 = "F10";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a115.setOnAction(even -> 
 {
	 seatFirst1 = "F11";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a125.setOnAction(even -> 
 {
	 seatFirst1 = "F12";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a135.setOnAction(even -> 
 {
	 seatFirst1 = "F13";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 
 
 ///////////G/////////////
 a19.setOnAction(even -> 
 {
	 seatFirst1 = "G1";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a26.setOnAction(even -> 
 {
	 seatFirst1 = "G2";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a36.setOnAction(even -> 
 {
	 seatFirst1 = "G3";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a46.setOnAction(even -> 
 {
	 seatFirst1 = "G4";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a56.setOnAction(even -> 
 {
	 seatFirst1 = "G5";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a66.setOnAction(even -> 
 {
	 seatFirst1 = "G6";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a76.setOnAction(even -> 
 {
	 seatFirst1 = "G7";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a86.setOnAction(even -> 
 {
	 seatFirst1 = "G8";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a96.setOnAction(even -> 
 {
	 seatFirst1 = "G9";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a106.setOnAction(even -> 
 {
	 seatFirst1 = "G10";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a116.setOnAction(even -> 
 {
	 seatFirst1 = "G11";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a126.setOnAction(even -> 
 {
	 seatFirst1 = "G12";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a136.setOnAction(even -> 
 {
	 seatFirst1 = "G13";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 
 
 //////////H////////////////////////////
 a110.setOnAction(even -> 
 {
	 seatFirst1 = "H1";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a27.setOnAction(even -> 
 {
	 seatFirst1 = "H2";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a37.setOnAction(even -> 
 {
	 seatFirst1 = "H3";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a47.setOnAction(even -> 
 {
	 seatFirst1 = "H4";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a57.setOnAction(even -> 
 {
	 seatFirst1 = "H5";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a67.setOnAction(even -> 
 {
	 seatFirst1 = "H6";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a77.setOnAction(even -> 
 {
	 seatFirst1 = "H7";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a87.setOnAction(even -> 
 {
	 seatFirst1 = "H8";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a97.setOnAction(even -> 
 {
	 seatFirst1 = "H9";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a107.setOnAction(even -> 
 {
	 seatFirst1 = "H10";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a117.setOnAction(even -> 
 {
	 seatFirst1 = "H11";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a127.setOnAction(even -> 
 {
	 seatFirst1 = "H12";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a137.setOnAction(even -> 
 {
	 seatFirst1 = "H13";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 
 
 ////////////I////////////////////
 a1100.setOnAction(even -> 
 {
	 seatFirst1 = "I1";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a28.setOnAction(even -> 
 {
	 seatFirst1 = "I2";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a38.setOnAction(even -> 
 {
	 seatFirst1 = "I3";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a48.setOnAction(even -> 
 {
	 seatFirst1 = "I4";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a58.setOnAction(even -> 
 {
	 seatFirst1 = "I5";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a68.setOnAction(even -> 
 {
	 seatFirst1 = "I6";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a78.setOnAction(even -> 
 {
	 seatFirst1 = "I7";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a88.setOnAction(even -> 
 {
	 seatFirst1 = "I8";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a98.setOnAction(even -> 
 {
	 seatFirst1 = "I9";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a108.setOnAction(even -> 
 {
	 seatFirst1 = "I10";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a118.setOnAction(even -> 
 {
	 seatFirst1 = "I11";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a128.setOnAction(even -> 
 {
	 seatFirst1 = "I12";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a138.setOnAction(even -> 
 {
	 seatFirst1 = "I13";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 
 ///////////////J/////////////////
 a1101.setOnAction(even -> 
 {
	 seatFirst1 = "J1";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a29.setOnAction(even -> 
 {
	 seatFirst1 = "J2";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a39.setOnAction(even -> 
 {
	 seatFirst1 = "J3";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a49.setOnAction(even -> 
 {
	 seatFirst1 = "J4";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a59.setOnAction(even -> 
 {
	 seatFirst1 = "J5";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a69.setOnAction(even -> 
 {
	 seatFirst1 = "J6";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a79.setOnAction(even -> 
 {
	 seatFirst1 = "J7";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a89.setOnAction(even -> 
 {
	 seatFirst1 = "J8";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a99.setOnAction(even -> 
 {
	 seatFirst1 = "J9";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a109.setOnAction(even -> 
 {
	 seatFirst1 = "J10";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a119.setOnAction(even -> 
 {
	 seatFirst1 = "J11";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a129.setOnAction(even -> 
 {
	 seatFirst1 = "J12";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a139.setOnAction(even -> 
 {
	 seatFirst1 = "J13";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 
 
 
 /////////////////K////////////////
 a1102.setOnAction(even -> 
 {
	 seatFirst1 = "K1";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a210.setOnAction(even -> 
 {
	 seatFirst1 = "K2";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a310.setOnAction(even -> 
 {
	 seatFirst1 = "K3";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a410.setOnAction(even -> 
 {
	 seatFirst1 = "K4";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a510.setOnAction(even -> 
 {
	 seatFirst1 = "K5";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a610.setOnAction(even -> 
 {
	 seatFirst1 = "K6";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a710.setOnAction(even -> 
 {
	 seatFirst1 = "K7";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a810.setOnAction(even -> 
 {
	 seatFirst1 = "K8";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a910.setOnAction(even -> 
 {
	 seatFirst1 = "K9";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a1010.setOnAction(even -> 
 {
	 seatFirst1 = "K10";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a1110.setOnAction(even -> 
 {
	 seatFirst1 = "K11";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a1210.setOnAction(even -> 
 {
	 seatFirst1 = "K12";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a1310.setOnAction(even -> 
 {
	 seatFirst1 = "K13";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 
 
 
 a1103.setOnAction(even -> 
 {
	 seatFirst1 = "L1";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a211.setOnAction(even -> 
 {
	 seatFirst1 = "L2";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a311.setOnAction(even -> 
 {
	 seatFirst1 = "L3";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a411.setOnAction(even -> 
 {
	 seatFirst1 = "L4";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a511.setOnAction(even -> 
 {
	 seatFirst1 = "L5";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a611.setOnAction(even -> 
 {
	 seatFirst1 = "L6";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a711.setOnAction(even -> 
 {
	 seatFirst1 = "L7";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a811.setOnAction(even -> 
 {
	 seatFirst1 = "L8";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a911.setOnAction(even -> 
 {
	 seatFirst1 = "L9";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a1011.setOnAction(even -> 
 {
	 seatFirst1 = "L10";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a1111.setOnAction(even -> 
 {
	 seatFirst1 = "L11";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a1211.setOnAction(even -> 
 {
	 seatFirst1 = "L12";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 a1311.setOnAction(even -> 
 {
	 seatFirst1 = "L13";
	 txtSeat1.setText(seatFirst1);
	 popup.hide();
 });
 
 
/////////////////////////////////////////////  
 
 
 
 
 
	}
	@FXML
	void seat2PA(ActionEvent event) throws Exception {
		Popup popup = new Popup();
		AnchorPane hbox = (AnchorPane) FXMLLoader.load(getClass().getResource("popup.fxml"));
		
		popup.getContent().add(hbox);
        popup.setAutoHide(true);
		popup.show(primaryStage);
		
////////////////A//////////////
 Button a1 = (Button) hbox.lookup("#a1");
 Button a2 = (Button) hbox.lookup("#a2");
 Button a3 = (Button) hbox.lookup("#a3");
 Button a4 = (Button) hbox.lookup("#a4");
 Button a5 = (Button) hbox.lookup("#a5");
 Button a6 = (Button) hbox.lookup("#a6");
 Button a7 = (Button) hbox.lookup("#a7");
 Button a8 = (Button) hbox.lookup("#a8");
 Button a9 = (Button) hbox.lookup("#a9");
 Button a10 = (Button) hbox.lookup("#a10");
 Button a11  =(Button) hbox.lookup("#a11");
 Button a12 = (Button) hbox.lookup("#a12");
 Button a13 = (Button) hbox.lookup("#a13");	  
 
 /////////////////B////////////////////
 Button a14 = (Button) hbox.lookup("#a14");
 Button a21 = (Button) hbox.lookup("#a21");
 Button a31 = (Button) hbox.lookup("#a31");
 Button a41 = (Button) hbox.lookup("#a41");
 Button a51 = (Button) hbox.lookup("#a51");
 Button a61 = (Button) hbox.lookup("#a61");
 Button a71 = (Button) hbox.lookup("#a71");
 Button a81 = (Button) hbox.lookup("#a81");
 Button a91 = (Button) hbox.lookup("#a91");
 Button a101 = (Button) hbox.lookup("#a101");
 Button a111 = (Button) hbox.lookup("#a111");
 Button a121 = (Button) hbox.lookup("#a121");
 Button a131 = (Button) hbox.lookup("#a131");	
 
 //////////////C///////////////////////////
 Button a15 = (Button) hbox.lookup("#a15");
 Button a22 = (Button) hbox.lookup("#a22");
 Button a32 = (Button) hbox.lookup("#a32");
 Button a42 = (Button) hbox.lookup("#a42");
 Button a52 = (Button) hbox.lookup("#a52");
 Button a62 = (Button) hbox.lookup("#a62");
 Button a72 = (Button) hbox.lookup("#a72");
 Button a82 = (Button) hbox.lookup("#a82");
 Button a92 = (Button) hbox.lookup("#a92");
 Button a102 = (Button) hbox.lookup("#a102");
 Button a112 = (Button) hbox.lookup("#a112");
 Button a122 = (Button) hbox.lookup("#a122");
 Button a132 = (Button) hbox.lookup("#a132");	
 
 //////////////////D////////////////////////
 Button a16 = (Button) hbox.lookup("#a16");
 Button a23 = (Button) hbox.lookup("#a23");
 Button a33 = (Button) hbox.lookup("#a33");
 Button a43 = (Button) hbox.lookup("#a43");
 Button a53 = (Button) hbox.lookup("#a53");
 Button a63 = (Button) hbox.lookup("#a63");
 Button a73 = (Button) hbox.lookup("#a73");
 Button a83 = (Button) hbox.lookup("#a83");
 Button a93 = (Button) hbox.lookup("#a93");
 Button a103 = (Button) hbox.lookup("#a103");
 Button a113 = (Button) hbox.lookup("#a113");
 Button a123 = (Button) hbox.lookup("#a123");
 Button a133 = (Button) hbox.lookup("#a133");	
 
 //////////////E////////////////////////////
 Button a17 = (Button) hbox.lookup("#a17");
 Button a24 = (Button) hbox.lookup("#a24");
 Button a34 = (Button) hbox.lookup("#a34");
 Button a44 = (Button) hbox.lookup("#a44");
 Button a54 = (Button) hbox.lookup("#a54");
 Button a64 = (Button) hbox.lookup("#a64");
 Button a74 = (Button) hbox.lookup("#a74");
 Button a84 = (Button) hbox.lookup("#a84");
 Button a94 = (Button) hbox.lookup("#a94");
 Button a104 = (Button) hbox.lookup("#a104");
 Button a114 = (Button) hbox.lookup("#a114");
 Button a124 = (Button) hbox.lookup("#a124");
 Button a134 = (Button) hbox.lookup("#a134");	
 
 ////////////F///////////////////////////////
 Button a18 = (Button) hbox.lookup("#a18");
 Button a25 = (Button) hbox.lookup("#a25");
 Button a35 = (Button) hbox.lookup("#a35");
 Button a45 = (Button) hbox.lookup("#a45");
 Button a55 = (Button) hbox.lookup("#a55");
 Button a65 = (Button) hbox.lookup("#a65");
 Button a75 = (Button) hbox.lookup("#a75");
 Button a85 = (Button) hbox.lookup("#a85");
 Button a95 = (Button) hbox.lookup("#a95");
 Button a105 = (Button) hbox.lookup("#a105");
 Button a115 = (Button) hbox.lookup("#a115");
 Button a125 = (Button) hbox.lookup("#a125");
 Button a135 = (Button) hbox.lookup("#a135");	
 
 ////////////G//////////////////////////////
 Button a19 = (Button) hbox.lookup("#a19");
 Button a26 = (Button) hbox.lookup("#a26");
 Button a36 = (Button) hbox.lookup("#a36");
 Button a46 = (Button) hbox.lookup("#a46");
 Button a56 = (Button) hbox.lookup("#a56");
 Button a66 = (Button) hbox.lookup("#a66");
 Button a76 = (Button) hbox.lookup("#a76");
 Button a86 = (Button) hbox.lookup("#a86");
 Button a96 = (Button) hbox.lookup("#a96");
 Button a106 = (Button) hbox.lookup("#a106");
 Button a116 = (Button) hbox.lookup("#a116");
 Button a126 = (Button) hbox.lookup("#a126");
 Button a136 = (Button) hbox.lookup("#a136");	
 
 ////////////////////H/////////////////////////
 Button a110 = (Button) hbox.lookup("#a110");
 Button a27 = (Button) hbox.lookup("#a27");
 Button a37 = (Button) hbox.lookup("#a37");
 Button a47 = (Button) hbox.lookup("#a47");
 Button a57 = (Button) hbox.lookup("#a57");
 Button a67 = (Button) hbox.lookup("#a67");
 Button a77 = (Button) hbox.lookup("#a77");
 Button a87 = (Button) hbox.lookup("#a87");
 Button a97 = (Button) hbox.lookup("#a97");
 Button a107 = (Button) hbox.lookup("#a107");
 Button a117 = (Button) hbox.lookup("#a117");
 Button a127 = (Button) hbox.lookup("#a127");
 Button a137 = (Button) hbox.lookup("#a137");	
 
 /////////////////I//////////////////////////
 Button a1100 = (Button) hbox.lookup("#a1100");
 Button a28 = (Button) hbox.lookup("#a28");
 Button a38 = (Button) hbox.lookup("#a38");
 Button a48 = (Button) hbox.lookup("#a48");
 Button a58 = (Button) hbox.lookup("#a58");
 Button a68 = (Button) hbox.lookup("#a68");
 Button a78 = (Button) hbox.lookup("#a78");
 Button a88 = (Button) hbox.lookup("#a88");
 Button a98 = (Button) hbox.lookup("#a98");
 Button a108 = (Button) hbox.lookup("#a108");
 Button a118 = (Button) hbox.lookup("#a118");
 Button a128 = (Button) hbox.lookup("#a128");
 Button a138 = (Button) hbox.lookup("#a138");
 
 //////////////J///////////////////////////////
 Button a1101 = (Button) hbox.lookup("#a1101");
 Button a29 = (Button) hbox.lookup("#a29");
 Button a39 = (Button) hbox.lookup("#a39");
 Button a49 = (Button) hbox.lookup("#a49");
 Button a59 = (Button) hbox.lookup("#a59");
 Button a69 = (Button) hbox.lookup("#a69");
 Button a79 = (Button) hbox.lookup("#a79");
 Button a89 = (Button) hbox.lookup("#a89");
 Button a99 = (Button) hbox.lookup("#a99");
 Button a109 = (Button) hbox.lookup("#a109");
 Button a119 = (Button) hbox.lookup("#a119");
 Button a129 = (Button) hbox.lookup("#a129");
 Button a139 = (Button) hbox.lookup("#a139");
 
 //////////////K///////////////////////////////
 Button a1102 = (Button) hbox.lookup("#a1102");
 Button a210 = (Button) hbox.lookup("#a210");
 Button a310 = (Button) hbox.lookup("#a310");
 Button a410 = (Button) hbox.lookup("#a410");
 Button a510 = (Button) hbox.lookup("#a510");
 Button a610 = (Button) hbox.lookup("#a610");
 Button a710 = (Button) hbox.lookup("#a710");
 Button a810 = (Button) hbox.lookup("#a810");
 Button a910 = (Button) hbox.lookup("#a910");
 Button a1010 = (Button) hbox.lookup("#a1010");
 Button a1110 = (Button) hbox.lookup("#a1110");
 Button a1210 = (Button) hbox.lookup("#a1210");
 Button a1310 = (Button) hbox.lookup("#a1310");
 
 //////////////L///////////////////////////////
 Button a1103 = (Button) hbox.lookup("#a1103");
 Button a211 = (Button) hbox.lookup("#a211");
 Button a311 = (Button) hbox.lookup("#a311");
 Button a411 = (Button) hbox.lookup("#a411");
 Button a511 = (Button) hbox.lookup("#a511");
 Button a611 = (Button) hbox.lookup("#a611");
 Button a711 = (Button) hbox.lookup("#a711");
 Button a811 = (Button) hbox.lookup("#a811");
 Button a911 = (Button) hbox.lookup("#a911");
 Button a1011 = (Button) hbox.lookup("#a1011");
 Button a1111 = (Button) hbox.lookup("#a1111");
 Button a1211 = (Button) hbox.lookup("#a1211");
 Button a1311 = (Button) hbox.lookup("#a1311");	     
 
 ////////////////A/////////////////////////
 a1.setOnAction(even -> 
 {
	 seatFirst2 = "A1";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a2.setOnAction(even -> 
 {
	 seatFirst2 = "A2";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a3.setOnAction(even -> 
 {
	 seatFirst2 = "A3";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a4.setOnAction(even -> 
 {
	 seatFirst2 = "A4";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a5.setOnAction(even -> 
 {
	 seatFirst2 = "A5";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a6.setOnAction(even -> 
 {
	 seatFirst2 = "A6";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a7.setOnAction(even -> 
 {
	 seatFirst2 = "A7";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a8.setOnAction(even -> 
 {
	 seatFirst2 = "A8";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a9.setOnAction(even -> 
 {
	 seatFirst2 = "A9";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a10.setOnAction(even -> 
 {
	 seatFirst2 = "A10";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a11.setOnAction(even -> 
 {
	 seatFirst2 = "A11";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a12.setOnAction(even -> 
 {
	 seatFirst2 = "A12";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a13.setOnAction(even -> 
 {
	 seatFirst2 = "A13";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });

 
 //////////////////////B////////////////////////
 a14.setOnAction(even -> 
 {
	 seatFirst2 = "B1";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a21.setOnAction(even -> 
 {
	 seatFirst2 = "B2";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a31.setOnAction(even -> 
 {
	 seatFirst2 = "B3";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a41.setOnAction(even -> 
 {
	 seatFirst2 = "B4";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a51.setOnAction(even -> 
 {
	 seatFirst2 = "B5";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a61.setOnAction(even -> 
 {
	 seatFirst2 = "B6";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a71.setOnAction(even -> 
 {
	 seatFirst2 = "B7";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a81.setOnAction(even -> 
 {
	 seatFirst2 = "B8";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a91.setOnAction(even -> 
 {
	 seatFirst2 = "B9";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a101.setOnAction(even -> 
 {
	 seatFirst2 = "B10";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a111.setOnAction(even -> 
 {
	 seatFirst2 = "B11";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a121.setOnAction(even -> 
 {
	 seatFirst2 = "B12";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a131.setOnAction(even -> 
 {
	 seatFirst2 = "B13";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 ////////////////C///////////////////////
 a15.setOnAction(even -> 
 {
	 seatFirst2 = "C1";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a22.setOnAction(even -> 
 {
	 seatFirst2 = "C2";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a32.setOnAction(even -> 
 {
	 seatFirst2 = "C3";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a42.setOnAction(even -> 
 {
	 seatFirst2 = "C4";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a52.setOnAction(even -> 
 {
	 seatFirst2 = "C5";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a62.setOnAction(even -> 
 {
	 seatFirst2 = "C6";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a72.setOnAction(even -> 
 {
	 seatFirst2 = "C7";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a82.setOnAction(even -> 
 {
	 seatFirst2 = "C8";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a92.setOnAction(even -> 
 {
	 seatFirst2 = "C9";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a102.setOnAction(even -> 
 {
	 seatFirst2 = "C10";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a112.setOnAction(even -> 
 {
	 seatFirst2 = "C11";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a122.setOnAction(even -> 
 {
	 seatFirst2 = "C12";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a132.setOnAction(even -> 
 {
	 seatFirst2 = "C13";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 
 
 /////D///////////////////
 
 a16.setOnAction(even -> 
 {
	 seatFirst2 = "D1";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a23.setOnAction(even -> 
 {
	 seatFirst2 = "D2";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a33.setOnAction(even -> 
 {
	 seatFirst2 = "D3";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a43.setOnAction(even -> 
 {
	 seatFirst2 = "D4";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a53.setOnAction(even -> 
 {
	 seatFirst2 = "D5";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a63.setOnAction(even -> 
 {
	 seatFirst2 = "D6";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a73.setOnAction(even -> 
 {
	 seatFirst2 = "D7";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a83.setOnAction(even -> 
 {
	 seatFirst2 = "D8";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a93.setOnAction(even -> 
 {
	 seatFirst2 = "D9";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a103.setOnAction(even -> 
 {
	 seatFirst2 = "D10";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a113.setOnAction(even -> 
 {
	 seatFirst2 = "D11";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a123.setOnAction(even -> 
 {
	 seatFirst2 = "D12";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a133.setOnAction(even -> 
 {
	 seatFirst2 = "D13";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 
 a17.setOnAction(even -> 
 {
	 seatFirst2 = "E1";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a24.setOnAction(even -> 
 {
	 seatFirst2 = "E2";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a34.setOnAction(even -> 
 {
	 seatFirst2 = "E3";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a44.setOnAction(even -> 
 {
	 seatFirst2 = "E4";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a54.setOnAction(even -> 
 {
	 seatFirst2 = "E5";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a64.setOnAction(even -> 
 {
	 seatFirst2 = "E6";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a74.setOnAction(even -> 
 {
	 seatFirst2 = "E7";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a84.setOnAction(even -> 
 {
	 seatFirst2 = "E8";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a94.setOnAction(even -> 
 {
	 seatFirst2 = "E9";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a104.setOnAction(even -> 
 {
	 seatFirst2 = "E10";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a114.setOnAction(even -> 
 {
	 seatFirst2 = "E11";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a124.setOnAction(even -> 
 {
	 seatFirst2 = "E12";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a134.setOnAction(even -> 
 {
	 seatFirst2 = "E13";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 
 
 //////////F//////////////
 a18.setOnAction(even -> 
 {
	 seatFirst2 = "F1";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a25.setOnAction(even -> 
 {
	 seatFirst2 = "F2";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a35.setOnAction(even -> 
 {
	 seatFirst2 = "F3";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a45.setOnAction(even -> 
 {
	 seatFirst2 = "F4";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a55.setOnAction(even -> 
 {
	 seatFirst2 = "F5";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a65.setOnAction(even -> 
 {
	 seatFirst2 = "F6";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a75.setOnAction(even -> 
 {
	 seatFirst2 = "F7";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a85.setOnAction(even -> 
 {
	 seatFirst2 = "F8";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a95.setOnAction(even -> 
 {
	 seatFirst2 = "F9";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a105.setOnAction(even -> 
 {
	 seatFirst2 = "F10";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a115.setOnAction(even -> 
 {
	 seatFirst2 = "F11";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a125.setOnAction(even -> 
 {
	 seatFirst2 = "F12";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a135.setOnAction(even -> 
 {
	 seatFirst2 = "F13";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 
 
 ///////////G/////////////
 a19.setOnAction(even -> 
 {
	 seatFirst2 = "G1";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a26.setOnAction(even -> 
 {
	 seatFirst2 = "G2";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a36.setOnAction(even -> 
 {
	 seatFirst2 = "G3";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a46.setOnAction(even -> 
 {
	 seatFirst2 = "G4";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a56.setOnAction(even -> 
 {
	 seatFirst2 = "G5";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a66.setOnAction(even -> 
 {
	 seatFirst2 = "G6";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a76.setOnAction(even -> 
 {
	 seatFirst2 = "G7";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a86.setOnAction(even -> 
 {
	 seatFirst2 = "G8";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a96.setOnAction(even -> 
 {
	 seatFirst2 = "G9";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a106.setOnAction(even -> 
 {
	 seatFirst2 = "G10";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a116.setOnAction(even -> 
 {
	 seatFirst2 = "G11";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a126.setOnAction(even -> 
 {
	 seatFirst2 = "G12";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a136.setOnAction(even -> 
 {
	 seatFirst2 = "G13";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 
 
 //////////H////////////////////////////
 a110.setOnAction(even -> 
 {
	 seatFirst2 = "H1";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a27.setOnAction(even -> 
 {
	 seatFirst2 = "H2";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a37.setOnAction(even -> 
 {
	 seatFirst2 = "H3";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a47.setOnAction(even -> 
 {
	 seatFirst2 = "H4";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a57.setOnAction(even -> 
 {
	 seatFirst2 = "H5";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a67.setOnAction(even -> 
 {
	 seatFirst2 = "H6";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a77.setOnAction(even -> 
 {
	 seatFirst2 = "H7";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a87.setOnAction(even -> 
 {
	 seatFirst2 = "H8";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a97.setOnAction(even -> 
 {
	 seatFirst2 = "H9";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a107.setOnAction(even -> 
 {
	 seatFirst2 = "H10";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a117.setOnAction(even -> 
 {
	 seatFirst2 = "H11";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a127.setOnAction(even -> 
 {
	 seatFirst2 = "H12";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a137.setOnAction(even -> 
 {
	 seatFirst2 = "H13";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 
 
 ////////////I////////////////////
 a1100.setOnAction(even -> 
 {
	 seatFirst2 = "I1";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a28.setOnAction(even -> 
 {
	 seatFirst2 = "I2";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a38.setOnAction(even -> 
 {
	 seatFirst2 = "I3";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a48.setOnAction(even -> 
 {
	 seatFirst2 = "I4";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a58.setOnAction(even -> 
 {
	 seatFirst2 = "I5";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a68.setOnAction(even -> 
 {
	 seatFirst2 = "I6";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a78.setOnAction(even -> 
 {
	 seatFirst2 = "I7";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a88.setOnAction(even -> 
 {
	 seatFirst2 = "I8";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a98.setOnAction(even -> 
 {
	 seatFirst2 = "I9";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a108.setOnAction(even -> 
 {
	 seatFirst2 = "I10";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a118.setOnAction(even -> 
 {
	 seatFirst2 = "I11";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a128.setOnAction(even -> 
 {
	 seatFirst2 = "I12";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a138.setOnAction(even -> 
 {
	 seatFirst2 = "I13";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 
 ///////////////J/////////////////
 a1101.setOnAction(even -> 
 {
	 seatFirst2 = "J1";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a29.setOnAction(even -> 
 {
	 seatFirst2 = "J2";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a39.setOnAction(even -> 
 {
	 seatFirst2 = "J3";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a49.setOnAction(even -> 
 {
	 seatFirst2 = "J4";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a59.setOnAction(even -> 
 {
	 seatFirst2 = "J5";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a69.setOnAction(even -> 
 {
	 seatFirst2 = "J6";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a79.setOnAction(even -> 
 {
	 seatFirst2 = "J7";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a89.setOnAction(even -> 
 {
	 seatFirst2 = "J8";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a99.setOnAction(even -> 
 {
	 seatFirst2 = "J9";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a109.setOnAction(even -> 
 {
	 seatFirst2 = "J10";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a119.setOnAction(even -> 
 {
	 seatFirst2 = "J11";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a129.setOnAction(even -> 
 {
	 seatFirst2 = "J12";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a139.setOnAction(even -> 
 {
	 seatFirst2 = "J13";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 
 
 
 /////////////////K////////////////
 a1102.setOnAction(even -> 
 {
	 seatFirst2 = "K1";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a210.setOnAction(even -> 
 {
	 seatFirst2 = "K2";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a310.setOnAction(even -> 
 {
	 seatFirst2 = "K3";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a410.setOnAction(even -> 
 {
	 seatFirst2 = "K4";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a510.setOnAction(even -> 
 {
	 seatFirst2 = "K5";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a610.setOnAction(even -> 
 {
	 seatFirst2 = "K6";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a710.setOnAction(even -> 
 {
	 seatFirst2 = "K7";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a810.setOnAction(even -> 
 {
	 seatFirst2 = "K8";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a910.setOnAction(even -> 
 {
	 seatFirst2 = "K9";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a1010.setOnAction(even -> 
 {
	 seatFirst2 = "K10";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a1110.setOnAction(even -> 
 {
	 seatFirst2 = "K11";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a1210.setOnAction(even -> 
 {
	 seatFirst2 = "K12";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a1310.setOnAction(even -> 
 {
	 seatFirst2 = "K13";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 
 
 
 a1103.setOnAction(even -> 
 {
	 seatFirst2 = "L1";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a211.setOnAction(even -> 
 {
	 seatFirst2 = "L2";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a311.setOnAction(even -> 
 {
	 seatFirst2 = "L3";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a411.setOnAction(even -> 
 {
	 seatFirst2 = "L4";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a511.setOnAction(even -> 
 {
	 seatFirst2 = "L5";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a611.setOnAction(even -> 
 {
	 seatFirst2 = "L6";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a711.setOnAction(even -> 
 {
	 seatFirst2 = "L7";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a811.setOnAction(even -> 
 {
	 seatFirst2 = "L8";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a911.setOnAction(even -> 
 {
	 seatFirst2 = "L9";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a1011.setOnAction(even -> 
 {
	 seatFirst2 = "L10";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a1111.setOnAction(even -> 
 {
	 seatFirst2 = "L11";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a1211.setOnAction(even -> 
 {
	 seatFirst2 = "L12";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 a1311.setOnAction(even -> 
 {
	 seatFirst2 = "L13";
	 txtSeat2.setText(seatFirst2);
	 popup.hide();
 });
 
 
/////////////////////////////////////////////  
 
 
 
 
 
	}
	@FXML
	void seat3PA(ActionEvent event) throws Exception {
		Popup popup = new Popup();
		AnchorPane hbox = (AnchorPane) FXMLLoader.load(getClass().getResource("popup.fxml"));
		
		popup.getContent().add(hbox);
        popup.setAutoHide(true);
		popup.show(primaryStage);
		
////////////////A//////////////
 Button a1 = (Button) hbox.lookup("#a1");
 Button a2 = (Button) hbox.lookup("#a2");
 Button a3 = (Button) hbox.lookup("#a3");
 Button a4 = (Button) hbox.lookup("#a4");
 Button a5 = (Button) hbox.lookup("#a5");
 Button a6 = (Button) hbox.lookup("#a6");
 Button a7 = (Button) hbox.lookup("#a7");
 Button a8 = (Button) hbox.lookup("#a8");
 Button a9 = (Button) hbox.lookup("#a9");
 Button a10 = (Button) hbox.lookup("#a10");
 Button a11  =(Button) hbox.lookup("#a11");
 Button a12 = (Button) hbox.lookup("#a12");
 Button a13 = (Button) hbox.lookup("#a13");	  
 
 /////////////////B////////////////////
 Button a14 = (Button) hbox.lookup("#a14");
 Button a21 = (Button) hbox.lookup("#a21");
 Button a31 = (Button) hbox.lookup("#a31");
 Button a41 = (Button) hbox.lookup("#a41");
 Button a51 = (Button) hbox.lookup("#a51");
 Button a61 = (Button) hbox.lookup("#a61");
 Button a71 = (Button) hbox.lookup("#a71");
 Button a81 = (Button) hbox.lookup("#a81");
 Button a91 = (Button) hbox.lookup("#a91");
 Button a101 = (Button) hbox.lookup("#a101");
 Button a111 = (Button) hbox.lookup("#a111");
 Button a121 = (Button) hbox.lookup("#a121");
 Button a131 = (Button) hbox.lookup("#a131");	
 
 //////////////C///////////////////////////
 Button a15 = (Button) hbox.lookup("#a15");
 Button a22 = (Button) hbox.lookup("#a22");
 Button a32 = (Button) hbox.lookup("#a32");
 Button a42 = (Button) hbox.lookup("#a42");
 Button a52 = (Button) hbox.lookup("#a52");
 Button a62 = (Button) hbox.lookup("#a62");
 Button a72 = (Button) hbox.lookup("#a72");
 Button a82 = (Button) hbox.lookup("#a82");
 Button a92 = (Button) hbox.lookup("#a92");
 Button a102 = (Button) hbox.lookup("#a102");
 Button a112 = (Button) hbox.lookup("#a112");
 Button a122 = (Button) hbox.lookup("#a122");
 Button a132 = (Button) hbox.lookup("#a132");	
 
 //////////////////D////////////////////////
 Button a16 = (Button) hbox.lookup("#a16");
 Button a23 = (Button) hbox.lookup("#a23");
 Button a33 = (Button) hbox.lookup("#a33");
 Button a43 = (Button) hbox.lookup("#a43");
 Button a53 = (Button) hbox.lookup("#a53");
 Button a63 = (Button) hbox.lookup("#a63");
 Button a73 = (Button) hbox.lookup("#a73");
 Button a83 = (Button) hbox.lookup("#a83");
 Button a93 = (Button) hbox.lookup("#a93");
 Button a103 = (Button) hbox.lookup("#a103");
 Button a113 = (Button) hbox.lookup("#a113");
 Button a123 = (Button) hbox.lookup("#a123");
 Button a133 = (Button) hbox.lookup("#a133");	
 
 //////////////E////////////////////////////
 Button a17 = (Button) hbox.lookup("#a17");
 Button a24 = (Button) hbox.lookup("#a24");
 Button a34 = (Button) hbox.lookup("#a34");
 Button a44 = (Button) hbox.lookup("#a44");
 Button a54 = (Button) hbox.lookup("#a54");
 Button a64 = (Button) hbox.lookup("#a64");
 Button a74 = (Button) hbox.lookup("#a74");
 Button a84 = (Button) hbox.lookup("#a84");
 Button a94 = (Button) hbox.lookup("#a94");
 Button a104 = (Button) hbox.lookup("#a104");
 Button a114 = (Button) hbox.lookup("#a114");
 Button a124 = (Button) hbox.lookup("#a124");
 Button a134 = (Button) hbox.lookup("#a134");	
 
 ////////////F///////////////////////////////
 Button a18 = (Button) hbox.lookup("#a18");
 Button a25 = (Button) hbox.lookup("#a25");
 Button a35 = (Button) hbox.lookup("#a35");
 Button a45 = (Button) hbox.lookup("#a45");
 Button a55 = (Button) hbox.lookup("#a55");
 Button a65 = (Button) hbox.lookup("#a65");
 Button a75 = (Button) hbox.lookup("#a75");
 Button a85 = (Button) hbox.lookup("#a85");
 Button a95 = (Button) hbox.lookup("#a95");
 Button a105 = (Button) hbox.lookup("#a105");
 Button a115 = (Button) hbox.lookup("#a115");
 Button a125 = (Button) hbox.lookup("#a125");
 Button a135 = (Button) hbox.lookup("#a135");	
 
 ////////////G//////////////////////////////
 Button a19 = (Button) hbox.lookup("#a19");
 Button a26 = (Button) hbox.lookup("#a26");
 Button a36 = (Button) hbox.lookup("#a36");
 Button a46 = (Button) hbox.lookup("#a46");
 Button a56 = (Button) hbox.lookup("#a56");
 Button a66 = (Button) hbox.lookup("#a66");
 Button a76 = (Button) hbox.lookup("#a76");
 Button a86 = (Button) hbox.lookup("#a86");
 Button a96 = (Button) hbox.lookup("#a96");
 Button a106 = (Button) hbox.lookup("#a106");
 Button a116 = (Button) hbox.lookup("#a116");
 Button a126 = (Button) hbox.lookup("#a126");
 Button a136 = (Button) hbox.lookup("#a136");	
 
 ////////////////////H/////////////////////////
 Button a110 = (Button) hbox.lookup("#a110");
 Button a27 = (Button) hbox.lookup("#a27");
 Button a37 = (Button) hbox.lookup("#a37");
 Button a47 = (Button) hbox.lookup("#a47");
 Button a57 = (Button) hbox.lookup("#a57");
 Button a67 = (Button) hbox.lookup("#a67");
 Button a77 = (Button) hbox.lookup("#a77");
 Button a87 = (Button) hbox.lookup("#a87");
 Button a97 = (Button) hbox.lookup("#a97");
 Button a107 = (Button) hbox.lookup("#a107");
 Button a117 = (Button) hbox.lookup("#a117");
 Button a127 = (Button) hbox.lookup("#a127");
 Button a137 = (Button) hbox.lookup("#a137");	
 
 /////////////////I//////////////////////////
 Button a1100 = (Button) hbox.lookup("#a1100");
 Button a28 = (Button) hbox.lookup("#a28");
 Button a38 = (Button) hbox.lookup("#a38");
 Button a48 = (Button) hbox.lookup("#a48");
 Button a58 = (Button) hbox.lookup("#a58");
 Button a68 = (Button) hbox.lookup("#a68");
 Button a78 = (Button) hbox.lookup("#a78");
 Button a88 = (Button) hbox.lookup("#a88");
 Button a98 = (Button) hbox.lookup("#a98");
 Button a108 = (Button) hbox.lookup("#a108");
 Button a118 = (Button) hbox.lookup("#a118");
 Button a128 = (Button) hbox.lookup("#a128");
 Button a138 = (Button) hbox.lookup("#a138");
 
 //////////////J///////////////////////////////
 Button a1101 = (Button) hbox.lookup("#a1101");
 Button a29 = (Button) hbox.lookup("#a29");
 Button a39 = (Button) hbox.lookup("#a39");
 Button a49 = (Button) hbox.lookup("#a49");
 Button a59 = (Button) hbox.lookup("#a59");
 Button a69 = (Button) hbox.lookup("#a69");
 Button a79 = (Button) hbox.lookup("#a79");
 Button a89 = (Button) hbox.lookup("#a89");
 Button a99 = (Button) hbox.lookup("#a99");
 Button a109 = (Button) hbox.lookup("#a109");
 Button a119 = (Button) hbox.lookup("#a119");
 Button a129 = (Button) hbox.lookup("#a129");
 Button a139 = (Button) hbox.lookup("#a139");
 
 //////////////K///////////////////////////////
 Button a1102 = (Button) hbox.lookup("#a1102");
 Button a210 = (Button) hbox.lookup("#a210");
 Button a310 = (Button) hbox.lookup("#a310");
 Button a410 = (Button) hbox.lookup("#a410");
 Button a510 = (Button) hbox.lookup("#a510");
 Button a610 = (Button) hbox.lookup("#a610");
 Button a710 = (Button) hbox.lookup("#a710");
 Button a810 = (Button) hbox.lookup("#a810");
 Button a910 = (Button) hbox.lookup("#a910");
 Button a1010 = (Button) hbox.lookup("#a1010");
 Button a1110 = (Button) hbox.lookup("#a1110");
 Button a1210 = (Button) hbox.lookup("#a1210");
 Button a1310 = (Button) hbox.lookup("#a1310");
 
 //////////////L///////////////////////////////
 Button a1103 = (Button) hbox.lookup("#a1103");
 Button a211 = (Button) hbox.lookup("#a211");
 Button a311 = (Button) hbox.lookup("#a311");
 Button a411 = (Button) hbox.lookup("#a411");
 Button a511 = (Button) hbox.lookup("#a511");
 Button a611 = (Button) hbox.lookup("#a611");
 Button a711 = (Button) hbox.lookup("#a711");
 Button a811 = (Button) hbox.lookup("#a811");
 Button a911 = (Button) hbox.lookup("#a911");
 Button a1011 = (Button) hbox.lookup("#a1011");
 Button a1111 = (Button) hbox.lookup("#a1111");
 Button a1211 = (Button) hbox.lookup("#a1211");
 Button a1311 = (Button) hbox.lookup("#a1311");	     
 
 ////////////////A/////////////////////////
 a1.setOnAction(even -> 
 {
	 seatFirst3 = "A1";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a2.setOnAction(even -> 
 {
	 seatFirst3 = "A2";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a3.setOnAction(even -> 
 {
	 seatFirst3 = "A3";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a4.setOnAction(even -> 
 {
	 seatFirst3 = "A4";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a5.setOnAction(even -> 
 {
	 seatFirst3 = "A5";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a6.setOnAction(even -> 
 {
	 seatFirst3 = "A6";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a7.setOnAction(even -> 
 {
	 seatFirst3 = "A7";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a8.setOnAction(even -> 
 {
	 seatFirst3 = "A8";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a9.setOnAction(even -> 
 {
	 seatFirst3 = "A9";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a10.setOnAction(even -> 
 {
	 seatFirst3 = "A10";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a11.setOnAction(even -> 
 {
	 seatFirst3 = "A11";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a12.setOnAction(even -> 
 {
	 seatFirst3 = "A12";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a13.setOnAction(even -> 
 {
	 seatFirst3 = "A13";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });

 
 //////////////////////B////////////////////////
 a14.setOnAction(even -> 
 {
	 seatFirst3 = "B1";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a21.setOnAction(even -> 
 {
	 seatFirst3 = "B2";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a31.setOnAction(even -> 
 {
	 seatFirst3 = "B3";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a41.setOnAction(even -> 
 {
	 seatFirst3 = "B4";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a51.setOnAction(even -> 
 {
	 seatFirst3 = "B5";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a61.setOnAction(even -> 
 {
	 seatFirst3 = "B6";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a71.setOnAction(even -> 
 {
	 seatFirst3 = "B7";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a81.setOnAction(even -> 
 {
	 seatFirst3 = "B8";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a91.setOnAction(even -> 
 {
	 seatFirst3 = "B9";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a101.setOnAction(even -> 
 {
	 seatFirst3 = "B10";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a111.setOnAction(even -> 
 {
	 seatFirst3 = "B11";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a121.setOnAction(even -> 
 {
	 seatFirst3 = "B12";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a131.setOnAction(even -> 
 {
	 seatFirst3 = "B13";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 ////////////////C///////////////////////
 a15.setOnAction(even -> 
 {
	 seatFirst3 = "C1";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a22.setOnAction(even -> 
 {
	 seatFirst3 = "C2";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a32.setOnAction(even -> 
 {
	 seatFirst3 = "C3";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a42.setOnAction(even -> 
 {
	 seatFirst3 = "C4";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a52.setOnAction(even -> 
 {
	 seatFirst3 = "C5";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a62.setOnAction(even -> 
 {
	 seatFirst3 = "C6";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a72.setOnAction(even -> 
 {
	 seatFirst3 = "C7";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a82.setOnAction(even -> 
 {
	 seatFirst3 = "C8";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a92.setOnAction(even -> 
 {
	 seatFirst3 = "C9";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a102.setOnAction(even -> 
 {
	 seatFirst3 = "C10";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a112.setOnAction(even -> 
 {
	 seatFirst3 = "C11";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a122.setOnAction(even -> 
 {
	 seatFirst3 = "C12";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a132.setOnAction(even -> 
 {
	 seatFirst3 = "C13";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 
 
 /////D///////////////////
 
 a16.setOnAction(even -> 
 {
	 seatFirst3 = "D1";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a23.setOnAction(even -> 
 {
	 seatFirst3 = "D2";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a33.setOnAction(even -> 
 {
	 seatFirst3 = "D3";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a43.setOnAction(even -> 
 {
	 seatFirst3 = "D4";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a53.setOnAction(even -> 
 {
	 seatFirst3 = "D5";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a63.setOnAction(even -> 
 {
	 seatFirst3 = "D6";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a73.setOnAction(even -> 
 {
	 seatFirst3 = "D7";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a83.setOnAction(even -> 
 {
	 seatFirst3 = "D8";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a93.setOnAction(even -> 
 {
	 seatFirst3 = "D9";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a103.setOnAction(even -> 
 {
	 seatFirst3 = "D10";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a113.setOnAction(even -> 
 {
	 seatFirst3 = "D11";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a123.setOnAction(even -> 
 {
	 seatFirst3 = "D12";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a133.setOnAction(even -> 
 {
	 seatFirst3 = "D13";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 
 a17.setOnAction(even -> 
 {
	 seatFirst3 = "E1";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a24.setOnAction(even -> 
 {
	 seatFirst3 = "E2";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a34.setOnAction(even -> 
 {
	 seatFirst3 = "E3";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a44.setOnAction(even -> 
 {
	 seatFirst3 = "E4";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a54.setOnAction(even -> 
 {
	 seatFirst3 = "E5";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a64.setOnAction(even -> 
 {
	 seatFirst3 = "E6";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a74.setOnAction(even -> 
 {
	 seatFirst3 = "E7";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a84.setOnAction(even -> 
 {
	 seatFirst3 = "E8";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a94.setOnAction(even -> 
 {
	 seatFirst3 = "E9";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a104.setOnAction(even -> 
 {
	 seatFirst3 = "E10";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a114.setOnAction(even -> 
 {
	 seatFirst3 = "E11";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a124.setOnAction(even -> 
 {
	 seatFirst3 = "E12";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a134.setOnAction(even -> 
 {
	 seatFirst3 = "E13";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 
 
 //////////F//////////////
 a18.setOnAction(even -> 
 {
	 seatFirst3 = "F1";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a25.setOnAction(even -> 
 {
	 seatFirst3 = "F2";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a35.setOnAction(even -> 
 {
	 seatFirst3 = "F3";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a45.setOnAction(even -> 
 {
	 seatFirst3 = "F4";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a55.setOnAction(even -> 
 {
	 seatFirst3 = "F5";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a65.setOnAction(even -> 
 {
	 seatFirst3 = "F6";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a75.setOnAction(even -> 
 {
	 seatFirst3 = "F7";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a85.setOnAction(even -> 
 {
	 seatFirst3 = "F8";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a95.setOnAction(even -> 
 {
	 seatFirst3 = "F9";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a105.setOnAction(even -> 
 {
	 seatFirst3 = "F10";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a115.setOnAction(even -> 
 {
	 seatFirst3 = "F11";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a125.setOnAction(even -> 
 {
	 seatFirst3 = "F12";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a135.setOnAction(even -> 
 {
	 seatFirst3 = "F13";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 
 
 ///////////G/////////////
 a19.setOnAction(even -> 
 {
	 seatFirst3 = "G1";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a26.setOnAction(even -> 
 {
	 seatFirst3 = "G2";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a36.setOnAction(even -> 
 {
	 seatFirst3 = "G3";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a46.setOnAction(even -> 
 {
	 seatFirst3 = "G4";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a56.setOnAction(even -> 
 {
	 seatFirst3 = "G5";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a66.setOnAction(even -> 
 {
	 seatFirst3 = "G6";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a76.setOnAction(even -> 
 {
	 seatFirst3 = "G7";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a86.setOnAction(even -> 
 {
	 seatFirst3 = "G8";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a96.setOnAction(even -> 
 {
	 seatFirst3 = "G9";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a106.setOnAction(even -> 
 {
	 seatFirst3 = "G10";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a116.setOnAction(even -> 
 {
	 seatFirst3 = "G11";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a126.setOnAction(even -> 
 {
	 seatFirst3 = "G12";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a136.setOnAction(even -> 
 {
	 seatFirst3 = "G13";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 
 
 //////////H////////////////////////////
 a110.setOnAction(even -> 
 {
	 seatFirst3 = "H1";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a27.setOnAction(even -> 
 {
	 seatFirst3 = "H2";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a37.setOnAction(even -> 
 {
	 seatFirst3 = "H3";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a47.setOnAction(even -> 
 {
	 seatFirst3 = "H4";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a57.setOnAction(even -> 
 {
	 seatFirst3 = "H5";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a67.setOnAction(even -> 
 {
	 seatFirst3 = "H6";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a77.setOnAction(even -> 
 {
	 seatFirst3 = "H7";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a87.setOnAction(even -> 
 {
	 seatFirst3 = "H8";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a97.setOnAction(even -> 
 {
	 seatFirst3 = "H9";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a107.setOnAction(even -> 
 {
	 seatFirst3 = "H10";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a117.setOnAction(even -> 
 {
	 seatFirst3 = "H11";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a127.setOnAction(even -> 
 {
	 seatFirst3 = "H12";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a137.setOnAction(even -> 
 {
	 seatFirst3 = "H13";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 
 
 ////////////I////////////////////
 a1100.setOnAction(even -> 
 {
	 seatFirst3 = "I1";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a28.setOnAction(even -> 
 {
	 seatFirst3 = "I2";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a38.setOnAction(even -> 
 {
	 seatFirst3 = "I3";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a48.setOnAction(even -> 
 {
	 seatFirst3 = "I4";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a58.setOnAction(even -> 
 {
	 seatFirst3 = "I5";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a68.setOnAction(even -> 
 {
	 seatFirst3 = "I6";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a78.setOnAction(even -> 
 {
	 seatFirst3 = "I7";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a88.setOnAction(even -> 
 {
	 seatFirst3 = "I8";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a98.setOnAction(even -> 
 {
	 seatFirst3 = "I9";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a108.setOnAction(even -> 
 {
	 seatFirst3 = "I10";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a118.setOnAction(even -> 
 {
	 seatFirst3 = "I11";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a128.setOnAction(even -> 
 {
	 seatFirst3 = "I12";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a138.setOnAction(even -> 
 {
	 seatFirst3 = "I13";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 
 ///////////////J/////////////////
 a1101.setOnAction(even -> 
 {
	 seatFirst3 = "J1";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a29.setOnAction(even -> 
 {
	 seatFirst3 = "J2";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a39.setOnAction(even -> 
 {
	 seatFirst3 = "J3";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a49.setOnAction(even -> 
 {
	 seatFirst3 = "J4";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a59.setOnAction(even -> 
 {
	 seatFirst3 = "J5";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a69.setOnAction(even -> 
 {
	 seatFirst3 = "J6";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a79.setOnAction(even -> 
 {
	 seatFirst3 = "J7";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a89.setOnAction(even -> 
 {
	 seatFirst3 = "J8";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a99.setOnAction(even -> 
 {
	 seatFirst3 = "J9";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a109.setOnAction(even -> 
 {
	 seatFirst3 = "J10";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a119.setOnAction(even -> 
 {
	 seatFirst3 = "J11";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a129.setOnAction(even -> 
 {
	 seatFirst3 = "J12";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a139.setOnAction(even -> 
 {
	 seatFirst3 = "J13";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 
 
 
 /////////////////K////////////////
 a1102.setOnAction(even -> 
 {
	 seatFirst3 = "K1";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a210.setOnAction(even -> 
 {
	 seatFirst3 = "K2";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a310.setOnAction(even -> 
 {
	 seatFirst3 = "K3";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a410.setOnAction(even -> 
 {
	 seatFirst3 = "K4";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a510.setOnAction(even -> 
 {
	 seatFirst3 = "K5";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a610.setOnAction(even -> 
 {
	 seatFirst3 = "K6";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a710.setOnAction(even -> 
 {
	 seatFirst3 = "K7";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a810.setOnAction(even -> 
 {
	 seatFirst3 = "K8";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a910.setOnAction(even -> 
 {
	 seatFirst3 = "K9";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a1010.setOnAction(even -> 
 {
	 seatFirst3 = "K10";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a1110.setOnAction(even -> 
 {
	 seatFirst3 = "K11";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a1210.setOnAction(even -> 
 {
	 seatFirst3 = "K12";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a1310.setOnAction(even -> 
 {
	 seatFirst3 = "K13";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 
 
 
 a1103.setOnAction(even -> 
 {
	 seatFirst3 = "L1";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a211.setOnAction(even -> 
 {
	 seatFirst3 = "L2";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a311.setOnAction(even -> 
 {
	 seatFirst3 = "L3";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a411.setOnAction(even -> 
 {
	 seatFirst3 = "L4";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a511.setOnAction(even -> 
 {
	 seatFirst3 = "L5";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a611.setOnAction(even -> 
 {
	 seatFirst3 = "L6";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a711.setOnAction(even -> 
 {
	 seatFirst3 = "L7";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a811.setOnAction(even -> 
 {
	 seatFirst3 = "L8";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a911.setOnAction(even -> 
 {
	 seatFirst3 = "L9";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a1011.setOnAction(even -> 
 {
	 seatFirst3 = "L10";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a1111.setOnAction(even -> 
 {
	 seatFirst3 = "L11";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a1211.setOnAction(even -> 
 {
	 seatFirst3 = "L12";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 a1311.setOnAction(even -> 
 {
	 seatFirst3 = "L13";
	 txtSeat3.setText(seatFirst3);
	 popup.hide();
 });
 
 
/////////////////////////////////////////////  
 
 
 
	}
	@FXML
	void seat4PA(ActionEvent event) throws Exception {
		Popup popup = new Popup();
		AnchorPane hbox = (AnchorPane) FXMLLoader.load(getClass().getResource("popup.fxml"));
		
		popup.getContent().add(hbox);
        popup.setAutoHide(true);
		popup.show(primaryStage);
		
		
		////////////////A//////////////
	     Button a1 = (Button) hbox.lookup("#a1");
	     Button a2 = (Button) hbox.lookup("#a2");
	     Button a3 = (Button) hbox.lookup("#a3");
	     Button a4 = (Button) hbox.lookup("#a4");
	     Button a5 = (Button) hbox.lookup("#a5");
	     Button a6 = (Button) hbox.lookup("#a6");
	     Button a7 = (Button) hbox.lookup("#a7");
	     Button a8 = (Button) hbox.lookup("#a8");
	     Button a9 = (Button) hbox.lookup("#a9");
	     Button a10 = (Button) hbox.lookup("#a10");
	     Button a11  =(Button) hbox.lookup("#a11");
	     Button a12 = (Button) hbox.lookup("#a12");
	     Button a13 = (Button) hbox.lookup("#a13");	  
	     
	     /////////////////B////////////////////
	     Button a14 = (Button) hbox.lookup("#a14");
	     Button a21 = (Button) hbox.lookup("#a21");
	     Button a31 = (Button) hbox.lookup("#a31");
	     Button a41 = (Button) hbox.lookup("#a41");
	     Button a51 = (Button) hbox.lookup("#a51");
	     Button a61 = (Button) hbox.lookup("#a61");
	     Button a71 = (Button) hbox.lookup("#a71");
	     Button a81 = (Button) hbox.lookup("#a81");
	     Button a91 = (Button) hbox.lookup("#a91");
	     Button a101 = (Button) hbox.lookup("#a101");
	     Button a111 = (Button) hbox.lookup("#a111");
	     Button a121 = (Button) hbox.lookup("#a121");
	     Button a131 = (Button) hbox.lookup("#a131");	
	     
	     //////////////C///////////////////////////
	     Button a15 = (Button) hbox.lookup("#a15");
	     Button a22 = (Button) hbox.lookup("#a22");
	     Button a32 = (Button) hbox.lookup("#a32");
	     Button a42 = (Button) hbox.lookup("#a42");
	     Button a52 = (Button) hbox.lookup("#a52");
	     Button a62 = (Button) hbox.lookup("#a62");
	     Button a72 = (Button) hbox.lookup("#a72");
	     Button a82 = (Button) hbox.lookup("#a82");
	     Button a92 = (Button) hbox.lookup("#a92");
	     Button a102 = (Button) hbox.lookup("#a102");
	     Button a112 = (Button) hbox.lookup("#a112");
	     Button a122 = (Button) hbox.lookup("#a122");
	     Button a132 = (Button) hbox.lookup("#a132");	
	     
	     //////////////////D////////////////////////
	     Button a16 = (Button) hbox.lookup("#a16");
	     Button a23 = (Button) hbox.lookup("#a23");
	     Button a33 = (Button) hbox.lookup("#a33");
	     Button a43 = (Button) hbox.lookup("#a43");
	     Button a53 = (Button) hbox.lookup("#a53");
	     Button a63 = (Button) hbox.lookup("#a63");
	     Button a73 = (Button) hbox.lookup("#a73");
	     Button a83 = (Button) hbox.lookup("#a83");
	     Button a93 = (Button) hbox.lookup("#a93");
	     Button a103 = (Button) hbox.lookup("#a103");
	     Button a113 = (Button) hbox.lookup("#a113");
	     Button a123 = (Button) hbox.lookup("#a123");
	     Button a133 = (Button) hbox.lookup("#a133");	
	     
	     //////////////E////////////////////////////
	     Button a17 = (Button) hbox.lookup("#a17");
	     Button a24 = (Button) hbox.lookup("#a24");
	     Button a34 = (Button) hbox.lookup("#a34");
	     Button a44 = (Button) hbox.lookup("#a44");
	     Button a54 = (Button) hbox.lookup("#a54");
	     Button a64 = (Button) hbox.lookup("#a64");
	     Button a74 = (Button) hbox.lookup("#a74");
	     Button a84 = (Button) hbox.lookup("#a84");
	     Button a94 = (Button) hbox.lookup("#a94");
	     Button a104 = (Button) hbox.lookup("#a104");
	     Button a114 = (Button) hbox.lookup("#a114");
	     Button a124 = (Button) hbox.lookup("#a124");
	     Button a134 = (Button) hbox.lookup("#a134");	
	     
	     ////////////F///////////////////////////////
	     Button a18 = (Button) hbox.lookup("#a18");
	     Button a25 = (Button) hbox.lookup("#a25");
	     Button a35 = (Button) hbox.lookup("#a35");
	     Button a45 = (Button) hbox.lookup("#a45");
	     Button a55 = (Button) hbox.lookup("#a55");
	     Button a65 = (Button) hbox.lookup("#a65");
	     Button a75 = (Button) hbox.lookup("#a75");
	     Button a85 = (Button) hbox.lookup("#a85");
	     Button a95 = (Button) hbox.lookup("#a95");
	     Button a105 = (Button) hbox.lookup("#a105");
	     Button a115 = (Button) hbox.lookup("#a115");
	     Button a125 = (Button) hbox.lookup("#a125");
	     Button a135 = (Button) hbox.lookup("#a135");	
	     
	     ////////////G//////////////////////////////
	     Button a19 = (Button) hbox.lookup("#a19");
	     Button a26 = (Button) hbox.lookup("#a26");
	     Button a36 = (Button) hbox.lookup("#a36");
	     Button a46 = (Button) hbox.lookup("#a46");
	     Button a56 = (Button) hbox.lookup("#a56");
	     Button a66 = (Button) hbox.lookup("#a66");
	     Button a76 = (Button) hbox.lookup("#a76");
	     Button a86 = (Button) hbox.lookup("#a86");
	     Button a96 = (Button) hbox.lookup("#a96");
	     Button a106 = (Button) hbox.lookup("#a106");
	     Button a116 = (Button) hbox.lookup("#a116");
	     Button a126 = (Button) hbox.lookup("#a126");
	     Button a136 = (Button) hbox.lookup("#a136");	
	     
	     ////////////////////H/////////////////////////
	     Button a110 = (Button) hbox.lookup("#a110");
	     Button a27 = (Button) hbox.lookup("#a27");
	     Button a37 = (Button) hbox.lookup("#a37");
	     Button a47 = (Button) hbox.lookup("#a47");
	     Button a57 = (Button) hbox.lookup("#a57");
	     Button a67 = (Button) hbox.lookup("#a67");
	     Button a77 = (Button) hbox.lookup("#a77");
	     Button a87 = (Button) hbox.lookup("#a87");
	     Button a97 = (Button) hbox.lookup("#a97");
	     Button a107 = (Button) hbox.lookup("#a107");
	     Button a117 = (Button) hbox.lookup("#a117");
	     Button a127 = (Button) hbox.lookup("#a127");
	     Button a137 = (Button) hbox.lookup("#a137");	
	     
	     /////////////////I//////////////////////////
	     Button a1100 = (Button) hbox.lookup("#a1100");
	     Button a28 = (Button) hbox.lookup("#a28");
	     Button a38 = (Button) hbox.lookup("#a38");
	     Button a48 = (Button) hbox.lookup("#a48");
	     Button a58 = (Button) hbox.lookup("#a58");
	     Button a68 = (Button) hbox.lookup("#a68");
	     Button a78 = (Button) hbox.lookup("#a78");
	     Button a88 = (Button) hbox.lookup("#a88");
	     Button a98 = (Button) hbox.lookup("#a98");
	     Button a108 = (Button) hbox.lookup("#a108");
	     Button a118 = (Button) hbox.lookup("#a118");
	     Button a128 = (Button) hbox.lookup("#a128");
	     Button a138 = (Button) hbox.lookup("#a138");
	     
	     //////////////J///////////////////////////////
	     Button a1101 = (Button) hbox.lookup("#a1101");
	     Button a29 = (Button) hbox.lookup("#a29");
	     Button a39 = (Button) hbox.lookup("#a39");
	     Button a49 = (Button) hbox.lookup("#a49");
	     Button a59 = (Button) hbox.lookup("#a59");
	     Button a69 = (Button) hbox.lookup("#a69");
	     Button a79 = (Button) hbox.lookup("#a79");
	     Button a89 = (Button) hbox.lookup("#a89");
	     Button a99 = (Button) hbox.lookup("#a99");
	     Button a109 = (Button) hbox.lookup("#a109");
	     Button a119 = (Button) hbox.lookup("#a119");
	     Button a129 = (Button) hbox.lookup("#a129");
	     Button a139 = (Button) hbox.lookup("#a139");
	     
	     //////////////K///////////////////////////////
	     Button a1102 = (Button) hbox.lookup("#a1102");
	     Button a210 = (Button) hbox.lookup("#a210");
	     Button a310 = (Button) hbox.lookup("#a310");
	     Button a410 = (Button) hbox.lookup("#a410");
	     Button a510 = (Button) hbox.lookup("#a510");
	     Button a610 = (Button) hbox.lookup("#a610");
	     Button a710 = (Button) hbox.lookup("#a710");
	     Button a810 = (Button) hbox.lookup("#a810");
	     Button a910 = (Button) hbox.lookup("#a910");
	     Button a1010 = (Button) hbox.lookup("#a1010");
	     Button a1110 = (Button) hbox.lookup("#a1110");
	     Button a1210 = (Button) hbox.lookup("#a1210");
	     Button a1310 = (Button) hbox.lookup("#a1310");
	     
	     //////////////L///////////////////////////////
	     Button a1103 = (Button) hbox.lookup("#a1103");
	     Button a211 = (Button) hbox.lookup("#a211");
	     Button a311 = (Button) hbox.lookup("#a311");
	     Button a411 = (Button) hbox.lookup("#a411");
	     Button a511 = (Button) hbox.lookup("#a511");
	     Button a611 = (Button) hbox.lookup("#a611");
	     Button a711 = (Button) hbox.lookup("#a711");
	     Button a811 = (Button) hbox.lookup("#a811");
	     Button a911 = (Button) hbox.lookup("#a911");
	     Button a1011 = (Button) hbox.lookup("#a1011");
	     Button a1111 = (Button) hbox.lookup("#a1111");
	     Button a1211 = (Button) hbox.lookup("#a1211");
	     Button a1311 = (Button) hbox.lookup("#a1311");	     
	     
	     ////////////////A/////////////////////////
	     a1.setOnAction(even -> 
	     {
	    	 seatFirst4 = "A1";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a2.setOnAction(even -> 
	     {
	    	 seatFirst4 = "A2";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a3.setOnAction(even -> 
	     {
	    	 seatFirst4 = "A3";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a4.setOnAction(even -> 
	     {
	    	 seatFirst4 = "A4";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a5.setOnAction(even -> 
	     {
	    	 seatFirst4 = "A5";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a6.setOnAction(even -> 
	     {
	    	 seatFirst4 = "A6";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a7.setOnAction(even -> 
	     {
	    	 seatFirst4 = "A7";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a8.setOnAction(even -> 
	     {
	    	 seatFirst4 = "A8";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a9.setOnAction(even -> 
	     {
	    	 seatFirst4 = "A9";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a10.setOnAction(even -> 
	     {
	    	 seatFirst4 = "A10";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a11.setOnAction(even -> 
	     {
	    	 seatFirst4 = "A11";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a12.setOnAction(even -> 
	     {
	    	 seatFirst4 = "A12";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a13.setOnAction(even -> 
	     {
	    	 seatFirst4 = "A13";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
  
	     
	     //////////////////////B////////////////////////
	     a14.setOnAction(even -> 
	     {
	    	 seatFirst4 = "B1";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a21.setOnAction(even -> 
	     {
	    	 seatFirst4 = "B2";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a31.setOnAction(even -> 
	     {
	    	 seatFirst4 = "B3";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a41.setOnAction(even -> 
	     {
	    	 seatFirst4 = "B4";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a51.setOnAction(even -> 
	     {
	    	 seatFirst4 = "B5";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a61.setOnAction(even -> 
	     {
	    	 seatFirst4 = "B6";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a71.setOnAction(even -> 
	     {
	    	 seatFirst4 = "B7";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a81.setOnAction(even -> 
	     {
	    	 seatFirst4 = "B8";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a91.setOnAction(even -> 
	     {
	    	 seatFirst4 = "B9";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a101.setOnAction(even -> 
	     {
	    	 seatFirst4 = "B10";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a111.setOnAction(even -> 
	     {
	    	 seatFirst4 = "B11";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a121.setOnAction(even -> 
	     {
	    	 seatFirst4 = "B12";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a131.setOnAction(even -> 
	     {
	    	 seatFirst4 = "B13";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     ////////////////C///////////////////////
	     a15.setOnAction(even -> 
	     {
	    	 seatFirst4 = "C1";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a22.setOnAction(even -> 
	     {
	    	 seatFirst4 = "C2";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a32.setOnAction(even -> 
	     {
	    	 seatFirst4 = "C3";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a42.setOnAction(even -> 
	     {
	    	 seatFirst4 = "C4";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a52.setOnAction(even -> 
	     {
	    	 seatFirst4 = "C5";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a62.setOnAction(even -> 
	     {
	    	 seatFirst4 = "C6";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a72.setOnAction(even -> 
	     {
	    	 seatFirst4 = "C7";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a82.setOnAction(even -> 
	     {
	    	 seatFirst4 = "C8";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a92.setOnAction(even -> 
	     {
	    	 seatFirst4 = "C9";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a102.setOnAction(even -> 
	     {
	    	 seatFirst4 = "C10";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a112.setOnAction(even -> 
	     {
	    	 seatFirst4 = "C11";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a122.setOnAction(even -> 
	     {
	    	 seatFirst4 = "C12";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a132.setOnAction(even -> 
	     {
	    	 seatFirst4 = "C13";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     
	     
	     /////D///////////////////
	     
	     a16.setOnAction(even -> 
	     {
	    	 seatFirst4 = "D1";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a23.setOnAction(even -> 
	     {
	    	 seatFirst4 = "D2";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a33.setOnAction(even -> 
	     {
	    	 seatFirst4 = "D3";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a43.setOnAction(even -> 
	     {
	    	 seatFirst4 = "D4";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a53.setOnAction(even -> 
	     {
	    	 seatFirst4 = "D5";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a63.setOnAction(even -> 
	     {
	    	 seatFirst4 = "D6";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a73.setOnAction(even -> 
	     {
	    	 seatFirst4 = "D7";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a83.setOnAction(even -> 
	     {
	    	 seatFirst4 = "D8";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a93.setOnAction(even -> 
	     {
	    	 seatFirst4 = "D9";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a103.setOnAction(even -> 
	     {
	    	 seatFirst4 = "D10";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a113.setOnAction(even -> 
	     {
	    	 seatFirst4 = "D11";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a123.setOnAction(even -> 
	     {
	    	 seatFirst4 = "D12";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a133.setOnAction(even -> 
	     {
	    	 seatFirst4 = "D13";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     
	     a17.setOnAction(even -> 
	     {
	    	 seatFirst4 = "E1";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a24.setOnAction(even -> 
	     {
	    	 seatFirst4 = "E2";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a34.setOnAction(even -> 
	     {
	    	 seatFirst4 = "E3";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a44.setOnAction(even -> 
	     {
	    	 seatFirst4 = "E4";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a54.setOnAction(even -> 
	     {
	    	 seatFirst4 = "E5";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a64.setOnAction(even -> 
	     {
	    	 seatFirst4 = "E6";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a74.setOnAction(even -> 
	     {
	    	 seatFirst4 = "E7";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a84.setOnAction(even -> 
	     {
	    	 seatFirst4 = "E8";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a94.setOnAction(even -> 
	     {
	    	 seatFirst4 = "E9";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a104.setOnAction(even -> 
	     {
	    	 seatFirst4 = "E10";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a114.setOnAction(even -> 
	     {
	    	 seatFirst4 = "E11";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a124.setOnAction(even -> 
	     {
	    	 seatFirst4 = "E12";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a134.setOnAction(even -> 
	     {
	    	 seatFirst4 = "E13";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     
	     
	     //////////F//////////////
	     a18.setOnAction(even -> 
	     {
	    	 seatFirst4 = "F1";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a25.setOnAction(even -> 
	     {
	    	 seatFirst4 = "F2";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a35.setOnAction(even -> 
	     {
	    	 seatFirst4 = "F3";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a45.setOnAction(even -> 
	     {
	    	 seatFirst4 = "F4";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a55.setOnAction(even -> 
	     {
	    	 seatFirst4 = "F5";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a65.setOnAction(even -> 
	     {
	    	 seatFirst4 = "F6";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a75.setOnAction(even -> 
	     {
	    	 seatFirst4 = "F7";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a85.setOnAction(even -> 
	     {
	    	 seatFirst4 = "F8";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a95.setOnAction(even -> 
	     {
	    	 seatFirst4 = "F9";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a105.setOnAction(even -> 
	     {
	    	 seatFirst4 = "F10";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a115.setOnAction(even -> 
	     {
	    	 seatFirst4 = "F11";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a125.setOnAction(even -> 
	     {
	    	 seatFirst4 = "F12";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a135.setOnAction(even -> 
	     {
	    	 seatFirst4 = "F13";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     
	     
	     ///////////G/////////////
	     a19.setOnAction(even -> 
	     {
	    	 seatFirst4 = "G1";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a26.setOnAction(even -> 
	     {
	    	 seatFirst4 = "G2";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a36.setOnAction(even -> 
	     {
	    	 seatFirst4 = "G3";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a46.setOnAction(even -> 
	     {
	    	 seatFirst4 = "G4";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a56.setOnAction(even -> 
	     {
	    	 seatFirst4 = "G5";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a66.setOnAction(even -> 
	     {
	    	 seatFirst4 = "G6";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a76.setOnAction(even -> 
	     {
	    	 seatFirst4 = "G7";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a86.setOnAction(even -> 
	     {
	    	 seatFirst4 = "G8";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a96.setOnAction(even -> 
	     {
	    	 seatFirst4 = "G9";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a106.setOnAction(even -> 
	     {
	    	 seatFirst4 = "G10";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a116.setOnAction(even -> 
	     {
	    	 seatFirst4 = "G11";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a126.setOnAction(even -> 
	     {
	    	 seatFirst4 = "G12";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a136.setOnAction(even -> 
	     {
	    	 seatFirst4 = "G13";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     
	     
	     //////////H////////////////////////////
	     a110.setOnAction(even -> 
	     {
	    	 seatFirst4 = "H1";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a27.setOnAction(even -> 
	     {
	    	 seatFirst4 = "H2";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a37.setOnAction(even -> 
	     {
	    	 seatFirst4 = "H3";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a47.setOnAction(even -> 
	     {
	    	 seatFirst4 = "H4";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a57.setOnAction(even -> 
	     {
	    	 seatFirst4 = "H5";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a67.setOnAction(even -> 
	     {
	    	 seatFirst4 = "H6";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a77.setOnAction(even -> 
	     {
	    	 seatFirst4 = "H7";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a87.setOnAction(even -> 
	     {
	    	 seatFirst4 = "H8";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a97.setOnAction(even -> 
	     {
	    	 seatFirst4 = "H9";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a107.setOnAction(even -> 
	     {
	    	 seatFirst4 = "H10";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a117.setOnAction(even -> 
	     {
	    	 seatFirst4 = "H11";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a127.setOnAction(even -> 
	     {
	    	 seatFirst4 = "H12";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a137.setOnAction(even -> 
	     {
	    	 seatFirst4 = "H13";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     
	     
	     ////////////I////////////////////
	     a1100.setOnAction(even -> 
	     {
	    	 seatFirst4 = "I1";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a28.setOnAction(even -> 
	     {
	    	 seatFirst4 = "I2";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a38.setOnAction(even -> 
	     {
	    	 seatFirst4 = "I3";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a48.setOnAction(even -> 
	     {
	    	 seatFirst4 = "I4";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a58.setOnAction(even -> 
	     {
	    	 seatFirst4 = "I5";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a68.setOnAction(even -> 
	     {
	    	 seatFirst4 = "I6";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a78.setOnAction(even -> 
	     {
	    	 seatFirst4 = "I7";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a88.setOnAction(even -> 
	     {
	    	 seatFirst4 = "I8";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a98.setOnAction(even -> 
	     {
	    	 seatFirst4 = "I9";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a108.setOnAction(even -> 
	     {
	    	 seatFirst4 = "I10";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a118.setOnAction(even -> 
	     {
	    	 seatFirst4 = "I11";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a128.setOnAction(even -> 
	     {
	    	 seatFirst4 = "I12";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a138.setOnAction(even -> 
	     {
	    	 seatFirst4 = "I13";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     
	     ///////////////J/////////////////
	     a1101.setOnAction(even -> 
	     {
	    	 seatFirst4 = "J1";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a29.setOnAction(even -> 
	     {
	    	 seatFirst4 = "J2";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a39.setOnAction(even -> 
	     {
	    	 seatFirst4 = "J3";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a49.setOnAction(even -> 
	     {
	    	 seatFirst4 = "J4";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a59.setOnAction(even -> 
	     {
	    	 seatFirst4 = "J5";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a69.setOnAction(even -> 
	     {
	    	 seatFirst4 = "J6";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a79.setOnAction(even -> 
	     {
	    	 seatFirst4 = "J7";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a89.setOnAction(even -> 
	     {
	    	 seatFirst4 = "J8";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a99.setOnAction(even -> 
	     {
	    	 seatFirst4 = "J9";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a109.setOnAction(even -> 
	     {
	    	 seatFirst4 = "J10";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a119.setOnAction(even -> 
	     {
	    	 seatFirst4 = "J11";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a129.setOnAction(even -> 
	     {
	    	 seatFirst4 = "J12";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a139.setOnAction(even -> 
	     {
	    	 seatFirst4 = "J13";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     
	     
	     
	     /////////////////K////////////////
	     a1102.setOnAction(even -> 
	     {
	    	 seatFirst4 = "K1";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a210.setOnAction(even -> 
	     {
	    	 seatFirst4 = "K2";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a310.setOnAction(even -> 
	     {
	    	 seatFirst4 = "K3";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a410.setOnAction(even -> 
	     {
	    	 seatFirst4 = "K4";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a510.setOnAction(even -> 
	     {
	    	 seatFirst4 = "K5";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a610.setOnAction(even -> 
	     {
	    	 seatFirst4 = "K6";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a710.setOnAction(even -> 
	     {
	    	 seatFirst4 = "K7";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a810.setOnAction(even -> 
	     {
	    	 seatFirst4 = "K8";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a910.setOnAction(even -> 
	     {
	    	 seatFirst4 = "K9";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a1010.setOnAction(even -> 
	     {
	    	 seatFirst4 = "K10";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a1110.setOnAction(even -> 
	     {
	    	 seatFirst4 = "K11";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a1210.setOnAction(even -> 
	     {
	    	 seatFirst4 = "K12";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a1310.setOnAction(even -> 
	     {
	    	 seatFirst4 = "K13";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     
	     
	     
	     a1103.setOnAction(even -> 
	     {
	    	 seatFirst4 = "L1";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a211.setOnAction(even -> 
	     {
	    	 seatFirst4 = "L2";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a311.setOnAction(even -> 
	     {
	    	 seatFirst4 = "L3";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a411.setOnAction(even -> 
	     {
	    	 seatFirst4 = "L4";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a511.setOnAction(even -> 
	     {
	    	 seatFirst4 = "L5";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a611.setOnAction(even -> 
	     {
	    	 seatFirst4 = "L6";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a711.setOnAction(even -> 
	     {
	    	 seatFirst4 = "L7";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a811.setOnAction(even -> 
	     {
	    	 seatFirst4 = "L8";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a911.setOnAction(even -> 
	     {
	    	 seatFirst4 = "L9";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a1011.setOnAction(even -> 
	     {
	    	 seatFirst4 = "L10";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a1111.setOnAction(even -> 
	     {
	    	 seatFirst4 = "L11";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a1211.setOnAction(even -> 
	     {
	    	 seatFirst4 = "L12";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     a1311.setOnAction(even -> 
	     {
	    	 seatFirst4 = "L13";
	    	 txtSeat4.setText(seatFirst4);
	    	 popup.hide();
	     });
	     
	     
	/////////////////////////////////////////////     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	}
	@FXML
	void seat5PA(ActionEvent event) throws Exception {
		if(seatFirst1 != " "&&seatFirst2 == " "&&seatFirst3 == " "&&seatFirst4 == " ")
		{
			if(cmbAmount.getValue() == "1장")
			{
				seatFinal = seatFirst1;
				txtSeat.setText(seatFinal);
			}
			else
			{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("오류");
				alert.setHeaderText("중복되거나 선택되지않은 좌석이 있습니다");
				alert.setContentText("장수에 맞게 좌석을 다르게 선택해 주세요.");

				alert.showAndWait();
				txtSeat.setText(null);
				txtSeat1.setText(null);
				txtSeat2.setText(null);
				txtSeat3.setText(null);
				txtSeat4.setText(null);
				seatFirst1 = " ";
				seatFirst2 = " ";
				seatFirst3 = " ";
				seatFirst4 = " ";
			}
			
		}
		
		
		
		else if(seatFirst1 != " "&&seatFirst2 != " "&&seatFirst3 == " "&&seatFirst4 == " "&&seatFirst1!=seatFirst2)
		{
			if(cmbAmount.getValue() == "2장")
			{
				seatFinal = seatFirst1+","+seatFirst2;
				txtSeat.setText(seatFinal);
			}
			else
			{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("오류");
				alert.setHeaderText("중복되거나 선택되지않은 좌석이 있습니다");
				alert.setContentText("장수에 맞게 좌석을 다르게 선택해 주세요.");

				alert.showAndWait();
				txtSeat.setText(null);
				txtSeat1.setText(null);
				txtSeat2.setText(null);
				txtSeat3.setText(null);
				txtSeat4.setText(null);
				seatFirst1 = " ";
				seatFirst2 = " ";
				seatFirst3 = " ";
				seatFirst4 = " ";
			}

		}
		
		
		
		else if(seatFirst1 != " "&&seatFirst2 != " "&&seatFirst3 != " "&&seatFirst4 == " "&&seatFirst1!=seatFirst2&&seatFirst2!=seatFirst3&&seatFirst1!=seatFirst3)
		{
			if(cmbAmount.getValue() == "3장")
			{
				seatFinal = seatFirst1+","+seatFirst2+","+seatFirst3;
				txtSeat.setText(seatFinal);
			}
			else
			{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("오류");
				alert.setHeaderText("중복되거나 선택되지않은 좌석이 있습니다");
				alert.setContentText("장수에 맞게 좌석을 다르게 선택해 주세요.");

				alert.showAndWait();
				txtSeat.setText(null);
				txtSeat1.setText(null);
				txtSeat2.setText(null);
				txtSeat3.setText(null);
				txtSeat4.setText(null);
				seatFirst1 = " ";
				seatFirst2 = " ";
				seatFirst3 = " ";
				seatFirst4 = " ";
			}

		}
		
		else if(seatFirst1 != " "&&seatFirst2 != " "&&seatFirst3 != " "&&seatFirst4 != " "&&seatFirst1!=seatFirst3&&seatFirst1!=seatFirst4&&seatFirst1!=seatFirst2&&seatFirst2!=seatFirst3&&seatFirst3!=seatFirst4&&seatFirst2!=seatFirst4&&seatFirst2!=seatFirst3&&seatFirst3!=seatFirst4)
		{
			if(cmbAmount.getValue() == "4장")
			{
				seatFinal = seatFirst1+","+seatFirst2+","+seatFirst3+","+seatFirst4;
				txtSeat.setText(seatFinal);
			}
			else
			{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("오류");
				alert.setHeaderText("중복되거나 선택되지않은 좌석이 있습니다");
				alert.setContentText("장수에 맞게 좌석을 다르게 선택해 주세요.");

				alert.showAndWait();
				txtSeat.setText(null);
				txtSeat1.setText(null);
				txtSeat2.setText(null);
				txtSeat3.setText(null);
				txtSeat4.setText(null);
				seatFirst1 = " ";
				seatFirst2 = " ";
				seatFirst3 = " ";
				seatFirst4 = " ";
			}

		}
		
		//seatFirst1==seatFirst2||seatFirst1==seatFirst3||
		//seatFirst1==seatFirst4||seatFirst2==seatFirst3||
		//seatFirst2==seatFirst4||seatFirst3==seatFirst4
		else
		{
			//오류팝업띄우기
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("오류");
			alert.setHeaderText("좌석을 전부 선택하지않음");
			alert.setContentText("장수에 맞게 좌석을 전부 선택해 주세요");

			alert.showAndWait();
			txtSeat.setText(null);
			txtSeat1.setText(null);
			txtSeat2.setText(null);
			txtSeat3.setText(null);
			txtSeat4.setText(null);
			seatFirst1 = " ";
			seatFirst2 = " ";
			seatFirst3 = " ";
			seatFirst4 = " ";
	    }

		
		
	}	
	
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
	@FXML
	void datepicker(ActionEvent event) {
		String date = datePicker.getEditor().getText();
		txtDate.setText(date);
	}

	@FXML
	void title(MouseEvent event) {
		txtTitle.setText(listMovie.getSelectionModel().getSelectedItem().getMovie_name());
		txtContent.setText(listMovie.getSelectionModel().getSelectedItem().getMovie_remark());
		imgPoster.setImage(new Image(listMovie.getSelectionModel().getSelectedItem().getMovie_poster()));
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

	public String getCustname(int custId) {
		String sql = "SELECT CUS_NAME FROM CUSTOMER " + "WHERE CUS_ID =? ";
		String num = null;
		try {
			Connection conn = ConnFactory.getConnection("movie.config.oracle");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				num = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	public int getMovieid(String movieName) {
		String sql = "SELECT MOVIE_ID FROM MOVIE " + "WHERE MOVIE_NAME =? ";
		int num = 0;
		try {
			Connection conn = ConnFactory.getConnection("movie.config.oracle");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieName);
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

	public int getGenreid(String genreName) {
		String sql = "SELECT GENRE_ID FROM GENRE " + "WHERE GENRE_NAME =? ";
		int num = 0;
		try {
			Connection conn = ConnFactory.getConnection("movie.config.oracle");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, genreName);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			num = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	
	public int getGradeid(String gradeName) {
		String sql = "SELECT GRADE_ID FROM GRADE " + "WHERE GRADE_NAME =? ";
		int num = 0;
		try {
			Connection conn = ConnFactory.getConnection("movie.config.oracle");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gradeName);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			num = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	public static GenreDao genreDao;
	public static GradeDao gradeDao;
	public static MovieDao movieDao;
	public static OrdersDao ordersDao;
	private Orders2Dao orders2Dao;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


		genreDao = new GenreDao();
		cmbGenre.getItems().addAll(genreDao.selectAll());

		gradeDao = new GradeDao();
		cmbGrade.getItems().addAll(gradeDao.selectAll());

		movieDao = new MovieDao();
		listMovie.getItems().addAll(movieDao.selectAll());

		List<String> list = new ArrayList<>();
		list.add("1장");
		list.add("2장");
		list.add("3장");
		list.add("4장");
		cmbAmount.getItems().addAll(list);

		List<String> list1 = new ArrayList<>();
		list1.add("00:00");
		list1.add("01:00");
		list1.add("02:00");
		list1.add("03:00");
		list1.add("04:00");
		list1.add("05:00");
		list1.add("06:00");
		list1.add("07:00");
		list1.add("08:00");
		list1.add("09:00");
		list1.add("10:00");
		list1.add("11:00");
		list1.add("12:00");
		list1.add("13:00");
		list1.add("14:00");
		list1.add("15:00");
		list1.add("16:00");
		list1.add("17:00");
		list1.add("18:00");
		list1.add("19:00");
		list1.add("20:00");
		list1.add("21:00");
		list1.add("22:00");
		list1.add("23:00");
		cmbTime.getItems().addAll(list1);
		
	
		
		//txtSeat.textProperty().bind(seatFinal.textProperty());
	
		
		
		colId.setCellValueFactory(new PropertyValueFactory("order_id"));
		colName.setCellValueFactory(new PropertyValueFactory("cus_name"));
		colTel.setCellValueFactory(new PropertyValueFactory("cus_tel"));
		colTitle.setCellValueFactory(new PropertyValueFactory("movie_name"));
		colDate.setCellValueFactory(new PropertyValueFactory("order_date"));
		colTime.setCellValueFactory(new PropertyValueFactory("order_time"));
		colAmount.setCellValueFactory(new PropertyValueFactory("order_amount"));
		colSeat.setCellValueFactory(new PropertyValueFactory("order_seat"));
		colPrice.setCellValueFactory(new PropertyValueFactory("movie_price"));
		orders2Dao = new Orders2Dao();
		List<Orders2> list3 = orders2Dao.selectAll();
		table.getItems().clear();
		table.getItems().addAll(list3);

	}

}
