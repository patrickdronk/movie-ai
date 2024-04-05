package nu.movingup.movieai.watchlist;

import nu.movingup.movieai.watchlist.events.MovieAddedToWatchListEvent;
import nu.movingup.movieai.watchlist.events.WatchListCreatedEvent;
import nu.movingup.movieai.watchlist.queries.GetBalanceQuery;
import nu.movingup.movieai.watchlist.queries.GetWatchListByUserId;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

import static java.util.Collections.*;

@Component
@ProcessingGroup("WatchListProjection")
public class WatchListProjection {
    private final WatchListRepository watchListRepository;

    public WatchListProjection(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }

    @EventHandler
    public void on(WatchListCreatedEvent event) {
        var watchListViewModel = new WatchListViewModel(event.id(), event.userId(), emptySet());
        watchListRepository.save(watchListViewModel);
    }

    @EventHandler
    public void on(MovieAddedToWatchListEvent event) {
        var watchList = watchListRepository.findById(event.watchListId());
        watchList.ifPresent(wl -> {
            Set<String> movieIds = new HashSet<>(wl.movieIds());
            movieIds.add(event.imdbId());
            wl = wl.withMovieIds(movieIds);
            watchListRepository.save(wl);
        });
    }

    @QueryHandler
    public Optional<WatchListViewModel> on(GetWatchListByUserId query) {
        return watchListRepository.findByUserId(query.userId());
    }

}