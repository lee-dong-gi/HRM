package app.domain;

public class KategorieDTO {
	int kanum;
	String kategoriename;
	public int getKanum() {
		return kanum;
	}
	public void setKanum(int kanum) {
		this.kanum = kanum;
	}
	public String getKategoriename() {
		return kategoriename;
	}
	public void setKategoriename(String kategoriename) {
		this.kategoriename = kategoriename;
	}
	@Override
	public String toString() {
		return "KategorieDTO [kanum=" + kanum + ", kategoriename=" + kategoriename + "]";
	}
	
	
}
