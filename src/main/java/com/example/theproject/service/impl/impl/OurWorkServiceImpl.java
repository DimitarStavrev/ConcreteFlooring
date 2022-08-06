package com.example.theproject.service.impl.impl;

import com.example.theproject.exceptions.ObjectNotFoundException;
import com.example.theproject.model.entity.OurWork;
import com.example.theproject.model.entity.User;
import com.example.theproject.model.service.OurWorkServiceModel;
import com.example.theproject.model.view.OurWorkDetailsView;
import com.example.theproject.model.view.OurWorkViewModel;
import com.example.theproject.repository.OurWorkRepository;
import com.example.theproject.repository.UserRepository;
import com.example.theproject.service.impl.CategoryService;
import com.example.theproject.service.impl.OurWorkService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OurWorkServiceImpl implements OurWorkService {

    private final OurWorkRepository ourWorkRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final UserRepository userRepository;

    public OurWorkServiceImpl(OurWorkRepository ourWorkRepository, ModelMapper modelMapper, CategoryService categoryService, UserRepository userRepository) {
        this.ourWorkRepository = ourWorkRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
    }

    @Override
    public List<OurWorkViewModel> findAllOurWorks() {
        return ourWorkRepository
                .findAll()
                .stream()
                .map(ourWork -> {
                    OurWorkViewModel ourWorkViewModel = modelMapper.map(ourWork, OurWorkViewModel.class);
                    if (ourWork.getPictures().isEmpty()) {
                        ourWorkViewModel.setPictureUrl("/images/no-image.jpg");
                    } else {
                        ourWorkViewModel.setPictureUrl(ourWork.getPictures().stream().findFirst().get().getUrl());
                    }
                    return ourWorkViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public void addOurWork(OurWorkServiceModel ourWorkServiceModel, UserDetails userDetails) {
        OurWork ourWork = modelMapper.map(ourWorkServiceModel, OurWork.class);
        ourWork.setCategory(categoryService.findCategoryByName(ourWorkServiceModel.getCategory()));

        User author = userRepository
                .findByUsername(userDetails.getUsername())
                .orElseThrow();

        ourWork.setUser(author);

        ourWorkRepository.save(ourWork);
    }

    @Override
    public OurWorkDetailsView findById(Long id) {
        return ourWorkRepository
                .findById(id)
                .map(ourWork -> modelMapper.map(ourWork, OurWorkDetailsView.class))
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }
}
