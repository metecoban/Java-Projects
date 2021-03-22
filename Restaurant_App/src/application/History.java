package application;

public class History {
	private String date;
	private String table;
	private String gain;

	public History(String date, String table, String gain) {
		this.date = date;
		this.table = table;
		this.gain = gain;
	}

	public String getDate() {
		return date;
	}

	public String getTable() {
		return table;
	}

	public String getGain() {
		return gain;
	}
}
