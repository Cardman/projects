package code.gui;



import code.gui.animations.AnimatedImage;
import code.gui.document.ProcessingSession;
import code.gui.images.AbstractImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class ProgressingWebDialog implements ProgressDialog {

    private static final int HEIGTH_ANIM = 100;

    private static final int WIDTH_ANIM = 100;

    private static final int TIME = 10;

    private static final String PER_CENT = "";
    private final AbsDialog absDialog;
    private final AbsCompoFactory compoFactory;
    private AbstractScheduledExecutorService images;
    private AbstractFuture taskImages;
    private AnimatedImage animation;

    public ProgressingWebDialog(AbstractProgramInfos _frameFactory) {
        compoFactory = _frameFactory.getCompoFactory();
        absDialog = _frameFactory.getFrameFactory().newDialog();
        absDialog.setModal(false);
    }

    public AbsDialog getAbsDialog() {
        return absDialog;
    }

    @Override
    public String getTitle() {
        return absDialog.getTitle();
    }

    @Override
    public void setTitle(String _title) {
        absDialog.setTitle(_title);
    }

    public void init(AbstractThreadFactory _fact, ProcessingSession _session, Iconifiable _window, CustList<AbstractImage> _images) {
        if (_window != null) {
            absDialog.setDialogIcon(_session.getGene().getImageFactory(),_window);
        }
        absDialog.setLocationRelativeToWindow(_window);
        AbsPreparedLabel anim_;
        if (!_images.isEmpty()) {
            anim_ = FrameUtil.prep(_session.getGene().getImageFactory());
            anim_.setPreferredSize(new MetaDimension(WIDTH_ANIM, HEIGTH_ANIM));
            animation = new AnimatedImage(_session.getGene().getImageFactory(),_fact, anim_, _images, TIME * 10);
        } else {
            anim_ = FrameUtil.prep(_session.getGene().getImageFactory());
            anim_.setPreferredSize(new MetaDimension(WIDTH_ANIM, HEIGTH_ANIM));
            anim_.setOpaque(true);
            anim_.setBackground(GuiConstants.WHITE);
        }
//        anim = new AnimatedLabel();
//        anim.setList(_images);
        AbsPanel p_ = compoFactory.newLineBox();
        p_.add(anim_);
        absDialog.setContentPane(p_);
        absDialog.pack();
        absDialog.setVisible(true);
    }

    public void startAnimation(AbstractThreadFactory _fact) {
        if (animation == null) {
            return;
        }
        images = _fact.newScheduledExecutorService();
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
//    @Override
//    public AnimatedLabel getAnim() {
//        return anim;
//    }

    @Override
    public String getPercent() {
        return PER_CENT;
    }
}
