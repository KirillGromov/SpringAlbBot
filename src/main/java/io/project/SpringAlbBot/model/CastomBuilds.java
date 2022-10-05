package io.project.SpringAlbBot.model;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CastomBuilds implements CostomizedBuild{

    @PersistenceContext
    private EntityManager em;


    public List getBuilds(){

        return em.createQuery("from Employees where salary = (select max(salary) from Employees )", Build.class)
                .getResultList();
    }
}
