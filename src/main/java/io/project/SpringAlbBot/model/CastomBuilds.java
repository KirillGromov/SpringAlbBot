package io.project.SpringAlbBot.model;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CastomBuilds implements CostomizedBuild{

    @PersistenceContext
    private EntityManager em;


    public List getBuilds(String item){

        return em.createQuery("from Employees where classBuild = " + item, Build.class)
                .getResultList();
    }
}
