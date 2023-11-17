import java.util.Scanner



data class MeetingRoom(val id: Int, val name: String, val capacity:Int)
data class Reservation(val roomId: Int, val startTime: String, val endTime: String)
class MeetingRoomBookingSystem {
    private val meetingRooms = mutableListOf<MeetingRoom>()
    private val reservations = mutableListOf<Reservation>()
    private var isRoomRegistered=false
    /***register**/
    fun register(room: MeetingRoom) {
        val roomExists = meetingRooms.any { it.id ==  room.id }

        if (roomExists) {
            println("room already registered")
        } else {
            meetingRooms.add(room)
            isRoomRegistered
            println("room registered successfully")
        }

    }
    /**display**/
    fun display() {
        println("Available Meeting Rooms:")
        var flag: Int = 0
        meetingRooms.forEach { room ->
            if (!isRoomReserved(room.id)) {
                println("Room ID: ${room.id}, Name: ${room.name} , Capacity:${room.capacity}")
                flag = 1
            }
        }
        if (flag == 0) {
            println("All rooms are reserved")
        }
    }
    fun display( startTime: String,  endTime: String,capacity: Int) {

        meetingRooms.forEach { room ->
            if (!isOverlappingReservation(room.id, startTime, endTime)) {
                if (room.capacity >= capacity) {
                    println("Room ID: ${room.id}, Name: ${room.name} , Capacity:${room.capacity}")


                }

            }
            else{
                println("Meeting room already reserverd ")
            }
        }
    }



    fun reserve(room_Id: Int,startTime: String,endTime: String,capacity: Int) :Boolean{
        if (!isValidTimeFormat(startTime, endTime)) {
            println("Invalid time format. Please use HH:mm.")
            return false
        }

        if (isOverlappingReservation(room_Id, startTime, endTime)) {
            println("Overlapping reservation found. Please choose a different time slot.")
            return false
        }
        val roomExists = meetingRooms.any { it.id ==  room_Id && it.capacity>=capacity}
        if(!roomExists){
            println("Reservation unsuccesful ")
            println("INVALID ROOMID OR CAPACITY")
            return false
        }

        val reservation = Reservation(room_Id, startTime, endTime)
        reservations.add(reservation)
        println("Room $room_Id reserved from $startTime to $endTime.")
        println("Room reserved successfully")
        return true
    }

    fun cancel(roomId: Int,startTime: String,endTime: String) {
        val reservation = reservations.find { it.roomId == roomId && it.startTime==startTime && it.endTime==endTime}
        val roomExists = meetingRooms.any { it.id ==  roomId }
        if (reservation != null) {
            reservations.remove(reservation)
            println("Reservation for Room $roomId with time slot $startTime to $endTime cancelled.")
        }
        else if (!roomExists) {
            println("No Meetingroom exists with entered roomId")
        }

        else {
            println("Room $roomId is not reserved.")
        }
    }
    private fun isValidTimeFormat(startTime: String, endTime: String): Boolean {
        val timeRegex = Regex("^([01]?[0-9]|2[0-3]):[0-5][0-9]$")
        return startTime.matches(timeRegex) && endTime.matches(timeRegex)
    }
    private fun isOverlappingReservation(roomId: Int, newStartTime: String, newEndTime: String): Boolean {
        val existingReservations = reservations.filter { it.roomId == roomId }
        for (reservation in existingReservations) {
            if (doTimeRangesOverlap(reservation.startTime, reservation.endTime, newStartTime, newEndTime)) {
                return true
            }
        }
        return false
    }
    private fun isRoomReserved(roomId: Int): Boolean {
        return reservations.any { it.roomId == roomId }
    }
    private fun doTimeRangesOverlap(startTime1: String, endTime1: String, startTime2: String, endTime2: String): Boolean {
        val formatter = java.text.SimpleDateFormat("HH:mm")
        val start1 = formatter.parse(startTime1)
        val end1 = formatter.parse(endTime1)
        val start2 = formatter.parse(startTime2)
        val end2 = formatter.parse(endTime2)
        return start1.before(end2) && end1.after(start2)
    }

}