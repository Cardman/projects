package aiki.gui.components.fight;



import aiki.facade.FacadeGame;
import aiki.game.fight.BallNumberRate;
import aiki.gui.WindowAiki;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.util.Ints;
import code.util.NatStringTreeMap;

public final class BallPanel {

    private final AbsPlainLabel title;

    private final AbsGraphicList<BallNumberRate> listeBall;

    private final FacadeGame facade;

    private final BallRenderer renderer;

    private final AbsPanel container;

    public BallPanel(WindowAiki _window, int _nb, String _titre, FacadeGame _facade) {
        renderer = new BallRenderer(_window.getFrames().getImageFactory(),_facade);
        listeBall = _window.getAikiFactory().getGeneBallNumberRate().createSimple(_window.getImageFactory(),renderer);
        facade = _facade;
        container = _window.getFrames().getCompoFactory().newBorder();
        container.setLoweredBorder();
        title = _window.getFrames().getCompoFactory().newPlainLabel(_titre);
        container.add(title, GuiConstants.BORDER_LAYOUT_NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        listeBall.setVisibleRowCount(_nb);
        initBalls();
        container.add(listeBall.self(),GuiConstants.BORDER_LAYOUT_CENTER);
        container.setPreferredSize(new MetaDimension(100,32*_nb));
    }

    public void setPanelTitle(String _title) {
        title.setText(_title);
    }

    public void initBalls() {
        listeBall.clear();
        NatStringTreeMap<BallNumberRate> map_ = facade.calculateCatchingRates();
        renderer.setMaxWidth(title,map_);
        for (BallNumberRate b: map_.values()) {
            listeBall.add(b);
        }
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
