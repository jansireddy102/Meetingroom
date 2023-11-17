import org.junit.Test

import org.junit.Assert.*

class MeetingRoomBookingSystemTest {

    @Test
    fun register() {
        val meetingroom=MeetingRoomBookingSystem()
        var room=MeetingRoom(id = 1, name = "kabini", capacity = 5)
        meetingroom.register(room)
        assertTrue(meetingroom.reserve(1,"09:30","10:30",5))
    }

    @Test
    fun display() {
        val meetingroom=MeetingRoomBookingSystem()
        meetingroom.register( MeetingRoom(id = 3, name = "Bhagirathi", capacity = 6))
        meetingroom.register(MeetingRoom(id = 2, name = "kabini", capacity = 11))
        meetingroom.display()
        meetingroom.reserve(3,"09:30","10:30",6)
        meetingroom.display()
        meetingroom.display("11:30","12:30",6)
    }


    @Test
    fun reserve() {
        val meetingroom=MeetingRoomBookingSystem()
        var room=MeetingRoom(id = 2, name = "kabini", capacity = 11)
        meetingroom.register(room)
        assertTrue(meetingroom.reserve(2,"09:30","10:30",11))
        assertFalse(meetingroom.reserve(2,"10:00","11:30",11))
    }

    @Test
    fun cancel() {
        val meetingroom=MeetingRoomBookingSystem()
        var room=MeetingRoom(id = 3, name = "Bhagirathi", capacity = 6)
        meetingroom.register(room)
        meetingroom.reserve(3,"13:40","14:40",6)
        meetingroom.cancel(3,"13:40","14:40")
        meetingroom.cancel(3,"13:40","14:40")

    }

    @Test
    fun Overlapping(){
        val meetingroom=MeetingRoomBookingSystem()
        meetingroom.register( MeetingRoom(id = 4, name = "Bhagirathi", capacity = 6))
        meetingroom.register(MeetingRoom(id = 5, name = "kabini", capacity = 11))
        assertTrue(  meetingroom.reserve(5,"13:30","15:00",5))
        assertFalse(meetingroom.reserve(5,"13:30","15:00",11))
    }

}