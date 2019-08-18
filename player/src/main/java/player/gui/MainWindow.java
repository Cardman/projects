package player.gui;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineEvent;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import code.gui.*;
import code.gui.images.ConverterGraphicBufferedImage;
import code.images.BaseSixtyFourUtil;
import code.maths.montecarlo.AbMonteCarlo;
import code.resources.ClipStream;
import code.resources.ResourceFiles;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.ElementList;
import code.sml.stream.ExtractFromFiles;
import code.stream.StreamBinaryFile;
import code.stream.StreamSoundFile;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public class MainWindow extends GroupFrame {
    private static final String ACCESS = "player.gui.mainwindow";

    private static final String START = "start";
    private static final String CLOSE = "close";
    private static final String STOP_EVT = "stop";
    private static final String ICON = "player.txt";
    private static final String TITLE_PLAYER = "titlePlayer";
    private static final String SONGS = "songs";
    private static final String RANDOM = "random";
    private static final String CANNOT_READ_TITLE = "cannotReadTitle";
    private static final String CANNOT_READ_MESSAGE_WPL = "cannotReadMessageWpl";
    private static final String CANNOT_READ_MESSAGE_WAV = "cannotReadMessageWav";
    private static final String RESOURCES_FOLDER = "resources_player";
    private static final String XML_HEADER = "<?xml";
    private static final String XML_END_HEADER = "?>";
    private static final String WPL_HEADER = "<?wpl";
    private static final String SEC = " s ";
    private static final String MIN = " m ";
    private static final String HOUR = " h ";
    private static final String SPACE = " ";
    private static final String REL_SEP = " / ";
    private static final String PAUSE = "||";
    private static final String MEDIA = "media";
    private static final String SRC = "src";
    private static final String STOP = "[]";
    private static final String PREVIOUS = "<|";
    private static final String NEXT = "|>";
    private static final String PLAY = ">";
    private static final String EMPTY = "";
    private static final String LINE_RETURN = "\n";
    private static final String WPL = ".wpl";
    private static final String WAV = ".wav";
    private static final String TXT = ".txt";
    private static final int SECOND_MILLIS = 1000;
    private static final short LIMIT_ASCII = 128;
    private static final String BEGIN_ESC = "&#";

    private static final String END_ESC = ";";
    private static final int FIRST_DIGIT = '0';
    private static final int FIRST_LOW_LETTER = 'a';
    private static final int FIRST_UPP_LETTER = 'A';
    private static final short BYTE = 256;
    private static final byte SIXTY_FOUR_BITS = 64;
    private static final byte SIXTEEN_BITS = 16;
    private static final byte FOUR_BITS = 4;
    private static final byte THREE_COLORS_BYTES = 3;
    private static final byte PADDING = 127;
    private static final byte NB_LETTERS = 26;
    private static final byte NB_LETTERS_UPP_LOW = 52;
    private static final byte NB_DIGITS_LETTERS = 62;
    private static StringMap<String> _messages_ = new StringMap<String>();

    private Timer timer;
    private TextLabel songsLabel = new TextLabel("");
    private ClipStream clipStream;
    private int noSong = -1;
    private TextArea songs = new TextArea(10, 40);
    private SongRenderer songRend = new SongRenderer();
    private CustCheckBox random = new CustCheckBox();
    private LabelButton play = new LabelButton(PLAY);
    private LabelButton playPrevious = new LabelButton(PREVIOUS);
    private LabelButton playNext = new LabelButton(NEXT);
    private LabelButton stop = new LabelButton(STOP);
    private TextLabel currentSong = new TextLabel(EMPTY);
    private TextLabel elapsedTime = new TextLabel(EMPTY);
    private boolean pausing;
    private boolean next;
    private boolean playSong;
    private int lastFrame;
    private StringList songsList = new StringList();

    private CustButtonGroup group = new CustButtonGroup();

    private CustList<RadioButton> radios = new CustList<RadioButton>();

    public MainWindow(String _lg,String[] _args) {
        super(_lg);
        initMessages(_lg);
        setTitle(_messages_.getVal(TITLE_PLAYER));
        setIconImage(getImage());
        Panel pane_ = Panel.newPageBox();
        songsLabel.setText(_messages_.getVal(SONGS));
        pane_.add(songsLabel);
        pane_.add(songs);
        random.setText(_messages_.getVal(RANDOM));
        pane_.add(random);
        Panel actions_ = new Panel();
//        playPrevious.setTextAndSize(PREVIOUS);
//        playPrevious.repaint();
        playPrevious.addMouseListener(new PreviousSong(this));
        actions_.add(playPrevious);
        play.addMouseListener(new ReadSong(this));
        actions_.add(play);
        playNext.addMouseListener(new NextSong(this));
        actions_.add(playNext);
        stop.addMouseListener(new StopSong(this));
        actions_.add(stop);
        if (_args.length > 1) {
            //a list
            for (String s: _args) {
                if (!s.endsWith(TXT)) {
                    if (!s.endsWith(WAV)) {
                        continue;
                    }
                }
                songs.append(s+LINE_RETURN);
            }
        } else {
            //one or zero files
            if (_args.length == 1) {
                if (_args[0].endsWith(WAV) || _args[0].endsWith(TXT) || _args[0].endsWith(WPL)) {
                    songs.append(_args[0]);
                }
            }
        }
        pane_.add(actions_);
        ScrollPane scr_ = new ScrollPane(songRend);
        scr_.setPreferredSize(new Dimension(100, 60));
        pane_.add(scr_);
        pane_.add(currentSong);
        pane_.add(elapsedTime);
        pane_.add(new Clock());
        for (String l: Constants.getAvailableLanguages()) {
            RadioButton radio_ = new RadioButton(Constants.getDisplayLanguage(l));
            radio_.addActionListener(new SetLanguage(l));
            radio_.setSelected(StringList.quickEq(l,_lg));
            group.add(radio_);
            pane_.add(radio_);
            radios.add(radio_);
        }
        setContentPane(pane_);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void initMessages(String _lg) {
        _messages_ = ExtractFromFiles.getMessagesFromLocaleClass(RESOURCES_FOLDER,_lg, ACCESS);
    }

    private static BufferedImage getImage() {
        BufferedImage image_ = null;
        try {
            String file_ = ResourceFiles.ressourceFichier(RESOURCES_FOLDER+StreamTextFile.SEPARATEUR+ ICON);
            image_ = ConverterGraphicBufferedImage.decodeToImage(BaseSixtyFourUtil.getImageByString(file_));
        } catch (RuntimeException _0) {
        }
        return image_;
    }

    public void playOrPause(boolean _click) {
        if (clipStream == null) {
            noSong ++;
            if (_click && random.isSelected()) {
                songsList = StringList.splitStrings(songs.getText(), LINE_RETURN);
                songsList.removeAllString(EMPTY);
                StringList songsList_ = new StringList();
                for (String o: suffledSongsNames(songsList)) {
                    songsList_.add(o);
                }
                songsList = songsList_;
            } else if (_click) {
                songsList = StringList.splitStrings(songs.getText(), LINE_RETURN);
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
            try {
                if (songsList.get(noSong).endsWith(WAV) || songsList.get(noSong).endsWith(TXT)) {
                    //.wav or .txt
                    ClipStream c_;
                    if (songsList.get(noSong).endsWith(WAV)) {
                        c_ = StreamSoundFile.openClip(StreamBinaryFile.loadFile(songsList.get(noSong)));
                    } else {
                        String txt_ = StreamTextFile.contentsOfFile(songsList.get(noSong));
                        c_ = openClip(txt_);
                    }
                    while (true) {
                        if (c_ != null) {
                            break;
                        }
                        noSong ++;
                        if (noSong >= songsList.size()) {
                            break;
                        }
                        if (songsList.get(noSong).endsWith(WAV)) {
                            c_ = StreamSoundFile.openClip(StreamBinaryFile.loadFile(songsList.get(noSong)));
                        } else {
                            String txt_ = StreamTextFile.contentsOfFile(songsList.get(noSong));
                            c_ = openClip(txt_);
                        }
                    }
                    if (songsList.isEmpty()) {
                        ConfirmDialog.showMessage(this,_messages_.getVal(CANNOT_READ_MESSAGE_WAV),_messages_.getVal(CANNOT_READ_TITLE),getLanguageKey(),JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    clipStream = c_;
                } else if (songsList.get(noSong).endsWith(WPL)) {
                    //.wpl
                    String txt_ = StreamTextFile.contentsOfFile(songsList.get(noSong));
                    txt_ = StringList.replace(txt_, WPL_HEADER, XML_HEADER);
                    int endHeader_ = txt_.indexOf(XML_END_HEADER);
                    if (endHeader_ < 0) {
                        endHeader_ = 0;
                    } else {
                        endHeader_ += 2;
                    }
                    txt_ = txt_.substring(endHeader_);
                    StringBuilder escapedXml_ = new StringBuilder();
                    for (char c: txt_.toCharArray()) {
                        if (c >= LIMIT_ASCII) {
                            escapedXml_.append(BEGIN_ESC);
                            escapedXml_.append((int)c);
                            escapedXml_.append(END_ESC);
                        } else {
                            escapedXml_.append(c);
                        }
                    }
                    txt_ = escapedXml_.toString();
                    Document doc_ = DocumentBuilder.parseSax(txt_);
                    ElementList e_ = doc_.getElementsByTagName(MEDIA);
                    int len_ = e_.getLength();
                    songsList.clear();
                    for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                        Element elt_ = e_.item(i);
                        String v_ = elt_.getAttribute(SRC);
                        if (!v_.endsWith(WAV)) {
                            if (!v_.endsWith(TXT)) {
                                continue;
                            }
                        }
                        songsList.add(v_);
                    }
                    songsList.removeAllString(EMPTY);
                    if (songsList.isEmpty()) {
                        ConfirmDialog.showMessage(this,_messages_.getVal(CANNOT_READ_MESSAGE_WPL),_messages_.getVal(CANNOT_READ_TITLE),getLanguageKey(),JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (random.isSelected()) {
                        StringList songsList_ = new StringList();
                        for (String o: suffledSongsNames(songsList)) {
                            songsList_.add(o);
                        }
                        songsList = songsList_;
                    }
                    ClipStream c_;
                    if (songsList.get(noSong).endsWith(WAV)) {
                        c_ = StreamSoundFile.openClip(StreamBinaryFile.loadFile(songsList.get(noSong)));
                    } else {
                        String txtIn_ = StreamTextFile.contentsOfFile(songsList.get(noSong));
                        c_ = openClip(txtIn_);
                    }
                    while (true) {
                        if (c_ != null) {
                            break;
                        }
                        noSong ++;
                        if (noSong >= songsList.size()) {
                            break;
                        }
                        if (songsList.get(noSong).endsWith(WAV)) {
                            c_ = StreamSoundFile.openClip(StreamBinaryFile.loadFile(songsList.get(noSong)));
                        } else {
                            String txtIn_ = StreamTextFile.contentsOfFile(songsList.get(noSong));
                            c_ = openClip(txtIn_);
                        }
                    }
                    clipStream = c_;
                    if (songsList.isEmpty()) {
                        ConfirmDialog.showMessage(this,_messages_.getVal(CANNOT_READ_MESSAGE_WPL),_messages_.getVal(CANNOT_READ_TITLE),getLanguageKey(),JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                songRend.setSongs(songsList);
                songRend.setNoSong(noSong);
                songRend.setSize();
                songRend.validate();
                songRend.repaint();
                PackingWindowAfter.pack(this);
                clipStream.getClip().start();
                clipStream.getClip().addLineListener(new SpeakingEvent(this));
                play.setTextAndSize(PAUSE);
                currentSong.setText(songsList.get(noSong));
                String strBegin_ = getStringTime(0);
                elapsedTime.setText(strBegin_+REL_SEP+getStringTime(clipStream.getClip().getMicrosecondLength()));
                if (timer == null) {
                    timer = new Timer(SECOND_MILLIS, new UpdateTimeEvent(this));
                    timer.start();
                }
            } catch (RuntimeException _0) {
                ConfirmDialog.showMessage(this,_messages_.getVal(CANNOT_READ_MESSAGE_WPL),_messages_.getVal(CANNOT_READ_TITLE),getLanguageKey(),JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (clipStream.getClip().isRunning()) {
                lastFrame = clipStream.getClip().getFramePosition();
                pausing = true;
                clipStream.getClip().stop();
            } else {
                if (lastFrame < clipStream.getClip().getFrameLength()) {
                    clipStream.getClip().setFramePosition(lastFrame);
                } else {
                    clipStream.getClip().setFramePosition(0);
                }
                pausing = false;
                clipStream.getClip().start();
            }

        }
    }

    public static StringList suffledSongsNames(StringList _list) {
        StringList list_ = new StringList(_list);
        Ints indexes_ = new Ints();
        Ints indexesEdited_ = new Ints();
        int size_ = list_.size();
        for (int i = CustList.FIRST_INDEX; i < size_; i++) {
            indexes_.add(i);
        }
        while (!indexes_.isEmpty()) {
            long len_ = indexes_.size();
            int rem_ = (int) AbMonteCarlo.randomLong(len_);
            //rem_ >= 0 && rem_ < len_
            indexesEdited_.add(indexes_.get(rem_));
            indexes_.removeAt(rem_);
        }
        StringList newList_ = new StringList();
        for (int i: indexesEdited_) {
            newList_.add(list_.get(i));
        }
        list_.clear();
        list_.addAllElts(newList_);
        return list_;
    }

    public static ClipStream openClip(String _imageString) {
        return StreamSoundFile.openClip(parseBaseSixtyFourBinary(_imageString));
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

            byte v_;
            if (ch_ >= FIRST_DIGIT && ch_ <= '9') {
                int diff_ = ch_ - FIRST_DIGIT;
                v_ = (byte) (NB_LETTERS_UPP_LOW + diff_);
            } else if (ch_ >= FIRST_LOW_LETTER && ch_ <= 'z') {
                int diff_ = ch_ - FIRST_LOW_LETTER;
                v_ = (byte) (NB_LETTERS+diff_);
            } else if (ch_ >= FIRST_UPP_LETTER && ch_ <= 'Z') {
                int diff_ = ch_ - FIRST_UPP_LETTER;
                v_ = (byte) diff_;
            } else if (ch_ == '+') {
                v_ = NB_DIGITS_LETTERS;
            } else if (ch_ == '/') {
                v_ = NB_DIGITS_LETTERS + 1;
            } else {
                v_ = PADDING;
            }

            //v!=-1
            quadruplet_[q_] = v_;
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
            clipStream.getClip().close();
            try {
                clipStream.getStream().close();
            } catch (IOException _0) {
            }
            clipStream = null;
        }
    }

    public void previousSong() {
        if (clipStream != null && !next) {
            lastFrame = 0;
            noSong--;
            noSong--;
            clipStream.getClip().close();
            try {
                clipStream.getStream().close();
            } catch (IOException _0) {
            }
            clipStream = null;
        }
    }

    public void stopSong() {
        if (clipStream != null && !next) {
            lastFrame = 0;
            playSong = false;
            songsList.clear();
            noSong = songsList.size();
            clipStream.getClip().close();
            try {
                clipStream.getStream().close();
            } catch (IOException _0) {
            }
            clipStream = null;
        }
    }

    public void updateClip(LineEvent _event) {
        String ev_ = toLowerCase(_event.getType().toString());
        if (StringList.quickEq(ev_, START)) {
            //LineEvent.Type.START
            play.setTextAndSize(PAUSE);
        } else if (StringList.quickEq(ev_, STOP_EVT)) {
            //LineEvent.Type.STOP
            //The end of a song pass here
            play.setTextAndSize(PLAY);
            if (!pausing) {
                next = true;
                playSong = true;
                clipStream.getClip().close();
                try {
                    clipStream.getStream().close();
                } catch (IOException _0) {
                }
                next = false;
            }
        } else if (StringList.quickEq(ev_, CLOSE)) {
            //LineEvent.Type.CLOSE
            play.setTextAndSize(PLAY);
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
        String l_ = getStringTime(clipStream.getClip().getMicrosecondLength());
        String c_ = getStringTime(clipStream.getClip().getMicrosecondPosition());
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
        ThreadUtil.exit();
    }

    @Override
    public boolean canChangeLanguage() {
        return true;
    }

    @Override
    public void changeLanguage(String _language) {
        setLanguageKey(_language);
        initMessages(_language);
        setTitle(_messages_.getVal(TITLE_PLAYER));
        random.setText(_messages_.getVal(RANDOM));
        songsLabel.setText(_messages_.getVal(SONGS));
    }

    @Override
    public String getApplicationName() {
        return "musicplayer";
    }
}
