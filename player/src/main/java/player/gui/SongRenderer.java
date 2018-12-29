package player.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import code.gui.PaintableLabel;
import code.util.CustList;
import code.util.StringList;

public class SongRenderer extends PaintableLabel implements MouseListener {

    private int noSong;

    private StringList songs = new StringList();

    public SongRenderer() {
        addMouseListener(this);
        setDefaultSize();
    }

    public SongRenderer(StringList _songs) {
        songs = _songs;
    }

    @Override
    public void interceptDimension(int _w, int _h) {
        super.interceptDimension(_w,_h);
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
        setPreferredSize(new Dimension(w_, f_.getHeight() * songs.size()));
    }
    public void setDefaultSize() {
        setSize(new Dimension(100, 60));
    }

    @Override
    public void paintComponent(Graphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0, 0, getWidth(), getHeight());
        FontMetrics f_ = getFontMetrics(getFont());
        int hstring_ = f_.getHeight();
        for (int i = CustList.FIRST_INDEX; i < songs.size(); i++) {
            if (i == noSong) {
                _g.setColor(Color.YELLOW);
                _g.fillRect(0, hstring_ * i, getWidth(), hstring_);
            }
            _g.setColor(Color.BLACK);
            _g.drawString(songs.get(i), 0, hstring_ + hstring_ * i);
        }
    }

    @Override
    public void mouseClicked(MouseEvent _e) {
    }

    @Override
    public void mousePressed(MouseEvent _e) {
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent _e) {
    }

    @Override
    public void mouseExited(MouseEvent _e) {
    }
}
