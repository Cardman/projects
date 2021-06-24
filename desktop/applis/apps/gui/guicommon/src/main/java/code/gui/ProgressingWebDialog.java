package code.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import code.gui.animations.AnimatedImage;
import code.gui.document.ProcessingSession;
import code.gui.images.AbstractImage;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class ProgressingWebDialog extends Dialog implements ProgressDialog {

    private static final int HEIGTH_ANIM = 100;

    private static final int WIDTH_ANIM = 100;

    private static final int TIME = 10;

    private static final String PER_CENT = "";

    private PreparedLabel anim;
    private AnimatedImage animation;

    public ProgressingWebDialog() {
        setModal(false);
    }

    public void init(AbstractThreadFactory _fact, ProcessingSession _session, Iconifiable _window, CustList<AbstractImage> _images) {
        if (_window != null) {
            setDialogIcon(_session.getGene().getImageFactory(),_window);
        }
        setLocationRelativeToWindow(_window);
        if (!_images.isEmpty()) {
            anim = PreparedLabel.prep(_session.getGene().getImageFactory());
            anim.setPreferredSize(new Dimension(WIDTH_ANIM, HEIGTH_ANIM));
            animation = new AnimatedImage(_session.getGene().getImageFactory(),_fact,anim, _images, TIME * 10);
        } else {
            anim = PreparedLabel.prep(_session.getGene().getImageFactory());
            anim.setPreferredSize(new Dimension(WIDTH_ANIM, HEIGTH_ANIM));
            anim.setOpaque(true);
            anim.setBackground(Color.WHITE);
        }
//        anim = new AnimatedLabel();
//        anim.setList(_images);
        Panel p_ = Panel.newLineBox();
        p_.add(anim);
        setContentPane(p_);
        pack();
        setVisible(true);
    }

    public void startAnimation(AbstractThreadFactory _fact) {
        if (animation == null) {
            return;
        }
        _fact.newStartedThread(animation);
    }

    public void stopAnimation() {
        if (animation == null) {
            return;
        }
        animation.stopAnimation();
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
