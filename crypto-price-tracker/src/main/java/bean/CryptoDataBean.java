package bean;

import java.io.Serializable;
import java.util.List;

import api.CryptoAPIClient;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import util.FormattedCryptoData;

@Named
@SessionScoped
public class CryptoDataBean implements Serializable {

	@Inject
	private CryptoAPIClient cryptoAPIClient;

	private List<FormattedCryptoData> cryptoList;
	
	public CryptoDataBean() {}

	@PostConstruct
	public void init() {
		loadCryptoData();
	}

	public void loadCryptoData() {
		cryptoList = cryptoAPIClient.getCryptoCurrency();
	}

	public List<FormattedCryptoData> getCryptoList() {
		return cryptoList;
	}

	public void setCryptoList(List<FormattedCryptoData> cryptoList) {
		this.cryptoList = cryptoList;
	}

}
