public class Binder  {
    private Game game;
    private Frame frame;

    public Binder(Game game, Frame frame) {
        this.game = game;
        this.frame = frame;
    }

    public void bindComponents() {
        setUserActionListener();
        setGameEventListener();
        setViewModelDataSource();
        setDataModelListener();
    }

    private void setUserActionListener() {
        frame.setUserActionListener(game);
    }

    private void setGameEventListener() {
        game.setGameEventListener(frame);
        game.fireHighScoreUpdated();
    }

    private void setViewModelDataSource()  {
        Gameboard gameboard = game.getGameBoard();
        GameTableViewModel gameTableModel = (GameTableViewModel) frame.getGameTable().getModel();
        gameTableModel.setGameBoardData(gameboard.getGameBoard());
    }

    private void setDataModelListener() {
        Gameboard gameboard = game.getGameBoard();
        GameTableViewModel gameTableModel = (GameTableViewModel) frame.getGameTable().getModel();

        gameboard.setDataModelListener(gameTableModel);
    }
}
