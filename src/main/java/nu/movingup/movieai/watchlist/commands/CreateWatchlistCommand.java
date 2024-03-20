package nu.movingup.movieai.watchlist.commands;

import java.util.UUID;

public record CreateWatchlistCommand(UUID id, UUID userId) {}
