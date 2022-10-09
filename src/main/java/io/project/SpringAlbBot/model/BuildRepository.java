package io.project.SpringAlbBot.model;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BuildRepository extends CrudRepository<Build, String>{


    @Query(
            value = "SELECT * FROM builds b WHERE b.class_build = 'Танк'",
            nativeQuery = true)
    List<Build> findAllTank();

    @Query(
            value = "SELECT * FROM builds b WHERE b.class_build = 'Стоп танк'",
            nativeQuery = true)
    List<Build> findAllStopTank();

    @Query(
            value = "SELECT * FROM builds b WHERE b.class_build = 'МДД'",
            nativeQuery = true)
    List<Build> findAllMdd();

    @Query(
            value = "SELECT * FROM builds b WHERE b.class_build = 'РДД'",
            nativeQuery = true)
    List<Build> findAllRdd();

    @Query(
            value = "SELECT * FROM builds b WHERE b.class_build = 'Саппорт'",
            nativeQuery = true)
    List<Build> findAllSupport();

    @Query(
            value = "SELECT * FROM builds b WHERE b.class_build = 'Друид'",
            nativeQuery = true)
    List<Build> findAllDruid();

    @Query(
            value = "SELECT * FROM builds b WHERE b.class_build = 'Холик'",
            nativeQuery = true)
    List<Build> findAllHill();

}



