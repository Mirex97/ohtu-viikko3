package ohtu;

public class TennisGame {

	private int p1Score = 0;
	private int p2Score = 0;
	private int tempScore = 0;
	private final int upperLimit = 4;
	private final int roundsTillEnd = 3;
	private final String player1Name;
	private final String player2Name;

	public TennisGame(final String player1, final String player2) {
		this.player1Name = player1;
		this.player2Name = player2;
	}

	public void wonPoint(final String playerName) {
		if (playerName == "player1") {
			p1Score += 1;
		} else {
			p2Score += 1;
		}
	}

	private String scoreEqual() {
		String score = "";
		switch (p1Score) {
			case 0:
				score = "Love-All";
				break;
			case 1:
				score = "Fifteen-All";
				break;
			case 2:
				score = "Thirty-All";
				break;
			case 3:
				score = "Forty-All";
				break;
			default:
				score = "Deuce";
				break;

		}
		return score;
	}

	private String scoreAdvantage() {
		String score = "";
		int minusResult = p1Score - p2Score;
		if (minusResult == 1) {
			score = "Advantage player1";
		} else if (minusResult == -1) {
			score = "Advantage player2";
		} else if (minusResult >= 2) {
			score = "Win for player1";
		} else {
			score = "Win for player2";
		}
		return score;
	}
	
	private String scoreStartingRounds() {
		String score = "";
		for (int i = 1; i < roundsTillEnd; i++) {
				if (i == 1) {
					tempScore = p1Score;
				} else {
					score += "-";
					tempScore = p2Score;
				}
				switch (tempScore) {
					case 0:
						score += "Love";
						break;
					case 1:
						score += "Fifteen";
						break;
					case 2:
						score += "Thirty";
						break;
					case 3:
						score += "Forty";
						break;
					default:
						continue;
				}
			}
		return score;
	}

	public String getScore() {
		String score = "";
		if (p1Score == p2Score) {
			score = scoreEqual();
		} else if (p1Score >= upperLimit || p2Score >= upperLimit) {
			score = scoreAdvantage();
		} else {
			score = scoreStartingRounds();
		}
		return score;
	}
}
