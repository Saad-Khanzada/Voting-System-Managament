

public class Person {
	
	public Vote vote;
	private int votes = 0;
	private class Vote {
		private Voter voter;
		private Vote vote;

		Vote(Voter V, Vote v) {
			voter = V;
			vote = v;

		}

	}

	public boolean vote(Voter V) {
		vote = new Vote(V, vote);
		votes++;
		return true;

	}

	public int votes() {

		return votes;
	}
	public void print_voters() {
		if(vote!=null) {
		for(Vote i=vote;i!=null;i=i.vote) {
			
			System.out.println(i.voter);
		}}else {System.out.println("empty list");}
		
	}
	public Voter[] get() {
		Voter[] list=new Voter[100];
		int k=0;
		for(Vote i=vote;i!=null;i=i.vote) {
			list[k++]=i.voter;
			
		}
		return list;
	}

}
