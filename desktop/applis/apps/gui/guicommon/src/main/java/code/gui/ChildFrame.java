package code.gui;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;

import java.awt.image.BufferedImage;

public abstract class ChildFrame extends CommonFrame {

    private final AbstractProgramInfos frames;
    private AbstractImage imageIconFrame;

    protected ChildFrame(String _lg,GroupFrame _group) {
        super(_lg);
        frames = _group.getFrames();
    }

    @Override
    public AbstractProgramInfos getFrames() {
        return frames;
    }

    protected void setDialogIcon(GroupFrame _group) {
        setIconImage(_group.getImageIconFrame());
        imageIconFrame = _group.getImageIconFrame();
    }

    public abstract void closeWindow();

    @Override
    public AbstractImage getImageIconFrame() {
        return imageIconFrame;
    }
}
