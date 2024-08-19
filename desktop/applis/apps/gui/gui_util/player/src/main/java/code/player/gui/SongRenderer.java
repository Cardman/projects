package code.player.gui;


import code.gui.AbsPaintableLabel;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.sml.util.*;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class SongRenderer {

    public static final String SONGS_APP = "songs";
    public static final String PLAYER_FILE = "player";
    public static final String RECORDER_FILE = "recorder";
    private int noSong;

    private CustList<LoadedSongBytes> songs = new CustList<LoadedSongBytes>();
    private final AbsPaintableLabel paintableLabel;
    private final AbsCompoFactory compo;
    public SongRenderer(AbsCompoFactory _compoFactory) {
        compo = _compoFactory;
        paintableLabel = _compoFactory.newAbsPaintableLabel();
    }
    public static TranslationsAppli initAppliTr(TranslationsLg _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getMapping().addEntry(SONGS_APP, a_);
        return a_;
    }
    public static TranslationsAppli getAppliTr(TranslationsLg _lgs) {
        return _lgs.getMapping().getVal(SONGS_APP);
    }
    public static StringMap<String> valPlayerMessages(TranslationsLg _lg) {
        return SongRenderer.getAppliTr(_lg).getMapping().getVal(PLAYER_FILE).getMapping();
    }
    public static StringMap<String> valRecorderMessages(TranslationsLg _lg) {
        return SongRenderer.getAppliTr(_lg).getMapping().getVal(RECORDER_FILE).getMapping();
    }
    public static TranslationsAppli updateEn(TranslationsAppli _a){
        _a.getMapping().addEntry(PLAYER_FILE,MessagesPlayer.en());
        _a.getMapping().addEntry(RECORDER_FILE,MessagesRecorder.en());
        return _a;
    }
    public static TranslationsAppli updateFr(TranslationsAppli _a){
        _a.getMapping().addEntry(PLAYER_FILE,MessagesPlayer.fr());
        _a.getMapping().addEntry(RECORDER_FILE,MessagesRecorder.fr());
        return _a;
    }
    public void setSongs(CustList<LoadedSongBytes> _songs) {
        songs = new CustList<LoadedSongBytes>(_songs);
    }

    public void setNoSong(int _noSong) {
        noSong = _noSong;
    }

    public void setSize(AbstractImageFactory _fact) {
        int w_ = 0;
        for (LoadedSongBytes s: songs) {
            w_ = NumberUtil.max(w_,compo.stringWidth(paintableLabel.getMetaFont(),s.getName()));
        }
        int h_ = compo.heightFont(paintableLabel.getMetaFont()) * songs.size();
//        if (w_ <= 0 || h_ <= 0) {
//            paintableLabel.setEmptyIcon();
//            return;
//        }
        AbstractImage img_ = _fact.newImageArgb(NumberUtil.max(1,w_), NumberUtil.max(1,h_));
//        CustGraphics gr_ = new CustGraphics(img_.getGraphics());
        img_.setFont(paintableLabel);
        paintComponent(img_);
        paintableLabel.setIcon(_fact,img_);
    }

    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0, 0, _g.getWidth(), _g.getHeight());
        int hstring_ = compo.heightFont(paintableLabel.getMetaFont());
        for (int i = IndexConstants.FIRST_INDEX; i < songs.size(); i++) {
            if (i == noSong) {
                _g.setColor(GuiConstants.YELLOW);
                _g.fillRect(0, hstring_ * i, _g.getWidth(), hstring_);
            }
            _g.setColor(GuiConstants.BLACK);
            _g.drawString(songs.get(i).getName(), 0, hstring_ + hstring_ * i);
        }
    }

    public AbsPaintableLabel getPaintableLabel() {
        return paintableLabel;
    }
}
