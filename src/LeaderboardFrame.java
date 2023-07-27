import javax.swing.*;
import java.awt.*;

public class LeaderboardFrame extends JFrame {
    public LeaderboardFrame(Leaderboard leaderboard, JFrame parentFrame) {
        this.setTitle("Leaderboards");
        this.setVisible(true);
        this.setSize(new Dimension(640,480));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(parentFrame);
        this.setAlwaysOnTop(true);

        LeaderboardTable ltb = new LeaderboardTable(new LeaderboardModel(leaderboard));

        this.getContentPane().add(ltb);
    }
}
