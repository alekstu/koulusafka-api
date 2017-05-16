package fi.koulusafka.api.service.util;

import java.text.DecimalFormat;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fi.koulusafka.api.constants.RestaurantConstants;

public final class UrlUtil {

	private static StringBuilder url = null;
	private static final Logger log = LoggerFactory.getLogger(UrlUtil.class);

	private UrlUtil() {
	}

	/**
	 * Create url to get json from Amica
	 * 
	 * @param restaurantId
	 *            restaurant identifier
	 * @param locale
	 *            user's location
	 * @return String url
	 */
	public static String generateAmicaUrl(String restaurantId, String languageKey) {

		url = new StringBuilder();
		String languageUrl = "";

		switch (languageKey) {

		case RestaurantConstants.LANGUAGE_KEY_EN:
			languageUrl = RestaurantConstants.AMICA_ENG_URL;
			break;

		case RestaurantConstants.LANGUAGE_KEY_SV:
			languageUrl = RestaurantConstants.AMICA_SV_URL;
			break;

		case RestaurantConstants.LANGUAGE_KEY_FI:
			languageUrl = RestaurantConstants.AMICA_FIN_URL;
			break;

		default:
			languageUrl = RestaurantConstants.AMICA_FIN_URL;
			break;
		}

		return url.append(RestaurantConstants.AMICA_BASE_URL).append(restaurantId).append(languageUrl).toString();
	}

	/**
	 * Create url to get json from Sodexo
	 * 
	 * @param restaurantId
	 *            restaurant identifier
	 * @param locale
	 *            user's location
	 * @return String url
	 */
	
	public static String generateSodexoWeekUrl(String restaurantId) {

		url = new StringBuilder();
		DateTime date = new DateTime();
		DecimalFormat dateFormat = new DecimalFormat("00"); // adds zero before
															// months if single
															// digit

		return url.append(RestaurantConstants.SODEXO_BASE_URL).append(restaurantId + "/").append(date.getYear() + "/")
				.append(dateFormat.format(date.getMonthOfYear()) + "/")
				.append(dateFormat.format(date.getDayOfMonth()) + "/").append(RestaurantConstants.LANGUAGE_KEY_FI)
				.toString();

	}

	/**
	 * Create url to get json from Unicafe
	 * 
	 * @param restaurantId
	 * @return
	 */

	public static String generateUniCafeWeekUrl(String restaurantId, String languageKey) {
		url = new StringBuilder();

		String languageUrl = "";

		switch (languageKey) {

		case RestaurantConstants.LANGUAGE_KEY_EN:
			languageUrl = RestaurantConstants.UNICAFE_URL_ENG;
			break;

		case RestaurantConstants.LANGUAGE_KEY_SV:
			languageUrl = RestaurantConstants.UNICAFE_URL_SWE;
			break;

		case RestaurantConstants.LANGUAGE_KEY_FI:
			languageUrl = RestaurantConstants.UNICAFE_URL_FIN;
			break;

		default:
			languageUrl = RestaurantConstants.UNICAFE_URL_FIN;
			break;
		}

		return url.append(RestaurantConstants.UNICAFE_BASE_URL).append(languageUrl).append(restaurantId).toString();

	}

}
