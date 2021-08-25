package code.gui;
import java.awt.Color;
import java.awt.Dimension;

import code.gui.animations.AnimatedImage;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbsFrameFactory;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;
import code.util.CustList;

public abstract class ProgressingDialog implements AbsCloseableDialog,ProgressDialog {

    private static final int HEIGTH_ANIM = 100;

    private static final int WIDTH_ANIM = 100;

    private static final int TIME = 10;

    private static final String PER_CENT = "0";

    private static final int DELTA = 100;
    private final AbsDialog absDialog;

    private AbsPreparedLabel anim;

    private ProgressBar bar;

    private AbstractScheduledExecutorService timer;
    private AbstractFuture future;

    private String perCent = PER_CENT;

    private String titleDialog = "";

    private AnimatedImage animation;
    private GroupFrame window;

    public ProgressingDialog(AbsFrameFactory _frameFactory) {
        absDialog = _frameFactory.newDialog(this);

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

    public void init(GroupFrame _window, CustList<AbstractImage> _images, boolean _setVisibility) {
        absDialog.setDialogIcon(_window.getImageFactory(),_window);
        window = _window;
        perCent = PER_CENT;
        absDialog.setLocationRelativeTo(_window);
        Panel contentPane_ = Panel.newPageBox();
        Panel label_ = Panel.newLineBox();
        if (!_images.isEmpty()) {
            anim = FrameUtil.prep(_window.getImageFactory());
            anim.setPreferredSize(new Dimension(WIDTH_ANIM, HEIGTH_ANIM));
            animation = new AnimatedImage(_window.getImageFactory(), _window.getThreadFactory(), anim, _images, TIME * 10);
        } else {
            anim = FrameUtil.prep(_window.getImageFactory());
            anim.setPreferredSize(new Dimension(WIDTH_ANIM, HEIGTH_ANIM));
            anim.setOpaque(true);
            anim.setBackground(Color.WHITE);
        }
//        anim.setList(_images);
        label_.add(anim);
        contentPane_.add(label_);
        bar = new ProgressBar();
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

    @Override
    public void closeWindow() {
        absDialog.closeWindow();
        absDialog.getPane().removeAll();
        stopTimer();
    }

    public void startAnimation() {
        if (animation == null) {
            return;
        }
        window.getThreadFactory().newStartedThread(animation);
    }

    public void stopAnimation() {
        if (animation == null) {
            return;
        }
        animation.stopAnimation();
    }

    public void stopTimer() {
        future.cancel(true);
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
