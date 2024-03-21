package nu.movingup.movieai.watchlist;

import nu.movingup.movieai.configuration.userIdConfig.UserId;
import nu.movingup.movieai.watchlist.commands.AddMovieToWatchListCommand;
import nu.movingup.movieai.watchlist.commands.CreateWatchlistCommand;
import nu.movingup.movieai.watchlist.queries.GetWatchListByUserId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.axonframework.messaging.responsetypes.ResponseTypes.optionalInstanceOf;

@RestController
public class WatchListController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public WatchListController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @GetMapping("/watchlist")
    public CompletableFuture<Optional<WatchListViewModel>> getWatchListByUserId(@UserId String userId) {
        return queryGateway.query(new GetWatchListByUserId(userId), optionalInstanceOf(WatchListViewModel.class));
    }

    @PostMapping("/watchlist")
    public void handle(@RequestBody CreateWatchlistCommand command, @UserId String userId) {
        command = command.withUserId(userId);
        commandGateway.send(command);
    }

    @PostMapping("/watchlist/add-movie-to-watchlist")
    public void handle(@RequestBody AddMovieToWatchListCommand command) {
        commandGateway.send(command);
    }
}