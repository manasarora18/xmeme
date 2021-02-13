package crio.stage.xmeme.controller;

import crio.stage.xmeme.dto.IdDTO;
import crio.stage.xmeme.dto.MemeDTO;
import crio.stage.xmeme.entity.Meme;
import crio.stage.xmeme.service.MemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
 * This class is for exposing the backend API calls to the Web client with the help of Mappings
 */

@RestController
@RequestMapping("/memes")
public class XmemeController {

    public final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemeService memeService;

    /*
    Method exposed via mappings to add new memes
     */
    @PostMapping(value = "/")
    public ResponseEntity<IdDTO> addMeme(@RequestBody MemeDTO memeDTO) {
        Meme meme = new Meme();
        BeanUtils.copyProperties(memeDTO, meme);
        Meme memeCreated = memeService.save(meme);
        IdDTO idDTO = new IdDTO();
        idDTO.setId(memeCreated.getId());
        LOG.debug("POST mapping request received for adding a new meme to DB");
        return new ResponseEntity<>(idDTO, HttpStatus.CREATED);
    }

    /*
    Method exposed via mappings to get all memes
    */
    @GetMapping(value = "/")
    public List<Meme> findAllMemes() {
        LOG.debug("GET mapping request received for getting all memes from DB");
        return memeService.findAllMemes();
    }

    /*
    Method exposed via mappings to get meme content by Id
    */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Meme>> findById(@PathVariable(value = "id") Integer id) {
        Optional<Meme> meme = memeService.findById(id);
        if (meme.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MemeDTO memeDTO = new MemeDTO();
        BeanUtils.copyProperties(meme, memeDTO);
        LOG.debug("GET mapping request received for getting a single meme content from DB with help of its id");
        return new ResponseEntity<>(meme, HttpStatus.FOUND);
    }

    /*
    Method exposed via mappings to update a meme already present in DB
    */
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Optional<Meme>> updateId(@PathVariable(value = "id") Integer id, @RequestBody MemeDTO memeDTO) {
        Optional<Meme> meme = memeService.findById(id);
        if (meme.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        BeanUtils.copyProperties(memeDTO, meme);
        memeService.patchMeme(meme);
        LOG.debug("PATCH mapping request received for updating a meme in DB");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
