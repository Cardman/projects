package aiki.gui.components.walk;


import aiki.comparators.ComparatorScreenCoords;
import aiki.facade.FacadeGame;
import aiki.gui.components.AbsMetaLabelPk;
import aiki.map.enums.Direction;
import aiki.map.util.ScreenCoords;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.GuiConstants;
import code.gui.events.AbsMouseListenerIntRel;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.*;

public final class Scene extends AbsMetaLabelPk implements AbsMouseListenerIntRel {

    private int sideLength;

    private int screenWidth;

    private int screenHeight;

    private TreeMap<ScreenCoords,AbstractImage> background = new TreeMap<ScreenCoords, AbstractImage>(new ComparatorScreenCoords());

    private TreeMap<ScreenCoords,CustList<AbstractImage>> foreground = new TreeMap<ScreenCoords,CustList<AbstractImage>>(new ComparatorScreenCoords());

    private AbstractImage herosImages;
    private ScreenCoords herosScreen = new ScreenCoords();

    private Direction dir;

    private int delta;

    private boolean apply;

    private boolean animated;

    private final IntTileRender tileRender;

    public Scene(AbsCompoFactory _compoFactory, IntTileRender _tileRender) {
        super(_compoFactory);
        setFocusable(true);
        getPaintableLabel().setBackground(GuiConstants.WHITE);
        tileRender = _tileRender;
    }

    public void setDimensions(FacadeGame _facade) {
        sideLength = _facade.getMap().getSideLength();
        screenWidth = _facade.getMap().getScreenWidth();
        screenHeight = _facade.getMap().getScreenHeight();
    }

    public void setPreferredSize() {
        setPreferredSize(new MetaDimension(sideLength*screenWidth, sideLength*screenHeight));
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

    public void load(AbstractImageFactory _fact, FacadeGame _facade, boolean _setPreferredSize) {
//        facade = _facade;
        TreeMap<ScreenCoords,AbstractImage> background_ = new TreeMap<ScreenCoords, AbstractImage>(new ComparatorScreenCoords());
        TreeMap<ScreenCoords,CustList<AbstractImage>> foreground_ = new TreeMap<ScreenCoords,CustList<AbstractImage>>(new ComparatorScreenCoords());
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
        for (EntryCust<ScreenCoords, int[][]> sc_: _facade.getBackgroundImages().entryList()) {
            int[][] img_ = sc_.getValue();
//            AbstractImage buff_ = ConverterGraphicBufferedImage.decodeToImage(_fact,img_);
//            ConverterGraphicBufferedImage.transparentAllWhite(buff_);
//            background_.put(sc_, buff_);
            background_.put(sc_.getKey(), tileRender.render(_fact, img_, _facade.getMap().getSideLength(), _facade.getMap().getSideLength()));
        }
//        background_.putAllMap(oldLine_);
//        _sideLength_ = _facade.getMap().getSideLength();
        for (EntryCust<ScreenCoords, CustList<int[][]>> sc_: _facade.getForegroundImages().entryList()) {
            CustList<AbstractImage> imgs_ = new CustList<AbstractImage>();
            for (int[][] b: sc_.getValue()) {
//                if (b.length == 0) {
//                    continue;
//                }
//                BufferedImage buff_ = ConverterBufferedImage.centerImage(b, _sideLength_);
//                AbstractImage buff_ = ConverterGraphicBufferedImage.decodeToImage(_fact,b);
//                ConverterGraphicBufferedImage.transparentAllWhite(buff_);
//                imgs_.add(buff_);
                imgs_.add(tileRender.render(_fact, b, _facade.getMap().getSideLength(), _facade.getMap().getSideLength()));
            }
            foreground_.put(sc_.getKey(), imgs_);
        }
//        foreground_.putAllMap(oldForeLine_);
        background = background_;
        foreground = foreground_;
        herosScreen = _facade.getHerosScreen();
        herosImages = tileRender.render(_fact, _facade.getHerosImages(), _facade.getMap().getSideLength(), _facade.getMap().getSideLength());
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

//    public void complete(FacadeGame _facade) {
//        //CustList<ScreenCoords> keys_;
//        //keys_ = _facade.getBackgroundImages().getKeys();
//        for (ScreenCoords s: background.getKeys()) {
//            if (s.getXcoords() < 0 || s.getYcoords() < 0) {
//                background.removeKey(s);
//                _facade.getBackgroundImages().removeKey(s);
//            } else if (s.getXcoords() == screenWidth || s.getYcoords() == screenHeight) {
//                background.removeKey(s);
//                _facade.getBackgroundImages().removeKey(s);
//            }
//        }
//        //keys_ = _facade.getForegroundImages().getKeys();
//        for (ScreenCoords s: foreground.getKeys()) {
//            if (s.getXcoords() < 0 || s.getYcoords() < 0) {
//                foreground.removeKey(s);
//                _facade.getForegroundImages().removeKey(s);
//            } else if (s.getXcoords() == screenWidth || s.getYcoords() == screenHeight) {
//                foreground.removeKey(s);
//                _facade.getForegroundImages().removeKey(s);
//            }
//        }
//        repaintLabel();
//    }

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
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0, 0, sideLength*screenWidth - 1, sideLength*screenHeight - 1);
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
            AbstractImage buff_ = background.getVal(sc_);
            _g.drawImage(buff_, sideLength* sc_.getXcoords() + xDelta_, sideLength * sc_.getYcoords() + yDelta_);
        }
        for (ScreenCoords sc_: foreground.getKeys()) {
//            if (sc_.getXcoords() == xHeros + dx_) {
//                if (sc_.getYcoords() == yHeros + dy_) {
//                    CustList<AbstractImage> imgs_ = foreground.getVal(sc_);
//                    int size_ = imgs_.size();
//                    for (int i = IndexConstants.FIRST_INDEX; i < size_; i++) {
//                        AbstractImage buff_ = imgs_.get(i);
//                        if (i != size_ - 1) {
//                            int wMin_ = buff_.getWidth();
//                            int hMin_ = buff_.getHeight();
//                            _g.drawImage(buff_, sideLength* sc_.getXcoords() + xDelta_ + (sideLength - wMin_) / 2, sideLength * sc_.getYcoords() + yDelta_ + (sideLength - hMin_) / 2);
//                        }
//                    }
//                    continue;
//                }
//            }
            for (AbstractImage b:foreground.getVal(sc_)) {
                int wMin_ = b.getWidth();
                int hMin_ = b.getHeight();
                _g.drawImage(b, sideLength* sc_.getXcoords() + xDelta_ + (sideLength - wMin_) / 2, sideLength * sc_.getYcoords() + yDelta_ + (sideLength - hMin_) / 2);
            }
        }
        int wMin_ = herosImages.getWidth();
        int hMin_ = herosImages.getHeight();
        _g.drawImage(herosImages, sideLength* (herosScreen.getXcoords() - dx_) + (sideLength - wMin_) / 2, sideLength * (herosScreen.getYcoords() - dy_) + (sideLength - hMin_) / 2);
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        setFocus();
    }

    public void setFocus() {
        getPaintableLabel().requestFocusInWindow();
    }
}
