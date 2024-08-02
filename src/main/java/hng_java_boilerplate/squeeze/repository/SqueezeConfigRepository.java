package hng_java_boilerplate.squeeze.repository;

import hng_java_boilerplate.squeeze.entity.SqueezeConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SqueezeConfigRepository extends JpaRepository<SqueezeConfig, UUID>, JpaSpecificationExecutor<SqueezeConfig> {
}
