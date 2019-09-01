package code.expressionlanguage.gui.unit;

public final class CreateMainWindow implements Runnable {
    private String language;

    public CreateMainWindow(String _language) {
        language = _language;
    }

    @Override
    public void run() {
        new MainWindow(language);
    }
}
