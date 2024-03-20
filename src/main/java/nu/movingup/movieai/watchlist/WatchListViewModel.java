package nu.movingup.movieai.watchlist;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public record WatchListViewModel(
        @Id UUID watchListId,
        UUID userId
) {
}
