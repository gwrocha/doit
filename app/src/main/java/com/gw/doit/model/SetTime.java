package com.gw.doit.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SetTime extends RealmObject implements Time {

    @PrimaryKey
    private Long id;

    private Integer order;

    private Integer nextOrder;

    private Integer amount;

    private RealmList<SetTime> setTimes;

    private RealmList<SingleTime> singleTimes;

    public SetTime(Integer amount){
        this.amount = amount;
        this.nextOrder = 1;
        setTimes = new RealmList<>();
        singleTimes = new RealmList<>();
    }

    public SetTime(){
        this(1);
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getOrder() { return order; }

    public void setOrder(Integer order) { this.order = order; }

    public Integer getNextOrder() { return nextOrder; }

    public void setNextOrder(Integer nextOrder) { this.nextOrder = nextOrder; }

    public Integer getAmount() { return amount; }

    public void setAmount(Integer amount) { this.amount = amount; }

    public RealmList<SetTime> getSetTimes() { return setTimes; }

    public void setSetTimes(RealmList<SetTime> setTimes) { this.setTimes = setTimes; }

    public RealmList<SingleTime> getSingleTimes() { return singleTimes; }

    public void setSingleTimes(RealmList<SingleTime> singleTimes) { this.singleTimes = singleTimes; }

    private Integer useNextOrder(){
        Integer order = nextOrder;
        nextOrder = order + 1;
        return order;
    }

    public Boolean addSetTime(SetTime time) {
        time.setOrder(useNextOrder());
        setTimes.add(time);
        return Boolean.TRUE;
    }

    public Boolean addSingleTime(SingleTime time) {
        time.setOrder(useNextOrder());
        singleTimes.add(time);
        return Boolean.TRUE;
    }

    @Override
    public List<SingleTime> getTimesToCount() {
        List<Time> times = new ArrayList<>();
        times.addAll(singleTimes);
        times.addAll(setTimes);

        List<SingleTime> singleTimes = times.stream()
                .sorted(Comparator.comparingInt(Time::getOrder))
                .flatMap(t -> t.getTimesToCount().stream())
                .collect(Collectors.toList());

        List<SingleTime> retorno = IntStream.rangeClosed(1, amount)
                .mapToObj(i -> singleTimes)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return retorno;
    }

    @Override
    public Integer getSeconds() {
        return getTimesToCount().stream()
                .collect(Collectors.summingInt(SingleTime::getSeconds));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SetTime)) return false;
        SetTime setTime = (SetTime) o;
        return Objects.equals(getId(), setTime.getId()) &&
                Objects.equals(getOrder(), setTime.getOrder()) &&
                Objects.equals(getAmount(), setTime.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrder(), getAmount());
    }

    @Override
    public String toString(){
        Integer seconds = getSeconds();
        if(seconds > 3600)
            return String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, (seconds % 60));
        else
            return String.format("%02d:%02d", seconds / 60, (seconds % 60));
    }
}
