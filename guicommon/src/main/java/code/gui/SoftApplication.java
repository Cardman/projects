package code.gui;
import code.imagesurl.data.Handler;

public abstract class SoftApplication extends SoftApplicationCore {

    protected SoftApplication() {
        Handler.install();
    }
}
