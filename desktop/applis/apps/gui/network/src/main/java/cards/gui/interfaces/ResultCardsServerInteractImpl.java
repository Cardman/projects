package cards.gui.interfaces;

import cards.facade.enumerations.GameEnum;
import cards.gui.dialogs.DialogServerCards;
import code.network.WindowNetWork;
import code.network.enums.IpType;

public final class ResultCardsServerInteractImpl implements ResultCardsServerInteract {

    private static final String LOCALHOST_OLD_IP = "127.0.0.1";

    private static final String LOCALHOST_NEW_IP = "::1";
    @Override
    public ResultCardsServer interact(WindowNetWork _app, GameEnum _jeuBouton) {
        DialogServerCards.setDialogServer(_app, _jeuBouton);
        String ip_ = DialogServerCards.getIpOrHostName(_app.getDialogServer());
        IpType ipType_ = DialogServerCards.getIpType(_app.getDialogServer());
        if (ip_ == null || ip_.isEmpty()) {
            if (ipType_ == IpType.IP_V6) {
                ip_ = LOCALHOST_NEW_IP;
            } else {
                ip_ = LOCALHOST_OLD_IP;
            }
        }
        if (!DialogServerCards.isChoosen(_app.getDialogServer())) {
            return null;
        }
        ResultCardsServer value_ = new ResultCardsServer();
        value_.setCreate(DialogServerCards.isCreate(_app.getDialogServer()));
        value_.setIp(ip_);
        value_.setIpType(ipType_);
        value_.setNbPlayers(DialogServerCards.getNbPlayers(_app.getDialogServer()));
        return value_;
    }
}
