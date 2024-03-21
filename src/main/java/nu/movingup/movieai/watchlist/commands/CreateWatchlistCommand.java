package nu.movingup.movieai.watchlist.commands;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record CreateWatchlistCommand(String id, String userId) implements CreateWatchlistCommandBuilder.With {}
