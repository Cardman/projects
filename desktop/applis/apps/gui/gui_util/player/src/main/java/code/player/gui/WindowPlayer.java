package code.player.gui;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import code.gui.*;
import code.gui.events.QuittingEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.images.BaseSixtyFourUtil;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.MonteCarloUtil;
import code.player.main.LaunchingPlayer;
import code.stream.*;
import code.scripts.messages.gui.MessPlayerGr;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.ElementList;
import code.sml.util.ResourcesMessagesUtil;
import code.threads.AbstractScheduledExecutorService;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class WindowPlayer extends GroupFrame implements LineShortListenable {
    private static final String ACCESS = "player.gui.mainwindow";

    private static final String CST_START = "start";
    private static final String CST_CLOSE = "close";
    private static final String CST_STOP_EVT = "stop";
    private static final String CST_TITLE_PLAYER = "titlePlayer";
    private static final String CST_SONGS = "songs";
    private static final String CST_RANDOM = "random";
    private static final String CST_CANNOT_READ_TITLE = "cannotReadTitle";
    private static final String CST_CANNOT_READ_MESSAGE_WPL = "cannotReadMessageWpl";
    private static final String CST_CANNOT_READ_MESSAGE_WAV = "cannotReadMessageWav";
    private static final String CST_RESOURCES_FOLDER = "resources_player";
    private static final String SEC = " s ";
    private static final String MIN = " m ";
    private static final String HOUR = " h ";
    private static final String SPACE = " ";
    private static final String REL_SEP = " / ";
    private static final String CST_PAUSE = "||";
    private static final String CST_KEY_PAUSE = "pause";
    private static final String CST_KEY_RANDOM = "random";
    private static final String CST_ATTR_VALUE = "value";
    private static final String CST_MEDIA = "media";
    private static final String CST_SRC = "src";
    private static final String CST_STOP = "[]";
    private static final String CST_PREVIOUS = "<|";
    private static final String CST_NEXT = "|>";
    private static final String CST_PLAY = ">";
    private static final String EMPTY = "";
    private static final String LINE_RETURN = "\n";
    private static final String WPL = ".wpl";
    private static final String WAV = ".wav";
    private static final String TXT = ".txt";
    private static final int SECOND_MILLIS = 1000;

    private static final int FIRST_DIGIT = '0';
    private static final int FIRST_LOW_LETTER = 'a';
    private static final int FIRST_UPP_LETTER = 'A';
    private static final byte SIXTY_FOUR_BITS = 64;
    private static final byte SIXTEEN_BITS = 16;
    private static final byte FOUR_BITS = 4;
    private static final byte THREE_COLORS_BYTES = 3;
    private static final byte PADDING = 127;
    private static final byte NB_LETTERS = 26;
    private static final byte NB_LETTERS_UPP_LOW = 52;
    private static final byte NB_DIGITS_LETTERS = 62;
    private final StringMap<String> messagesFiles = MessPlayerGr.ms();
    private StringMap<String> messages = new StringMap<String>();

    private AbstractScheduledExecutorService timer;
    private final TextLabel songsLabel = new TextLabel("");
    private AbsClipStream clipStream;
    private int noSong = -1;
    private final TextArea songs = new TextArea(10, 40);
    private final SongRenderer songRend = new SongRenderer();
    private final CustCheckBox random = new CustCheckBox();
    private final LabelButton play = new LabelButton(CST_PLAY);
    private final LabelButton playPrevious = new LabelButton(CST_PREVIOUS);
    private final LabelButton playNext = new LabelButton(CST_NEXT);
    private final LabelButton stop = new LabelButton(CST_STOP);
    private final TextLabel currentNoSong = new TextLabel(EMPTY);
    private final TextLabel currentSong = new TextLabel(EMPTY);
    private final ScrollPane scroll;
    private final TextLabel elapsedTime = new TextLabel(EMPTY);
    private boolean pausing;
    private boolean next;
    private boolean playSong;
    private int lastFrame;
    private StringList songsList = new StringList();
    private String contentList = "";

    private final CustButtonGroup group = new CustButtonGroup();

    private final CustList<RadioButton> radios = new CustList<RadioButton>();

    public WindowPlayer(String _lg, AbstractProgramInfos _list) {
        super(_lg, _list);
        initMessages(_lg);
        setTitle(messages.getVal(CST_TITLE_PLAYER));
        setIconImage(LaunchingPlayer.getIcon(_list.getImageFactory()));
        Panel pane_ = Panel.newPageBox();
        songsLabel.setText(messages.getVal(CST_SONGS));
        pane_.add(songsLabel);
        pane_.add(songs);
        random.setText(messages.getVal(CST_RANDOM));
        pane_.add(random);
        Panel actions_ = Panel.newLineBox();
//        playPrevious.setTextAndSize(PREVIOUS);
//        playPrevious.repaint();
        playPrevious.addMouseList(new PreviousSong(this));
        actions_.add(playPrevious);
        play.addMouseList(new ReadSong(this));
        actions_.add(play);
        playNext.addMouseList(new NextSong(this));
        actions_.add(playNext);
        stop.addMouseList(new StopSong(this));
        actions_.add(stop);
        pane_.add(actions_);
        scroll = new ScrollPane(songRend);
        scroll.setPreferredSize(new Dimension(256, 352));
        pane_.add(scroll);
        pane_.add(currentNoSong);
        pane_.add(currentSong);
        pane_.add(elapsedTime);
        pane_.add(new Clock(_list.getThreadFactory()));
//        for (String l: Constants.getAvailableLanguages()) {
//            RadioButton radio_ = new RadioButton(Constants.getDisplayLanguage(l));
//            radio_.addActionListener(new SetLanguage(l, getFrames()));
//            radio_.setSelected(StringUtil.quickEq(l,_lg));
//            group.add(radio_);
//            pane_.add(radio_);
//            radios.add(radio_);
//        }
        setContentPane(pane_);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
    }

    @Override
    public void dispose() {
        saveState();
        basicDispose();
    }

    public void loadList(String _fileName) {
        songs.append(_fileName);
        String txt_ = StreamTextFile.contentsOfFile(_fileName,getFileCoreStream(),getStreams());
        Document doc_ = DocumentBuilder.parseSax(txt_);
        if (doc_ != null) {
            playOrPause(true);
        }
    }

    public void initMessages(String _lg) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(CST_RESOURCES_FOLDER, _lg, ACCESS);
        String loadedResourcesMessages_ = messagesFiles.getVal(fileName_);
        messages = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
    }

    public void playOrPause(boolean _click) {
        if (clipStream == null) {
            noSong++;
            if (_click && random.isSelected()) {
                songsList = StringUtil.splitStrings(songs.getText(), LINE_RETURN);
                songsList.removeAllString(EMPTY);
                StringList songsList_ = new StringList();
                for (String o: suffledSongsNames(songsList,getGenerator())) {
                    songsList_.add(o);
                }
                songsList = songsList_;
            } else if (_click) {
                songsList = StringUtil.splitStrings(songs.getText(), LINE_RETURN);
                songsList.removeAllString(EMPTY);
            }
            if (noSong >= songsList.size()) {
                if (!_click) {
                    return;
                }
                noSong = 0;
                if (songsList.isEmpty()) {
                    return;
                }
            }
            if (!songsList.isValidIndex(noSong)) {
                ConfirmDialog.showMessage(this, messages.getVal(CST_CANNOT_READ_MESSAGE_WPL), messages.getVal(CST_CANNOT_READ_TITLE),getLanguageKey(),JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (songsList.get(noSong).endsWith(WAV) || songsList.get(noSong).endsWith(TXT)) {
                //.wav or .txt
                AbsClipStream c_;
                if (songsList.get(noSong).endsWith(WAV)) {
                    c_ = getFrames().openClip(StreamBinaryFile.loadFile(songsList.get(noSong),getFileCoreStream(),getStreams()));
                } else {
                    String txt_ = StreamTextFile.contentsOfFile(songsList.get(noSong),getFileCoreStream(),getStreams());
                    c_ = openClip(txt_);
                }
                while (true) {
                    if (c_ != null) {
                        break;
                    }
                    noSong++;
                    if (noSong >= songsList.size()) {
                        break;
                    }
                    if (songsList.get(noSong).endsWith(WAV)) {
                        c_ = getFrames().openClip(StreamBinaryFile.loadFile(songsList.get(noSong),getFileCoreStream(),getStreams()));
                    } else {
                        String txt_ = StreamTextFile.contentsOfFile(songsList.get(noSong),getFileCoreStream(),getStreams());
                        c_ = openClip(txt_);
                    }
                }
                if (songsList.isEmpty()) {
                    ConfirmDialog.showMessage(this, messages.getVal(CST_CANNOT_READ_MESSAGE_WAV), messages.getVal(CST_CANNOT_READ_TITLE),getLanguageKey(),JOptionPane.ERROR_MESSAGE);
                    return;
                }
                clipStream = c_;
            } else if (songsList.get(noSong).endsWith(WPL)) {
                //.wpl
                String txt_ = StreamTextFile.contentsOfFile(songsList.get(noSong),getFileCoreStream(),getStreams());
                Document doc_ = DocumentBuilder.parseSax(txt_);
                if (doc_ == null) {
                    ConfirmDialog.showMessage(this, messages.getVal(CST_CANNOT_READ_MESSAGE_WPL), messages.getVal(CST_CANNOT_READ_TITLE),getLanguageKey(),JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ElementList e_ = doc_.getElementsByTagName(CST_MEDIA);
                int len_ = e_.getLength();
                songsList.clear();
                for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
                    Element elt_ = e_.item(i);
                    String v_ = elt_.getAttribute(CST_SRC);
                    if (!v_.endsWith(WAV)) {
                        if (!v_.endsWith(TXT)) {
                            continue;
                        }
                    }
                    songsList.add(v_);
                }
                songsList.removeAllString(EMPTY);
                if (songsList.isEmpty()) {
                    ConfirmDialog.showMessage(this, messages.getVal(CST_CANNOT_READ_MESSAGE_WPL), messages.getVal(CST_CANNOT_READ_TITLE),getLanguageKey(),JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ElementList elts_ = doc_.getDocumentElement().getChildElements();
                boolean applyRand_ = true;
                if (elts_.getLength() > 0) {
                    if (StringUtil.quickEq(CST_KEY_PAUSE,elts_.get(0).getTagName())) {
                        String txtCont_ = elts_.get(0).getAttribute(CST_ATTR_VALUE);
                        int paused_ = NumberUtil.parseInt(txtCont_);
                        if (songsList.isValidIndex(paused_)) {
                            noSong = paused_;
                            applyRand_ = false;
                        }
                    } else if (StringUtil.quickEq(CST_KEY_RANDOM,elts_.get(0).getTagName())) {
                        random.setSelected(true);
                    }
                }
                if (applyRand_ && random.isSelected()) {
                    StringList songsList_ = new StringList();
                    for (String o: suffledSongsNames(songsList,getGenerator())) {
                        songsList_.add(o);
                    }
                    songsList = songsList_;
                }
                Document list_ = DocumentBuilder.newXmlDocument();
                Element mainDoc_ = list_.createElement("smil");
                for (String s: songsList) {
                    Element elt_ = list_.createElement(CST_MEDIA);
                    elt_.setAttribute(CST_SRC,s);
                    mainDoc_.appendChild(elt_);
                }
                contentList = mainDoc_.export();
                AbsClipStream c_;
                if (songsList.get(noSong).endsWith(WAV)) {
                    c_ = getFrames().openClip(StreamBinaryFile.loadFile(songsList.get(noSong),getFileCoreStream(),getStreams()));
                } else {
                    String txtIn_ = StreamTextFile.contentsOfFile(songsList.get(noSong),getFileCoreStream(),getStreams());
                    c_ = openClip(txtIn_);
                }
                while (true) {
                    if (c_ != null) {
                        break;
                    }
                    noSong++;
                    if (noSong >= songsList.size()) {
                        break;
                    }
                    if (songsList.get(noSong).endsWith(WAV)) {
                        c_ = getFrames().openClip(StreamBinaryFile.loadFile(songsList.get(noSong),getFileCoreStream(),getStreams()));
                    } else {
                        String txtIn_ = StreamTextFile.contentsOfFile(songsList.get(noSong),getFileCoreStream(),getStreams());
                        c_ = openClip(txtIn_);
                    }
                }
                clipStream = c_;
                if (songsList.isEmpty()) {
                    ConfirmDialog.showMessage(this, messages.getVal(CST_CANNOT_READ_MESSAGE_WPL), messages.getVal(CST_CANNOT_READ_TITLE),getLanguageKey(),JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            songRend.setSongs(songsList);
            songRend.setNoSong(noSong);
            songRend.setSize(getImageFactory());
            scroll.revalidate();
            pack();
            clipStream.start();
            clipStream.addLineListener(this);
            play.setTextAndSize(CST_PAUSE);
            currentNoSong.setText((noSong+1)+"/"+songsList.size());
            currentSong.setText(songsList.get(noSong));
            String strBegin_ = getStringTime(0);
            elapsedTime.setText(strBegin_+REL_SEP+getStringTime(clipStream.getMicrosecondLength()));
            if (timer == null) {
                timer = getThreadFactory().newScheduledExecutorService();
                timer.scheduleAtFixedRate(new UpdateSongTimeEvent(this),0,1000);
//                timer = new Timer(SECOND_MILLIS, new UpdateTimeEvent(this));
//                timer.start();
            }
        } else {
            if (clipStream.isRunning()) {
                saveState();
                lastFrame = clipStream.getFramePosition();
                pausing = true;
                clipStream.stop();
            } else {
                if (lastFrame < clipStream.getFrameLength()) {
                    clipStream.setFramePosition(lastFrame);
                } else {
                    clipStream.setFramePosition(0);
                }
                pausing = false;
                clipStream.start();
            }

        }
    }

    void saveState() {
        if (songsList.isEmpty()) {
            return;
        }
        Document list_ = DocumentBuilder.newXmlDocument();
        Element mainDoc_ = list_.createElement("smil");
        Element pause_ = list_.createElement(CST_KEY_PAUSE);
        pause_.setAttribute(CST_ATTR_VALUE,Long.toString(noSong));
        mainDoc_.appendChild(pause_);
        for (String s: songsList) {
            Element elt_ = list_.createElement(CST_MEDIA);
            elt_.setAttribute(CST_SRC,s);
            mainDoc_.appendChild(elt_);
        }
        contentList = mainDoc_.export();
        StreamTextFile.saveTextFile("last.wpl",contentList,getStreams());
    }

    public static StringList suffledSongsNames(StringList _list, AbstractGenerator _gene) {
        StringList list_ = new StringList(_list);
        Ints indexes_ = new Ints();
        Ints indexesEdited_ = new Ints();
        int size_ = list_.size();
        for (int i = IndexConstants.FIRST_INDEX; i < size_; i++) {
            indexes_.add(i);
        }
        while (!indexes_.isEmpty()) {
            long len_ = indexes_.size();
            int rem_ = (int) MonteCarloUtil.randomLong(len_,_gene);
            //rem_ >= 0 && rem_ < len_
            indexesEdited_.add(indexes_.get(rem_));
            indexes_.remove(rem_);
        }
        StringList newList_ = new StringList();
        for (int i: indexesEdited_) {
            newList_.add(list_.get(i));
        }
        list_.clear();
        list_.addAllElts(newList_);
        return list_;
    }

    public AbsClipStream openClip(String _imageString) {
        if (_imageString == null) {
            return null;
        }
        return getFrames().openClip(parseBaseSixtyFourBinary(_imageString));
    }

    public static byte[] parseBaseSixtyFourBinary(String _text) {
        int buflen_ = guessLength(_text);
        byte[] out_ = new byte[buflen_];
        int o_=0;

        int len_ = _text.length();

        byte[] quadruplet_ = new byte[FOUR_BITS];
        int q_=0;

        // convert each quadruplet to three bytes.
        for(int i=0; i<len_; i++ ) {
            char ch_ = _text.charAt(i);
            //v!=-1
            quadruplet_[q_] = BaseSixtyFourUtil.charToByte(ch_);
            q_++;

            if(q_==FOUR_BITS) {
                // quadruplet is now filled.
                int firstBytes_ = quadruplet_[0];
                int secondBytes_ = quadruplet_[1];
                int thirdBytes_ = quadruplet_[2];
                int fourthBytes_ = quadruplet_[THREE_COLORS_BYTES];
                out_[o_] = (byte) (FOUR_BITS * firstBytes_ + secondBytes_ / SIXTEEN_BITS);
                o_++;
                if( quadruplet_[2]!=PADDING ) {
                    out_[o_] = (byte)(secondBytes_ * SIXTEEN_BITS + thirdBytes_ / FOUR_BITS);
                    o_++;
                }
                if( quadruplet_[THREE_COLORS_BYTES]!=PADDING ) {
                    out_[o_] = (byte)(thirdBytes_ * SIXTY_FOUR_BITS +fourthBytes_);
                    o_++;
                }
                q_=0;
            }
        }
        return out_;
    }
    private static int guessLength(String _text) {
        int len_ = _text.length();

        int size_ = len_/FOUR_BITS*THREE_COLORS_BYTES;
        int j_=len_-1;
        while (j_ >= 0) {
            if (_text.charAt(j_) == '=') {
                j_--;
                continue;
            }
            break;
        }

        j_++;
        int padSize_ = len_-j_;
        if(padSize_ >2) {
            return size_;
        }

        return size_-padSize_;
    }
    public void nextSong() {
        if (clipStream != null && !next) {
            lastFrame = 0;
            clipStream.closeClip();
            tryClose();
            clipStream = null;
        }
    }

    public void previousSong() {
        if (clipStream != null && !next) {
            lastFrame = 0;
            noSong--;
            noSong--;
            clipStream.closeClip();
            tryClose();
            clipStream = null;
        }
    }

    public void stopSong() {
        if (clipStream != null && !next) {
            lastFrame = 0;
            playSong = false;
            songsList.clear();
            noSong = songsList.size();
            clipStream.closeClip();
            tryClose();
            clipStream = null;
        }
    }

    void tryClose() {
        if (clipStream == null) {
            return;
        }
        clipStream.closeStream();
    }

    @Override
    public void update(String _type, long _position) {
        String ev_ = toLowerCase(_type);
        if (StringUtil.quickEq(ev_, CST_START)) {
            //LineEvent.Type.START
            play.setTextAndSize(CST_PAUSE);
        } else if (StringUtil.quickEq(ev_, CST_STOP_EVT)) {
            //LineEvent.Type.STOP
            //The end of a song pass here
            play.setTextAndSize(CST_PLAY);
            if (!pausing) {
                next = true;
                playSong = true;
                clipStream.closeClip();
                tryClose();
                next = false;
            }
        } else if (StringUtil.quickEq(ev_, CST_CLOSE)) {
            //LineEvent.Type.CLOSE
            play.setTextAndSize(CST_PLAY);
            clipStream = null;
            pausing = false;
            if (!playSong) {
                return;
            }
            playOrPause(false);
            playSong = false;
        }
    }
    public static String toLowerCase(String _string) {
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder(len_);
        for (int i = 0; i < len_; i++) {
            char curr_ = _string.charAt(i);
            if (curr_ >= 'A' && curr_ <= 'Z') {
                int char_ = curr_ - 'A' + 'a';
                str_.append((char)char_);
                continue;
            }
            str_.append(curr_);
        }
        return str_.toString();
    }
    public void setElapsedTime() {
        if (clipStream == null) {
            return;
        }
        String l_ = getStringTime(clipStream.getMicrosecondLength());
        String c_ = getStringTime(clipStream.getMicrosecondPosition());
        elapsedTime.setText(c_+REL_SEP+l_);
    }

    private static String getStringTime(long _micro) {
        long t_ = _micro;
        long s_ = t_ / 1000000l;
        long m_ = s_ /60l;
        s_ = s_ % 60l;
        long h_ = m_ / 60l;
        m_ = m_ % 60;
        String time_ = EMPTY;
        if (h_ < 10) {
            time_ += SPACE + h_ + HOUR;
        } else {
            time_ += h_ + HOUR;
        }
        if (m_ < 10) {
            time_ += SPACE + m_ + MIN;
        } else {
            time_ += m_ + MIN;
        }
        if (s_ < 10) {
            time_ += SPACE + s_ + SEC;
        } else {
            time_ += s_ + SEC;
        }
        return time_;
    }

    @Override
    public void quit() {
        basicDispose();
    }

    @Override
    public boolean canChangeLanguage() {
        return true;
    }

    @Override
    public void changeLanguage(String _language) {
        setLanguageKey(_language);
        initMessages(_language);
        setTitle(messages.getVal(CST_TITLE_PLAYER));
        random.setText(messages.getVal(CST_RANDOM));
        songsLabel.setText(messages.getVal(CST_SONGS));
    }

    @Override
    public String getApplicationName() {
        return LaunchingPlayer.getMainWindowClass();
    }
}
