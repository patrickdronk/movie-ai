package nu.movingup.movieai.watchlist;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WatchListRepository extends MongoRepository<WatchListViewModel, UUID> {
    Optional<WatchListViewModel> findByUserId(UUID userId);
}
