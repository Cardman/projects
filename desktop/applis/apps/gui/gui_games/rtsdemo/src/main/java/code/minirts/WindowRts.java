package code.minirts;

import code.gui.*;
import code.gui.events.*;
import code.gui.images.AbstractImage;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.images.BaseSixtyFourUtil;
import code.maths.geo.CustPoint;
import code.minirts.events.*;
import code.minirts.rts.RtsDirection;
import code.minirts.rts.Facade;
import code.scripts.messages.gui.MessPlayerGr;
import code.threads.*;
import code.util.CustList;
import code.util.StringMap;






public final class WindowRts extends GroupFrame implements AbsOpenQuit {

    public static final String MOUSE_ARROW_FILE = "resources_rts/mouse_arrow.txt";

    public static final String NOTE_FILE = "resources_rts/note.txt";

    public static final String FOLDER = "rts_imgs";

    private final StringMap<String> messagesFiles = MessPlayerGr.ms();

//    private final Cursor currentCursor = Cursor.getDefaultCursor();

    private final AbsPlainButton animate = getCompoFactory().newPlainButton("Animate");

    private final AbsCustCheckBox pause = getCompoFactory().newCustCheckBox("Pause");

    private final AbsPlainButton stop = getCompoFactory().newPlainButton("Stop");

    private final Facade facade = new Facade();

    private final PanelBattle battleground = new PanelBattle(facade, getCompoFactory());
    private final AbstractAtomicBoolean stopped;
    private final AbstractAtomicBoolean paused;
    private final AbstractAtomicLong count;
    private AnimationUnitSoldier thread;
    private AbstractScheduledExecutorService sch;
    private AbstractFuture fut;

    private final AbsCustCheckBox addSoldier = getCompoFactory().newCustCheckBox("Add soldier");
    private final AbsPlainLabel currentCoords = getCompoFactory().newPlainLabel("");

    private final AbstractAtomicBoolean dragged;

    private CustPoint first = new CustPoint(0,0);

    private CustPoint last = new CustPoint(0,0);
    private String noteFile = "";

    public WindowRts(String _lg, AbstractProgramInfos _list) {
        super(_lg, _list);
        GuiBaseUtil.choose(_lg, this, _list.getCommon());
        CustList<AbsMetaLabelRts> elts_ = new CustList<AbsMetaLabelRts>();
        stopped = _list.getThreadFactory().newAtomicBoolean();
        stopped.set(true);
        paused = _list.getThreadFactory().newAtomicBoolean();
        dragged = _list.getThreadFactory().newAtomicBoolean();
        count = _list.getThreadFactory().newAtomicLong();
        AbsPanel contentPane_ = getCompoFactory().newBorder();
        AbsPanel scene_ = getCompoFactory().newBorder();
        scene_.add(currentCoords,GuiConstants.BORDER_LAYOUT_NORTH);
        InteractClick i_ = new InteractClick(this);
        battleground.addMouseListener(i_);
        battleground.addMouseMotionListener(i_);
        battleground.setSize(new MetaDimension(2048, 2048));
//        JPanel panelGame_ = new JPanel(new BorderLayout());
        AbsPanel battlegroundWrapper_ = getCompoFactory().newAbsolute();
        battlegroundWrapper_.add(battleground.getContainer());
        CustPoint cust_ = facade.getTopLeftPoint();
        battleground.setLocation(cust_);
//        battleground.setLocation(facade.getTopLeftPoint());
        battlegroundWrapper_.setPreferredSize(new MetaDimension(256, 256));
        scene_.add(battlegroundWrapper_, GuiConstants.BORDER_LAYOUT_CENTER);
//        panel_.add(battlegroundWrapper_, BorderLayout.CENTER);
        RtsKeyPad left_ = new RtsKeyPad(RtsDirection.LEFT, getCompoFactory());
        RtsKeyPad right_ = new RtsKeyPad(RtsDirection.RIGHT, getCompoFactory());
        RtsKeyPad up_ = new RtsKeyPad(RtsDirection.UP, getCompoFactory());
        RtsKeyPad down_ = new RtsKeyPad(RtsDirection.DOWN, getCompoFactory());
        elts_.add(left_);
        elts_.add(right_);
        elts_.add(up_);
        elts_.add(down_);
        RtsTask task_ = new RtsTask(battleground, this, facade);
        AbstractScheduledExecutorService t_ = getThreadFactory().newScheduledExecutorService();
//        t_.scheduleAtFixedRate(task_,0,100, TimeUnit.MILLISECONDS);
//        ScheduledExecutorService t_ = new Timer(0, task_);
//        t_.setDelay(100);
        up_.addMouseListener(new RtsMouseTask(RtsDirection.UP,task_, t_));
        down_.addMouseListener(new RtsMouseTask(RtsDirection.DOWN, task_, t_));
        left_.addMouseListener(new RtsMouseTask(RtsDirection.LEFT, task_, t_));
        right_.addMouseListener(new RtsMouseTask(RtsDirection.RIGHT, task_, t_));
        contentPane_.add(up_.getPaintableLabel(), GuiConstants.BORDER_LAYOUT_NORTH);
        contentPane_.add(down_.getPaintableLabel(), GuiConstants.BORDER_LAYOUT_SOUTH);
        contentPane_.add(left_.getPaintableLabel(), GuiConstants.BORDER_LAYOUT_WEST);
        contentPane_.add(right_.getPaintableLabel(), GuiConstants.BORDER_LAYOUT_EAST);
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
        String note_ = messagesFiles.getVal("resources_player/player.txt");
        noteFile = note_;
        AbstractImage or_ = ConverterGraphicBufferedImage.decodeToImage(getImageFactory(),BaseSixtyFourUtil.getImageByString(note_));
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
        scene_.add(buttons_, GuiConstants.BORDER_LAYOUT_SOUTH);
        contentPane_.add(scene_, GuiConstants.BORDER_LAYOUT_CENTER);
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
        thread = new AnimationUnitSoldier(animate,pause,stop, battleground,this);
    }

    public AbsPlainLabel getCurrentCoords() {
        return currentCoords;
    }

    private void setCursor(AbsPanel _battlegroundWrapper, int _wCurs, int _hCurs, int[] _pixels) {
        getFrames().getFrameFactory().setCursor(_battlegroundWrapper,_wCurs, _hCurs, _pixels);
    }

//    @Override
    public void dispose() {
        GuiBaseUtil.trEx(this);
    }

    public void setEnabledPause(boolean _enabled) {
        pause.setEnabled(_enabled);
    }

    public boolean isAddingSoldier() {
        return addSoldier.isSelected();
    }

    public void moveCamera(CustPoint _p, int _x, int _y) {
        if (thread.isStopped()) {
            return;
        }
        thread.moveCamera(_p, _x, _y);
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
        thread.selectOrDeselect(_x, _y);
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
        thread = new AnimationUnitSoldier(animate,pause,stop,battleground,this);
        thread.reset();
        sch = getThreadFactory().newScheduledExecutorService();
        fut = sch.scheduleAtFixedRateNanos(thread,0,1);
        pause.setEnabled(true);
        stop.setEnabled(true);
    }

    public void cancel() {
        if (fut == null) {
            return;
        }
        fut.cancel(false);
        sch.shutdown();
    }

    public boolean isDragged() {
        return dragged.get();
    }

    public void setDragged(boolean _dragged) {
        dragged.set(_dragged);
    }

    public CustPoint getFirst() {
        return first;
    }

    public void setFirst(int _x, int _y) {
        first = new CustPoint(_x, _y);
    }

    public CustPoint getLast() {
        return last;
    }

    public void setLast(int _x, int _y) {
        last = new CustPoint(_x, _y);
    }

    public PanelBattle getBattleground() {
        return battleground;
    }

    public AbstractAtomicBoolean getStopped() {
        return stopped;
    }

    public AbstractAtomicBoolean getPaused() {
        return paused;
    }

//    public Cursor getCurrentCursor() {
//        return currentCursor;
//    }

    @Override
    public void quit() {
        GuiBaseUtil.trEx(this);
    }

    @Override
    public String getApplicationName() {
        return LaunchingDemo.getMainWindowClass();
    }

//    @Override
//    public boolean canChangeLanguage() {
//        return true;
//    }

    @Override
    public void changeLanguage(String _language) {
        setLanguageKey(_language);
    }

    public String getNoteFile() {
        return noteFile;
    }
}