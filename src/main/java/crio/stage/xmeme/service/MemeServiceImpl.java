package crio.stage.xmeme.service;

import crio.stage.xmeme.entity.Meme;
import crio.stage.xmeme.repository.MemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/*
 * This class is for implementation of MicroService Architecture performing all the major DB Operations
 * (saving, updating, getting data from Meme DB)
 */

@Service
public class MemeServiceImpl implements MemeService {
    @Autowired
    MemeRepo memeRepo;

    @Override
    public Meme save(Meme meme) {
        return memeRepo.save(meme);
    }

    @Override
    public List<Meme> findAllMemes() {
        return (List<Meme>) memeRepo.findAll();
    }

    @Override
    public void patchMeme(Optional<Meme> meme) {
        memeRepo.save(meme.get());
    }

    @Override
    public Optional<Meme> findById(Integer id) {
        return memeRepo.findById(id);
    }
}
