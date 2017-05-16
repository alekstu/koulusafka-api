package fi.koulusafka.api.constants;

public class RestaurantConstants {
	

	public static final String RESTAURANT_BRAND_AMICA = "Amica";
	
	public static final String RESTAURANT_BRAND_SODEXO = "Sodexo";
	
	public static final String RESTAURANT_BRAND_UNICAFE= "UniCafe";
	
	// baseUrl + ravintolaId + language
	public static final String AMICA_BASE_URL = "http://www.amica.fi/modules/json/json/Index?costNumber=";
	
	
	// output/weekly_json/ + ravintolanumero + "/" + PÄIVÄMÄÄRÄ + maakoodi(en,fi)  <-- ei väliä koska samassa jsonissa molemmat kielet
	public static final String SODEXO_BASE_URL = "http://www.sodexo.fi/ruokalistat/output/weekly_json/";
	
	
	// hyyravintolat.fi/json/ + MAAKOODI + ravintolanumero    (maakoodit: fin/sv/en) <-- ei väliä koska samassa jsonissa molemmat kielet
	public static final String UNICAFE_BASE_URL = "http://messi.hyyravintolat.fi/json/";
	public static final String UNICAFE_URL_FIN = "fin/";
	public static final String UNICAFE_URL_ENG = "eng/";
	public static final String UNICAFE_URL_SWE = "swe/";
	
	public static final String LANGUAGE_KEY_FI = "fi";
	public static final String LANGUAGE_KEY_EN = "en";
	public static final String LANGUAGE_KEY_SV = "sv";
	
	
	
	//----------- LANGUAGE URLS
	
	
	public static final String AMICA_FIN_URL 	= "&language=fi";
	public static final String AMICA_ENG_URL 	= "&language=en";
	public static final String AMICA_SV_URL 	= "&language=sv-FI";
	
	public static final String MESSAGE_SOURCE_WEEKDAY = "weekday.";
	
	// -- Disabled weekdays
	
	public static final String WEEKEND = "Lauantai|lördag|Saturday|Sunnuntai|söndag|Sunday";
	
	public static final String ERROR_RESTAURANT_NOT_FOUND = "Restaurant not found";

}
