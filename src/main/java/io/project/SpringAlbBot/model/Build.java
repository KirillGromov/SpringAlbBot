package io.project.SpringAlbBot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="builds")
public class Build {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String head;
    private String body;
    private String legs;
    private String cloak;

    private String weapon_1;

    private String weapon_2;
    private String elixir;
    private String food;

    private String groupSize;
    private String classBuild;


    public String getWeapon_1() {
        return weapon_1;
    }

    public void setWeapon_1(String weapon_1) {
        this.weapon_1 = weapon_1;
    }

    public String getWeapon_2() {
        return weapon_2;
    }

    public void setWeapon_2(String weapon_2) {
        this.weapon_2 = weapon_2;
    }

    public String getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(String groupSize) {
        this.groupSize = groupSize;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassBuild() {
        return classBuild;
    }

    public void setClassBuild(String classBuild) {
        this.classBuild = classBuild;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLegs() {
        return legs;
    }

    public void setLegs(String legs) {
        this.legs = legs;
    }

    public String getCloak() {
        return cloak;
    }

    public void setCloak(String cloak) {
        this.cloak = cloak;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getElixir() {
        return elixir;
    }

    public void setElixir(String elixir) {
        this.elixir = elixir;
    }

    public String getSize() {
        return groupSize;
    }

    public void setSize(String size) {
        this.groupSize = size;
    }


    @Override
    public String toString() {
        return "Build{" +
                "id=" + id +
                ", head='" + head + '\'' +
                ", body='" + body + '\'' +
                ", legs='" + legs + '\'' +
                ", cloak='" + cloak + '\'' +
                ", weapon_1='" + weapon_1 + '\'' +
                ", weapon_2='" + weapon_2 + '\'' +
                ", elixir='" + elixir + '\'' +
                ", food='" + food + '\'' +
                ", groupSize='" + groupSize + '\'' +
                ", classBuild='" + classBuild + '\'' +
                '}';
    }
}
