package code.player.gui;


import code.gui.AbsPaintableLabel;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.util.StringList;
import code.util.core.IndexConstants;

public final class SongRenderer {

    private int noSong;

    private StringList songs = new StringList();
    private final AbsPaintableLabel paintableLabel;
    private final AbsCompoFactory compo;
    public SongRenderer(AbsCompoFactory _compoFactory) {
        compo = _compoFactory;
        paintableLabel = _compoFactory.newAbsPaintableLabel();
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
            int ws_ = compo.stringWidth(paintableLabel.getMetaFont(),s);
            if (ws_ > w_) {
                w_ = ws_;
            }
        }
        int h_ = paintableLabel.heightFont() * songs.size();
        if (w_ <= 0 || h_ <= 0) {
            paintableLabel.setEmptyIcon();
            return;
        }
        AbstractImage img_ = _fact.newImageArgb(w_, h_);
//        CustGraphics gr_ = new CustGraphics(img_.getGraphics());
        img_.setFont(paintableLabel);
        paintComponent(img_);
        paintableLabel.setIcon(_fact,img_);
    }

    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0, 0, paintableLabel.getWidth(), paintableLabel.getHeight());
        int hstring_ = paintableLabel.heightFont();
        for (int i = IndexConstants.FIRST_INDEX; i < songs.size(); i++) {
            if (i == noSong) {
                _g.setColor(GuiConstants.YELLOW);
                _g.fillRect(0, hstring_ * i, paintableLabel.getWidth(), hstring_);
            }
            _g.setColor(GuiConstants.BLACK);
            _g.drawString(songs.get(i), 0, hstring_ + hstring_ * i);
        }
    }

    public AbsPaintableLabel getPaintableLabel() {
        return paintableLabel;
    }
}
