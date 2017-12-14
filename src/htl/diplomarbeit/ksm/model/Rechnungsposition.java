package htl.diplomarbeit.ksm.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Rechnungsposition {
	private final SimpleIntegerProperty rechnung_id;
	private final SimpleStringProperty rBezeichnung;
	private final SimpleStringProperty rEinzelpreis;
	
	public Rechnungsposition(int rechnung_id, String cBezeichnung, String cEinzelpreis){
		this.rechnung_id = new SimpleIntegerProperty(rechnung_id);
		this.rBezeichnung = new SimpleStringProperty(cBezeichnung);
		this.rEinzelpreis = new SimpleStringProperty(cEinzelpreis);
	}
	
	public Integer getRID(){
		return rechnung_id.get();
	}
	public void setRID(Integer v){
		rechnung_id.set(v);
	}
	
	public String getRBezeichnung(){
		return rBezeichnung.get();
	}
	public void setRBezeichnung(String v){
		rBezeichnung.set(v);
	}
	
	public String getREinzelpreis(){
		return rEinzelpreis.get();
	}
	public void setREinzelpreis(String v){
		rEinzelpreis.set(v);
	}
	
}
