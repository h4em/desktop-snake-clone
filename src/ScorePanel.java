import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ScorePanel extends JPanel implements ScoreListener {
    private JTextField scoreField;

    private JTextField highscoreField;

    //TODO: zmienic fonta
    public ScorePanel() {
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(new JLabel("Score: "), gbc);

        gbc.gridy = 1;
        this.add(new JLabel("Highscore: "), gbc);

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 1;
        gbc.gridy = 0;
        scoreField = new JTextField("0");
        scoreField.setEditable(false);
        scoreField.setBorder(BorderFactory.createEmptyBorder());
        this.add(scoreField, gbc);

        gbc.gridy = 1;
        highscoreField = new JTextField("0");
        highscoreField.setEditable(false);
        highscoreField.setBorder(BorderFactory.createEmptyBorder());
        this.add(highscoreField, gbc);

        this.setColors();
    }

    //TODO: ten int newScore jest zly nie?
    @Override
    public void scoreUpdated(int newScore) {
        scoreField.setText("" + newScore);
    }

    @Override
    public void highscoreUpdated(int newScore) {
        ;
    }

    private void setColors() {
        Color backgroundColor = Color.WHITE;

        this.setBackground(backgroundColor);
        this.highscoreField.setBackground(backgroundColor);
        this.scoreField.setBackground(backgroundColor);
    }
}