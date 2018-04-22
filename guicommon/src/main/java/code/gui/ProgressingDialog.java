package code.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import code.gui.animations.AnimatedImage;
import code.util.CustList;

public abstract class ProgressingDialog extends Dialog implements ProgressDialog {

    private static final int HEIGTH_ANIM = 100;

    private static final int WIDTH_ANIM = 100;

    private static final int TIME = 10;

    private static final String PER_CENT = "0";

    private static final int DELTA = 100;

    private JLabel anim;

    private JProgressBar bar;

    private Timer timer;

    private String perCent = PER_CENT;

    private String titleDialog = "";

    private AnimatedImage animation;

    public void init(Iconifiable _window, CustList<BufferedImage> _images, boolean _setVisibility) {
        if (_window != null) {
            setDialogIcon(_window);
        }
        perCent = PER_CENT;
        setLocationRelativeToWindow(_window);
        Panel contentPane_ = new Panel();
        contentPane_.setLayout(new BoxLayout(contentPane_.getComponent(), BoxLayout.PAGE_AXIS));
        Panel label_ = new Panel();
        if (!_images.isEmpty()) {
            anim = new JLabel();
            anim.setPreferredSize(new Dimension(WIDTH_ANIM, HEIGTH_ANIM));
            animation = new AnimatedImage(anim, _images, TIME * 10);
        } else {
            anim = new JLabel();
            anim.setPreferredSize(new Dimension(WIDTH_ANIM, HEIGTH_ANIM));
            anim.setOpaque(true);
            anim.setBackground(Color.WHITE);
        }
//        anim.setList(_images);
        label_.add(anim);
        contentPane_.add(label_);
        bar = new JProgressBar();
        bar.setValue(0);
        contentPane_.add(bar);
        setContentPane(contentPane_);
        pack();
        TaskPaintingLabel task_ = new TaskPaintingLabel(this);
        timer = new Timer(0, task_);
        timer.setDelay(DELTA);
        timer.start();
        if (_setVisibility) {
            setVisible(true);
        }
    }

    @Override
    public void closeWindow() {
        super.closeWindow();
        getPane().removeAll();
        stopTimer();
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

    public void stopTimer() {
        timer.stop();
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

    public void setPerCent(String _perCent) {
        perCent = _perCent;
        if (bar == null) {
            return;
        }
        try {
            bar.setValue(Integer.parseInt(_perCent));
        } catch (NumberFormatException _0) {
            _0.printStackTrace();
        }
    }

    @Override
    public String getPercent() {
        return perCent;
    }
}
