package com.management.student.studentresult.controller;

import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleActionController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/addRole")
    public Role addRole(@RequestBody Role role){

        Role newrole = new Role(role.getName(), role.getCreatedBy(), role.getModifiedBy());
        return roleService.saveRole(newrole);
    }

    @GetMapping("/allRoles")
    public @ResponseBody ResponseEntity<?> allRoles(){
        List<String> roleNamesList= new ArrayList<>();
        try {
            List<Role> rolesList=roleService.getRoles();
            for(int i=0;i<rolesList.size();i++)
                roleNamesList.add(rolesList.get(i).getName());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<String>("Error in fetching Roles!", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<List<String>>(roleNamesList,HttpStatus.OK);
    }

}
