
public class Idea {
	
	//Attributes
	private String title;
	private String date;
	private String other;
	
	//Constructors
	public Idea(String title, String date) {
		this.title=title;
		this.date=date;
	}
	
	public Idea () {
		this.title="";
		this.date="";
		this.date="";
	}
	
	public Idea(String title, String date, String other) {
		this.title = title;
		this.date = date;
		this.other = other;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Override
	public String toString() {
		if(!other.equals(""))
			return "Idea: title=" + title + ", date=" + date + ", other=" + other;
		else
			return "Idea: title=" + title + ", date=" + date;
	}
}
