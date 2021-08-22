package aiki.gui.threads;

import aiki.facade.FacadeGame;
import aiki.gui.components.fight.Battle;
import code.gui.images.AbstractImage;
import code.threads.ThreadUtil;
import code.gui.images.ConverterGraphicBufferedImage;

/**This class thread is independant from EDT,
Thread safe class*/
public final class FightTrainerIntroThread extends FightIntroThread {

    private AbstractImage trainer;

    /**This class thread is independant from EDT*/
    public FightTrainerIntroThread(FacadeGame _facade, Battle _battle) {
        super(_facade, _battle);
    }

    @Override
    public void run() {
        initHeros();
        int[][] name_ = getFacade().getTrainerImage();
        trainer = ConverterGraphicBufferedImage.decodeToImage(getBattle().getWindow().getImageFactory(), name_);
        getBattle().setHerosOppositeSex(getHerosOppositeSex(), getFacade().isDualFight());
        getBattle().drawAnimationFightIni(getHeros(), trainer);
        while (getBattle().isKeepAnimation()) {
            ThreadUtil.sleep(getBattle().getWindow().getThreadFactory(),5l);
            getBattle().drawAnimationFightIniInst();
        }
        getBattle().setComments();
        getBattle().display();
//        ThreadInvoker.invokeNow(new DoneFightIntroThread(getBattle()));
    }
}
