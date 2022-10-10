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

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Алебарда'",
            nativeQuery = true)
    List<Build> findAllAleba();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Посох падших'",
            nativeQuery = true)
    List<Build> findAllPadhsh();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Благодатный посох'",
            nativeQuery = true)
    List<Build> findAllBolago();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Большой священный посох'",
            nativeQuery = true)
    List<Build> findAllBigHoly();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Дикий посох'",
            nativeQuery = true)
    List<Build> findAllWild();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Посох порчи'",
            nativeQuery = true)
    List<Build> findAllDamage();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Загадочный посох'",
            nativeQuery = true)
    List<Build> findAllMysterious();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Средоточие злобы'",
            nativeQuery = true)
    List<Build> findAllZloba();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Мистический посох'",
            nativeQuery = true)
    List<Build> findAllMystical();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Песнь заката'",
            nativeQuery = true)
    List<Build> findAllSong();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Гибельный посох'",
            nativeQuery = true)
    List<Build> findAllDisastrous();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Посох обреченности'",
            nativeQuery = true)
    List<Build> findAllDoom();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Заледенелый посох'",
            nativeQuery = true)
    List<Build> findAllIcy();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Хранители клятвы'",
            nativeQuery = true)
    List<Build> findAllOathKeeper();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Разрушитель миров'",
            nativeQuery = true)
    List<Build> findAllDestroyer();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Булава Камлана'",
            nativeQuery = true)
    List<Build> findAllKamlan();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Коса душ'",
            nativeQuery = true)
    List<Build> findAllScytheSouls();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Булава'",
            nativeQuery = true)
    List<Build> findAllMace();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Длань правосудия'",
            nativeQuery = true)
    List<Build> findAllJustice();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Тяжёлая булава'",
            nativeQuery = true)
    List<Build> findAllHeavyMace();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Хранитель рощи'",
            nativeQuery = true)
    List<Build> findAllKeeperOfGrove();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Искатель Грааля'",
            nativeQuery = true)
    List<Build> findAllGraal();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Песнь рассвета'",
            nativeQuery = true)
    List<Build> findAllDawn();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Бадон'",
            nativeQuery = true)
    List<Build> findAllBadon();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Туманный пронзатель'",
            nativeQuery = true)
    List<Build> findAllImpaler();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Арбалет'",
            nativeQuery = true)
    List<Build> findAllCrossbow();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Плачущий арбалет'",
            nativeQuery = true)
    List<Build> findAllSadCrossbow();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Осадный арбалет'",
            nativeQuery = true)
    List<Build> findAllSiegeCrossbow();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Творец энергии'",
            nativeQuery = true)
    List<Build> findAllEnergyCreator();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Посох лесного пожара'",
            nativeQuery = true)
    List<Build> findAllWildfireStaff();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Посох серы'",
            nativeQuery = true)
    List<Build> findAllStaffOfSulfur();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Призма вечного холода'",
            nativeQuery = true)
    List<Build> findAllPrismCold();

    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Демонический посох'",
            nativeQuery = true)
    List<Build> findAllDemonStaff();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Большой проклятый посох'",
            nativeQuery = true)
    List<Build> findAllGreatCursedStaff();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Проклятый череп'",
            nativeQuery = true)
    List<Build> findAllCursedSkull();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Большой топор'",
            nativeQuery = true)
    List<Build> findAllBigAx();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Адская коса'",
            nativeQuery = true)
    List<Build> findAllHellishScythe();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Медвежьи лапы'",
            nativeQuery = true)
    List<Build> findAllBearPaws();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Королевский меч'",
            nativeQuery = true)
    List<Build> findAllRoyalSword();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Меч резни'",
            nativeQuery = true)
    List<Build> findAllSwordOfCarnage();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Галатины'",
            nativeQuery = true)
    List<Build> findAllGalatians();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Царетворец'",
            nativeQuery = true)
    List<Build> findAllKingmaker();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Охотник за душами'",
            nativeQuery = true)
    List<Build> findAllSoulsHunter();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Копьё рассвета'",
            nativeQuery = true)
    List<Build> findAllSpearOfDawn();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Кровопускатель'",
            nativeQuery = true)
    List<Build> findAllBloodletter();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Клык демона'",
            nativeQuery = true)
    List<Build> findAllDemonFang();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Укротители ярости'",
            nativeQuery = true)
    List<Build> findAllRageTamers();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Шипастые перчатки'",
            nativeQuery = true)
    List<Build> findAllSpikedGloves();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Медвежьи перчатки'",
            nativeQuery = true)
    List<Build> findAllBearGloves();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Вороний цестус'",
            nativeQuery = true)
    List<Build> findAllRavenCestus();
    @Query(
            value = "SELECT * FROM builds b WHERE b.weapon_1 = 'Кулаки авалона'",
            nativeQuery = true)
    List<Build> findAllFistsOfAvalon();


}



