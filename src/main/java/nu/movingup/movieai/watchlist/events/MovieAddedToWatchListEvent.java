package nu.movingup.movieai.watchlist.events;

public record MovieAddedToWatchListEvent(String watchListId, String imdbId) {
}
