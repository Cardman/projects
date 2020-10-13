package cards.gui.animations;

import code.bean.nat.BeanNatLgNames;

public final class PreparedPagesCards extends AbstractPreparedPagesCards {

    public PreparedPagesCards(String _conf,String _lg, BeanNatLgNames _stds) {
        super(_conf, _lg, _stds);
    }
    @Override
    public void run() {
        prepare();
    }
}
