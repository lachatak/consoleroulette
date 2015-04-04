package org.kaloz.roulette.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.kaloz.roulette.domain.core.Pocket.pocket;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_BET_RESULT_PLAYER1;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_PLAYER_BET_PLAYER1;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_PLAYER_POSITION_PLAYER1_WIN;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_ZERO_PLAYER_POSITION_PLAYER1;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kaloz.roulette.domain.core.BetResult;
import org.kaloz.roulette.domain.core.PlayerBet;
import org.kaloz.roulette.domain.core.PlayerPosition;

public class CroupierUTest {

    private Croupier testObj;

    @Before
    public void before() {
        testObj = new Croupier();
    }

    @Test
    public void testValidPlayerRegistration() {
        // act
        testObj.registerPlayer(VALID_ZERO_PLAYER_POSITION_PLAYER1);
        List<PlayerPosition> playerPosition = testObj.playerPositions();
        List<PlayerBet> playerBet = testObj.currentBets();

        // assert
        assertThat(playerPosition, hasSize(1));
        assertThat(playerPosition, contains(VALID_ZERO_PLAYER_POSITION_PLAYER1));
        assertThat(playerBet, empty());
    }

    @Test(expected = PlayerNotRegisteredException.class)
    public void testNotRegisteredPlayerBetShouldThrowException() {
        // act
        testObj.placeBet(VALID_PLAYER_BET_PLAYER1);
    }

    @Test
    public void testRegisteredPlayerShouldBeAbleToPlaceBet() {
        // setup
        testObj.registerPlayer(VALID_ZERO_PLAYER_POSITION_PLAYER1);

        // act
        testObj.placeBet(VALID_PLAYER_BET_PLAYER1);
        List<PlayerPosition> playerPosition = testObj.playerPositions();
        List<PlayerBet> playerBet = testObj.currentBets();

        // assert
        assertThat(playerPosition, contains(VALID_ZERO_PLAYER_POSITION_PLAYER1));
        assertThat(playerPosition, hasSize(1));
        assertThat(playerBet, hasSize(1));
        assertThat(playerBet, contains(VALID_PLAYER_BET_PLAYER1));
    }

    @Test
    public void testAnnounceWinningNumber() {
        // setup
        testObj.registerPlayer(VALID_ZERO_PLAYER_POSITION_PLAYER1);
        testObj.placeBet(VALID_PLAYER_BET_PLAYER1);

        // act
        List<BetResult> result = testObj.announceWinningPocket(pocket(1));
        List<PlayerPosition> playerPosition = testObj.playerPositions();
        List<PlayerBet> playerBet = testObj.currentBets();

        // assert
        assertThat(result, contains(VALID_BET_RESULT_PLAYER1));
        assertThat(result, hasSize(1));
        assertThat(playerPosition, contains(VALID_PLAYER_POSITION_PLAYER1_WIN));
        assertThat(playerPosition, hasSize(1));
        assertThat(playerBet, empty());
    }
}