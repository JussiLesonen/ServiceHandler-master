public class ClassRoomCourse extends Course{
    private String roomName;

    public ClassRoomCourse(){
        roomName = "room";
    }

    public ClassRoomCourse(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
