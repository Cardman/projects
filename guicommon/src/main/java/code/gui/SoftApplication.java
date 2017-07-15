package code.gui;
import code.formathtml.images.data.Handler;

public abstract class SoftApplication extends SoftApplicationCore {

    protected SoftApplication() {
        Handler.install();
    }
}
