package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private List<TimeEntry> timeEntries = new ArrayList<>();
    private long currentId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(currentId);
        currentId++;
        timeEntries.add(timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return timeEntries.stream().filter(timeEntry -> timeEntry.getId() == timeEntryId).findFirst().orElse(null);
    }

    @Override
    public List<TimeEntry> list() {
        return timeEntries;
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {
        TimeEntry foundTimeEntry = find(timeEntryId);
        if(foundTimeEntry != null) {
            foundTimeEntry.setProjectId(timeEntry.getProjectId());
            foundTimeEntry.setUserId(timeEntry.getUserId());
            foundTimeEntry.setDate(timeEntry.getDate());
            foundTimeEntry.setHours(timeEntry.getHours());
            return foundTimeEntry;
        } else {
            return null;
        }
    }

    @Override
    public void delete(long timeEntryId) {
        TimeEntry foundTimeEntry = find(timeEntryId);
        if(foundTimeEntry != null) {
            timeEntries.remove(foundTimeEntry);
        }

    }
}
