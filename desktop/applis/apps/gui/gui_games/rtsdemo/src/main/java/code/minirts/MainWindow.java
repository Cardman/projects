package code.minirts;

import code.gui.CustCheckBox;
import code.gui.GroupFrame;
import code.gui.Panel;
import code.gui.PlainButton;
import code.gui.events.QuittingEvent;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.initialize.AbstractProgramInfos;
import code.images.BaseSixtyFourUtil;
import code.maths.geo.CustPoint;
import code.minirts.events.*;
import code.minirts.rts.RtsDirection;
import code.minirts.rts.Facade;
import code.scripts.messages.gui.MessPlayerGr;
import code.threads.AbstractThread;
import code.util.StringMap;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public final class MainWindow extends GroupFrame {

    public static final String MOUSE_ARROW_FILE = "resources_rts/mouse_arrow.txt";

    public static final String NOTE_FILE = "resources_rts/note.txt";

    public static final String FOLDER = "rts_imgs";

    private static final String SELECT = "select";
    private final StringMap<String> messagesFiles = MessPlayerGr.ms();

    private final Cursor currentCursor = Cursor.getDefaultCursor();

    private final PlainButton animate = new PlainButton("Animate");

    private final CustCheckBox pause = new CustCheckBox("Pause");

    private final PlainButton stop = new PlainButton("Stop");

    private final Facade facade = new Facade();

    private final PanelBattle battleground = new PanelBattle(facade);
    private final AtomicBoolean stopped = new AtomicBoolean();
    private final AtomicBoolean paused = new AtomicBoolean();
    private final AtomicLong count = new AtomicLong();
    private AnimationUnitSoldier thread = new AnimationUnitSoldier(battleground,this);
    private AbstractThread threadLau;

    private final CustCheckBox addSoldier = new CustCheckBox("Add soldier");

    private final AtomicBoolean dragged = new AtomicBoolean();

    private CustPoint first = new CustPoint(0,0);

    private CustPoint last = new CustPoint(0,0);
    private String noteFile = "";

    public MainWindow(String _lg, AbstractProgramInfos _list) {
        super(_lg, _list);
        threadLau = getThreadFactory().newThread(thread);
        Panel contentPane_ = Panel.newBorder();
        Panel scene_ = Panel.newBorder();
        InteractClick i_ = new InteractClick(this);
        battleground.addMouseListener(i_);
        battleground.addMouseMotionListener(i_);
        battleground.setSize(new Dimension(2048, 2048));
//        JPanel panelGame_ = new JPanel(new BorderLayout());
        Panel battlegroundWrapper_ = Panel.newAbsolute();
        battlegroundWrapper_.add(battleground.getContainer());
        CustPoint cust_ = facade.getTopLeftPoint();
        battleground.setLocation(cust_);
//        battleground.setLocation(facade.getTopLeftPoint());
        battlegroundWrapper_.setPreferredSize(new Dimension(256, 256));
        scene_.add(battlegroundWrapper_, BorderLayout.CENTER);
//        panel_.add(battlegroundWrapper_, BorderLayout.CENTER);
        RtsKeyPad left_ = new RtsKeyPad(RtsDirection.LEFT);
        RtsKeyPad right_ = new RtsKeyPad(RtsDirection.RIGHT);
        RtsKeyPad up_ = new RtsKeyPad(RtsDirection.UP);
        RtsKeyPad down_ = new RtsKeyPad(RtsDirection.DOWN);
        RtsTask task_ = new RtsTask(battleground, this, facade);
        Timer t_ = new Timer(0, task_);
        t_.setDelay(100);
        up_.addMouseListener(new RtsMouseTask(RtsDirection.UP,task_, t_));
        down_.addMouseListener(new RtsMouseTask(RtsDirection.DOWN, task_, t_));
        left_.addMouseListener(new RtsMouseTask(RtsDirection.LEFT, task_, t_));
        right_.addMouseListener(new RtsMouseTask(RtsDirection.RIGHT, task_, t_));
        contentPane_.add(up_, BorderLayout.NORTH);
        contentPane_.add(down_, BorderLayout.SOUTH);
        contentPane_.add(left_, BorderLayout.WEST);
        contentPane_.add(right_, BorderLayout.EAST);
        animate.addActionListener(new Animate(this));
        Panel buttons_ = Panel.newLineBox();
        buttons_.add(animate);
        buttons_.add(addSoldier);
        pause.addActionListener(new RtsPause(this));
        buttons_.add(pause);
        stop.addActionListener(new Stop(this));
        buttons_.add(stop);
        Toolkit tool_ = Toolkit.getDefaultToolkit();
        String note_ = messagesFiles.getVal("resources_player/player.txt");
        noteFile = note_;
        BufferedImage or_ = ConverterGraphicBufferedImage.decodeToImage(BaseSixtyFourUtil.getImageByString(note_));
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
        Image b_ = tool_.createImage(new MemoryImageSource(wCurs_, hCurs_, pixels_, 0, wCurs_));
        Cursor c_ = tool_.createCustomCursor(b_, new Point(0, 0),SELECT);
        battlegroundWrapper_.setCursor(c_);
        scene_.add(buttons_, BorderLayout.SOUTH);
        contentPane_.add(scene_, BorderLayout.CENTER);
        battlegroundWrapper_.repaintSecondChildren();
        battleground.getContainer().repaintSecondChildren();
        contentPane_.repaintSecondChildren();
        setContentPane(contentPane_);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
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

    public void moveCamera(Point _pt) {
        if (!threadLau.isAlive()) {
            return;
        }
        thread.moveCamera(_pt);
    }

    public void pause() {
        if (!threadLau.isAlive()) {
            return;
        }
        thread.pause();
    }

    public void stopGame() {
        if (!threadLau.isAlive()) {
            return;
        }
        thread.stopGame();
    }

    public void addNewSoldier(int _x, int _y) {
        if (!threadLau.isAlive()) {
            return;
        }
        thread.addNewSoldier(_x, _y,count.getAndIncrement());
    }

    public void setNewLocation(int _x, int _y) {
        if (!threadLau.isAlive()) {
            return;
        }
        thread.setNewLocation(_x, _y);
    }

    public void selectOrDeselect(int _x, int _y) {
        if (!threadLau.isAlive()) {
            return;
        }
        thread.selectOrDeselect(_x, _y);
    }

    public void selectOrDeselectMulti() {
        if (!threadLau.isAlive()) {
            return;
        }
        thread.selectOrDeselect(first, last);
    }

    public void animate() {
        //Un seul thread peut affecter l'objet de la classe Balle
        //Si un thread est en train d'executer, on empeche les autres de passer
        if (threadLau.isAlive()) {
            return;
        }
        thread = new AnimationUnitSoldier(battleground,this);
        threadLau = getThreadFactory().newThread(thread);
        threadLau.start();
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

    public AtomicBoolean getStopped() {
        return stopped;
    }

    public AtomicBoolean getPaused() {
        return paused;
    }

    public Cursor getCurrentCursor() {
        return currentCursor;
    }

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