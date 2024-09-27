package code.player.gui;





import code.gui.*;
import code.gui.events.*;
import code.gui.images.AbstractImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.MonteCarloUtil;
import code.sml.core.DocumentWriterCoreUtil;
import code.stream.*;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.ElementList;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class WindowPlayer extends GroupFrame implements LineShortListenable,AbsOpenQuit {
    public static final String SMIL = "5";
    public static final String CST_MEDIA = "0";
    public static final String CST_KEY_PAUSE = "2";
    public static final String CST_KEY_RANDOM = "1";

//    private static final String CST_START = "Start";
//    private static final String CST_CLOSE = "Close";
//    private static final String CST_STOP_EVT = "Stop";
    private static final String REL_SEP = " / ";
    private static final String CST_PAUSE = "||";
//    private static final String CST_STOP = "[]";
    private static final String CST_PREVIOUS = "<|";
    private static final String CST_NEXT = "|>";
    private static final String CST_PLAY = ">";
    private static final String EMPTY = "";
    private static final String LINE_RETURN = "\n";

//    private static final String RESOURCES_FOLDER = "resources_player";
//    private static final String ICON = "player.txt";
    private final ReportingFrame resultFile = ReportingFrame.newInstance(getFrames());
//    private final StringMap<String> messagesFiles = new StringMap<String>();
    private StringMap<String> messages = new StringMap<String>();

    private final AbstractScheduledExecutorService timer = getThreadFactory().newScheduledExecutorService();
    private final AbsPlainLabel songsLabel = getCompoFactory().newPlainLabel("");
    private final AbsPlainLabel songsSave = getCompoFactory().newPlainLabel("");
    private AbsClipStream clipStream;
    private int noSong = -1;
    private final AbsTextArea songs = getCompoFactory().newTextArea(10, 40);
    private final SongRenderer songRend = new SongRenderer(getCompoFactory());
    private final AbsCustCheckBox random = getCompoFactory().newCustCheckBox();
    private final AbsButton play = getCompoFactory().newPlainButton(CST_PLAY);
    private final AbsButton playPrevious = getCompoFactory().newPlainButton(CST_PREVIOUS);
    private final AbsButton playNext = getCompoFactory().newPlainButton(CST_NEXT);
    private final AbsButton stop = getCompoFactory().newPlainButton("\u23F9");
    private final AbsTextField fileSave = getCompoFactory().newTextField();
    private final AbsPlainLabel currentNoSong = getCompoFactory().newPlainLabel(EMPTY);
    private final AbsPlainLabel currentSong = getCompoFactory().newPlainLabel(EMPTY);
    private final AbsScrollPane scroll;
    private final AbsPlainLabel elapsedTime = getCompoFactory().newPlainLabel(EMPTY);
    private boolean pausing;
//    private boolean next;
//    private boolean closing;
//    private boolean playSong;
    private CustList<LoadedSongBytes> songsList = new CustList<LoadedSongBytes>();
//    private String contentList = "";
    private long elapsed;

//    private final CustButtonGroup group = new CustButtonGroup();

//    private final CustList<AbsRadioButton> radios = new CustList<AbsRadioButton>();
    private AbstractFuture abstractFuture;
    private final AbsButton mainButton;

    public WindowPlayer(String _lg, AbstractProgramInfos _list, LanguagesButtonsPair _pair, AbstractImage _icon) {
        super(_list);
        mainButton = _pair.getMainButton();
        GuiBaseUtil.choose(this, _list);
        initMessages(_lg);
        setTitle(messages.getVal(MessagesPlayer.TITLE));
        setIconImage(_icon);
        AbsPanel pane_ = getCompoFactory().newPageBox();
        songsLabel.setText(messages.getVal(MessagesPlayer.SONGS));
        pane_.add(songsLabel);
        pane_.add(songs);
        random.setText(messages.getVal(MessagesPlayer.RANDOM));
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
        AbsPanel actionsSave_ = getCompoFactory().newLineBox();
        songsSave.setText(messages.getVal(MessagesPlayer.LAST));
        actionsSave_.add(songsSave);
        actionsSave_.add(fileSave);
        pane_.add(actionsSave_);
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

//    @Override
//    public void dispose() {
//        saveState();
//        GuiBaseUtil.trEx(this);
//    }

    public void loadList(String _fileName) {
        songs.append(_fileName);
        playOrPause(true);
//        String txt_ = StreamTextFile.contentsOfFile(_fileName,getFileCoreStream(),getStreams());
//        Document doc_ = DocumentBuilder.parseNoTextDocument(txt_);
//        if (doc_ != null) {
//            playOrPause(true);
//        }
    }

    public void initMessages(String _lg) {
        setLanguageKey(_lg);
//        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(CST_RESOURCES_FOLDER, _lg, ACCESS);
//        String loadedResourcesMessages_ = messagesFiles.getVal(fileName_);
        messages = MessagesSongs.valPlayerMessages(getFrames().currentLg());
    }

    public void playOrPause(boolean _click) {
        if (clipStream != null) {
            manageAlreadyLaunched();
            return;
        }
        noSong++;
        if (_click) {
            shuffleListOrSounds();
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
//            if (!songsList.isValidIndex(noSong)) {
//                resultFile.display(messages.getVal(CST_CANNOT_READ_TITLE),messages.getVal(CST_CANNOT_READ_MESSAGE_WPL));
//                getFrames().getMessageDialogAbs().input(getCommonFrame(), messages.getVal(CST_CANNOT_READ_MESSAGE_WPL), messages.getVal(CST_CANNOT_READ_TITLE), GuiConstants.ERROR_MESSAGE);
//                return;
//            }
        byte[] bytes_ = getBytes();
        if (!listSong(bytes_)) {
//        if (bytes_.length > 0 && bytes_[0] != '<')
            //.wav or .mp3 or .txt
            clipStream = GuiBaseUtil.getAbsClipStream(getFrames(),bytes_, messages.getVal(MessagesPlayer.BASE_KEY));
            possibleLaunch();
            return;
        }
//        if (bytes_.length <= 0) {
//            return;
//        }
        //.wpl
        String txt_ = StringUtil.decode(bytes_);
        Document doc_ = DocumentBuilder.parseNoTextDocument(txt_);
        if (doc_ == null) {
            resultFile.display(messages.getVal(MessagesPlayer.CANNOT_READ_TITLE),messages.getVal(MessagesPlayer.CANNOT_READ_MESSAGE));
//                    getFrames().getMessageDialogAbs().input(getCommonFrame(), messages.getVal(CST_CANNOT_READ_MESSAGE_WPL), messages.getVal(CST_CANNOT_READ_TITLE), GuiConstants.ERROR_MESSAGE);
            return;
        }
        ElementList e_ = doc_.getElementsByTagName(CST_MEDIA);
        int len_ = e_.getLength();
        songsList.clear();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            Element elt_ = e_.item(i);
            String v_ = elt_.getAttribute(DocumentWriterCoreUtil.VALUE);
            byte[] data_ = StreamBinaryFile.loadFile(v_, getStreams()).getBytes();
            if (valid(data_)) {
                songsList.add(new LoadedSongBytes(v_,data_));
            }
        }
        if (songsList.isEmpty()) {
            resultFile.display(messages.getVal(MessagesPlayer.CANNOT_READ_TITLE),messages.getVal(MessagesPlayer.CANNOT_READ_MESSAGE));
//                    getFrames().getMessageDialogAbs().input(getCommonFrame(), messages.getVal(CST_CANNOT_READ_MESSAGE_WPL), messages.getVal(CST_CANNOT_READ_TITLE), GuiConstants.ERROR_MESSAGE);
            return;
        }
        shuffleWavMp3(doc_);
//        export();
        clipStream = getAbsClipStream();
//                if (songsList.isEmpty()) {
//                    resultFile.display(messages.getVal(CST_CANNOT_READ_TITLE),messages.getVal(CST_CANNOT_READ_MESSAGE_WPL));
//                    getFrames().getMessageDialogAbs().input(getCommonFrame(), messages.getVal(CST_CANNOT_READ_MESSAGE_WPL), messages.getVal(CST_CANNOT_READ_TITLE), GuiConstants.ERROR_MESSAGE);
//                    return;
//                }
        possibleLaunch();
    }

//    private void export() {
//        Document list_ = DocumentBuilder.newXmlDocument();
//        Element mainDoc_ = list_.createElement(SMIL);
//        for (LoadedSongBytes s: songsList) {
//            Element elt_ = list_.createElement(CST_MEDIA);
//            elt_.setAttribute(CST_SRC,s.getName());
//            mainDoc_.appendChild(elt_);
//        }
//        contentList = mainDoc_.export();
//    }

    private void manageAlreadyLaunched() {
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

    private void shuffleListOrSounds() {
        StringList songsListName_ = StringUtil.splitStrings(songs.getText(), LINE_RETURN);
        songsListName_.removeAllString(EMPTY);
//                songsList = StringUtil.splitStrings(songs.getText(), LINE_RETURN);
//                songsList.removeAllString(EMPTY);
        CustList<LoadedSongBytes> filter_ = new CustList<LoadedSongBytes>();
        int len_ = songsListName_.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String v_ = songsListName_.get(i);
            byte[] bytes_ = StreamBinaryFile.loadFile(v_, getStreams()).getBytes();
            if (listSong(bytes_) ||valid(bytes_)) {
                filter_.add(new LoadedSongBytes(v_,bytes_));
            }
        }
        songsList = filter_;
        shuffle();
    }

    private boolean listSong(byte[] _bytes) {
        return _bytes.length > 0 && _bytes[0] == '<';
    }

    public void possibleLaunch() {
        songRend.setSongs(songsList);
        songRend.setNoSong(noSong);
        songRend.setSize(getImageFactory());
        scroll.revalidate();
        pack();
        if (clipStream == null) {
            resultFile.display(messages.getVal(MessagesPlayer.CANNOT_READ_TITLE),messages.getVal(MessagesPlayer.CANNOT_READ_MESSAGE));
            return;
        }
        clipStream.addLineListener(this);
        currentNoSong.setText((noSong+1)+"/"+songsList.size());
        currentSong.setText(songsList.get(noSong).getName());
        String strBegin_ = GuiBaseUtil.getStringTime(0);
        elapsedTime.setText(strBegin_+REL_SEP+GuiBaseUtil.getStringTime(clipStream.getMicrosecondLength()));
        cancelIfPossible();
        abstractFuture = timer.scheduleAtFixedRate(new UpdateSongTimeEvent(this), 0, 1);
    }

    private void shuffleWavMp3(Document _doc) {
        CustList<Element> elts_ = new CustList<Element>();
        for (Element e: _doc.getDocumentElement().getChildElements()) {
            if (StringUtil.quickEq(CST_KEY_PAUSE,e.getTagName())) {
                elts_.add(e);
            }
            if (StringUtil.quickEq(CST_KEY_RANDOM,e.getTagName())) {
                elts_.add(e);
            }
        }
        if (!elts_.isEmpty()) {
            if (StringUtil.quickEq(CST_KEY_PAUSE, elts_.get(0).getTagName())) {
                String txtCont_ = elts_.get(0).getAttribute(DocumentWriterCoreUtil.VALUE);
                int paused_ = NumberUtil.parseInt(txtCont_);
                if (songsList.isValidIndex(paused_)) {
                    noSong = paused_;
                } else {
                    shuffle();
                }
//            } else if (StringUtil.quickEq(CST_KEY_RANDOM, elts_.get(0).getTagName())) {
//                random.setSelected(true);
//                shuffle();
            } else {
//                shuffle();
                random.setSelected(true);
                shuffle();
            }
        } else {
            shuffle();
        }
    }

    private void shuffle() {
        if (random.isSelected()) {
            /*StringList songsList_ = new StringList();
            for (String o: suffledSongsNames(songsList,getGenerator())) {
                songsList_.add(o);
            }
            songsList = songsList_;*/
            songsList = shuffledSongsNames(songsList,getGenerator());
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
        return GuiBaseUtil.getAbsClipStream(getFrames(),bytes_, messages.getVal(MessagesPlayer.BASE_KEY));
    }

    private boolean valid(byte[] _bytes) {
        AbsClipStream absClipStream_ = GuiBaseUtil.getAbsClipStream(getFrames(),_bytes, messages.getVal(MessagesPlayer.BASE_KEY));
        if (absClipStream_ == null) {
            return false;
        }
        absClipStream_.closeClipStream();
        return true;
    }

    private byte[] getBytes() {
        return GuiConstants.nullToEmpty(songsList.get(noSong).getData());
    }

    void saveState() {
//        if (songsList.isEmpty()) {
//            return;
//        }
        Document list_ = DocumentBuilder.newXmlDocument();
        Element mainDoc_ = list_.createElement(SMIL);
        Element pause_ = list_.createElement(CST_KEY_PAUSE);
        pause_.setAttribute(DocumentWriterCoreUtil.VALUE,Long.toString(noSong));
        mainDoc_.appendChild(pause_);
        for (LoadedSongBytes s: songsList) {
            Element elt_ = list_.createElement(CST_MEDIA);
            elt_.setAttribute(DocumentWriterCoreUtil.VALUE,s.getName());
            mainDoc_.appendChild(elt_);
        }
//        contentList = mainDoc_.export();
//        StreamTextFile.saveTextFile("last.wpl",contentList,getStreams());
        StreamTextFile.saveTextFile(fileSave.getText(),mainDoc_.export(),getStreams());
    }

    public static CustList<LoadedSongBytes> shuffledSongsNames(CustList<LoadedSongBytes> _list, AbstractGenerator _gene) {
        CustList<LoadedSongBytes> list_ = new CustList<LoadedSongBytes>(_list);
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
        CustList<LoadedSongBytes> newList_ = new CustList<LoadedSongBytes>();
        for (int i: indexesEdited_) {
            newList_.add(list_.get(i));
        }
        list_.clear();
        list_.addAllElts(newList_);
        return list_;
    }

    public void nextSong() {
        if (clipStream != null) {
//        if (clipStream != null && !next)
            closeClipStream();
//            clipStream = null;
        }
    }

    public void previousSong() {
        if (clipStream != null) {
//        if (clipStream != null && !next)
            noSong--;
            noSong--;
            closeClipStream();
//            clipStream = null;
        }
    }

    public void stopSong() {
        if (clipStream != null) {
//        if (clipStream != null && !next)
//            playSong = false;
            songsList.clear();
            noSong = songsList.size();
            closeClipStream();
//            clipStream = null;
        }
    }

    @Override
    public void update(String _type, long _position) {
        events(_type);
    }

    @Override
    public void updateMp3(String _type, long _position) {
        events(_type);
    }

//    private void wav(String _ev) {
//        if (StringUtil.quickEq(_ev, CST_START)) {
//            //LineEvent.Type.START
//            play.setText(CST_PAUSE);
//        } else if (StringUtil.quickEq(_ev, CST_STOP_EVT)) {
//            //LineEvent.Type.STOP
//            //The end of a song pass here
//            play.setText(CST_PLAY);
//            if (!pausing) {
//                next = true;
//                playSong = true;
//                cancelIfPossible();
//                closeClipStream();
//                next = false;
//            }
//        } else if (StringUtil.quickEq(_ev, CST_CLOSE)) {
//            //LineEvent.Type.CLOSE
//            play.setText(CST_PLAY);
//            pausing = false;
//            closeClip();
//        }
//    }

    private void events(String _ev) {
        if (StringUtil.quickEq(_ev, MessagesSongs.START)) {
            play.setText(CST_PAUSE);
        } else if (StringUtil.quickEq(_ev, MessagesSongs.STOP)) {
            //LineEvent.Type.STOP
            //The end of a song pass here
            play.setText(CST_PLAY);
            if (!pausing) {
//                next = true;
//                playSong = true;
                cancelIfPossible();
//                    closeClipStream();
//                }
//                next = false;
                closeClip();
            }
        }
    }

    private void closeClip() {
        clipStream = null;
//        if (!playSong) {
//            return;
//        }
        playOrPause(false);
//        playSong = false;
    }

    private void closeClipStream() {
//        closing = true;
        clipStream.closeClipStream();
//        closing = false;
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
        getCommonFrame().setVisible(false);
        LanguageDialogButtons.enable(mainButton,true);
        GuiBaseUtil.trEx(this, getFrames());
    }

//    @Override
//    public boolean canChangeLanguage() {
//        return true;
//    }

    @Override
    public void changeLanguage(String _language) {
        setLanguageKey(_language);
        initMessages(_language);
        setTitle(messages.getVal(MessagesPlayer.TITLE));
        random.setText(messages.getVal(MessagesPlayer.RANDOM));
        songsLabel.setText(messages.getVal(MessagesPlayer.SONGS));
        songsSave.setText(messages.getVal(MessagesPlayer.LAST));
    }

    @Override
    public String getApplicationName() {
        return MessagesSongs.APPS_MUSICPLAYER;
    }

    public AbsButton getPlay() {
        return play;
    }

    public AbsButton getPlayNext() {
        return playNext;
    }

    public AbsButton getPlayPrevious() {
        return playPrevious;
    }

    public AbsButton getStop() {
        return stop;
    }

    public AbsCustCheckBox getRandom() {
        return random;
    }

    public AbsTextArea getSongs() {
        return songs;
    }

    public boolean isPausing() {
        return pausing;
    }

    public int getNoSong() {
        return noSong;
    }

    public AbsClipStream getClipStream() {
        return clipStream;
    }
}
