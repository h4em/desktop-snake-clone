import javax.swing.*;
import java.awt.*;
public
    class GameOverDialog
    extends JDialog {
    public GameOverDialog(JFrame parentFrame) {
        setTitle("Game over!");
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        addQuitButton(gbc);
        addRetryButton(gbc);

        validate();
        pack();
        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    private void addQuitButton(GridBagConstraints gbc) {
        JButton jButton = new JButton("Quit");

        jButton.addActionListener(e -> fireQuitButtonPressed());

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.insets = new Insets(10, 10, 10, 0);

        add(jButton, gbc);
    }

    private void addRetryButton(GridBagConstraints gbc) {
        JButton jButton = new JButton("Retry");

        jButton.addActionListener(e -> fireRetryButtonPressed());

        gbc.gridx = 1;
        gbc.gridy = 0;

        gbc.insets = new Insets(10, 10, 10, 10);

        add(jButton, gbc);
    }

    private UserActionListener userActionListener;

    public void setUserActionListener(UserActionListener userActionListener) {this.userActionListener = userActionListener;}

    private void fireQuitButtonPressed() {
        userActionListener.quitButtonPressed();
        SwingUtilities.invokeLater(() -> dispose());
    }

    private void fireRetryButtonPressed() {
        userActionListener.retryButtonPressed();
        SwingUtilities.invokeLater(() -> dispose());
    }
}
