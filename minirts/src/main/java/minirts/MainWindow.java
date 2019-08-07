package minirts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import code.gui.CustCheckBox;
import code.gui.PlainButton;
import minirts.events.Animate;
import minirts.events.InteractClick;
import minirts.events.MouseTask;
import minirts.events.Pause;
import minirts.events.Stop;
import minirts.events.Task;
import minirts.rts.CustPoint;
import minirts.rts.Direction;
import minirts.rts.Facade;
import code.gui.GroupFrame;
import code.gui.Panel;
import code.gui.images.ConverterGraphicBufferedImage;
import code.images.BaseSixtyFourUtil;
import code.resources.ResourceFiles;

public final class MainWindow extends GroupFrame {

    public static final String MOUSE_ARROW_FILE = "resources_rts/mouse_arrow.txt";

    public static final String NOTE_FILE = "resources_rts/note.txt";

    public static final String FOLDER = "rts_imgs";

    public static final Cursor DEFAULT_CURSOR1 = Cursor.getDefaultCursor();

    public static Cursor _currentCursor_ = DEFAULT_CURSOR1;

    public static Cursor _custCursor_ = _currentCursor_;

    private static final String SELECT = "select";

    private PlainButton animate = new PlainButton("Animate");

    private CustCheckBox pause = new CustCheckBox("Pause");

    private PlainButton stop = new PlainButton("Stop");

    private Facade facade = new Facade();

    private PanelBattle battleground = new PanelBattle(facade);

    private AnimationUnitSoldier thread = new AnimationUnitSoldier(battleground);
    private Thread threadLau = new Thread(thread);

    private CustCheckBox addSoldier = new CustCheckBox("Add soldier");

    private boolean dragged;

    private CustPoint first = new CustPoint();

    private CustPoint last = new CustPoint();

    public MainWindow(String _lg) {
        super(_lg);
        AnimationUnitSoldier.setWindow(this);
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
        Point pt_ = new Point();
        pt_.x = -cust_.getX();
        pt_.y = -cust_.getY();
        battleground.setLocation(pt_);
//        battleground.setLocation(facade.getTopLeftPoint());
        battlegroundWrapper_.setPreferredSize(new Dimension(256, 256));
        scene_.add(battlegroundWrapper_, BorderLayout.CENTER);
//        panel_.add(battlegroundWrapper_, BorderLayout.CENTER);
        KeyPad left_ = new KeyPad(Direction.LEFT);
        KeyPad right_ = new KeyPad(Direction.RIGHT);
        KeyPad up_ = new KeyPad(Direction.UP);
        KeyPad down_ = new KeyPad(Direction.DOWN);
        Task task_ = new Task(battleground, this, facade);
        Timer t_ = new Timer(0, task_);
        t_.setDelay(100);
        up_.addMouseListener(new MouseTask(Direction.UP,task_, t_));
        down_.addMouseListener(new MouseTask(Direction.DOWN, task_, t_));
        left_.addMouseListener(new MouseTask(Direction.LEFT, task_, t_));
        right_.addMouseListener(new MouseTask(Direction.RIGHT, task_, t_));
        contentPane_.add(up_, BorderLayout.NORTH);
        contentPane_.add(down_, BorderLayout.SOUTH);
        contentPane_.add(left_, BorderLayout.WEST);
        contentPane_.add(right_, BorderLayout.EAST);
        animate.addActionListener(new Animate(this));
        Panel buttons_ = new Panel();
        buttons_.add(animate);
        buttons_.add(addSoldier);
        pause.addActionListener(new Pause(this));
        buttons_.add(pause);
        stop.addActionListener(new Stop(this));
        buttons_.add(stop);
        Toolkit tool_ = Toolkit.getDefaultToolkit();
        String note_ = ResourceFiles.ressourceFichier(NOTE_FILE);
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
        _custCursor_ = c_;
        battlegroundWrapper_.setCursor(c_);
        scene_.add(buttons_, BorderLayout.SOUTH);
        contentPane_.add(scene_, BorderLayout.CENTER);
        setContentPane(contentPane_);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
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
        AnimationUnitSoldier.moveCamera(_pt);
    }

    public void pause() {
        if (!threadLau.isAlive()) {
            return;
        }
        AnimationUnitSoldier.pause();
    }

    public void stopGame() {
        if (!threadLau.isAlive()) {
            return;
        }
        AnimationUnitSoldier.stopGame();
    }

    public void addNewSoldier(int _x, int _y) {
        if (!threadLau.isAlive()) {
            return;
        }
        AnimationUnitSoldier.addNewSoldier(_x, _y);
    }

    public void setNewLocation(int _x, int _y) {
        if (!threadLau.isAlive()) {
            return;
        }
        AnimationUnitSoldier.setNewLocation(_x, _y);
    }

    public void selectOrDeselect(int _x, int _y) {
        if (!threadLau.isAlive()) {
            return;
        }
        AnimationUnitSoldier.selectOrDeselect(_x, _y);
    }

    public void selectOrDeselectMulti() {
        if (!threadLau.isAlive()) {
            return;
        }
        AnimationUnitSoldier.selectOrDeselect(first, last);
    }

    public void animate() {
        //Un seul thread peut affecter l'objet de la classe Balle
        //Si un thread est en train d'executer, on empeche les autres de passer
        if (threadLau.isAlive()) {
            return;
        }
        thread = new AnimationUnitSoldier(battleground);
        threadLau = new Thread(thread);
        threadLau.start();
    }

    public boolean isDragged() {
        return dragged;
    }

    public void setDragged(boolean _dragged) {
        dragged = _dragged;
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

    @Override
    public void quit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getApplicationName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean canChangeLanguage() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void changeLanguage(String _language) {
        // TODO Auto-generated method stub
        
    }

}