
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

// TIME COMPLEXITY: O(E * R), where E is the number of events and R is the number of members in the request

public final class FindMeetingQuery {
    public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
        if (request.getDuration() > 1440)
            return new ArrayList<>();
        else if (events == null || events.isEmpty())
            return new ArrayList<>(Collections.singletonList(TimeRange.fromStartEnd(0, 1440, false)));
        else{
            List<List<Integer>> meetingTimeRanges = new ArrayList<>();

            for (Event singleEvent: events){
                if (requestedMemberInExistingMeeting(request.getAttendees(), singleEvent.getAttendees()))
                    meetingTimeRanges.add(Arrays.asList(singleEvent.getWhen().start(), singleEvent.getWhen().end()));
            }

            int i = 0;
            while (i < meetingTimeRanges.size()-1){
                if (overlaps(meetingTimeRanges.get(i), meetingTimeRanges.get(i+1)))
                    meetingTimeRanges.add(i, joinOverlappingTimeRanges(meetingTimeRanges.remove(i), meetingTimeRanges.remove(i)));
                else
                    i++;
            }

            ArrayList<TimeRange> freeTimeSlots = new ArrayList<>();

            int startTime = 0;
            int endTime;
            for (List<Integer> singleMeetingTime: meetingTimeRanges){
                endTime = singleMeetingTime.get(0);
                if (endTime - startTime >= request.getDuration())
                    freeTimeSlots.add(TimeRange.fromStartEnd(startTime, endTime, false));
                startTime = singleMeetingTime.get(1);
            }
            if (startTime < 1440)
                freeTimeSlots.add(TimeRange.fromStartEnd(startTime, 1440, false));

            return freeTimeSlots;
        }
    }
    public static boolean requestedMemberInExistingMeeting(Collection<String> requestedMembers, Set<String> eventMembers){
        for (String singleRequestedMember: requestedMembers){
            if (eventMembers.contains(singleRequestedMember))
                return true;
        }
        return false;
    }
    public static boolean overlaps(List<Integer> meetingA, List<Integer> meetingB){
        if (meetingA.get(0) > meetingB.get(0) && meetingA.get(0) < meetingB.get(1))
            return true;
        else if (meetingA.get(1) > meetingB.get(0) && meetingA.get(1) < meetingB.get(1))
            return true;
        else if (meetingB.get(0) > meetingA.get(0) && meetingB.get(0) < meetingA.get(1))
            return true;
        else return meetingB.get(1) > meetingA.get(0) && meetingB.get(1) < meetingA.get(1);
    }
    public static List<Integer> joinOverlappingTimeRanges(List<Integer> meetingA, List<Integer> meetingB) {
        return Arrays.asList(Math.min(meetingA.get(0), meetingB.get(0)), Math.max(meetingA.get(1), meetingB.get(1)));
    }
}
