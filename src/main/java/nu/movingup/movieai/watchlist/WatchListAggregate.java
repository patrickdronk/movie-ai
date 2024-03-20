package nu.movingup.movieai.watchlist;

import nu.movingup.movieai.watchlist.commands.CreateWatchlistCommand;
import nu.movingup.movieai.watchlist.events.WatchListCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class WatchListAggregate {

    @AggregateIdentifier
    private UUID watchListId;

    public WatchListAggregate() {
    }

    @CommandHandler
    public WatchListAggregate(CreateWatchlistCommand command) {
        apply(new WatchListCreatedEvent(command.id(), command.userId()));
    }

    @EventSourcingHandler
    public void on(WatchListCreatedEvent event) {
        this.watchListId = event.id();
    }
}
