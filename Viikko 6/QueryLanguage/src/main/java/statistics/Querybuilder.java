package statistics;

import statistics.matcher.*;

public class Querybuilder {

	private Matcher matcher;

	public Querybuilder() {
		this.matcher = new All();

	}

	public Matcher build() {
		Matcher match = matcher;
		matcher = new All();
		return match;
	}

	public Querybuilder playsIn(String team) {
		this.matcher = new And(new PlaysIn(team), matcher);
		return this;
	}

	public Querybuilder hasAtLeast(int value, String section) {
		this.matcher = new And(new HasAtLeast(value, section), matcher);
		return this;
	}
	
	public Querybuilder or(Matcher... matchers) {
		this.matcher = new Or(matchers);
		return this;
	}
	
	public Querybuilder not(Matcher matcher) {
		this.matcher = new And(new Not(matcher), this.matcher);
		return this;
	}
	
	public Querybuilder hasFewerThan(int value, String section) {
		this.matcher = new And(new HasFewerThan(value, section), matcher);
		return this;
	}
	
	public Querybuilder oneOf(Matcher... matchers) {
		this.matcher = new Or(matchers);
		return this;
	}
	

}
