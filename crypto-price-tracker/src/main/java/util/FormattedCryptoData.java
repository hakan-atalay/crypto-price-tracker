package util;

public class FormattedCryptoData {
	private String realId;
	private String displayId;
	private String name;
	private String price;
	private String marketCap;

	public FormattedCryptoData(String realId, String displayId, String name, String price, String marketCap) {
		this.realId = realId;
		this.displayId = displayId;
		this.name = name;
		this.price = price;
		this.marketCap = marketCap;
	}

	public String getRealId() {
		return realId;
	}

	public void setRealId(String realId) {
		this.realId = realId;
	}

	public String getDisplayId() {
		return displayId;
	}

	public void setDisplayId(String displayId) {
		this.displayId = displayId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}

	@Override
	public String toString() {
		return "FormattedCryptoData [realId=" + realId + ", displayId=" + displayId + ", name=" + name + ", price="
				+ price + ", marketCap=" + marketCap + "]";
	}

}
