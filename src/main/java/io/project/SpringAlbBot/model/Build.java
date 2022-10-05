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

    private String classBuild;

    private String head;

    private String body;

    private String legs;

    private String cloak;

    private String food;

    private String elixir;

    private String size;


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
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    @Override
    public String toString() {
        return "Build{" +
                "id=" + id +
                ", classBuild='" + classBuild + '\'' +
                ", head='" + head + '\'' +
                ", body='" + body + '\'' +
                ", legs='" + legs + '\'' +
                ", cloak='" + cloak + '\'' +
                ", food='" + food + '\'' +
                ", elixir='" + elixir + '\'' +
                ", size='" + size + '\'' +
                '}';
    }


}
