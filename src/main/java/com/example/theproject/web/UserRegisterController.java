package com.example.theproject.web;

import com.example.theproject.model.binding.UserRegisterBindingModel;
import com.example.theproject.model.service.UserServiceModel;
import com.example.theproject.service.impl.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserRegisterController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegisterController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        boolean equalsPasswords = userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword());

        if (bindingResult.hasErrors() || !equalsPasswords){
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                            bindingResult);

            return "redirect:/users/register";
        }


        UserServiceModel usernameExists = userService.isUsernameExists(userRegisterBindingModel.getUsername());
        UserServiceModel emailExists = userService.isEmailExists(userRegisterBindingModel.getEmail());

        if (usernameExists != null || emailExists != null){
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                            bindingResult);
            return "redirect:/users/register";
        }


        userService.registerUser(
                modelMapper.map(
                        userRegisterBindingModel, UserServiceModel.class));

        return "redirect:/";
    }
}
