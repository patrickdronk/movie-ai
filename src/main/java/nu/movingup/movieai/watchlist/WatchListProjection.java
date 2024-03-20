package nu.movingup.movieai.watchlist;

import nu.movingup.movieai.watchlist.events.WatchListCreatedEvent;
import nu.movingup.movieai.watchlist.queries.GetWatchListByUserId;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@ProcessingGroup("WatchListProjection")
public class WatchListProjection {
    private final WatchListRepository watchListRepository;

    public WatchListProjection(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }

    @EventHandler
    public void on(WatchListCreatedEvent event) {
        var watchListViewModel = new WatchListViewModel(UUID.randomUUID(), event.userId());
        watchListRepository.save(watchListViewModel);
    }

    @QueryHandler
    public Optional<WatchListViewModel> on(GetWatchListByUserId query) {
        return watchListRepository.findByUserId(query.userId());
    }
}
