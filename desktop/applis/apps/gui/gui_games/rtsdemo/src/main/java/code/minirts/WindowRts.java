package code.minirts;

import code.gui.*;
import code.gui.events.*;
import code.gui.files.MessagesGuiFct;
import code.gui.images.AbstractImage;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.Rate;
import code.maths.geo.RatePoint;
import code.minirts.events.*;
import code.minirts.rts.RtsDirection;
import code.minirts.rts.Facade;
import code.threads.*;
import code.util.*;






public final class WindowRts extends GroupFrame implements AbsOpenQuit {

//    public static final String MOUSE_ARROW_FILE = "resources_rts/mouse_arrow.txt";

//    public static final String NOTE_FILE = "resources_rts/note.txt";

//    private final StringMap<String> messagesFiles = MessPlayerGr.ms();

//    private final Cursor currentCursor = Cursor.getDefaultCursor();

    private final AbsButton animate = getCompoFactory().newPlainButton("");

    private final AbsCustCheckBox pause = getCompoFactory().newCustCheckBox("");

    private final AbsButton stop = getCompoFactory().newPlainButton("");

    private final Facade facade = new Facade();

    private final PanelBattle battleground;
    private final AbstractAtomicBoolean stopped;
    private final AbstractAtomicBoolean paused;
    private final AbstractAtomicLong count;
    private final RtsKeyPad left;
    private final RtsKeyPad right;
    private final RtsKeyPad up;
    private final RtsKeyPad down;
    private AnimationUnitSoldier thread;
    private AbstractScheduledExecutorService sch;
    private AbstractFuture fut;

    private final AbsCustCheckBox addSoldier = getCompoFactory().newCustCheckBox("");
    private final AbsPlainLabel currentCoords = getCompoFactory().newPlainLabel("");

    private final AbstractAtomicBoolean dragged;

    private RatePoint first = new RatePoint(Rate.zero(),Rate.zero());

    private RatePoint last = new RatePoint(Rate.zero(),Rate.zero());
//    private String noteFile = "";
    private AbsRtsTaskEnabled taskEnabled;
    private final LanguagesButtonsPair mainButton;
    public WindowRts(AbstractProgramInfos _list, LanguagesButtonsPair _pair, int[][] _note) {
        super(_list);
        AbsPanel battlegroundWrapper_ = getCompoFactory().newAbsolute();
        battleground = new PanelBattle(facade, getCompoFactory(), battlegroundWrapper_);
        StringMap<String> mes_ = MessagesRts.valMessages(_list.currentLg());
        animate.setText(mes_.getVal(MessagesRts.ANIMATE));
        pause.setText(mes_.getVal(MessagesRts.PAUSE));
        stop.setText(mes_.getVal(MessagesRts.STOP));
        addSoldier.setText(mes_.getVal(MessagesRts.ADD_SOLDIER));
        mainButton = _pair;
        setTaskEnabled(new DefRtsTaskEnabled());
        GuiBaseUtil.choose(this, _list);
        CustList<AbsMetaLabelRts> elts_ = new CustList<AbsMetaLabelRts>();
        stopped = _list.getThreadFactory().newAtomicBoolean();
        stopped.set(true);
        paused = _list.getThreadFactory().newAtomicBoolean();
        dragged = _list.getThreadFactory().newAtomicBoolean();
        count = _list.getThreadFactory().newAtomicLong();
        AbsPanel contentPane_ = getCompoFactory().newBorder();
        AbsPanel scene_ = getCompoFactory().newBorder();
        scene_.add(currentCoords, MessagesGuiFct.BORDER_LAYOUT_NORTH);
        InteractClick i_ = new InteractClick(this);
        battleground.addMouseListener(i_);
        battleground.addMouseMotionListener(i_);
        battleground.setSize(new MetaDimension((int) facade.getScreen().getWidth().ll(), (int) facade.getScreen().getHeight().ll()));
//        JPanel panelGame_ = new JPanel(new BorderLayout());
        battlegroundWrapper_.add(battleground.getContainer());
        RatePoint cust_ = facade.getTopLeftPoint();
        battleground.setLocation(cust_);
//        battleground.setLocation(facade.getTopLeftPoint());
        battlegroundWrapper_.setPreferredSize(new MetaDimension(256, 256));
        scene_.add(battlegroundWrapper_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
//        panel_.add(battlegroundWrapper_, BorderLayout.CENTER);
        left = new RtsKeyPad(RtsDirection.LEFT, getCompoFactory());
        RtsKeyPad left_ = left;
        right = new RtsKeyPad(RtsDirection.RIGHT, getCompoFactory());
        RtsKeyPad right_ = right;
        up = new RtsKeyPad(RtsDirection.UP, getCompoFactory());
        RtsKeyPad up_ = up;
        down = new RtsKeyPad(RtsDirection.DOWN, getCompoFactory());
        RtsKeyPad down_ = down;
        elts_.add(left_);
        elts_.add(right_);
        elts_.add(up_);
        elts_.add(down_);
        RtsTask task_ = new RtsTask(this);
        AbstractBaseExecutorService t_ = getThreadFactory().newExecutorService();
//        t_.scheduleAtFixedRate(task_,0,100, TimeUnit.MILLISECONDS);
//        ScheduledExecutorService t_ = new Timer(0, task_);
//        t_.setDelay(100);
        up_.addMouseListener(new RtsMouseTask(RtsDirection.UP,task_, t_));
        down_.addMouseListener(new RtsMouseTask(RtsDirection.DOWN, task_, t_));
        left_.addMouseListener(new RtsMouseTask(RtsDirection.LEFT, task_, t_));
        right_.addMouseListener(new RtsMouseTask(RtsDirection.RIGHT, task_, t_));
        contentPane_.add(up_.getPaintableLabel(), MessagesGuiFct.BORDER_LAYOUT_NORTH);
        contentPane_.add(down_.getPaintableLabel(), MessagesGuiFct.BORDER_LAYOUT_SOUTH);
        contentPane_.add(left_.getPaintableLabel(), MessagesGuiFct.BORDER_LAYOUT_WEST);
        contentPane_.add(right_.getPaintableLabel(), MessagesGuiFct.BORDER_LAYOUT_EAST);
        animate.addActionListener(new Animate(this));
        AbsPanel buttons_ = getCompoFactory().newLineBox();
        buttons_.add(animate);
        buttons_.add(addSoldier);
        pause.setEnabled(false);
        pause.addActionListener(new RtsPause(this));
        buttons_.add(pause);
        stop.setEnabled(false);
        stop.addActionListener(new Stop(this));
        buttons_.add(stop);
//        noteFile = note_;
        AbstractImage or_ = ConverterGraphicBufferedImage.decodeToImage(getImageFactory(),_note);
        int wCurs_ = or_.getWidth();
        int hCurs_ = or_.getHeight();
        int[] pixels_ = new int[wCurs_ * hCurs_];
        for (int j = 0; j < hCurs_; j++) {
            for (int i = 0; i < wCurs_; i++) {
                if (or_.getRGB(i, j) == GuiConstants.WHITE) {
                    continue;
                }
                pixels_[j * wCurs_ + i] = or_.getRGB(i, j);
            }
        }
        setCursor(battlegroundWrapper_, wCurs_, hCurs_, pixels_);
        scene_.add(buttons_, MessagesGuiFct.BORDER_LAYOUT_SOUTH);
        contentPane_.add(scene_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
        AbsMetaLabelRts.repaintChildren(elts_,getImageFactory());
//        battlegroundWrapper_.repaintSecondChildren(getImageFactory());
//        battleground.getContainer().repaintSecondChildren(getImageFactory());
//        contentPane_.repaintSecondChildren(getImageFactory());
        setContentPane(contentPane_);
        pack();
        setVisible(true);
//        exitMode(_list);
//        setDefaultCloseOperation(GuiConstants.EXIT_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
        thread = new AnimationUnitSoldier(animate,pause,stop, this);
    }

    public AbsPlainLabel getCurrentCoords() {
        return currentCoords;
    }

    private void setCursor(AbsPanel _battlegroundWrapper, int _wCurs, int _hCurs, int[] _pixels) {
        getFrames().getFrameFactory().setCursor(_battlegroundWrapper,_wCurs, _hCurs, _pixels);
    }

//    @Override
//    public void dispose() {
//        GuiBaseUtil.trEx(this);
//    }

    public void setEnabledPause(boolean _enabled) {
        pause.setEnabled(_enabled);
    }

    public boolean isAddingSoldier() {
        return addSoldier.isSelected();
    }

    public AbsCustCheckBox getAddSoldier() {
        return addSoldier;
    }

    public void moveCamera(int _x, int _y) {
        if (thread.isStopped()) {
            return;
        }
        thread.moveCamera(new Rate(_x), new Rate(_y));
    }

    public void pause() {
        thread.pause();
    }

    public void stopGame() {
        thread.stopGame();
    }

    public void addNewSoldier(int _x, int _y) {
        if (thread.isStopped()) {
            return;
        }
        thread.addNewSoldier(_x, _y,count.getAndIncrement());
    }

    public void setNewLocation(int _x, int _y) {
        if (thread.isStopped()) {
            return;
        }
        thread.setNewLocation(_x, _y);
    }

    public void selectOrDeselect(int _x, int _y) {
        if (thread.isStopped()) {
            return;
        }
        thread.selectOrDeselect(new Rate(_x), new Rate(_y));
    }

    public void selectOrDeselectMulti() {
        if (thread.isStopped()) {
            return;
        }
        thread.selectOrDeselect(first, last);
    }

    public void animate() {
        //Un seul thread peut affecter l'objet de la classe Balle
        //Si un thread est en train d'executer, on empeche les autres de passer
        animate.setEnabled(false);
        thread = new AnimationUnitSoldier(animate,pause,stop, this);
        thread.reset();
        sch = getThreadFactory().newScheduledExecutorService();
        fut = sch.scheduleAtFixedRateNanos(thread,0,1);
        pause.setEnabled(true);
        stop.setEnabled(true);
    }

    public void cancel() {
//        if (fut == null) {
//            return;
//        }
        fut.cancel(false);
        sch.shutdown();
    }

    public boolean isDragged() {
        return dragged.get();
    }

    public void setDragged(boolean _dragged) {
        dragged.set(_dragged);
    }

    public RatePoint getFirst() {
        return first;
    }

    public void setFirst(int _x, int _y) {
        first = new RatePoint(new Rate(_x), new Rate(_y));
    }

    public RatePoint getLast() {
        return last;
    }

    public void setLast(int _x, int _y) {
        last = new RatePoint(new Rate(_x), new Rate(_y));
    }

    public PanelBattle getBattleground() {
        return battleground;
    }

    public AbstractAtomicBoolean getStopped() {
        return stopped;
    }

    public Facade getFacade() {
        return facade;
    }

    public AbsButton getAnimate() {
        return animate;
    }

    public AbsButton getStop() {
        return stop;
    }

    public AnimationUnitSoldier getThread() {
        return thread;
    }

    public RtsKeyPad getLeft() {
        return left;
    }

    public RtsKeyPad getRight() {
        return right;
    }

    public RtsKeyPad getUp() {
        return up;
    }

    public RtsKeyPad getDown() {
        return down;
    }

    public AbsCustCheckBox getPause() {
        return pause;
    }

    public AbstractAtomicBoolean getPaused() {
        return paused;
    }

    public AbsRtsTaskEnabled getTaskEnabled() {
        return taskEnabled;
    }

    public void setTaskEnabled(AbsRtsTaskEnabled _t) {
        this.taskEnabled = _t;
    }

//    public Cursor getCurrentCursor() {
//        return currentCursor;
//    }

    @Override
    public void quit() {
        getCommonFrame().setVisible(false);
        LanguageDialogButtons.enable(mainButton.getMainButton(),true);
//        LanguageComponentButtons.enableButtons(mainButton.getButtons(),true);
        GuiBaseUtil.trEx(this, getFrames());
    }

    @Override
    public String getApplicationName() {
        return MessagesRts.APPS_RTS;
    }

//    @Override
//    public boolean canChangeLanguage() {
//        return true;
//    }

    @Override
    public void changeLanguage(String _language) {
        setLanguageKey(_language);
    }

//    public String getNoteFile() {
//        return noteFile;
//    }
}