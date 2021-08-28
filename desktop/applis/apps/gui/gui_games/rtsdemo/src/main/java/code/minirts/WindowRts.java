package code.minirts;

import code.gui.*;
import code.gui.events.QuittingEvent;
import code.gui.images.AbstractImage;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.initialize.AbstractProgramInfos;
import code.images.BaseSixtyFourUtil;
import code.maths.geo.CustPoint;
import code.minirts.events.*;
import code.minirts.rts.RtsDirection;
import code.minirts.rts.Facade;
import code.scripts.messages.gui.MessPlayerGr;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractAtomicLong;
import code.threads.AbstractScheduledExecutorService;
import code.threads.AbstractThread;
import code.util.StringMap;

import javax.swing.*;
import java.awt.*;

public final class WindowRts extends GroupFrame {

    public static final String MOUSE_ARROW_FILE = "resources_rts/mouse_arrow.txt";

    public static final String NOTE_FILE = "resources_rts/note.txt";

    public static final String FOLDER = "rts_imgs";

    private final StringMap<String> messagesFiles = MessPlayerGr.ms();

//    private final Cursor currentCursor = Cursor.getDefaultCursor();

    private final PlainButton animate = new PlainButton("Animate");

    private final CustCheckBox pause = new CustCheckBox("Pause");

    private final PlainButton stop = new PlainButton("Stop");

    private final Facade facade = new Facade();

    private final PanelBattle battleground = new PanelBattle(facade, getCompoFactory());
    private final AbstractAtomicBoolean stopped;
    private final AbstractAtomicBoolean paused;
    private final AbstractAtomicLong count;
    private AnimationUnitSoldier thread;
    private AbstractThread threadLau;

    private final CustCheckBox addSoldier = new CustCheckBox("Add soldier");

    private final AbstractAtomicBoolean dragged;

    private CustPoint first = new CustPoint(0,0);

    private CustPoint last = new CustPoint(0,0);
    private String noteFile = "";

    public WindowRts(String _lg, AbstractProgramInfos _list) {
        super(_lg, _list);
        stopped = _list.getThreadFactory().newAtomicBoolean();
        stopped.set(true);
        paused = _list.getThreadFactory().newAtomicBoolean();
        dragged = _list.getThreadFactory().newAtomicBoolean();
        count = _list.getThreadFactory().newAtomicLong();
        AbsPanel contentPane_ = getCompoFactory().newBorder();
        AbsPanel scene_ = getCompoFactory().newBorder();
        InteractClick i_ = new InteractClick(this);
        battleground.addMouseListener(i_);
        battleground.addMouseMotionListener(i_);
        battleground.setSize(new Dimension(2048, 2048));
//        JPanel panelGame_ = new JPanel(new BorderLayout());
        AbsPanel battlegroundWrapper_ = getCompoFactory().newAbsolute();
        battlegroundWrapper_.add(battleground.getContainer());
        CustPoint cust_ = facade.getTopLeftPoint();
        battleground.setLocation(cust_);
//        battleground.setLocation(facade.getTopLeftPoint());
        battlegroundWrapper_.setPreferredSize(new Dimension(256, 256));
        scene_.add(battlegroundWrapper_, BorderLayout.CENTER);
//        panel_.add(battlegroundWrapper_, BorderLayout.CENTER);
        RtsKeyPad left_ = new RtsKeyPad(RtsDirection.LEFT, getCompoFactory());
        RtsKeyPad right_ = new RtsKeyPad(RtsDirection.RIGHT, getCompoFactory());
        RtsKeyPad up_ = new RtsKeyPad(RtsDirection.UP, getCompoFactory());
        RtsKeyPad down_ = new RtsKeyPad(RtsDirection.DOWN, getCompoFactory());
        RtsTask task_ = new RtsTask(battleground, this, facade);
        AbstractScheduledExecutorService t_ = getThreadFactory().newScheduledExecutorService();
//        t_.scheduleAtFixedRate(task_,0,100, TimeUnit.MILLISECONDS);
//        ScheduledExecutorService t_ = new Timer(0, task_);
//        t_.setDelay(100);
        up_.addMouseListener(new RtsMouseTask(RtsDirection.UP,task_, t_));
        down_.addMouseListener(new RtsMouseTask(RtsDirection.DOWN, task_, t_));
        left_.addMouseListener(new RtsMouseTask(RtsDirection.LEFT, task_, t_));
        right_.addMouseListener(new RtsMouseTask(RtsDirection.RIGHT, task_, t_));
        contentPane_.add(up_, BorderLayout.NORTH);
        contentPane_.add(down_, BorderLayout.SOUTH);
        contentPane_.add(left_, BorderLayout.WEST);
        contentPane_.add(right_, BorderLayout.EAST);
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
                if (or_.getRGB(i, j) == Color.WHITE.getRGB()) {
                    continue;
                }
                pixels_[j * wCurs_ + i] = or_.getRGB(i, j);
            }
        }
        setCursor(battlegroundWrapper_, wCurs_, hCurs_, pixels_);
        scene_.add(buttons_, BorderLayout.SOUTH);
        contentPane_.add(scene_, BorderLayout.CENTER);
        battlegroundWrapper_.repaintSecondChildren(getImageFactory());
        battleground.getContainer().repaintSecondChildren(getImageFactory());
        contentPane_.repaintSecondChildren(getImageFactory());
        setContentPane(contentPane_);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
        thread = new AnimationUnitSoldier(animate,pause,stop, battleground,this);
        threadLau = getThreadFactory().newThread(thread);
    }

    private static void setCursor(AbsPanel _battlegroundWrapper, int _wCurs, int _hCurs, int[] _pixels) {
        _battlegroundWrapper.setCursor(_wCurs, _hCurs, _pixels);
    }

    @Override
    public void dispose() {
        basicDispose();
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
        threadLau = getThreadFactory().newThread(thread);
        threadLau.start();
        pause.setEnabled(true);
        stop.setEnabled(true);
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
        basicDispose();
    }

    @Override
    public String getApplicationName() {
        return LaunchingDemo.getMainWindowClass();
    }

    @Override
    public boolean canChangeLanguage() {
        return true;
    }

    @Override
    public void changeLanguage(String _language) {
        //
    }

    public String getNoteFile() {
        return noteFile;
    }
}