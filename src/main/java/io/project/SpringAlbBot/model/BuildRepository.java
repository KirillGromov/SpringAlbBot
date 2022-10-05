package io.project.SpringAlbBot.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BuildRepository extends CrudRepository<Build, String>, CostomizedBuild<Build> {

}
