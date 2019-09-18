package controller;

import org.springframework.web.bind.annotation.*;

public class UserController {

    /**
     * Возвращает список организаций
     * по параметрам name, inn, isAcitve
     *
     * @return
     */

 /*   @PostMapping(value = "/list", consumes = "application/json", produces = "application/json")

    public @ResponseBody
    List<OrgsListOutView> getOrgsByParams(@RequestBody OrgsListInView view) {
        List<OrgsListOutView> orgs = organizationService.orgsFilter(view);
        return orgs;
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody
    OrganizationView getOrgById(@PathVariable Long id) {
        OrganizationView org = organizationService.getOrgById(id);
        return org;
    }

  */
}
