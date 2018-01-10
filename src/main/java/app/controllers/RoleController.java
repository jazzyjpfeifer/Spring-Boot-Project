package app.controllers;

import app.entity.Role;
import app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "roles")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @GetMapping()
    public String showRoles(Model model) {

        model.addAttribute("roles", roleRepository.findAll());

        return "roles/roles";

    }

    @GetMapping(value = "/add")
    public String addRole(Model model) {

        Role newRole = new Role();

        model.addAttribute("role", newRole);

        return "roles/roles_add";
    }

    @PostMapping(value = "/save")
    public String saveRole(@ModelAttribute("role") Role role) {

        System.out.println("Saving new record...");

        System.out.println("Here is the role" + role);

        roleRepository.save(role);

        System.out.println("Post was saved to the database successfully!");

        return "redirect:/roles";
    }

    @GetMapping("/edit")
    public String editRoleById(@RequestParam("roleId") int id, Model model) {

        model.addAttribute("role", roleRepository.findOne(id));

        return "roles/roles_add";
    }


    @GetMapping(value = "/delete")
    public String deleteRoleById(@RequestParam("roleId") int id) {

        System.out.println("Deleting record...");

        roleRepository.delete(id);

        System.out.println("Record was removed successfully form the database");

        return "redirect:/roles";
    }




}
