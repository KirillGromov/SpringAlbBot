package io.project.SpringAlbBot.model;

import java.util.List;

public interface CostomizedBuild<Build> {

    List<Build> getBuilds(String item);
}
