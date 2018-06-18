package tkg.aiwolf.talk;

import java.util.List;

import org.aiwolf.client.lib.ContentBuilder;
import org.aiwolf.client.lib.DivinedResultContentBuilder;
import org.aiwolf.common.data.Species;

import jp.ne.sakura.vopaldragon.aiwolf.framework.EventType;
import jp.ne.sakura.vopaldragon.aiwolf.framework.Game;
import jp.ne.sakura.vopaldragon.aiwolf.framework.GameAgent;
import jp.ne.sakura.vopaldragon.aiwolf.framework.GameEvent;
import jp.ne.sakura.vopaldragon.aiwolf.util.Utils;
import tkg.aiwolf.model.TFAFGameModel;

/**
 * COしてたら占い結果を言う
 */
public class TalkDivineWithEvilScore5ver extends TFAFTalkTactic {

    @Override
    public ContentBuilder talkImpl(int turn, int skip, int utter, TFAFGameModel model, Game game) {
        if (divineTarget != null && game.getSelf().hasCO()) {
            GameAgent target = null;
            if (game.getDay() == 1) {
                if (result == Species.HUMAN) {
                    List<GameAgent> alives = game.getAliveOthers();
                    if (divineTarget != null) {
                        alives.remove(divineTarget);
                    }
                    double[] evilScore = model.getEvilScore();
                    Utils.sortByScore(alives, evilScore, false);
                    target = alives.get(0);
                    return new DivinedResultContentBuilder(target.agent, Species.WEREWOLF);
                } else {
                    return new DivinedResultContentBuilder(divineTarget.agent, result);
                }
            }
            if (game.getDay() == 2) {
                if (divineTarget != null && result == Species.WEREWOLF) {
                    target = divineTarget;
                } else {
                    List<GameAgent> others = game.getAliveOthers();
                    if (divineTarget != null) {
                        others.remove(divineTarget);
                    }
                    double[] evilScore = model.getEvilScore();
                    Utils.sortByScore(others, evilScore, false);
                    target = others.get(0);
                }
                return new DivinedResultContentBuilder(target.agent, Species.WEREWOLF);
            }
        }
        return null;
    }

    private GameAgent divineTarget;
    private Species result;

    @Override
    public void handleEvent(Game g, GameEvent e) {
        if (e.type == EventType.DIVINE) {
            divineTarget = e.target;
            result = e.species;
        }
    }

}
