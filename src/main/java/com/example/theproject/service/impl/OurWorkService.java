package com.example.theproject.service.impl;

import com.example.theproject.model.service.OurWorkServiceModel;
import com.example.theproject.model.view.OurWorkDetailsView;
import com.example.theproject.model.view.OurWorkViewModel;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface OurWorkService {
    List<OurWorkViewModel> findAllOurWorks();

    void addOurWork(OurWorkServiceModel ourWorkServiceModel, UserDetails userDetails);

    OurWorkDetailsView findById(Long id);
}
