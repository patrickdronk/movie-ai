package nu.movingup.movieai.watchlist.events;

import java.util.UUID;

public record WatchListCreatedEvent(UUID id, UUID userId) {
}
