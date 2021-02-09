package crio.stage.xmeme.controller;

import crio.stage.xmeme.dto.IdDTO;
import crio.stage.xmeme.dto.MemeDTO;
import crio.stage.xmeme.entity.Meme;
import crio.stage.xmeme.service.MemeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/memes")
public class XmemeController {
    @Autowired
    MemeService memeService;

    @PostMapping(value = "/")
    public ResponseEntity<IdDTO> addMeme(@RequestBody MemeDTO memeDTO) {
        Meme meme = new Meme();
        BeanUtils.copyProperties(memeDTO, meme);
        Meme memeCreated = memeService.save(meme);
        IdDTO idDTO = new IdDTO();
        idDTO.setId(memeCreated.getId());
        return new ResponseEntity<>(idDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/")
    public List<Meme> findAllMemes() {
        return memeService.findAllMemes();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Meme>> findById(@PathVariable(value = "id") Integer id) {
        Optional<Meme> meme = memeService.findById(id);
        if (meme.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MemeDTO memeDTO = new MemeDTO();
        BeanUtils.copyProperties(meme, memeDTO);
        return new ResponseEntity<>(meme, HttpStatus.FOUND);
    }
}
