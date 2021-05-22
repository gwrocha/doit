package com.gw.doit.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Plan extends RealmObject{

    @PrimaryKey
    private  Long id;

    private  String description;

    private Date createdAt;

    private SetTime setTime;

    public Plan(){}

    public Plan(String description){
        this.description = description;
        this.createdAt = new Date();
        this.setTime = new SetTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public SetTime getSetTime() {
        return setTime;
    }

    public void setSetTime(SetTime setTime) {
        this.setTime = setTime;
    }

    public Boolean addTime(Time time){
        if(time instanceof SingleTime)
            return this.setTime.addSingleTime((SingleTime) time);
        else
            return this.setTime.addSetTime((SetTime) time);
    }

    public List<SingleTime> getTimes() {
        return setTime.getSingleTimes();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return Objects.equals(getDescription(), plan.getDescription()) &&
                Objects.equals(getCreatedAt(), plan.getCreatedAt()) &&
                Objects.equals(setTime, plan.setTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getCreatedAt(), setTime);
    }

    @Override
    public String toString() {
        return "Plan{" +
                "description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", Time=" + setTime +
                '}';
    }
}
