package code.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import code.images.ConverterBufferedImage;
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

    public void init(Iconifiable _window, CustList<BufferedImage> _images, boolean _setVisibility) {
        if (_window != null) {
            setDialogIcon(_window);
        }
        perCent = PER_CENT;
        setLocationRelativeToWindow(_window);
        JPanel contentPane_ = new JPanel();
        contentPane_.setLayout(new BoxLayout(contentPane_, BoxLayout.PAGE_AXIS));
        JPanel label_ = new JPanel();
        if (!_images.isEmpty()) {
            byte[] data_ = ConverterBufferedImage.toBytesGif(_images, TIME, true);
            anim = new JLabel(new ImageIcon(data_));
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
        getContentPane().removeAll();
        stopTimer();
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
