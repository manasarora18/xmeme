package crio.stage.xmeme.repository;

import crio.stage.xmeme.entity.Meme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

/*
 * This interface is for extending CrudRepository operations to perform all the major DB Operations
 * (saving, updating, fetching, deleting data from Database)
 */

@Repository
public interface MemeRepo extends CrudRepository<Meme, Integer>, QueryByExampleExecutor<Meme>, PagingAndSortingRepository<Meme, Integer> {
}
