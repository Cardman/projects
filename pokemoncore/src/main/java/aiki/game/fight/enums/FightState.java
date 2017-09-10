package aiki.game.fight.enums;
import code.serialize.CheckedData;


@CheckedData
public enum FightState {
    SWITCH_PROPOSE, SWITCH_WHILE_KO_USER, ATTAQUES, APPRENDRE_EVOLUER, SWITCH_APRES_ATTAQUE, CAPTURE_KO, FIN_CBT_SAUVAGE,
    FIN_CBT_DRESSEUR, REDESSIN_SCENE, SURNOM, RIEN;
}
