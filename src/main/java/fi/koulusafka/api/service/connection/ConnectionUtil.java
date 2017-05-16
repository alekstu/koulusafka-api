package fi.koulusafka.api.service.connection;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public final class ConnectionUtil {
	


	private static Logger log = LoggerFactory.getLogger(ConnectionUtil.class);

	private final static String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.92 Safari/537.36";

	public static String getResponseBody(String url) throws IOException {
		
		return new OkHttpClient().newCall(
					new Request.Builder().url(url).addHeader("USER_AGENT", USER_AGENT)
						.build())
						.execute().body().string();
	}


}