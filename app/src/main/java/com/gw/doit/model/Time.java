package com.gw.doit.model;

import java.util.List;

public interface Time {

    public Integer getOrder();

    public Integer getSeconds();

    public List<SingleTime> getTimesToCount();

}
