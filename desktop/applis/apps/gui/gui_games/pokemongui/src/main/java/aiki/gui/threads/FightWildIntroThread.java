package aiki.gui.threads;
import java.awt.image.BufferedImage;

import aiki.facade.FacadeGame;
import aiki.gui.components.fight.Battle;
import code.threads.ThreadUtil;
import code.gui.images.ConverterGraphicBufferedImage;

/**This class thread is independant from EDT,
Thread safe class*/
public final class FightWildIntroThread extends FightIntroThread {

    private BufferedImage pokemon;

    /**This class thread is independant from EDT*/
    public FightWildIntroThread(FacadeGame _facade, Battle _battle) {
        super(_facade, _battle);
    }

    @Override
    public void run() {
        initHeros();
        String name_ = getFacade().getFight().wildPokemon().getName();
        pokemon = ConverterGraphicBufferedImage.decodeToImage(getFacade().getData().getMaxiPkFront().getVal(name_));
        getBattle().setHerosOppositeSex(getHerosOppositeSex(), false);
        getBattle().drawAnimationFightIni(getHeros(), pokemon);
        while (getBattle().isKeepAnimation()) {
            ThreadUtil.sleep(5l);
            getBattle().drawAnimationFightIniInst();
        }
        getBattle().setComments();
        getBattle().display();
//        ThreadInvoker.invokeNow(new DoneFightIntroThread(getBattle()));
    }
}
