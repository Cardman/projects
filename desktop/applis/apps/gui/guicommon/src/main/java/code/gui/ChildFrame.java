package code.gui;
import java.awt.image.BufferedImage;

public abstract class ChildFrame extends CommonFrame {

    private BufferedImage imageIconFrame;

    protected ChildFrame(String _lg) {
        super(_lg);
    }

    protected void setDialogIcon(GroupFrame _group) {
        setIconImage(_group.getImageIconFrame());
        imageIconFrame = _group.getImageIconFrame();
    }

    public abstract void closeWindow();

    @Override
    public BufferedImage getImageIconFrame() {
        return imageIconFrame;
    }
}
