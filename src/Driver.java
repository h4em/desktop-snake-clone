public class Driver {
    public static void main(String args[]) {
        Game game = new Game();
        Frame frame = new Frame();
        frame.setSetupListener(() -> new Binder(game, frame).bindComponents());
    }
}
