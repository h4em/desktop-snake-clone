import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel implements ScoreListener {
    private JTextField scoreField;

    public ScorePanel() {
        //TODO: zmienic caly layout, tu przy duzym wyniku sie bedzie pierdolic
        this.setLayout(new GridLayout(1, 2));

        this.add(new JLabel("Score: "));

        scoreField = new JTextField("0");
        scoreField.setEditable(false);
        scoreField.setBorder(BorderFactory.createEmptyBorder());
        this.add(scoreField);
    }

    //TODO: ten int newScore jest zly nie?
    @Override
    public void scoreUpdated(int newScore) {
        scoreField.setText("" + newScore);
    }
}