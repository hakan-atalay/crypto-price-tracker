package util;

import java.util.List;

public class ApiResponse {
	private List<CryptoData> data;

	public List<CryptoData> getData() {
		return data;
	}

	public void setData(List<CryptoData> data) {
		this.data = data;
	}

	public static class CryptoData {
		private int id;
		private String name;
		private Quote quote;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Quote getQuote() {
			return quote;
		}

		public void setQuote(Quote quote) {
			this.quote = quote;
		}

		public static class Quote {
			private USD USD;

			public USD getUSD() {
				return USD;
			}

			public void setUSD(USD USD) {
				this.USD = USD;
			}

			public static class USD {
				private Double price;
				private Double market_cap;

				public Double getPrice() {
					return price;
				}

				public void setPrice(Double price) {
					this.price = price;
				}

				public Double getMarket_cap() {
					return market_cap;
				}

				public void setMarket_cap(Double market_cap) {
					this.market_cap = market_cap;
				}
			}
		}
	}
}
