package aiki.gui.dialogs;



import aiki.gui.WindowAiki;
import aiki.gui.WindowAikiInt;
import aiki.gui.dialogs.events.ClosingProgressingDialog;
import code.gui.*;
import code.gui.animations.AnimatedImage;
import code.gui.images.AbstractImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBooleanCore;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;
import code.util.CustList;

public final class ProgressingDialog implements ProgressDialog {

    private static final int HEIGTH_ANIM = 100;

    private static final int WIDTH_ANIM = 100;

    private static final int TIME = 10;

    private static final String PER_CENT = "0";

    private static final int DELTA = 100;
    private final AbsCommonFrame absDialog;

    private AbsProgressBar bar;
    private AbstractScheduledExecutorService images;
    private AbstractFuture taskImages;
    private AbstractScheduledExecutorService timer;
    private AbstractFuture future;

    private String perCent = PER_CENT;

//    private String titleDialog = "";

    private AnimatedImage animation;
//    private GroupFrame window;
    private AbstractAtomicBooleanCore loadFlag;
    private AbsPlainLabel comp;
    private final AbstractProgramInfos frames;

    public ProgressingDialog(AbstractProgramInfos _frameFactory, WindowAiki _frame) {
        this(_frameFactory, _frame.getModal());
    }

    public ProgressingDialog(AbstractProgramInfos _frameFactory, AbstractAtomicBooleanCore _frame) {
        frames = _frameFactory;
        absDialog = _frameFactory.getFrameFactory().newCommonFrame();
        absDialog.addWindowListener(new ClosingProgressingDialog(this, _frame));
        loadFlag =_frameFactory.getThreadFactory().newAtomicBoolean();
        comp = _frameFactory.getCompoFactory().newPlainLabel("");
    }

    public AbsCommonFrame getAbsDialog() {
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

    public void init(AbstractAtomicBooleanCore _load, WindowAiki _window, CustList<AbstractImage> _images, boolean _setVisibility) {
        init(_load, _window, _images, _setVisibility, _window.getCommonFrame());
    }

    public void init(AbstractAtomicBooleanCore _load, WindowAikiInt _window, CustList<AbstractImage> _images, boolean _setVisibility, AbsCommonFrame _com) {
        _window.getModal().set(true);
        loadFlag = _load;
        absDialog.setIconImage(_com.getImageIconFrame());
        perCent = PER_CENT;
        absDialog.setLocationRelativeTo(_com);
        AbsPanel contentPane_ = frames.getCompoFactory().newPageBox();
        comp = frames.getCompoFactory().newPlainLabel("");
        contentPane_.add(comp);
        AbsPanel label_ = frames.getCompoFactory().newLineBox();
        AbsPreparedLabel anim_;
        if (!_images.isEmpty()) {
            anim_ = GuiBaseUtil.prep(frames.getImageFactory());
            anim_.setPreferredSize(new MetaDimension(WIDTH_ANIM, HEIGTH_ANIM));
            animation = new AnimatedImage(frames.getImageFactory(), frames.getThreadFactory(), anim_, _images, TIME * 10);
        } else {
            anim_ = GuiBaseUtil.prep(frames.getImageFactory());
            anim_.setPreferredSize(new MetaDimension(WIDTH_ANIM, HEIGTH_ANIM));
            anim_.setOpaque(true);
            anim_.setBackground(GuiConstants.WHITE);
        }
//        anim.setList(_images);
        label_.add(anim_);
        contentPane_.add(label_);
        bar = frames.getCompoFactory().newAbsProgressBar();
        bar.setValue(0);
        contentPane_.add(bar);
        absDialog.setContentPane(contentPane_);
        absDialog.pack();
        timer = frames.getThreadFactory().newScheduledExecutorService();
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
        images = frames.getThreadFactory().newScheduledExecutorService();
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
        if (future != null) {
            future.cancel(true);
        }
        if (timer != null) {
            timer.shutdown();
        }
//        timer.stop();
    }

//    public void setTitleDialog(String _titleDialog) {
//        titleDialog = _titleDialog;
//    }
//
//    public String getTitleDialog() {
//        return titleDialog;
//    }

//    @Override
//    public AnimatedLabel getAnim() {
//        return anim;
//    }

    public void setPerCent(int _perCent) {
        perCent = Long.toString(_perCent);
        comp.setText(perCent+" %");
        if (bar == null) {
            return;
        }
        bar.setValue(_perCent);
    }

    public String getPercent() {
        return perCent;
    }
}
