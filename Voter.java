
public class Voter {

	private String name;
	private String fname;
	private int voter_id;
	private String cnic;
	private String dob;
	private String Mobile;
	private String district;
	private String proffession;
	private String Religion;
	private int pin;
	private int canvote;

	public Voter(int V, String n, String fn, String dob, String m, String c, String d, String p, String r,int pin,int cv) {
		name = n;
		fname = fn;
		cnic = c;
		voter_id = V;
		this.dob = dob;
		Mobile = m;
		district = d;
		proffession = p;
		Religion = r;
        this.pin=pin;
        canvote=cv;
	}
    public int getid() {
    	return voter_id;
    }
    public int can1() {
    	return canvote;
    }
    public int getPin() {
    	return pin;
    }
	public String getName() {
		return name;

	}

	public String getFName() {
		return fname;

	}

	public String getCnic() {

		return cnic;
	}

	public String getDob() {

		return dob;
	}

	public String getMobile() {

		return Mobile;
	}

	public String getDistrict() {

		return district;
	}

	public String getProffession() {

		return proffession;
	}

	public String getReligion() {
		return Religion;

	}

	public String toString() {

		String S = new String();

		S +="Voter Id :"+voter_id+", Name :"+name; 
		return S;
	}

}
