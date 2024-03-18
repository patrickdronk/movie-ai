package nu.movingup.movieai.watchlist;

import nu.movingup.movieai.watchlist.commands.CreateWatchListCommand;
import nu.movingup.movieai.watchlist.events.WatchListCreatedEvent;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class WatchListAggregate {
    @AggregateIdentifier
    private UUID watchListId;

    public WatchListAggregate(CreateWatchListCommand command) {
        apply(new WatchListCreatedEvent(command.id()));
    }
}
