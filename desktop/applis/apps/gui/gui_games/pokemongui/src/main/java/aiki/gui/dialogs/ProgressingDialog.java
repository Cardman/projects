package aiki.gui.dialogs;



import aiki.db.LoadFlag;
import aiki.gui.dialogs.events.ClosingProgressingDialog;
import aiki.gui.threads.LoadFlagImpl;
import code.gui.*;
import code.gui.animations.AnimatedImage;
import code.gui.images.AbstractImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;
import code.util.CustList;

public final class ProgressingDialog implements ProgressDialog {

    private static final int HEIGTH_ANIM = 100;

    private static final int WIDTH_ANIM = 100;

    private static final int TIME = 10;

    private static final String PER_CENT = "0";

    private static final int DELTA = 100;
    private final AbsDialog absDialog;

    private AbsProgressBar bar;
    private AbstractScheduledExecutorService images;
    private AbstractFuture taskImages;
    private AbstractScheduledExecutorService timer;
    private AbstractFuture future;

    private String perCent = PER_CENT;

    private String titleDialog = "";

    private AnimatedImage animation;
    private GroupFrame window;
    private LoadFlag loadFlag;

    public ProgressingDialog(AbstractProgramInfos _frameFactory) {
        absDialog = _frameFactory.getFrameFactory().newDialog(new ClosingProgressingDialog(this));
        loadFlag = new LoadFlagImpl(_frameFactory.getThreadFactory().newAtomicBoolean());
    }

    public AbsDialog getAbsDialog() {
        return absDialog;
    }

    @Override
    public void setTitle(String _title) {
        absDialog.setTitle(_title);
    }

    @Override
    public String getTitle() {
        return absDialog.getTitle();
    }

    public void init(LoadFlag _load, GroupFrame _window, CustList<AbstractImage> _images, boolean _setVisibility) {
        loadFlag = _load;
        absDialog.setDialogIcon(_window.getImageFactory(),_window.getCommonFrame());
        window = _window;
        perCent = PER_CENT;
        absDialog.setLocationRelativeTo(_window.getCommonFrame());
        AbsPanel contentPane_ = _window.getCompoFactory().newPageBox();
        AbsPanel label_ = _window.getCompoFactory().newLineBox();
        AbsPreparedLabel anim_;
        if (!_images.isEmpty()) {
            anim_ = FrameUtil.prep(_window.getImageFactory());
            anim_.setPreferredSize(new MetaDimension(WIDTH_ANIM, HEIGTH_ANIM));
            animation = new AnimatedImage(_window.getImageFactory(), _window.getThreadFactory(), anim_, _images, TIME * 10);
        } else {
            anim_ = FrameUtil.prep(_window.getImageFactory());
            anim_.setPreferredSize(new MetaDimension(WIDTH_ANIM, HEIGTH_ANIM));
            anim_.setOpaque(true);
            anim_.setBackground(GuiConstants.WHITE);
        }
//        anim.setList(_images);
        label_.add(anim_);
        contentPane_.add(label_);
        bar = window.getCompoFactory().newAbsProgressBar();
        bar.setValue(0);
        contentPane_.add(bar);
        absDialog.setContentPane(contentPane_);
        absDialog.pack();
        timer = _window.getThreadFactory().newScheduledExecutorService();
        future = timer.scheduleAtFixedRate(new TaskPaintingLabel(this),0,DELTA);
//        TaskPaintingLabel task_ = new TaskPaintingLabel(this);
//        timer = new Timer(0, task_);
//        timer.setDelay(DELTA);
//        timer.start();
        if (_setVisibility) {
            absDialog.setVisible(true);
        }
    }

//    @Override
//    public void closeWindow() {
//        absDialog.closeWindow();
//        absDialog.getPane().removeAll();
//        st();
//    }

    public void st() {
        stopTimer();
        loadFlag.set(false);
    }

    public void startAnimation() {
        if (animation == null) {
            return;
        }
        images = window.getThreadFactory().newScheduledExecutorService();
        animation.reset();
        taskImages = images.scheduleAtFixedRateNanos(animation,0,1);
    }

    public void stopAnimation() {
        if (taskImages == null) {
            return;
        }
        taskImages.cancel(false);
        images.shutdown();
        taskImages = null;
    }

    public void stopTimer() {
        future.cancel(true);
        timer.shutdown();
//        timer.stop();
    }

    public void setTitleDialog(String _titleDialog) {
        titleDialog = _titleDialog;
    }

    public String getTitleDialog() {
        return titleDialog;
    }

//    @Override
//    public AnimatedLabel getAnim() {
//        return anim;
//    }

    public void setPerCent(int _perCent) {
        perCent = Long.toString(_perCent);
        if (bar == null) {
            return;
        }
        bar.setValue(_perCent);
    }

    @Override
    public String getPercent() {
        return perCent;
    }
}
