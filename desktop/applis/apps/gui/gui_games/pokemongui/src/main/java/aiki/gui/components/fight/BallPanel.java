package aiki.gui.components.fight;


import aiki.facade.FacadeGame;
import aiki.game.fight.BallNumberRate;
import aiki.gui.WindowAiki;
import aiki.gui.listeners.BallCatchingSelection;
import aiki.main.AikiFactory;
import aiki.main.PkNonModalEvent;
import code.gui.AbsPanel;
import code.gui.AbsPlainLabel;
import code.gui.ScrollCustomGraphicList;
import code.gui.files.MessagesGuiFct;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.Ints;
import code.util.NatStringTreeMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class BallPanel {

    private static final String SPACE = " ";

    private static final String SPACES = SPACE+SPACE;

    private static final String PERCENT = " %";

    private final AbsPlainLabel title;

    private final ScrollCustomGraphicList<BallNumberRate> listeBall;

    private final FacadeGame facade;

    private final BallRenderer renderer;

    private final AbsPanel container;
    private final AbsCompoFactory compoFactory;
    private final int maxVisible;

    public BallPanel(WindowAiki _window, int _nb, String _titre, FacadeGame _facade) {
        renderer = new BallRenderer(_window.getFrames().getImageFactory(),_facade,_window.getTileRender());
        listeBall = AikiFactory.ballPanel(_window.getCompoFactory(), _window.getImageFactory(),renderer, new PkNonModalEvent(_window.getModal()));
        facade = _facade;
        compoFactory = _window.getFrames().getCompoFactory();
        container = compoFactory.newBorder();
        container.setLoweredBorder();
        title = compoFactory.newPlainLabel(_titre);
        container.add(title, MessagesGuiFct.BORDER_LAYOUT_NORTH);
        listeBall.getElements().setFont(title.getMetaFont());
        maxVisible = _nb;
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
//        int s_ = _facade.getData().getMap().getSideLength();
//        listeBall.getScrollPane().setPreferredSize(new MetaDimension(100,s_*_nb));
        initBalls(-1, -1);
        container.add(listeBall.getScrollPane(), MessagesGuiFct.BORDER_LAYOUT_CENTER);
//        container.setPreferredSize(new MetaDimension(100,s_*_nb+16));
    }

    public void setPanelTitle(String _title) {
        title.setText(_title);
        title.setToolTipText(_title);
    }

    public void initBalls(int _creatureSauvage, int _creatureUt) {
        listeBall.clear();
        MetaFont metaFont_ = listeBall.getElements().getMetaFont();
        NatStringTreeMap<BallNumberRate> map_ = facade.calculateCatchingRatesSingle(_creatureSauvage,_creatureUt);
        int widthNb_ = number(metaFont_,map_,compoFactory);
        int widthRate_ = rate(metaFont_,map_,compoFactory);
        int maxPerc_ = compoFactory.stringWidth(metaFont_, StringUtil.concat("100", PERCENT));
        int s_ = facade.getData().getMap().getSideLength();
        int w_ = NumberUtil.max(100, s_+20+ widthNb_+widthRate_+maxPerc_);
        int h_ = NumberUtil.max(s_, metaFont_.getRealSize() + 2);
        listeBall.getScrollPane().setPreferredSize(new MetaDimension(w_,(h_ + 2)*maxVisible));
        renderer.setMaxWidth(widthNb_, widthRate_, maxPerc_);
        for (BallNumberRate b: map_.values()) {
            listeBall.add(b);
        }
        listeBall.computeDimensions();
    }
    public static int number(MetaFont _fm, NatStringTreeMap<BallNumberRate> _balls, AbsCompoFactory _compoFactory) {
        int widthNb_ = 0;
        for (BallNumberRate b: _balls.values()) {
            widthNb_ = NumberUtil.max(widthNb_, _compoFactory.stringWidth(_fm, b.getNumber().toNumberString()+SPACES));
        }
        return widthNb_;
    }
    public static int rate(MetaFont _fm, NatStringTreeMap<BallNumberRate> _balls, AbsCompoFactory _compoFactory) {
        int widthRt_ = 0;
        for (BallNumberRate b: _balls.values()) {
            MonteCarloNumber law_ = b.getLaw();
            int wEv_;
            if (law_.nbEvents() == 1) {
                Rate ev_ = law_.getEvent(0);
                wEv_ = _compoFactory.stringWidth(_fm,ev_.toNumberString()+SPACES);
            } else {
                wEv_ = 0;
            }
            int w_ = wEv_;
            widthRt_ = NumberUtil.max(widthRt_, w_);
        }
        return widthRt_;
    }

    public void setListener(Battle _battle) {
        listeBall.setListener(new BallCatchingSelection(_battle));
    }

    public ScrollCustomGraphicList<BallNumberRate> getListeBall() {
        return listeBall;
    }

    public BallNumberRate getSelectedBall() {
        Ints ind_ = listeBall.getSelectedIndexes();
        if (ind_.isEmpty()) {
            return null;
        }
        return listeBall.get(ind_.first());
    }

    public AbsPanel getContainer() {
        return container;
    }
}
