public class Player {
    private String nickname;
    private int highScore;

    public Player() {
        //nwm
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getNickname() {
        return nickname;
    }

    public int getHighScore() {
        return highScore;
    }
}
