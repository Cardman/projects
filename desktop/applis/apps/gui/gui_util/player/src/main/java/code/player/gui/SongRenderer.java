package code.player.gui;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

import code.gui.CustGraphics;
import code.gui.PaintableLabel;
import code.util.StringList;
import code.util.core.IndexConstants;

public class SongRenderer extends PaintableLabel {

    private int noSong;

    private StringList songs = new StringList();

    public SongRenderer() {
    }

    public SongRenderer(StringList _songs) {
        songs = _songs;
    }

    public void setSongs(StringList _songs) {
        songs = new StringList(_songs);
    }

    public void setNoSong(int _noSong) {
        noSong = _noSong;
    }

    public void setSize() {
        FontMetrics f_ = getFontMetrics(getFont());
        int w_ = 0;
        for (String s: songs) {
            int ws_ = f_.stringWidth(s);
            if (ws_ > w_) {
                w_ = ws_;
            }
        }
        int h_ = f_.getHeight() * songs.size();
        if (w_ <= 0 || h_ <= 0) {
            return;
        }
        BufferedImage img_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_ARGB);
        CustGraphics gr_ = new CustGraphics(img_.getGraphics());
        gr_.setFont(getFont());
        paintComponent(gr_);
        setIcon(img_);
    }

    @Override
    public void paintComponent(CustGraphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0, 0, getWidth(), getHeight());
        FontMetrics f_ = getFontMetrics(getFont());
        int hstring_ = f_.getHeight();
        for (int i = IndexConstants.FIRST_INDEX; i < songs.size(); i++) {
            if (i == noSong) {
                _g.setColor(Color.YELLOW);
                _g.fillRect(0, hstring_ * i, getWidth(), hstring_);
            }
            _g.setColor(Color.BLACK);
            _g.drawString(songs.get(i), 0, hstring_ + hstring_ * i);
        }
    }

}
