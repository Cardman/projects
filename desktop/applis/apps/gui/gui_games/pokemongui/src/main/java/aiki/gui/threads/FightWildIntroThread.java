package aiki.gui.threads;

import aiki.facade.FacadeGame;
import aiki.gui.components.fight.Battle;
import code.gui.images.AbstractImage;
import code.threads.ThreadUtil;
import code.gui.images.ConverterGraphicBufferedImage;

/**This class thread is independant from EDT,
Thread safe class*/
public final class FightWildIntroThread extends FightIntroThread {

    private AbstractImage pokemon;

    /**This class thread is independant from EDT*/
    public FightWildIntroThread(FacadeGame _facade, Battle _battle) {
        super(_facade, _battle);
    }

    @Override
    public void run() {
        initHeros();
        String name_ = getFacade().getFight().wildPokemon().getName();
        pokemon = ConverterGraphicBufferedImage.decodeToImage(getBattle().getWindow().getImageFactory(), getFacade().getData().getMaxiPkFront().getVal(name_));
        getBattle().setHerosOppositeSex(getHerosOppositeSex(), false);
        getBattle().drawAnimationFightIni(getHeros(), pokemon);
        while (getBattle().isKeepAnimation()) {
            ThreadUtil.sleep(getBattle().getWindow().getThreadFactory(),5l);
            getBattle().drawAnimationFightIniInst();
        }
        getBattle().setComments();
        getBattle().display();
        getBattle().getWindow().reenableBasicFight();
//        ThreadInvoker.invokeNow(new DoneFightIntroThread(getBattle()));
    }
}
