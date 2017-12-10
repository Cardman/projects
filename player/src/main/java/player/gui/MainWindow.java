package player.gui;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineEvent;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import code.xml.components.Document;
import code.xml.components.Element;
import code.xml.components.NodeList;

import code.gui.Clock;
import code.gui.GroupFrame;
import code.gui.LabelButton;
import code.gui.PackingWindowAfter;
import code.gui.SetStyle;
import code.images.ConverterBufferedImage;
import code.maths.montecarlo.AbMonteCarlo;
import code.resources.ClipStream;
import code.resources.ResourceFiles;
import code.stream.StreamBinaryFile;
import code.stream.StreamSoundFile;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.xml.XmlParser;
import code.xml.util.ExtractFromFiles;

public class MainWindow extends GroupFrame {
    private static final String ACCESS = "player.gui.MainWindow";

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

    private static StringMap<String> _messages_ = new StringMap<String>();

    private Timer timer;
    private JLabel songsLabel = new JLabel();
    private ClipStream clipStream;
    private int noSong = -1;
    private JTextArea songs = new JTextArea(10, 40);
    private SongRenderer songRend = new SongRenderer();
    private JCheckBox random = new JCheckBox();
    private LabelButton play = new LabelButton(PLAY);
    private LabelButton playPrevious = new LabelButton(PREVIOUS);
    private LabelButton playNext = new LabelButton(NEXT);
    private LabelButton stop = new LabelButton(STOP);
    private JLabel currentSong = new JLabel(EMPTY);
    private JLabel elapsedTime = new JLabel(EMPTY);
    private boolean pausing;
    private boolean next;
    private boolean playSong;
    private int lastFrame;
    private StringList songsList = new StringList();

    private ButtonGroup group = new ButtonGroup();

    private CustList<JRadioButton> radios = new CustList<JRadioButton>();

    public MainWindow(String[] _args) {
        initMessages();
        setTitle(_messages_.getVal(TITLE_PLAYER));
        setIconImage(getImage());
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        songsLabel.setText(_messages_.getVal(SONGS));
        getContentPane().add(songsLabel);
        getContentPane().add(songs);
        random.setText(_messages_.getVal(RANDOM));
        getContentPane().add(random);
        JPanel actions_ = new JPanel();
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
        getContentPane().add(actions_);
        JScrollPane scr_ = new JScrollPane(songRend);
        scr_.setPreferredSize(new Dimension(100, 60));
        getContentPane().add(scr_);
        getContentPane().add(currentSong);
        getContentPane().add(elapsedTime);
        getContentPane().add(new Clock());
        for (String l: Constants.getAvailableLanguages()) {
            JRadioButton radio_ = new JRadioButton(Constants.getDisplayLanguage(l));
            radio_.addActionListener(new SetLanguage(l));
            radio_.setSelected(StringList.quickEq(l,Constants.getLanguage()));
            group.add(radio_);
            getContentPane().add(radio_);
            radios.add(radio_);
        }
        pack();
        setVisible(true);
        SetStyle.setupStyle(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void initMessages() {
        _messages_ = ExtractFromFiles.getMessagesFromLocaleClass(RESOURCES_FOLDER, Constants.getLanguage(), ACCESS);
    }

    private static BufferedImage getImage() {
        BufferedImage image_ = null;
        try {
            String file_ = ResourceFiles.ressourceFichier(RESOURCES_FOLDER+StreamTextFile.SEPARATEUR+ ICON);
            image_ = ConverterBufferedImage.decodeToImage(file_);
        } catch (RuntimeException _0) {
        }
        return image_;
    }

    public void playOrPause(boolean _click) {
        if (clipStream == null) {
            noSong ++;
            if (_click && random.isSelected()) {
                songsList = new StringList(songs.getText().split(LINE_RETURN));
                songsList.removeAllString(EMPTY);
                StringList songsList_ = new StringList();
                for (Object o: AbMonteCarlo.suffledElts(songsList.toArray())) {
                    songsList_.add(o.toString());
                }
                songsList = songsList_;
            } else if (_click) {
                songsList = new StringList(songs.getText().split(LINE_RETURN));
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
                        c_ = StreamSoundFile.openClip(txt_);
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
                            c_ = StreamSoundFile.openClip(txt_);
                        }
                    }
                    clipStream = c_;
                } else if (songsList.get(noSong).endsWith(WPL)) {
                    //.wpl
                    String txt_ = StreamTextFile.contentsOfFile(songsList.get(noSong));
                    txt_ = StringList.replace(txt_, WPL_HEADER, XML_HEADER);
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
                    Document doc_ = XmlParser.parseSax(txt_);
                    NodeList e_ = doc_.getElementsByTagName(MEDIA);
                    int len_ = e_.getLength();
                    songsList.clear();
                    for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                        Element elt_ = (Element) e_.item(i);
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
                        JOptionPane.showMessageDialog(this, _messages_.getVal(CANNOT_READ_MESSAGE_WPL), _messages_.getVal(CANNOT_READ_TITLE), JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (random.isSelected()) {
                        StringList songsList_ = new StringList();
                        for (Object o: AbMonteCarlo.suffledElts(songsList.toArray())) {
                            songsList_.add(o.toString());
                        }
                        songsList = songsList_;
                    }
                    ClipStream c_;
                    if (songsList.get(noSong).endsWith(WAV)) {
                        c_ = StreamSoundFile.openClip(StreamBinaryFile.loadFile(songsList.get(noSong)));
                    } else {
                        String txtIn_ = StreamTextFile.contentsOfFile(songsList.get(noSong));
                        c_ = StreamSoundFile.openClip(txtIn_);
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
                            c_ = StreamSoundFile.openClip(txtIn_);
                        }
                    }
                    clipStream = c_;
                }
                songRend.setSongs(songsList);
                songRend.setNoSong(noSong);
                songRend.setSize();
                songRend.validate();
                songRend.repaint();
                PackingWindowAfter.pack(this);
                clipStream.getClip().start();
                clipStream.getClip().addLineListener(new SpeakingEvent(this));
                play.setText(PAUSE);
                currentSong.setText(songsList.get(noSong));
                String strBegin_ = getStringTime(0);
                elapsedTime.setText(strBegin_+REL_SEP+getStringTime(clipStream.getClip().getMicrosecondLength()));
                if (timer == null) {
                    timer = new Timer(SECOND_MILLIS, new UpdateTimeEvent(this));
                    timer.start();
                }
            } catch (NoSuchSongException _0) {
                JOptionPane.showMessageDialog(this, _messages_.getVal(CANNOT_READ_MESSAGE_WPL), _messages_.getVal(CANNOT_READ_TITLE), JOptionPane.ERROR_MESSAGE);
            } catch (RuntimeException _0) {
                JOptionPane.showMessageDialog(this, _messages_.getVal(CANNOT_READ_MESSAGE_WAV), _messages_.getVal(CANNOT_READ_TITLE), JOptionPane.ERROR_MESSAGE);
            }
        } else {

            if (clipStream.getClip().isRunning()) {
                lastFrame = clipStream.getClip().getFramePosition();
                pausing = true;
                clipStream.getClip().stop();
                System.gc();
//                pausing = false;
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

    public void nextSong() {
        if (clipStream != null && !next) {
            lastFrame = 0;
            clipStream.getClip().close();
            try {
                clipStream.getStream().close();
            } catch (IOException _0) {
                _0.printStackTrace();
            }
            System.gc();
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
                _0.printStackTrace();
            }
            System.gc();
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
                _0.printStackTrace();
            }
            System.gc();
            clipStream = null;
        }
    }

    public void updateClip(LineEvent _event) {
        if (_event.getType().toString().equalsIgnoreCase(START)) {
            //LineEvent.Type.START
            play.setText(PAUSE);
        } else if (_event.getType().toString().equalsIgnoreCase(STOP_EVT)) {
            //LineEvent.Type.STOP
            //The end of a song pass here
//            System.out.println(lastFrame);
//            System.out.println(clip.getLongFramePosition());
            play.setText(PLAY);
            if (!pausing) {
                next = true;
                playSong = true;
                clipStream.getClip().close();
                try {
                    clipStream.getStream().close();
                } catch (IOException _0) {
                    _0.printStackTrace();
                }
                System.gc();
                next = false;
            }
        } else if (_event.getType().toString().equalsIgnoreCase(CLOSE)) {
            //LineEvent.Type.CLOSE
            play.setText(PLAY);
            clipStream = null;
            pausing = false;
            if (!playSong) {
                return;
            }
            playOrPause(false);
            playSong = false;
        }
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
        Constants.exit();
    }

    @Override
    public boolean canChangeLanguage() {
        return true;
    }

    @Override
    public void changeLanguage(String _language) {
        Constants.setSystemLanguage(_language);
        initMessages();
        setTitle(_messages_.getVal(TITLE_PLAYER));
        random.setText(_messages_.getVal(RANDOM));
        songsLabel.setText(_messages_.getVal(SONGS));
    }

}
