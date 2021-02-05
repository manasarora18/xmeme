package crio.stage.xmeme.service;

import crio.stage.xmeme.entity.Meme;
import crio.stage.xmeme.repository.MemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Meme> findById(Integer id) {
        return memeRepo.findById(id);
    }
}
