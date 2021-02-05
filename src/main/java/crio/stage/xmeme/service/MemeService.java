package crio.stage.xmeme.service;

import crio.stage.xmeme.entity.Meme;

import java.util.List;
import java.util.Optional;

public interface MemeService {
    Meme save(Meme meme);

    List<Meme> findAllMemes();

    Optional<Meme> findById(Integer id);
}
