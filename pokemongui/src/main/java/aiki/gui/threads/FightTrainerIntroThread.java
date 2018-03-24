package aiki.gui.threads;
import java.awt.image.BufferedImage;

import aiki.facade.FacadeGame;
import aiki.gui.components.fight.Battle;
import code.images.ConverterBufferedImage;
import code.util.consts.Constants;

/**This class thread is independant from EDT,
Thread safe class*/
public final class FightTrainerIntroThread extends FightIntroThread {

    private BufferedImage trainer;

    /**This class thread is independant from EDT*/
    public FightTrainerIntroThread(FacadeGame _facade, Battle _battle) {
        super(_facade, _battle);
    }

    @Override
    public void run() {
        initHeros();
        int[][] name_ = getFacade().getTrainerImage();
        trainer = ConverterBufferedImage.decodeToImage(name_);
        getBattle().setHerosOppositeSex(getHerosOppositeSex(), getFacade().isDualFight());
        getBattle().drawAnimationFightIni(getHeros(), trainer);
        while (getBattle().isKeepAnimation()) {
            Constants.sleep(5l);
            getBattle().drawAnimationFightIniInst();
        }
        getBattle().setComments();
        getBattle().display();
//        ThreadInvoker.invokeNow(new DoneFightIntroThread(getBattle()));
    }
}
