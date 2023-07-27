public class Player {
    private String nickname;
    private int score;

    public Player(String nickname, int score) {
        this.nickname = nickname;
        this.score = score;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNickname() {
        return nickname;
    }

    public int getScore() {
        return score;
    }
}
