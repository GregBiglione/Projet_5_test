package com.greg.todoc.events;

import java.util.Date;

public class FilterByDateEvent {
    Date startDate;
    Date enddate;

    public FilterByDateEvent(Date startDate, Date enddate) {
        this.startDate = startDate;
        this.enddate = enddate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEnddate() {
        return enddate;
    }
}
