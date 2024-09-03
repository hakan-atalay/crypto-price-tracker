package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

import jakarta.enterprise.context.ApplicationScoped;
import util.ApiResponse;
import util.FormattedCryptoData;

@ApplicationScoped
public class CryptoAPIClient {
	private static final DecimalFormat df = new DecimalFormat("#,##0.00");

	public List<FormattedCryptoData> getCryptoCurrency() {
		List<FormattedCryptoData> cryptoList = new ArrayList<>();
		Gson gson = new Gson();
		try {
			HttpURLConnection connect;
			URL url = new URL(
					"https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=**????****");
			connect = (HttpURLConnection) url.openConnection();
			connect.setRequestMethod("GET");

			BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			StringBuffer response = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			ApiResponse apiResponse = gson.fromJson(response.toString(), ApiResponse.class);
			List<ApiResponse.CryptoData> data = apiResponse.getData();

			int index = 1;
			for (ApiResponse.CryptoData crypto : data) {
				String realId = String.valueOf(crypto.getId());
				String displayId = String.valueOf(index++);
				String name = crypto.getName();
				String price = formatNumber(crypto.getQuote().getUSD().getPrice());
				String marketCap = formatNumber(crypto.getQuote().getUSD().getMarket_cap());

				FormattedCryptoData formattedCrypto = new FormattedCryptoData(realId, displayId, name, price,
						marketCap);
				cryptoList.add(formattedCrypto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cryptoList;
	}

	private String formatNumber(Double number) {
		if (number == null) {
			return "N/A";
		}
		return df.format(number);
	}
	
}
