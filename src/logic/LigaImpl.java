package logic;
import data.DataLayer;

public class LigaImpl implements LigaInterface {
	
	DataLayer dataLayer = new DataLayer();
	
	
	
	public void createLiga(String ligaName) {
		dataLayer.createLiga(ligaName);
	}

	
	public void updateLiga(Liga liga) {
		
	}

	
	public void deleteLiga(Liga liga) {
		
	}
	
	
}

