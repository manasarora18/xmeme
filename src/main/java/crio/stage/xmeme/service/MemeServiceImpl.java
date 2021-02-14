package crio.stage.xmeme.service;

import crio.stage.xmeme.entity.Meme;
import crio.stage.xmeme.repository.MemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
        Page<Meme> memePage = memeRepo.findAll(PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "id")));
        return memePage.getContent();
    }

    @Override
    public void patchMeme(Optional<Meme> meme) {
        memeRepo.save(meme.get());
    }

    @Override
    public Optional<Meme> findById(Integer id) {
        return memeRepo.findById(id);
    }

    @Override
    public boolean isMemePresent(Meme meme) {
        Meme checkMeme = new Meme();
        checkMeme.setName(meme.getName());
        checkMeme.setUrl(meme.getUrl());
        checkMeme.setCaption(meme.getCaption());
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");
        Example<Meme> example = Example.of(checkMeme, exampleMatcher);
        Iterable<Meme> memeList = memeRepo.findAll(example);
        List<Meme> memes = (List<Meme>) memeList;
        return !memes.isEmpty();
    }
}
