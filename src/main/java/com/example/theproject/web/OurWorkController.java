package com.example.theproject.web;

import com.example.theproject.model.binding.OurWorksAddBindingModel;
import com.example.theproject.model.service.OurWorkServiceModel;
import com.example.theproject.model.user.ProjetUserDetails;
import com.example.theproject.service.impl.OurWorkService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/our-works")
public class OurWorkController {

    private final OurWorkService ourWorkService;
    private final ModelMapper modelMapper;

    public OurWorkController(OurWorkService ourWorkService, ModelMapper modelMapper) {
        this.ourWorkService = ourWorkService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public OurWorksAddBindingModel ourWorksAddBindingModel(){
        return new OurWorksAddBindingModel();
    }

    @GetMapping("")
    public String ourWorks(Model model){
        model.addAttribute("ourWorks", ourWorkService.findAllOurWorks());
        return "our-works";
    }

    @GetMapping("/add")
    public String add(){
        return "our-works-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid OurWorksAddBindingModel ourWorksAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal ProjetUserDetails projetUserDetails){
        if (bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("ourWorksAddBindingModel", ourWorksAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.ourWorksAddBindingModel",
                            bindingResult);
            return "redirect:/our-works/add";
        }

        OurWorkServiceModel ourWorkServiceModel = modelMapper.map(ourWorksAddBindingModel, OurWorkServiceModel.class);

        ourWorkService.addOurWork(ourWorkServiceModel,projetUserDetails);

        return "redirect:/our-works";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model){

        model.addAttribute("ourWork", ourWorkService.findById(id));
        return "our-works-details";
    }
}
