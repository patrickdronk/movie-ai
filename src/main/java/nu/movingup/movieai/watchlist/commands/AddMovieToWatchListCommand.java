package nu.movingup.movieai.watchlist.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record AddMovieToWatchListCommand(@TargetAggregateIdentifier String watchListId, String imdbId) {
}
