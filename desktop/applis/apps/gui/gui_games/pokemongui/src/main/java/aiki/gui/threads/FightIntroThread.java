package aiki.gui.threads;

import aiki.facade.FacadeGame;
import aiki.gui.components.fight.Battle;
import code.gui.images.AbstractImage;
import code.gui.images.ConverterGraphicBufferedImage;

/**This class thread is independant from EDT,
Thread safe class*/
public abstract class FightIntroThread implements Runnable {

    private FacadeGame facade;

    private Battle battle;

    private AbstractImage heros;
    private AbstractImage herosOppositeSex;

    /**This class thread is independant from EDT*/
    public FightIntroThread(FacadeGame _facade, Battle _battle) {
        facade = _facade;
        battle = _battle;
    }

    protected void initHeros() {
        heros = ConverterGraphicBufferedImage.decodeToImage(battle.getWindow().getImageFactory(), facade.getBackHeros());
        herosOppositeSex = ConverterGraphicBufferedImage.decodeToImage(battle.getWindow().getImageFactory(), facade.getBackHerosSexOpposite());
    }

    protected FacadeGame getFacade() {
        return facade;
    }

    protected Battle getBattle() {
        return battle;
    }

    protected AbstractImage getHeros() {
        return heros;
    }

    protected AbstractImage getHerosOppositeSex() {
        return herosOppositeSex;
    }
}
