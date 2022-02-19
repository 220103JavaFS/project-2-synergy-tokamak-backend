package com.app.satpoint.repos;

import com.app.satpoint.models.Satellite;
import com.app.satpoint.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SatelliteDAO  extends JpaRepository<Satellite, Long> {

    Optional<Satellite> findSatelliteByNoradId(int noradId);
    Optional<List<Satellite>> findSatellitesByFavedBy(User user);
    Optional<List<Satellite>> findTop20ByOrderByNumFavoritesDesc();
    Optional<Satellite> findSatelliteBySatName(String satName);
}
