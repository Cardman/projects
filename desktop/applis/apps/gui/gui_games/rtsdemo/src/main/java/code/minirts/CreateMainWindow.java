package code.minirts;

public class CreateMainWindow implements Runnable {
    private String lg;
    public CreateMainWindow(String _lg) {
        lg = _lg;
    }

    @Override
    public void run() {
        new MainWindow(lg);
    }
}
