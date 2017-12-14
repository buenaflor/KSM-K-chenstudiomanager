package htl.diplomarbeit.ksm.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import htl.diplomarbeit.ksm.model.Auftragsuebersicht;
import htl.diplomarbeit.ksm.model.Person;
import htl.diplomarbeit.ksm.model.Rechnung;
import htl.diplomarbeit.ksm.model.Rechnungsposition;
import htl.diplomarbeit.ksm.model.Rechnungsuebersicht;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class DatabaseConnection {	
	public static ObservableList<Person> tempList = FXCollections.observableArrayList();
	public static ObservableList<Auftragsuebersicht> tempList2 = FXCollections.observableArrayList();

	private static Connection getConnection(){
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://193.170.234.29:3306/ksm";
			String user = "ksm_user";
			String password = "Passw0rd.ksm";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Verbindung zu " + url + " wurde hergestellt.");
			return conn;
		} catch (SQLException ex) {
			System.err.println("Verbindung konnte nicht hergestellt werden.");
			System.err.println("SQLException: " + ex.getMessage());
	        System.err.println("SQLState: " + ex.getSQLState());
	        System.err.println("VendorError: " + ex.getErrorCode());
	        
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			//alert.initOwner();  // Wozu??
			alert.setTitle("Verbindungsfehler");
			alert.setHeaderText("Verbindung zur Datenbank konnte nicht hergestellt werden! Stellen Sie sicher, dass Sie mit dem HTL-Schulnetzwerk verbunden sind.");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return null;
	}

	public static void instertInDatabaseKunde(int kunden_nr, String geschlecht, String vorname, String nachname, String strasse, String stadt,
			String plz, String geburtstag, String email, String telefon_nr, String regist_datum) throws Exception{
		try {
			Connection con = getConnection();
			String insertStatement = "INSERT INTO kunde (kunden_nr, geschlecht, vorname, nachname, strasse, stadt,"
					+ "plz, geburtstag, email, telefon_nr, regist_datum)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement posted = con.prepareStatement(insertStatement);
			
			posted.setInt(1, kunden_nr);
			posted.setString(2, geschlecht);
			posted.setString(3, vorname);
			posted.setString(4, nachname);
			posted.setString(5, strasse);
			posted.setString(6, stadt);
			posted.setString(7, plz);
			posted.setString(8, geburtstag);
			posted.setString(9, email);
			posted.setString(10, telefon_nr);
			posted.setString(11, regist_datum);
			
			posted.executeUpdate();
			System.out.println("Insert Completed.");
		} catch (Exception e) {
			System.out.println(e);
		} 
	}
	
	public static void updateInDatabaseKunde(int kunden_nr, String geschlecht, String vorname, String nachname, String strasse, String stadt, String plz, String geburtstag, String email, String telefon_nr){
		try {
			Connection con = getConnection();
			
			String updateStatement = "UPDATE kunde SET geschlecht=?, vorname=?, nachname=?, strasse=?, stadt=?, plz=?, geburtstag=?, email=?, telefon_nr=? WHERE kunden_nr=?";
			PreparedStatement update = con.prepareStatement(updateStatement);
			
			//update.setString(1, ""); //1. Fragezeichen wird ersetzt mit dem 2. Parameter
			
			update.setString(1, geschlecht);
			update.setString(2, vorname);
			update.setString(3, nachname);
			update.setString(4, strasse);
			update.setString(5, stadt);
			update.setString(6, plz);
			update.setString(7, geburtstag);
			update.setString(8, email);
			update.setString(9, telefon_nr);
			update.setInt(10, kunden_nr);
			
			update.executeUpdate();
			System.out.println("Update Completed.");
		} catch (Exception e) {
			System.out.println(e);
		} 
	}
	
	public static ObservableList<Person> getFromDatabaseKunde(){
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT kunden_nr, geschlecht, vorname, nachname, strasse, stadt,"
					+ "plz, geburtstag, email, telefon_nr, regist_datum FROM kunde");
			
			ResultSet result = statement.executeQuery();
			
			ObservableList<Person> dbList = FXCollections.observableArrayList();
			
			while (result.next()) {		
				dbList.add(new Person(Integer.parseInt(result.getString("kunden_nr")),
						result.getString("geschlecht"),
						result.getString("vorname"),
						result.getString("nachname"),
						result.getString("strasse"),
						result.getString("stadt"),
						result.getString("plz"),
						result.getString("geburtstag"),
						result.getString("email"),
						result.getString("telefon_nr"),
						result.getString("regist_datum")));
			}
			tempList = dbList;
			return dbList;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static int getHighestCustomerId(){
		try {
			Connection con = getConnection();
			
			PreparedStatement statement = con.prepareStatement("SELECT max(kunden_nr) FROM kunde");
			
			ResultSet result = statement.executeQuery();
			int maxCustomerId = 0;
			
			if(result.next()){
				maxCustomerId = Integer.parseInt(result.getString(1));
			}
			
			System.out.println("MaximumKundenID: " + maxCustomerId);
			
			return maxCustomerId;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return 0;
	}
	
	public static void instertInDatabaseRechnung(int rechnungs_nr, int rechnung_kunden_nr, String datum, String lieferdatum, BigDecimal nettobetrag, BigDecimal bruttobetrag){
		try {
			Connection con = getConnection();
			String statement = "INSERT INTO rechnung (rechnungs_nr,  rechnung_kunden_nr, datum, lieferdatum, nettobetrag, bruttobetrag) VALUES (?,?,?,?,?,?)";
			PreparedStatement posted = con.prepareStatement(statement);
			
			posted.setInt(1, rechnungs_nr);
			posted.setInt(2, rechnung_kunden_nr);
			posted.setString(3, datum);
			posted.setString(4, lieferdatum);
			posted.setBigDecimal(5, nettobetrag);
			posted.setBigDecimal(6, bruttobetrag);
			
			posted.executeUpdate();
			System.out.println("Insert Completed.");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void instertInDatabaseRechnungsposition(int pos_nr, int rechnungs_nr, String bezeichnung, BigDecimal einzelpreis){
		try {
			Connection con = getConnection();
			String statement = "INSERT INTO rechnungsposition (pos_nr,  rechnungs_nr, bezeichnung, einzelpreis) VALUES (?,?,?,?)";
			PreparedStatement posted = con.prepareStatement(statement);
			
			posted.setInt(1, pos_nr);
			posted.setInt(2, rechnungs_nr);
			posted.setString(3, bezeichnung);
			posted.setBigDecimal(4, einzelpreis);
			
			
			posted.executeUpdate();
			System.out.println("Insert Completed.");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public static ObservableList<Rechnungsuebersicht> getFromDatabaseRechnung(){
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT datum, lieferdatum, rechnungs_nr, kunden_nr, vorname, nachname, telefon_nr, email, nettobetrag, bruttobetrag  FROM kunde, rechnung WHERE rechnung.rechnung_kunden_nr = kunde.kunden_nr");
			
			ResultSet result = statement.executeQuery();
			
			ObservableList<Rechnungsuebersicht> dbList = FXCollections.observableArrayList();
			
			while (result.next()) {		
				dbList.add(new Rechnungsuebersicht(result.getString("datum"),
						result.getString("lieferdatum"),
						Integer.parseInt(result.getString("rechnungs_nr")),
						Integer.parseInt(result.getString("kunden_nr")),
						result.getString("vorname"),
						result.getString("nachname"),
						result.getString("telefon_nr"),
						result.getString("email"),
						result.getString("nettobetrag"),
						result.getString("bruttobetrag")));
			}
			
			return dbList;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static ObservableList<Rechnungsposition> getFromDatabaseRechnungsposition(int rechnung_id){
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT pos_nr, bezeichnung, einzelpreis FROM rechnungsposition WHERE rechnungs_nr = ?");
			statement.setInt(1, rechnung_id);
			
			ResultSet result = statement.executeQuery();
			
			ObservableList<Rechnungsposition> dbList = FXCollections.observableArrayList();
			
			while (result.next()) {		
				dbList.add(new Rechnungsposition(Integer.parseInt(result.getString("pos_nr")),
						result.getString("bezeichnung"),
						result.getString("einzelpreis")));
			}
			
			return dbList;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static boolean checkRechnungsId(int rechnung_id){
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT rechnungs_nr FROM rechnung");
			
			ResultSet result = statement.executeQuery();

			while (result.next()) {		
				if(Integer.parseInt(result.getString("rechnungs_nr")) == rechnung_id){
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	public static int getHighestRechnungsId(){
		try {
			Connection con = getConnection();
			
			PreparedStatement statement = con.prepareStatement("SELECT max(rechnungs_nr) FROM rechnung");
			
			ResultSet result = statement.executeQuery();
			int maxRechnungsId = 0;
			
			if(result.next()){
				maxRechnungsId = Integer.parseInt(result.getString(1));
			}
			
			System.out.println("MaximumRechnungsID: " + maxRechnungsId);
			
			return maxRechnungsId;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return 0;
	}
	
	public static void insertInDatabaseAuftrag(int auftrags_nr, int auftrag_kunden_nr, String datum, String termin, String lieferanschrift, boolean stromanschluss, boolean wasseranschluss, boolean demontage, boolean montage, BigDecimal preis_netto, BigDecimal gesamtpreis, BigDecimal erst_anzahlung, BigDecimal zweit_anzahlung, int erst_anzahlung_prozent, int zweit_anzahlung_prozent){
		try {
			Connection con = getConnection();
			String statement = "INSERT INTO auftrag (auftrags_nr, auftrag_kunden_nr, datum, termin, lieferanschrift, stromanschluss, wasseranschluss, demontage, montage, preis_netto, gesamtpreis, erst_anzahlung, zweit_anzahlung, erst_anzahlung_prozent, zweit_anzahlung_prozent) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement posted = con.prepareStatement(statement);
			
			posted.setInt(1, auftrags_nr);
			posted.setInt(2, auftrag_kunden_nr);
			posted.setString(3, datum);
			posted.setString(4, termin);
			posted.setString(5, lieferanschrift);
			posted.setBoolean(6, stromanschluss);
			posted.setBoolean(7, wasseranschluss);
			posted.setBoolean(8, demontage);
			posted.setBoolean(9, montage);
			posted.setBigDecimal(10, preis_netto);
			posted.setBigDecimal(11, gesamtpreis);
			posted.setBigDecimal(12, erst_anzahlung);
			posted.setBigDecimal(13, zweit_anzahlung);
			posted.setInt(14, erst_anzahlung_prozent);
			posted.setInt(15, zweit_anzahlung_prozent);
			
			posted.executeUpdate();
			System.out.println("Insert Completed.");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void instertInDatabaseAuftragsposition(int auftrags_nr, int pos_nr, int produkt_nr, String zusatztext){
		try {
			Connection con = getConnection();
			String statement = "INSERT INTO auftragsposition (auftrags_nr, pos_nr, produkt_nr, zusatztext) VALUES (?,?,?,?)";
			PreparedStatement posted = con.prepareStatement(statement);
			
			posted.setInt(1, auftrags_nr);
			posted.setInt(2, pos_nr);
			posted.setInt(3, produkt_nr);
			posted.setString(4, zusatztext);
			
			
			posted.executeUpdate();
			System.out.println("Insert Completed.");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public static ObservableList<Auftragsuebersicht> getFromDatabaseAuftrag(){
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT datum, termin, auftrags_nr, kunden_nr, vorname, nachname, telefon_nr, email, lieferanschrift, gesamtpreis, preis_netto, erst_anzahlung, zweit_anzahlung, erst_anzahlung_prozent, zweit_anzahlung_prozent, montage, demontage, wasseranschluss, stromanschluss FROM kunde, auftrag WHERE auftrag.auftrag_kunden_nr = kunde.kunden_nr");
			
			ResultSet result = statement.executeQuery();
			
			ObservableList<Auftragsuebersicht> dbList = FXCollections.observableArrayList();
			
			while (result.next()) {		
				dbList.add(new Auftragsuebersicht(result.getString("datum"),
						result.getString("termin"),
						result.getInt("auftrags_nr"),
						result.getInt("kunden_nr"),
						result.getString("vorname"),
						result.getString("nachname"),
						result.getString("telefon_nr"),
						result.getString("email"),
						result.getString("lieferanschrift"),
						result.getString("gesamtpreis"),
						result.getString("preis_netto"),
						result.getString("erst_anzahlung"),
						result.getString("zweit_anzahlung"),
						result.getInt("erst_anzahlung_prozent"),
						result.getInt("zweit_anzahlung_prozent"),
						result.getBoolean("montage"),
						result.getBoolean("demontage"),
						result.getBoolean("wasseranschluss"),
						result.getBoolean("stromanschluss")));
			}
			tempList2 = dbList;
			return dbList;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static String getFromDatabaseAuftragsposition(int auftrags_nr, int pos_nr){
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT zusatztext FROM auftragsposition WHERE auftrags_nr = ? AND pos_nr = ?");
			statement.setInt(1, auftrags_nr);
			statement.setInt(2, pos_nr);
			
			ResultSet result = statement.executeQuery();
			
			
			String text = "";
			if(result.next()){
				text = result.getString(1);
				System.out.println("Result====== " + text);
			}
			return text;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
//	public static void instertInDatabaseProdukte(int produkt_nr, String zusatztext){
//		try {
//			Connection con = getConnection();
//			String statement = "UPDATE produkte SET zusatztext=? WHERE produkt_nr = ?";
//			PreparedStatement posted = con.prepareStatement(statement);
//			
//			posted.setString(1, zusatztext);
//			posted.setInt(2, produkt_nr);
//			
//			
//			posted.executeUpdate();
//			System.out.println("Insert(Update) in produkte Completed.");
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//	}
	
//	public static void instertInDatabaseProdukte(int produkt_nr, String bezeichnung, String zusatztext, int auftrags_nr){
//		try {
//			Connection con = getConnection();
//			String statement = "INSERT INTO produkte (produkt_nr,  bezeichnung, zusatztext, auftrags_nr) VALUES (?,?,?,?)";
//			PreparedStatement posted = con.prepareStatement(statement);
//			
//			posted.setInt(1, produkt_nr);
//			posted.setString(2, bezeichnung);
//			posted.setString(3, zusatztext);
//			posted.setInt(4, auftrags_nr);
//			
//			
//			posted.executeUpdate();
//			System.out.println("Insert Completed.");
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//	}
	
	public static int getHighestAuftragsId(){
		try {
			Connection con = getConnection();
			
			PreparedStatement statement = con.prepareStatement("SELECT max(auftrags_nr) FROM auftrag");
			
			ResultSet result = statement.executeQuery();
			int maxAuftragsId = 0;
			
			if(result.next()){
				maxAuftragsId = Integer.parseInt(result.getString(1));
			}
			
			System.out.println("MaximumAuftragsID: " + maxAuftragsId);
			
			return maxAuftragsId;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return 0;
	}
}
