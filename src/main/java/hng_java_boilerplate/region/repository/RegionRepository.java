package hng_java_boilerplate.region.repository;

import hng_java_boilerplate.region.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, String> {
    Optional<Region> findByUserId(String userId);
}
