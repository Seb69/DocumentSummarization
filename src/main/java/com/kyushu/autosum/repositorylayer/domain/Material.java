package com.kyushu.autosum.repositorylayer.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.kyushu.autosum.weblayer.view.View;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANDRE on 15/04/16.
 */
@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Material.class)
    private Integer MATERIAL_ID;

    @Version
    private Integer version;

    @JsonView(View.Material.class)
    private String materialFilePATH;

    @JsonView(View.Material.class)
    @Column(columnDefinition = "LONGTEXT")
    private String text;

    @ElementCollection(fetch=FetchType.LAZY)
    @JsonView(View.Material.class)
    private List<String> keywordsList;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @JoinTable(
            name = "JOIN_MATERIAL_SLIDE"
    )
    @JsonView(View.Material.class)
    private List<Slide> slideList = new ArrayList<>();

    private String userId;


    public Integer getId() {
        return MATERIAL_ID;
    }

    public void setId(Integer MATERIAL_ID) {
        this.MATERIAL_ID = MATERIAL_ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMaterialFilePATH() {
        return materialFilePATH;
    }

    public void setMaterialFilePATH(String materialFilePATH) {
        this.materialFilePATH = materialFilePATH;
    }

    public List<Slide> getSlideList() {
        return slideList;
    }

    public void setSlideList(List<Slide> slideList) {
        this.slideList = slideList;
    }

    public void addSlide(Slide slide) {
        slideList.add(slide);
    }

    public void removeSlide(Slide slide) {
        this.slideList.remove(slide);
    }

    public List<String> getKeywordsList() {
        return keywordsList;
    }

    public void setKeywordsList(List<String> keywordsList) {
        this.keywordsList = keywordsList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Material{" +
                "MATERIAL_ID=" + MATERIAL_ID +
                ", version=" + version +
                ", materialFilePATH='" + materialFilePATH + '\'' +
                ", text='" + text + '\'' +
                ", keywordsList=" + keywordsList +
                ", slideList=" + slideList +
                ", userId='" + userId + '\'' +
                '}';
    }
}
