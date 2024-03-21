package nu.movingup.movieai.watchlist;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WatchListRepository extends MongoRepository<WatchListViewModel, String> {
    Optional<WatchListViewModel> findByUserId(String userId);
}
