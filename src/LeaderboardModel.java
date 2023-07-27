import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LeaderboardModel extends AbstractTableModel {

    private Leaderboard leaderboard;
    public LeaderboardModel(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch(columnIndex) {
            case 0 -> leaderboard.getPlayerList().get(rowIndex).getNickname();
            case 1 -> "" + leaderboard.getPlayerList().get(rowIndex).getScore();
            default -> null;
        };
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    @Override
    public int getRowCount() {
        return leaderboard.getPlayerList().size();
    }
}
