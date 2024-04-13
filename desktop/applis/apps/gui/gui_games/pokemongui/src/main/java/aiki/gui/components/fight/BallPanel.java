package aiki.gui.components.fight;



import aiki.facade.FacadeGame;
import aiki.game.fight.BallNumberRate;
import aiki.gui.WindowAiki;
import aiki.gui.listeners.BallCatchingSelection;
import aiki.main.AikiFactory;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.*;
import code.util.Ints;
import code.util.NatStringTreeMap;

public final class BallPanel {

    private final AbsPlainLabel title;

    private final ScrollCustomGraphicList<BallNumberRate> listeBall;

    private final FacadeGame facade;

    private final BallRenderer renderer;

    private final AbsPanel container;
    private final AbsCompoFactory compoFactory;

    public BallPanel(WindowAiki _window, int _nb, String _titre, FacadeGame _facade) {
        renderer = new BallRenderer(_window.getFrames().getImageFactory(),_facade);
        listeBall = AikiFactory.ballPanel(_window.getCompoFactory(), _window.getImageFactory(),renderer);
        facade = _facade;
        compoFactory = _window.getFrames().getCompoFactory();
        container = compoFactory.newBorder();
        container.setLoweredBorder();
        title = compoFactory.newPlainLabel(_titre);
        container.add(title, GuiConstants.BORDER_LAYOUT_NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        int s_ = _facade.getData().getMap().getSideLength();
        listeBall.getScrollPane().setPreferredSize(new MetaDimension(100,s_*_nb));
        initBalls(-1, -1);
        container.add(listeBall.getScrollPane(),GuiConstants.BORDER_LAYOUT_CENTER);
        container.setPreferredSize(new MetaDimension(100,s_*_nb+16));
    }

    public void setPanelTitle(String _title) {
        title.setText(_title);
    }

    public void initBalls(int _creatureSauvage, int _creatureUt) {
        listeBall.clear();
        NatStringTreeMap<BallNumberRate> map_ = facade.calculateCatchingRatesSingle((byte) _creatureSauvage,(byte) _creatureUt);
        renderer.setMaxWidth(title,map_,compoFactory);
        for (BallNumberRate b: map_.values()) {
            listeBall.add(b);
        }
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
