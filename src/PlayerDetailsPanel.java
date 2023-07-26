import javax.swing.*;
import java.awt.*;

public class PlayerDetailsPanel extends JPanel implements ScoreListener {
    private JTextField playerNameField;
    private JTextField scoreField;

    @Override
    public void scoreChanged() {

    }

    public PlayerDetailsPanel() {
        this.setLayout(new GridLayout(2, 1));

        JLabel playerLabel = new JLabel("Player:");
        playerNameField = new JTextField(20);
        playerNameField.setEditable(false);

        JLabel scoreLabel = new JLabel("Score:");
        scoreField = new JTextField(20);
        scoreField.setEditable(false);

        this.add(playerLabel);
        this.add(playerNameField);
        this.add(scoreLabel);
        this.add(scoreField);
    }
}
