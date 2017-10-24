package server.controllers;

import server.exceptions.ResponseException;
import server.models.Event;
import server.models.Student;

import java.sql.SQLException;
import java.util.ArrayList;

import server.providers.EventTable;

/**
 * Created by STFU on 19/10/2017
 */


public class EventController {

    /**
     * EventController klassen forbinder vores endpoints med vores providers,
     * som sender SQL statements til databasen
     */

    //Controller klasse for event. Laver kald til databasen.
    //API forbindes med database.
    EventTable eventTable = new EventTable();

    public ArrayList getAttendingStudents(String idEvent) throws IllegalAccessException {
        ArrayList attendingStudents = eventTable.getAttendingStudents(idEvent);
        eventTable.close();
        return attendingStudents;
    }

    public boolean createEvent(Event eventData, Student student) throws SQLException {
        boolean createEvent = eventTable.createEvent(eventData, student);
        eventTable.close();
        return createEvent;
    }


    public ArrayList<Event> getAllEvents() {
        ArrayList getAllEvents = eventTable.getAllEvents();
        eventTable.close();
        return getAllEvents;
    }

    public boolean deleteEvent(Event event, Student student) throws Exception {
        boolean deleteEvent = eventTable.deleteEvent(event, student);
        eventTable.close();
        return deleteEvent;
    }

    public boolean joinEvent(int eventId, int studentId) throws ResponseException {
        boolean joinEvent = eventTable.joinEvent(eventId, studentId);
        eventTable.close();
        return joinEvent;
    }

    public boolean updateEvent(Event event, Student student) throws Exception {
        boolean updateEvent = eventTable.updateEvent(event, student);
        eventTable.close();
        return updateEvent;

    }
}

