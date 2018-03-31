package aiki.gui.components.walk;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import aiki.comparators.ComparatorScreenCoords;
import aiki.facade.FacadeGame;
import aiki.map.enums.Direction;
import aiki.map.util.ScreenCoords;
import code.gui.images.ConverterGraphicBufferedImage;
import code.util.CustList;
import code.util.TreeMap;

public class Scene extends JLabel implements MouseListener {

    private static int _sideLength_;

    private static int _screenWidth_;

    private static int _screenHeight_;

    private static int _xHeros_;

    private static int _yHeros_;

//    private TreeMap<ScreenCoords,BufferedImage> buff = new TreeMap<new>(new Comparator<ScreenCoords>() {
//        @Override
//        public int compare(ScreenCoords _o1, ScreenCoords _o2) {
//            int res_ = Integer.compare(_o1.getXcoords(), _o2.getXcoords());
//            if (res_ != 0) {
//                return res_;
//            }
//            return Integer.compare(_o1.getYcoords(), _o2.getYcoords());
//        }
//    });
    private TreeMap<ScreenCoords,BufferedImage> background = new TreeMap<ScreenCoords, BufferedImage>(new ComparatorScreenCoords());

//    private TreeMap<ScreenCoords,CustList<BufferedImage>> fore = new TreeMap<new>(new Comparator<ScreenCoords>() {
//        @Override
//        public int compare(ScreenCoords _o1, ScreenCoords _o2) {
//            int res_ = Integer.compare(_o1.getXcoords(), _o2.getXcoords());
//            if (res_ != 0) {
//                return res_;
//            }
//            return Integer.compare(_o1.getYcoords(), _o2.getYcoords());
//        }
//    });
    private TreeMap<ScreenCoords,CustList<BufferedImage>> foreground = new TreeMap<ScreenCoords,CustList<BufferedImage>>(new ComparatorScreenCoords());

//    private BufferedImage hero;

//    private boolean dirOnly;

    private Direction dir;

    private int delta;

    private boolean apply;

    private boolean animated;

//    private FacadeGame facade;

//    private int nb;

//    private boolean export;

    public Scene() {
        setFocusable(true);
        setBackground(Color.WHITE);
    }

    public static void setDimensions(FacadeGame _facade) {
        _sideLength_ = _facade.getMap().getSideLength();
        _screenWidth_ = _facade.getMap().getScreenWidth();
        _screenHeight_ = _facade.getMap().getScreenHeight();
        _xHeros_ = _facade.getMap().getSpaceBetweenLeftAndHeros();
        _yHeros_ = _facade.getMap().getSpaceBetweenTopAndHeros();
    }

    public void setPreferredSize() {
        setPreferredSize(new Dimension(_sideLength_*_screenWidth_, _sideLength_*_screenHeight_));
    }

    public void setAnimated(boolean _animated) {
        animated = _animated;
    }

//    public void setNb() {
//        export = true;
//        nb = 0;
//    }

//    public void resetExport() {
//        export = false;
//    }

    public void load(FacadeGame _facade, boolean _setPreferredSize) {
//        facade = _facade;
        TreeMap<ScreenCoords,BufferedImage> background_ = new TreeMap<ScreenCoords, BufferedImage>(new ComparatorScreenCoords());
        TreeMap<ScreenCoords,CustList<BufferedImage>> foreground_ = new TreeMap<ScreenCoords,CustList<BufferedImage>>(new ComparatorScreenCoords());
//        Map<ScreenCoords, BufferedImage> oldLine_;
//        oldLine_ = new Map<>();
//        Map<ScreenCoords, CustList<BufferedImage>> oldForeLine_;
//        oldForeLine_ = new Map<>();
//        if (_clear) {
//            if (dir == Direction.DOWN) {
//                for (ScreenCoords s: background.getKeys()) {
//                    if (s.getYcoords() == 0) {
//                        oldLine_.put(new ScreenCoords(s.getXcoords(), -1), background.getVal(s));
//                    }
//                }
//                for (ScreenCoords s: foreground.getKeys()) {
//                    if (s.getYcoords() == 0) {
//                        oldForeLine_.put(new ScreenCoords(s.getXcoords(), -1), foreground.getVal(s));
//                    }
//                }
//            } else if (dir == Direction.UP) {
//                for (ScreenCoords s: background.getKeys()) {
//                    if (s.getYcoords() == _screenHeight_ - 1) {
//                        oldLine_.put(new ScreenCoords(s.getXcoords(), _screenHeight_), background.getVal(s));
//                    }
//                }
//                for (ScreenCoords s: foreground.getKeys()) {
//                    if (s.getYcoords() == _screenHeight_ - 1) {
//                        oldForeLine_.put(new ScreenCoords(s.getXcoords(), _screenHeight_), foreground.getVal(s));
//                    }
//                }
//            } else if (dir == Direction.RIGHT) {
//                for (ScreenCoords s: background.getKeys()) {
//                    if (s.getXcoords() == 0) {
//                        oldLine_.put(new ScreenCoords(-1, s.getYcoords()), background.getVal(s));
//                    }
//                }
//                for (ScreenCoords s: foreground.getKeys()) {
//                    if (s.getXcoords() == 0) {
//                        oldForeLine_.put(new ScreenCoords(-1, s.getYcoords()), foreground.getVal(s));
//                    }
//                }
//            } else if (dir == Direction.LEFT) {
//                for (ScreenCoords s: background.getKeys()) {
//                    if (s.getXcoords() == _screenWidth_ - 1) {
//                        oldLine_.put(new ScreenCoords(_screenWidth_, s.getYcoords()), background.getVal(s));
//                    }
//                }
//                for (ScreenCoords s: foreground.getKeys()) {
//                    if (s.getXcoords() == _screenWidth_ - 1) {
//                        oldForeLine_.put(new ScreenCoords(_screenWidth_, s.getYcoords()), foreground.getVal(s));
//                    }
//                }
//            }
//        }
//        background.clear();
//        foreground.clear();
        for (ScreenCoords sc_: _facade.getBackgroundImages().getKeys()) {
            int[][] img_ = _facade.getBackgroundImages().getVal(sc_);
            BufferedImage buff_ = ConverterGraphicBufferedImage.decodeToImage(img_);
            ConverterGraphicBufferedImage.transparentAllWhite(buff_);
            background_.put(sc_, buff_);
        }
//        background_.putAllMap(oldLine_);
//        _sideLength_ = _facade.getMap().getSideLength();
        for (ScreenCoords sc_: _facade.getForegroundImages().getKeys()) {
            CustList<BufferedImage> imgs_ = new CustList<BufferedImage>();
            for (int[][] b: _facade.getForegroundImages().getVal(sc_)) {
                if (b.length == 0) {
                    continue;
                }
//                BufferedImage buff_ = ConverterBufferedImage.centerImage(b, _sideLength_);
                BufferedImage buff_ = ConverterGraphicBufferedImage.decodeToImage(b);
                ConverterGraphicBufferedImage.transparentAllWhite(buff_);
                imgs_.add(buff_);
            }
            foreground_.put(sc_, imgs_);
        }
//        foreground_.putAllMap(oldForeLine_);
        background = background_;
        foreground = foreground_;
//        _screenWidth_ = _facade.getMap().getScreenWidth();
//        _screenHeight_ = _facade.getMap().getScreenHeight();
//        _xHeros_ = _facade.getMap().getSpaceBetweenLeftAndHeros();
//        _yHeros_ = _facade.getMap().getSpaceBetweenTopAndHeros();
        setDir(_facade);
//        dirOnly = false;
        if (_setPreferredSize) {
            setPreferredSize();
        }
        //setPreferredSize(new Dimension(_sideLength_*_screenWidth_, _sideLength_*_screenHeight_));
    }

    public void complete(FacadeGame _facade) {
        //CustList<ScreenCoords> keys_;
        //keys_ = _facade.getBackgroundImages().getKeys();
        for (ScreenCoords s: background.getKeys()) {
            if (s.getXcoords() < 0 || s.getYcoords() < 0) {
                background.removeKey(s);
                _facade.getBackgroundImages().removeKey(s);
            } else if (s.getXcoords() == _screenWidth_ || s.getYcoords() == _screenHeight_) {
                background.removeKey(s);
                _facade.getBackgroundImages().removeKey(s);
            }
        }
        //keys_ = _facade.getForegroundImages().getKeys();
        for (ScreenCoords s: foreground.getKeys()) {
            if (s.getXcoords() < 0 || s.getYcoords() < 0) {
                foreground.removeKey(s);
                _facade.getForegroundImages().removeKey(s);
            } else if (s.getXcoords() == _screenWidth_ || s.getYcoords() == _screenHeight_) {
                foreground.removeKey(s);
                _facade.getForegroundImages().removeKey(s);
            }
        }
        repaint();
    }

    public void setDir(FacadeGame _f) {
        dir = _f.getGame().getPlayerOrientation();
    }

    public void keepTiles() {
        setDelta(0, false);
    }

    public void setDelta(int _d, boolean _apply) {
        delta = _d;
        apply = _apply;
    }

    @Override
    protected void paintComponent(Graphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0, 0, _sideLength_*_screenWidth_ - 1, _sideLength_*_screenHeight_ - 1);
        int dx_ = 0;
        int dy_ = 0;
        if (animated) {
            dx_ = dir.getx();
            dy_ = dir.gety();
        }
        int xDelta_ = -dir.getx() * delta;
        int yDelta_ = -dir.gety() * delta;
        if (!apply) {
            dx_ = 0;
            dy_ = 0;
        }
        for (ScreenCoords sc_: background.getKeys()) {
            BufferedImage buff_ = background.getVal(sc_);
            _g.drawImage(buff_, _sideLength_* sc_.getXcoords() + xDelta_, _sideLength_ * sc_.getYcoords() + yDelta_, null);
        }
        for (ScreenCoords sc_: foreground.getKeys()) {
            if (sc_.getXcoords() == _xHeros_ + dx_) {
                if (sc_.getYcoords() == _yHeros_ + dy_) {
                    CustList<BufferedImage> imgs_ = foreground.getVal(sc_);
                    int size_ = imgs_.size();
                    for (int i = CustList.FIRST_INDEX; i < size_; i++) {
                        BufferedImage buff_ = imgs_.get(i);
                        if (i != size_ - 1) {
                            int wMin_ = buff_.getWidth();
                            int hMin_ = buff_.getHeight();
                            _g.drawImage(buff_, _sideLength_* sc_.getXcoords() + xDelta_ + (_sideLength_ - wMin_) / 2, _sideLength_ * sc_.getYcoords() + yDelta_ + (_sideLength_ - hMin_) / 2, null);
                        }
                    }
                    continue;
                }
            }
            for (BufferedImage b:foreground.getVal(sc_)) {
                int wMin_ = b.getWidth();
                int hMin_ = b.getHeight();
                _g.drawImage(b, _sideLength_* sc_.getXcoords() + xDelta_ + (_sideLength_ - wMin_) / 2, _sideLength_ * sc_.getYcoords() + yDelta_ + (_sideLength_ - hMin_) / 2, null);
            }
        }
        if (!foreground.isEmpty()) {
            ScreenCoords sc_ = new ScreenCoords(_xHeros_ + dx_, _yHeros_ + dy_);
            CustList<BufferedImage> imgs_ = foreground.getVal(sc_);
            if (!imgs_.isEmpty()) {
                BufferedImage buff_ = imgs_.last();
                int wMin_ = buff_.getWidth();
                int hMin_ = buff_.getHeight();
                _g.drawImage(buff_, _sideLength_* (sc_.getXcoords() - dx_) + (_sideLength_ - wMin_) / 2, _sideLength_ * (sc_.getYcoords() - dy_) + (_sideLength_ - hMin_) / 2, null);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent _e) {
    }

    @Override
    public void mouseEntered(MouseEvent _e) {
    }

    @Override
    public void mouseExited(MouseEvent _e) {
    }

    @Override
    public void mousePressed(MouseEvent _e) {
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        setFocus();
    }

    public void setFocus() {
        requestFocus(false);
        requestFocusInWindow(false);
    }
}
