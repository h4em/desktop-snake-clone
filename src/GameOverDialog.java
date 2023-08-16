import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public
    class GameOverDialog
    extends JDialog
    implements GameStatusListener {
    private UserActionListener userActionListener;

    //TODO: zamkniecie dialogu
    public GameOverDialog(JFrame parentFrame) {
        setTitle("Game over!");
        setResizable(false);
        setAlwaysOnTop(true);

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
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userActionListener.quitButtonPressed();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.insets = new Insets(10, 10, 10, 0);

        add(jButton, gbc);
    }

    private void addRetryButton(GridBagConstraints gbc) {
        JButton jButton = new JButton("Retry");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userActionListener.retryButtonPressed();
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 0;

        gbc.insets = new Insets(10, 10, 10, 10);

        add(jButton, gbc);
    }

    public void setUserActionListener(UserActionListener ual) {userActionListener = ual;}

    @Override
    public void gameEnded() {
        ;
    }
    //TODO: przy close operation niech sie zamyka wszystko, albo niech nie da sie zamknac.
}
