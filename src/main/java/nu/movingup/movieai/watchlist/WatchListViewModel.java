package nu.movingup.movieai.watchlist;

import io.soabase.recordbuilder.core.RecordBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@RecordBuilder
public record WatchListViewModel(
        String watchListId,
        String userId
) {
}
