package aiki.sml;

import aiki.db.*;
import aiki.fight.abilities.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.pokemon.*;
import aiki.fight.status.*;
import aiki.sml.init.*;
import code.util.*;

public final class LoadResInit {
    private LoadResInit(){}
    public static void loadResources(DataBase _d) {
        _d.setCombos(CoInit.co());
        _d.completeMembersCombos();
        _d.setMap(Dm.map());
        for (EntryCust<String, PokemonData> e: PkInit.pk().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
        _d.calculateAvgPound();
        for (EntryCust<String, MoveData> e: MvInit.mv().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
        patch(_d);
        for (EntryCust<String, Item> e: ItInit.it().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
        for (EntryCust<String, AbilityData> e: AbInit.ab().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
        for (EntryCust<String, Status> e: StInit.st().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
        _d.completeVariables();
        _d.sortEndRound();
//        _perCentLoading.addPercent(delta_);
        _d.completeMoveTutors();
        _d.getMap().initializeLinks();
        _d.getMap().initInteractiveElements();
        _d.getMap().initializeTree();
        _d.getMap().initializeAccessibility();
        _d.initializeWildPokemon();
        _d.getFamilies().addAllEntries(PkInit.fs());
    }

    private static void patch(DataBase _d) {
        for (EntryCust<String,MoveData> e: _d.getMoves().entryList()) {
            if (e.getValue() instanceof DamagingMoveData) {
                for (Effect f: e.getValue().getEffects()) {
                    if (f instanceof EffectDamage) {
                        EffectDamage dam_ = (EffectDamage) f;
                        dam_.patch();
                    }
                }
            }
        }
    }
}
