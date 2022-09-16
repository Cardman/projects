package aiki.game.fight;

import aiki.fight.items.ItemForBattle;

final class UsedItemForBattle {
    private final ItemForBattle itemForBattle;
    private final int indiceTirage;
    private final TeamPosition tp;

    UsedItemForBattle(ItemForBattle _it, int _in, TeamPosition _t) {
        this.itemForBattle = _it;
        this.indiceTirage = _in;
        this.tp = _t;
    }

    int getIndiceTirage() {
        return indiceTirage;
    }

    ItemForBattle getItemForBattle() {
        return itemForBattle;
    }

    TeamPosition getTp() {
        return tp;
    }
}
