package code.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import code.gui.animations.AnimatedImage;
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

    public void init(Iconifiable _window, CustList<BufferedImage> _images) {
        if (_window != null) {
            setDialogIcon(_window);
        }
        setLocationRelativeToWindow(_window);
        if (!_images.isEmpty()) {
            anim = new PreparedLabel();
            anim.setPreferredSize(new Dimension(WIDTH_ANIM, HEIGTH_ANIM));
            animation = new AnimatedImage(anim, _images, TIME * 10);
        } else {
            anim = new PreparedLabel();
            anim.setPreferredSize(new Dimension(WIDTH_ANIM, HEIGTH_ANIM));
            anim.setOpaque(true);
            anim.setBackground(Color.WHITE);
        }
//        anim = new AnimatedLabel();
//        anim.setList(_images);
        Panel p_ = new Panel();
        p_.add(anim);
        setContentPane(p_);
        pack();
        setVisible(true);
    }

    public void startAnimation() {
        if (animation == null) {
            return;
        }
        animation.start();
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
