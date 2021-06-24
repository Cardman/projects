package code.player.gui;
import java.awt.Color;

import code.gui.PaintableLabel;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
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

    public void setSize(AbstractImageFactory _fact) {
        int w_ = 0;
        for (String s: songs) {
            int ws_ = stringWidth(s);
            if (ws_ > w_) {
                w_ = ws_;
            }
        }
        int h_ = heightFont() * songs.size();
        if (w_ <= 0 || h_ <= 0) {
            setEmptyIcon();
            return;
        }
        AbstractImage img_ = _fact.newImageArgb(w_, h_);
//        CustGraphics gr_ = new CustGraphics(img_.getGraphics());
        img_.setFont(getFont());
        paintComponent(img_);
        setIcon(_fact,img_);
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0, 0, getWidth(), getHeight());
        int hstring_ = heightFont();
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
