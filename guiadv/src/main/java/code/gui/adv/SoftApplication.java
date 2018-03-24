package code.gui.adv;
import code.gui.SoftApplicationCore;
import code.imagesurl.data.Handler;

public abstract class SoftApplication extends SoftApplicationCore {

    protected SoftApplication() {
        Handler.install();
    }
}
