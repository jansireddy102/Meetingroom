import java.util.*

fun main(args: Array<String>) {

    val scan= Scanner(System.`in`)
    val bookingSystem = MeetingRoomBookingSystem()
    var id:Int;
    var name:String;
    var capacity:Int;
    var startTime:String;
    var endTime:String
    while(true) {
        println("Enter the required function\n1.register meeting room\n2.display availble rooms\n3.display available for particular time slots\n4.reserve meeting room\n5.cancel meeting room\n6.To exit the booking system")
        var x=scan.nextInt()

        when (x) {
            1 -> {
                println("Enter the room id")
                id = scan.nextInt()
                println("Enter the name")
                name = scan.next()
                println("Enter the capacity")
                capacity = scan.nextInt()
                if (id < 0 || id == null) {
                    println("Enter the valid id")
                    id = scan.nextInt()
                }
                if (name.isEmpty()) {
                    println("Enter the valid name")
                    name = scan.next()

                }
                if (capacity > 2 && capacity > 13 || capacity == null) {
                    println("capacity should be greater than two and limited to twele")
                    println("Enter the valid capacity ")
                    capacity = scan.nextInt()
                }
                bookingSystem.register(MeetingRoom(id, name, capacity))
            }

            2 -> bookingSystem.display()
            3  ->{
                println("Enter the slot details")
                println("Enter the start time")
                startTime=scan.next()
                println("Enter the end time")
                endTime=scan.next()
                println("Enter the capaciy")
                capacity=scan.nextInt()
                bookingSystem.display(startTime,endTime,capacity)
            }
            4 -> {
                println("Enter the room id")
                id = scan.nextInt()
                println("start time ")
                startTime = scan.next()
                println("End time")
                endTime = scan.next()
                println("Enter the capacity")
                capacity=scan.nextInt()
                bookingSystem.reserve(id, startTime, endTime,capacity)
            }

            5 -> {
                println("Enter the roomId")
                id = scan.nextInt()
                println("start time ")
                startTime = scan.next()
                println("End time")
                endTime = scan.next()
                bookingSystem.cancel(id,startTime,endTime)
            }
            6->{
                System.exit(0)
            }
        }
    }
}