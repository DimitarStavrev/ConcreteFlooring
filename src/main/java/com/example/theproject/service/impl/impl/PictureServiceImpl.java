package com.example.theproject.service.impl.impl;

import com.example.theproject.model.entity.Picture;
import com.example.theproject.repository.PictureRepository;
import com.example.theproject.service.impl.PictureService;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }


    @Override
    public Picture findByTitle(String title) {
        return pictureRepository.findByTitle(title)
                .orElse(null);
    }
}
