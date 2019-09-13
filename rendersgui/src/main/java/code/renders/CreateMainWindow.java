package code.renders;

import code.util.StringMap;

public final class CreateMainWindow implements Runnable {
    private String language;

    private StringMap<Object> args;
    public CreateMainWindow(String _language, StringMap<Object> _args) {
        language = _language;
        args = _args;
    }

    @Override
    public void run() {
        MainWindow mainWindow_ = new MainWindow(language);
        if (!args.isEmpty()) {
            mainWindow_.loadRenderConf(args.firstKey());
        }
    }
}
