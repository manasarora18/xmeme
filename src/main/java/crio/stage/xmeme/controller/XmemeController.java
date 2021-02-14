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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * This class is for exposing the backend API calls to the Web client with the help of Mappings
 */

@RestController
@CrossOrigin("*")
public class XmemeController {

    public final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemeService memeService;

    /*
    Method exposed via mappings to add new memes
     */
    @PostMapping(value = "/memes")
    public ResponseEntity<IdDTO> addMeme(@RequestBody MemeDTO memeDTO) {
        Meme meme = new Meme();
        BeanUtils.copyProperties(memeDTO, meme);
        if (!memeService.isMemePresent(meme)) {
            LOG.debug("POST mapping request received for adding a new meme to DB");
            System.out.println("POST mapping request received for adding a new meme to DB");
            try {
                Meme memeCreated = memeService.save(meme);
                IdDTO idDTO = new IdDTO();
                idDTO.setId(memeCreated.getId().toString());
                return new ResponseEntity<>(idDTO, HttpStatus.CREATED);
            } catch (Exception e) {
                LOG.debug("POST Mapping could not proceed with DB");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /*
    Method exposed via mappings to get all memes
    */
    @GetMapping(value = "/memes")
    public ResponseEntity<List<MemeDTO>> findAllMemes() {
        try {
            LOG.debug("GET mapping request received for getting all memes from DB");
            System.out.println("GET mapping request received for getting all memes from DB");
            List<Meme> memeList = memeService.findAllMemes();
            ArrayList<MemeDTO> memeDTOList = new ArrayList<MemeDTO>();
            for (Meme meme : memeList) {
                MemeDTO memeDTO = new MemeDTO();
                memeDTO.setName(meme.getName());
                memeDTO.setId(meme.getId().toString());
                memeDTO.setCaption(meme.getCaption());
                memeDTO.setUrl(meme.getUrl());
                memeDTOList.add(memeDTO);
            }
            return new ResponseEntity<>(memeDTOList, HttpStatus.OK);
        } catch (Exception e) {
            LOG.debug("GET all Mapping could not proceed with DB");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*
    Method exposed via mappings to get meme content by Id
    */
    @GetMapping(value = "/{id}")
    public ResponseEntity<MemeDTO> findById(@PathVariable(value = "id") Integer id) {
        try {
            Optional<Meme> meme = memeService.findById(id);
            if (null == meme || meme.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            MemeDTO memeDTO = new MemeDTO();
            memeDTO.setId(meme.get().getId().toString());
            memeDTO.setUrl(meme.get().getUrl());
            memeDTO.setCaption(meme.get().getCaption());
            memeDTO.setName(meme.get().getName());
            System.out.println("GET mapping request received for getting a single meme content from DB with help of its id");
            LOG.debug("GET mapping request received for getting a single meme content from DB with help of its id");
            return new ResponseEntity<>(memeDTO, HttpStatus.OK);
        } catch (Exception e) {
            LOG.debug("GET by ID Mapping could not proceed with DB");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*
    Method exposed via mappings to update a meme already present in DB
    */
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Optional<Meme>> updateId(@PathVariable(value = "id") Integer id, @RequestBody MemeDTO memeDTO) {
        try {
            Optional<Meme> meme = memeService.findById(id);
            if (meme.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            meme.get().setCaption(memeDTO.getCaption());
            meme.get().setUrl(memeDTO.getUrl());
            memeService.patchMeme(meme);
            System.out.println("PATCH mapping request received for updating a meme in DB");
            LOG.debug("PATCH mapping request received for updating a meme in DB");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOG.debug("PATCH Mapping could not proceed with DB");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
