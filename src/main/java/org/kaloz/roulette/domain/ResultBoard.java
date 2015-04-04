package org.kaloz.roulette.domain;

import java.util.List;

import org.kaloz.roulette.domain.core.BetResult;
import org.kaloz.roulette.domain.core.PlayerPosition;
import org.kaloz.roulette.domain.core.Pocket;

public interface ResultBoard {

    void updateBetResults(final Pocket pocket, final List<BetResult> betResults);

    void updatePlayerPositions(final List<PlayerPosition> playerPositions);

}
