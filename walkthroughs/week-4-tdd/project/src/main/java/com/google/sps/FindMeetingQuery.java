// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;

// TIME COMPLEXITY: O(E * R), where E is the number of events and R is the number of members in the request

public final class FindMeetingQuery {
    public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
        if (request.getDuration() > TimeRange.WHOLE_DAY.end())
            return new ArrayList<>();
        else if (events == null || events.isEmpty())
            return new ArrayList<>(Collections.singletonList(TimeRange.WHOLE_DAY));
        else{
            ArrayList<TimeRange> meetingTimeRanges = new ArrayList<>();
            for (Event event: events){
                if (!Collections.disjoint(request.getAttendees(), event.getAttendees())){
                    TimeRange eventTimeRange = event.getWhen();
                    if (meetingTimeRanges.size() == 0)
                        meetingTimeRanges.add(eventTimeRange);
                    else{
                        int lastIndex = meetingTimeRanges.size()-1;
                        TimeRange lastMeetingTime = meetingTimeRanges.get(lastIndex);
                        if (eventTimeRange.overlaps(lastMeetingTime))
                            meetingTimeRanges.set(lastIndex, joinTimes(eventTimeRange, lastMeetingTime));
                        else
                            meetingTimeRanges.add(eventTimeRange);
                    }
                }
            } // For loop adds all the meeting time ranges of the day into an ArrayList

            ArrayList<TimeRange> freeTimeSlots = new ArrayList<>();

            int startTime = 0;
            int endTime;
            for (TimeRange singleMeetingTime: meetingTimeRanges){ // O(E)
                endTime = singleMeetingTime.start();
                if (endTime - startTime >= request.getDuration())
                    freeTimeSlots.add(TimeRange.fromStartEnd(startTime, endTime, false));
                startTime = singleMeetingTime.end();
            } // For loop adds all the free time slots (all the gaps between the meetings) to freeTimeSlots
            if (startTime < TimeRange.WHOLE_DAY.end())
                freeTimeSlots.add(TimeRange.fromStartEnd(startTime, TimeRange.WHOLE_DAY.end(), false));

            return freeTimeSlots;
        }
    }
    public static TimeRange joinTimes(TimeRange meetingA, TimeRange meetingB) {
        return TimeRange.fromStartEnd(Math.min(meetingA.start(), meetingB.start()), Math.max(meetingA.end(), meetingB.end()), false);
    }
}