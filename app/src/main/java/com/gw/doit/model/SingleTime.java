package com.gw.doit.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SingleTime extends RealmObject implements Time {

    @PrimaryKey
    private Long id;

    private Integer order;

    private Integer seconds;

    public SingleTime(){}

    public SingleTime(Integer seconds){
        this.seconds = seconds;
    }

    public SingleTime(Long id, Integer seconds){
        this.seconds = seconds;
        this.id = id;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getOrder() { return order; }

    public void setOrder(Integer order) { this.order = order; }

    public Integer getSeconds() { return seconds; }

    public void setSeconds(Integer seconds) { this.seconds = seconds; }

    @Override
    public List<SingleTime> getTimesToCount() {
        return Arrays.asList(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingleTime)) return false;
        SingleTime that = (SingleTime) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getOrder(), that.getOrder()) &&
                Objects.equals(getSeconds(), that.getSeconds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrder(), getSeconds());
    }

    @Override
    public String toString(){
        return String.format("%02d:%02d", seconds / 60, (seconds % 60));
    }

}
