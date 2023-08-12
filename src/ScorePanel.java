import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ScorePanel extends JPanel implements ScoreListener {
    private JTextField scoreField;
    private JTextField highscoreField;

    //TODO: zmiana fonta?
    public ScorePanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;

        addScoreLabel(gbc);
        addHighscoreLabel(gbc);

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addScoreField(gbc);
        addHighscoreField(gbc);

        setColors();
    }

    private void addScoreLabel(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Score: "), gbc);
    }

    private void addHighscoreLabel(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Highscore: "), gbc);
    }

    private void addScoreField(GridBagConstraints gbc) {
        gbc.gridx = 1;
        gbc.gridy = 0;
        scoreField = new JTextField("0");
        scoreField.setEditable(false);
        scoreField.setBorder(BorderFactory.createEmptyBorder());
        add(scoreField, gbc);
    }

    private void addHighscoreField(GridBagConstraints gbc) {
        gbc.gridx = 1;
        gbc.gridy = 1;
        highscoreField = new JTextField("0");
        highscoreField.setEditable(false);
        highscoreField.setBorder(BorderFactory.createEmptyBorder());
        add(highscoreField, gbc);
    }

    private void setColors() {
        Color backgroundColor = Color.WHITE;

        this.setBackground(backgroundColor);
        this.highscoreField.setBackground(backgroundColor);
        this.scoreField.setBackground(backgroundColor);
    }

    //TODO: ten int newScore jest zly nie?
    @Override
    public void scoreUpdated(int newScore) {
        scoreField.setText("" + newScore);
    }

    @Override
    public void highscoreUpdated(int newScore) {
        highscoreField.setText("" + newScore);
    }
}