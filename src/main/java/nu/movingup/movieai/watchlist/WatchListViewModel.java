package nu.movingup.movieai.watchlist;

import io.soabase.recordbuilder.core.RecordBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
@RecordBuilder
public record WatchListViewModel(
        String watchListId,
        String userId,
        Set<String> movieIds
) implements WatchListViewModelBuilder.With {
}
