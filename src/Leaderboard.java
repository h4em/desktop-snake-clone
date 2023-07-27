import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Leaderboard {
    List<Player> playerList;
    public Leaderboard() {
        playerList = new LinkedList<>();
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Player> getPlayerList() {
        return this.playerList;
    }

    public void tryAddingPlayer(Player player) {
        playerList.add(player);
        Collections.sort(playerList, new Comparator<Player>() {
            public int compare(Player player1, Player player2) {
                return player2.getScore() - player1.getScore();
            }
        });

        if(playerList.size() > 10) {
            int lastIdx = playerList.size() - 1;
            playerList.remove(lastIdx);
        }
    }
}
