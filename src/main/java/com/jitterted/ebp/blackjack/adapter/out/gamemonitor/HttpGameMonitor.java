package com.jitterted.ebp.blackjack.adapter.out.gamemonitor;

import com.jitterted.ebp.blackjack.domain.Game;
import com.jitterted.ebp.blackjack.domain.port.GameMonitor;
import org.springframework.web.client.RestTemplate;

public class HttpGameMonitor implements GameMonitor {
    @Override
    public void roundCompleted(Game game) {
        GameResultDto gameResultDto = GameResultDto.from(game);
        try {
            post("https://blackjack-game-monitor.herokuapp.com/api/gameresults",
                 gameResultDto);
        } catch (Exception e) {
            // translate this TECHNICAL Exception
            // into a DOMAIN exception
            throw new RuntimeException(e);
        }
    }


    public void post(String uri, GameResultDto gameResultDto) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, gameResultDto, GameResultDto.class);
    }
}
