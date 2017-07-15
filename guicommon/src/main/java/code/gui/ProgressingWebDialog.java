package code.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.images.ConverterBufferedImage;
import code.util.CustList;

public final class ProgressingWebDialog extends Dialog implements ProgressDialog {

    private static final int HEIGTH_ANIM = 100;

    private static final int WIDTH_ANIM = 100;

    private static final int TIME = 10;

    private static final String PER_CENT = "";

    private JLabel anim;

    public ProgressingWebDialog() {
        setModal(false);
    }

    public void init(Iconifiable _window, CustList<BufferedImage> _images) {
        if (_window != null) {
            setDialogIcon(_window);
        }
        setLocationRelativeToWindow(_window);
        byte[] data_ = null;
        if (!_images.isEmpty()) {
            data_ = ConverterBufferedImage.toBytesGif(_images, TIME, true);
        }
        if (data_ != null) {
            anim = new JLabel(new ImageIcon(data_));
        } else {
            anim = new JLabel();
            anim.setPreferredSize(new Dimension(WIDTH_ANIM, HEIGTH_ANIM));
            anim.setOpaque(true);
            anim.setBackground(Color.WHITE);
        }
//        anim = new AnimatedLabel();
//        anim.setList(_images);
        setContentPane(anim);
        pack();
        setVisible(true);
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
