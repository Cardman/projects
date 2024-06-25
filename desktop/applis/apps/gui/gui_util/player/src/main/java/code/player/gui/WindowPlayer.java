package code.player.gui;





import code.gui.*;
import code.gui.events.*;
import code.gui.files.FileDialog;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.MonteCarloUtil;
import code.stream.*;
import code.scripts.messages.gui.MessPlayerGr;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.ElementList;
import code.sml.util.ResourcesMessagesUtil;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class WindowPlayer extends GroupFrame implements LineShortListenable,AbsOpenQuit {
    public static final String APPS_MUSICPLAYER = "musicplayer";
    private static final String ACCESS = "player.gui.mainwindow";

    private static final String CST_START = "start";
    private static final String CST_CLOSE = "close";
    private static final String CST_STOP_EVT = "stop";
    private static final String CST_TITLE_PLAYER = "titlePlayer";
    private static final String CST_SONGS = "songs";
    private static final String CST_RANDOM = "random";
    private static final String CST_CANNOT_READ_TITLE = "cannotReadTitle";
    private static final String CST_CANNOT_READ_MESSAGE_WPL = "cannotReadMessageWpl";
    private static final String CST_RESOURCES_FOLDER = "resources_player";
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

    private static final String START_MP_3 = "start_mp3";
    private static final String STOP_MP_3 = "stop_mp3";
    private static final String RESOURCES_FOLDER = "resources_player";
    private static final String ICON = "player.txt";
    private final ReportingFrame resultFile = ReportingFrame.newInstance(getFrames());
    private final StringMap<String> messagesFiles = MessPlayerGr.ms();
    private StringMap<String> messages = new StringMap<String>();

    private final AbstractScheduledExecutorService timer = getThreadFactory().newScheduledExecutorService();
    private final AbsPlainLabel songsLabel = getCompoFactory().newPlainLabel("");
    private AbsClipStream clipStream;
    private int noSong = -1;
    private final AbsTextArea songs = getCompoFactory().newTextArea(10, 40);
    private final SongRenderer songRend = new SongRenderer(getCompoFactory());
    private final AbsCustCheckBox random = getCompoFactory().newCustCheckBox();
    private final AbsButton play = getCompoFactory().newPlainButton(CST_PLAY);
    private final AbsButton playPrevious = getCompoFactory().newPlainButton(CST_PREVIOUS);
    private final AbsButton playNext = getCompoFactory().newPlainButton(CST_NEXT);
    private final AbsButton stop = getCompoFactory().newPlainButton(CST_STOP);
    private final AbsPlainLabel currentNoSong = getCompoFactory().newPlainLabel(EMPTY);
    private final AbsPlainLabel currentSong = getCompoFactory().newPlainLabel(EMPTY);
    private final AbsScrollPane scroll;
    private final AbsPlainLabel elapsedTime = getCompoFactory().newPlainLabel(EMPTY);
    private boolean pausing;
    private boolean next;
    private boolean playSong;
    private StringList songsList = new StringList();
    private String contentList = "";
    private long elapsed;

//    private final CustButtonGroup group = new CustButtonGroup();

//    private final CustList<AbsRadioButton> radios = new CustList<AbsRadioButton>();
    private AbstractFuture abstractFuture;

    public WindowPlayer(String _lg, AbstractProgramInfos _list) {
        super(_list);
        GuiBaseUtil.choose(_lg, this, _list.getCommon());
        initMessages(_lg);
        setTitle(messages.getVal(CST_TITLE_PLAYER));
        setIconImage(getIcon(_list.getImageFactory()));
        AbsPanel pane_ = getCompoFactory().newPageBox();
        songsLabel.setText(messages.getVal(CST_SONGS));
        pane_.add(songsLabel);
        pane_.add(songs);
        random.setText(messages.getVal(CST_RANDOM));
        pane_.add(random);
        AbsPanel actions_ = getCompoFactory().newLineBox();
//        playPrevious.setTextAndSize(PREVIOUS);
//        playPrevious.repaint();
        playPrevious.addActionListener(new PreviousSong(this));
        actions_.add(playPrevious);
        play.addActionListener(new ReadSong(this));
        actions_.add(play);
        playNext.addActionListener(new NextSong(this));
        actions_.add(playNext);
        stop.addActionListener(new StopSong(this));
        actions_.add(stop);
        pane_.add(actions_);
        scroll = getCompoFactory().newAbsScrollPane(songRend.getPaintableLabel());
        scroll.setPreferredSize(new MetaDimension(256, 352));
        pane_.add(scroll);
        pane_.add(currentNoSong);
        pane_.add(currentSong);
        pane_.add(elapsedTime);
        pane_.add(new Clock(_list));
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
//        exitMode(_list);
//        setDefaultCloseOperation(GuiConstants.EXIT_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
    }

    public static AbstractImage getIcon(AbstractImageFactory _fact) {
        return FileDialog.getImage(MessPlayerGr.ms().getVal(StringUtil.concat(RESOURCES_FOLDER, StreamTextFile.SEPARATEUR, ICON)), _fact);
    }

    //    @Override
    public void dispose() {
        saveState();
        GuiBaseUtil.trEx(this);
    }

    public void loadList(String _fileName) {
        songs.append(_fileName);
        String txt_ = StreamTextFile.contentsOfFile(_fileName,getFileCoreStream(),getStreams());
        Document doc_ = DocumentBuilder.parseNoTextDocument(txt_);
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
            if (_click) {
                songsList = StringUtil.splitStrings(songs.getText(), LINE_RETURN);
                songsList.removeAllString(EMPTY);
                StringList filter_ = new StringList();
                int len_ = songsList.size();
                for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
                    String v_ = songsList.get(i);
                    byte[] bytes_ = StreamBinaryFile.loadFile(v_, getStreams()).getBytes();
                    if (bytes_.length > 0&&bytes_[0]=='<'||valid(bytes_)) {
                        filter_.add(v_);
                    }
                }
                songsList = filter_;
                suffle();
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
                resultFile.display(messages.getVal(CST_CANNOT_READ_TITLE),messages.getVal(CST_CANNOT_READ_MESSAGE_WPL));
//                getFrames().getMessageDialogAbs().input(getCommonFrame(), messages.getVal(CST_CANNOT_READ_MESSAGE_WPL), messages.getVal(CST_CANNOT_READ_TITLE), GuiConstants.ERROR_MESSAGE);
                return;
            }
            byte[] bytes_ = getBytes();
            if (bytes_.length > 0 && bytes_[0] != '<') {
                //.wav or .mp3 or .txt
                clipStream = GuiBaseUtil.getAbsClipStream(getFrames(),bytes_);
            } else if (bytes_.length > 0) {
                //.wpl
                String txt_ = StringUtil.decode(bytes_);
                Document doc_ = DocumentBuilder.parseNoTextDocument(txt_);
                if (doc_ == null) {
                    resultFile.display(messages.getVal(CST_CANNOT_READ_TITLE),messages.getVal(CST_CANNOT_READ_MESSAGE_WPL));
//                    getFrames().getMessageDialogAbs().input(getCommonFrame(), messages.getVal(CST_CANNOT_READ_MESSAGE_WPL), messages.getVal(CST_CANNOT_READ_TITLE), GuiConstants.ERROR_MESSAGE);
                    return;
                }
                ElementList e_ = doc_.getElementsByTagName(CST_MEDIA);
                int len_ = e_.getLength();
                songsList.clear();
                for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
                    Element elt_ = e_.item(i);
                    String v_ = elt_.getAttribute(CST_SRC);
                    if (valid(StreamBinaryFile.loadFile(v_, getStreams()).getBytes())) {
                        songsList.add(v_);
                    }
                }
                if (songsList.isEmpty()) {
                    resultFile.display(messages.getVal(CST_CANNOT_READ_TITLE),messages.getVal(CST_CANNOT_READ_MESSAGE_WPL));
//                    getFrames().getMessageDialogAbs().input(getCommonFrame(), messages.getVal(CST_CANNOT_READ_MESSAGE_WPL), messages.getVal(CST_CANNOT_READ_TITLE), GuiConstants.ERROR_MESSAGE);
                    return;
                }
                ElementList elts_ = doc_.getDocumentElement().getChildElements();
                if (elts_.getLength() > 0) {
                    if (StringUtil.quickEq(CST_KEY_PAUSE,elts_.get(0).getTagName())) {
                        String txtCont_ = elts_.get(0).getAttribute(CST_ATTR_VALUE);
                        int paused_ = NumberUtil.parseInt(txtCont_);
                        if (songsList.isValidIndex(paused_)) {
                            noSong = paused_;
                        } else {
                            suffle();
                        }
                    } else if (StringUtil.quickEq(CST_KEY_RANDOM,elts_.get(0).getTagName())) {
                        random.setSelected(true);
                        suffle();
                    } else {
                        suffle();
                    }
                } else {
                    suffle();
                }
                Document list_ = DocumentBuilder.newXmlDocument();
                Element mainDoc_ = list_.createElement("smil");
                for (String s: songsList) {
                    Element elt_ = list_.createElement(CST_MEDIA);
                    elt_.setAttribute(CST_SRC,s);
                    mainDoc_.appendChild(elt_);
                }
                contentList = mainDoc_.export();
                clipStream = getAbsClipStream();
                if (songsList.isEmpty()) {
                    resultFile.display(messages.getVal(CST_CANNOT_READ_TITLE),messages.getVal(CST_CANNOT_READ_MESSAGE_WPL));
//                    getFrames().getMessageDialogAbs().input(getCommonFrame(), messages.getVal(CST_CANNOT_READ_MESSAGE_WPL), messages.getVal(CST_CANNOT_READ_TITLE), GuiConstants.ERROR_MESSAGE);
                    return;
                }
            }
            songRend.setSongs(songsList);
            songRend.setNoSong(noSong);
            songRend.setSize(getImageFactory());
            scroll.revalidate();
            pack();
            if (clipStream == null) {
                return;
            }
            clipStream.addLineListener(this);
            play.setText(CST_PAUSE);
            currentNoSong.setText((noSong+1)+"/"+songsList.size());
            currentSong.setText(songsList.get(noSong));
            String strBegin_ = GuiBaseUtil.getStringTime(0);
            elapsedTime.setText(strBegin_+REL_SEP+GuiBaseUtil.getStringTime(clipStream.getMicrosecondLength()));
            cancelIfPossible();
            abstractFuture = timer.scheduleAtFixedRate(new UpdateSongTimeEvent(this), 0, 1);
        } else {
            if (clipStream.isRunning()) {
                saveState();
                play.setText(CST_PLAY);
                pausing = true;
                clipStream.stop(elapsed);
            } else {
                pausing = false;
                clipStream.resume();
            }

        }
    }

    private void suffle() {
        if (random.isSelected()) {
            StringList songsList_ = new StringList();
            for (String o: suffledSongsNames(songsList,getGenerator())) {
                songsList_.add(o);
            }
            songsList = songsList_;
        }
    }

    private void cancelIfPossible() {
        if (abstractFuture != null) {
            abstractFuture.cancel(false);
        }
        elapsed = 0;
    }

    private AbsClipStream getAbsClipStream() {
        byte[] bytes_ = getBytes();
        return GuiBaseUtil.getAbsClipStream(getFrames(),bytes_);
    }

    private boolean valid(byte[] _bytes) {
        AbsClipStream absClipStream_ = GuiBaseUtil.getAbsClipStream(getFrames(),_bytes);
        if (absClipStream_ == null) {
            return false;
        }
        absClipStream_.closeClipStream();
        return true;
    }

    private byte[] getBytes() {
        return GuiConstants.nullToEmpty(StreamBinaryFile.loadFile(songsList.get(noSong), getStreams()).getBytes());
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

    public AbsClipStream openClip(byte[] _imageString) {
        return getFrames().openClip(_imageString);
    }

    public void nextSong() {
        if (clipStream != null && !next) {
            closeClipStream();
            clipStream = null;
        }
    }

    public void previousSong() {
        if (clipStream != null && !next) {
            noSong--;
            noSong--;
            closeClipStream();
            clipStream = null;
        }
    }

    public void stopSong() {
        if (clipStream != null && !next) {
            playSong = false;
            songsList.clear();
            noSong = songsList.size();
            closeClipStream();
            clipStream = null;
        }
    }

    @Override
    public void update(String _type, long _position) {
        String ev_ = toLowerCase(_type);
        wav(ev_);
    }

    @Override
    public void updateMp3(String _type, long _position) {
        String ev_ = toLowerCase(_type);
        mp3(ev_);
    }

    private void wav(String _ev) {
        if (StringUtil.quickEq(_ev, CST_START)) {
            //LineEvent.Type.START
            play.setText(CST_PAUSE);
        } else if (StringUtil.quickEq(_ev, CST_STOP_EVT)) {
            //LineEvent.Type.STOP
            //The end of a song pass here
            play.setText(CST_PLAY);
            if (!pausing) {
                next = true;
                playSong = true;
                cancelIfPossible();
                closeClipStream();
                next = false;
            }
        } else if (StringUtil.quickEq(_ev, CST_CLOSE)) {
            //LineEvent.Type.CLOSE
            play.setText(CST_PLAY);
            clipStream = null;
            pausing = false;
            if (!playSong) {
                return;
            }
            playOrPause(false);
            playSong = false;
        }
    }

    private void mp3(String _ev) {
        if (StringUtil.quickEq(_ev, START_MP_3)) {
            play.setText(CST_PAUSE);
        } else if (StringUtil.quickEq(_ev, STOP_MP_3)) {
            play.setText(CST_PLAY);
            if (!pausing) {
                next = true;
                playSong = true;
                cancelIfPossible();
                closeClipStream();
                clipStream = null;
                next = false;
                if (!playSong) {
                    return;
                }
                playOrPause(false);
                playSong = false;
            }
        }
    }

    private void closeClipStream() {
        clipStream.closeClipStream();
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
        if (pausing ||clipStream == null) {
            return;
        }
        String l_ = GuiBaseUtil.getStringTime(clipStream.getMicrosecondLength());
        String c_ = GuiBaseUtil.getStringTime(elapsed);
        elapsedTime.setText(c_+REL_SEP+l_);
        elapsed+=1000;
    }

    @Override
    public void quit() {
        GuiBaseUtil.trEx(this);
    }

//    @Override
//    public boolean canChangeLanguage() {
//        return true;
//    }

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
        return APPS_MUSICPLAYER;
    }

    public AbsClipStream getClipStream() {
        return clipStream;
    }
}
