package aiki.gui.threads;
import java.awt.image.BufferedImage;

import aiki.facade.FacadeGame;
import aiki.gui.components.fight.Battle;
import code.images.ConverterBufferedImage;

/**This class thread is independant from EDT,
Thread safe class*/
public abstract class FightIntroThread extends Thread {

    private FacadeGame facade;

    private Battle battle;

    private BufferedImage heros;
    private BufferedImage herosOppositeSex;

    /**This class thread is independant from EDT*/
    public FightIntroThread(FacadeGame _facade, Battle _battle) {
        facade = _facade;
        battle = _battle;
    }

    protected void initHeros() {
        heros = ConverterBufferedImage.decodeToImage(facade.getBackHeros());
        herosOppositeSex = ConverterBufferedImage.decodeToImage(facade.getBackHerosSexOpposite());
    }

    protected FacadeGame getFacade() {
        return facade;
    }

    protected Battle getBattle() {
        return battle;
    }

    protected BufferedImage getHeros() {
        return heros;
    }

    protected BufferedImage getHerosOppositeSex() {
        return herosOppositeSex;
    }
}
