package com.kyushu.autosum.repositorylayer.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.kyushu.autosum.weblayer.view.View;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ANDRE on 19/04/16.
 */
@Entity
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Material.class)
    private Integer SLIDE_ID;

    @Version
    private Integer version;

    @JsonView(View.Material.class)
    private Double textScore;

    @JsonView(View.Material.class)
    private Double backgroundScore;

    @JsonView(View.Material.class)
    private Double saliencyMapScore;

    @JsonView(View.Material.class)
    private Double interFrameScore;

    @JsonView(View.Material.class)
    private Double globalScore;

    @JsonView(View.Material.class)
    private Integer time;

    @JsonView(View.Material.class)
    @Column(columnDefinition = "LONGTEXT")
    private String text;

    @ElementCollection( fetch=FetchType.LAZY)
    @JsonView(View.Material.class)
    private List<String> keywordsList;

    @Transient
    private Boolean isSelected;

    public Integer getSLIDE_ID() {
        return SLIDE_ID;
    }

    public void setSLIDE_ID(Integer SLIDE_ID) {
        this.SLIDE_ID = SLIDE_ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getKeywordsList() {
        return keywordsList;
    }

    public void setKeywordsList(List<String> keywordsList) {
        this.keywordsList = keywordsList;
    }

    public void addKeyword(String keyword) {
        this.keywordsList.add(keyword);
    }

    public Double getTextScore() {
        return textScore;
    }

    public void setTextScore(Double textScore) {
        this.textScore = textScore;
    }

    public void setBackgroundScore(Double backgroundScore) {
        this.backgroundScore = backgroundScore;
    }

    public void setInterFrameScore(Double interFrameScore) {
        this.interFrameScore = interFrameScore;
    }

    public Double getBackgroundScore() {
        return backgroundScore;
    }

    public Double getInterFrameScore() {
        return interFrameScore;
    }

    public Integer getTime() {
        return time;
    }

    public Double getGlobalScore() {
        return globalScore;
    }

    public void setGlobalScore(Double globalScore) {
        this.globalScore = globalScore;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public Double getSaliencyMapScore() {
        return saliencyMapScore;
    }

    public void setSaliencyMapScore(Double saliencyMapScore) {
        this.saliencyMapScore = saliencyMapScore;
    }

    @Override
    public String toString() {
        return "Slide{" +
                "SLIDE_ID=" + SLIDE_ID +
                ", version=" + version +
                ", textScore=" + textScore +
                ", backgroundScore=" + backgroundScore +
                ", interFrameScore=" + interFrameScore +
                ", globalScore=" + globalScore +
                ", time=" + time +
                ", text='" + text + '\'' +
                ", keywordsList=" + keywordsList +
                ", isSelected=" + isSelected +
                '}';
    }
}
