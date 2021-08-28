package aiki.gui.components.fight;

import java.awt.Dimension;

import aiki.facade.FacadeGame;
import aiki.game.fight.BallNumberRate;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.Ints;
import code.util.NatStringTreeMap;

public final class BallPanel {

    private final AbsPlainLabel title;

    private final AbsGraphicList<BallNumberRate> listeBall;

    private final FacadeGame facade;

    private final BallRenderer renderer;

    private final AbsPanel container;

    public BallPanel(AbstractProgramInfos _fact, int _nb, String _titre, FacadeGame _facade, AbsGraphicList<BallNumberRate> _liste) {
        listeBall = _liste;
        facade = _facade;
        container = _fact.getCompoFactory().newBorder();
        container.setLoweredBorder();
        title = _fact.getCompoFactory().newPlainLabel(_titre);
        container.add(title, GuiConstants.BORDER_LAYOUT_NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        listeBall.setVisibleRowCount(_nb);
        renderer = new BallRenderer(_fact.getImageFactory(),facade);
        listeBall.setRender(renderer);
        initBalls();
        container.add(listeBall.self(),GuiConstants.BORDER_LAYOUT_CENTER);
        container.setPreferredSize(new Dimension(100,32*_nb));
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
