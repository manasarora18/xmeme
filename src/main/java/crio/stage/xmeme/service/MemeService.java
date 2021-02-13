package crio.stage.xmeme.service;

import crio.stage.xmeme.entity.Meme;

import java.util.List;
import java.util.Optional;


/*
 * This interface is for listing all the major DB Operations
 * (saving, updating, getting data from Meme DB)
 */
public interface MemeService {
    Meme save(Meme meme);

    List<Meme> findAllMemes();

    void patchMeme(Optional<Meme> meme);

    Optional<Meme> findById(Integer id);

    boolean isMemePresent(Meme meme);
}
