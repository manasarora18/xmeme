package crio.stage.xmeme.repository;

import crio.stage.xmeme.entity.Meme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemeRepo extends CrudRepository<Meme, Integer> {
}
