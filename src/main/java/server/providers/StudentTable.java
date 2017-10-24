package server.providers;

import server.models.Event;
import server.models.Student;
import server.utility.Authenticator;

import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;

public class StudentTable extends DBmanager {

    public ArrayList getAttendingEvents(String idStudent) throws IllegalAccessException {
        Event event = null;
        ResultSet resultSet = null;
        ArrayList attendingEvents = new ArrayList();

        //henter alle events en studerende deltager på.
        try {
            PreparedStatement getAttendingEvents = getConnection().prepareStatement
                    ("SELECT she.*, s.*, e.* " +
                            "FROM students_has_dsevent she " +
                            "INNER JOIN students s " +
                            "ON she.students_idStudent = s.idStudent " +
                            "INNER JOIN dsevent e " +
                            "ON she.dsevent_idEvent = e.idEvent " +
                            "WHERE s.idStudent = ?");

            getAttendingEvents.setString(1, idStudent);
            resultSet = getAttendingEvents.executeQuery();

            while (resultSet.next()) {
                try {
                    //Opretter ny instans af de studenter der er i ArrayListen. (Måden man henter oplysninger).
                    event = new Event();
                    event.setIdEvent(resultSet.getInt("idEvent"));
                    event.setEventName(resultSet.getString("eventName"));
                    event.setOwner(resultSet.getInt("owner"));
                    event.setLocation(resultSet.getString("location"));
                    event.setPrice(resultSet.getInt("price"));
                    event.setEventDate(resultSet.getString("eventDate"));
                    event.setDescription(resultSet.getString("description"));

                    attendingEvents.add(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        //Returnerer attendingStudents med oplysninger.
        return attendingEvents;
    }

    public boolean addStudent(Student student) throws SQLException {
        // Denne metode er taget fra henrik (Slack)
        long unixTime = System.currentTimeMillis() / 1000L;
        //generer salt password

        student.setSalt(student.getEmail() + unixTime);

        //generer hashed password med salt.
        student.setPassword(Authenticator.hashWithSalt(student.getPassword(), student.getSalt()));
        student.setCreatedTime(unixTime);

        PreparedStatement addStudentStatement = getConnection().prepareStatement
                ("INSERT INTO students (firstName, lastName, email, password, createdTime) VALUES (?, ?, ?, ?, ?)");

        try {
            addStudentStatement.setString(1, student.getFirstName());
            addStudentStatement.setString(2, student.getLastName());
            addStudentStatement.setString(3, student.getEmail());
            addStudentStatement.setString(4, student.getPassword());
            addStudentStatement.setLong(5, student.getCreatedTime());
            try {
                int rowsUpdated = addStudentStatement.executeUpdate();
                if (rowsUpdated != 1) {
                    throw new SQLException("More or less than 1 row was affected");
                }
            } catch (SQLIntegrityConstraintViolationException integrity) {
                integrity.printStackTrace();
                throw new SQLException("the user already exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("SQL Error");
        }

        return true;
    }

    public Student getStudentByEmail(String email) {
        Student student = null;

        ResultSet resultSet = null;

        try {
            PreparedStatement getStudentEmailStatement = getConnection().prepareStatement("SELECT * FROM students WHERE email = ?");

            getStudentEmailStatement.setString(1, email);
            resultSet = getStudentEmailStatement.executeQuery();

            while (resultSet.next()) {
                student = new Student();
                student.setIdStudent(resultSet.getInt("idStudent"));
                student.setEmail(resultSet.getString("email"));
                student.setPassword(resultSet.getString("password"));
                student.setCreatedTime(resultSet.getLong("createdTime"));
            }

            if (student == null) {
                throw new IllegalArgumentException();
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    //Metoden mottar en email og et passord og vertifiserer brukeren som logger inn
    public Student authorize(String email, String password) throws SQLException {

        ResultSet resultSet = null;
        Student studentFound = null;

        try {
            PreparedStatement authorize = getConnection().prepareStatement("SELECT * FROM Students WHERE email = ? AND Password = ? AND Deleted = 0");
            authorize.setString(1, email);
            // Skal spille sammen med hashing, ellers stemmer det ikke overens med oplysningene fra databasen
            authorize.setString(2, Authenticator.hashWithSalt(password, "foo"));

            resultSet = authorize.executeQuery();

            // I tilfælde af at der findes en bruger med dette login, så gemmes oplysningerne i student-objektet.
            while (resultSet.next()) {
                try {
                    studentFound = new Student();
                    studentFound.setIdStudent(resultSet.getInt("idStudent"));
                    studentFound.setFirstName(resultSet.getString("firstName"));
                    studentFound.setLastName(resultSet.getString("lastName"));
                    studentFound.setPassword(resultSet.getString("password"));
                    studentFound.setSalt(resultSet.getString("salt"));
                    studentFound.setEmail(resultSet.getString("email"));
                    //studentFound.setToken();

                } catch (SQLException e) {

                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());

        }
        return studentFound;

    }

    // Indsætter en token i DB til et bestemt idStudent
    public void addToken(String token, String idStudent) throws SQLException {

        PreparedStatement addTokenStatement;
        try {
            addTokenStatement = getConnection().prepareStatement("INSERT INTO tokens (token, idStudent) VALUES (?,?)");
            addTokenStatement.setString(1, token);
            addTokenStatement.setString(2, idStudent);
            addTokenStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    // Sletter en token i databasen til et bestemt idStudent
    public boolean deleteToken(String idStudent) throws SQLException {
        PreparedStatement deleteTokenStatement = getConnection().prepareStatement(" DELETE FROM tokens WHERE token = ?");

        try {
            deleteTokenStatement.setString(1, idStudent);
            int rowsAffected = deleteTokenStatement.executeUpdate();
            if(rowsAffected == 1) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void addToken(String token, int idStudent) throws SQLException {
        PreparedStatement addTokenStatement;
        try {
            addTokenStatement = getConnection().prepareStatement("INSERT INTO tokens (token, students_idStudent) VALUES (? , ?)");
            addTokenStatement.setString(1, token);
            addTokenStatement.setInt(2, idStudent);
            addTokenStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Student getStudentFromToken(String token) throws SQLException {
        ResultSet resultSet = null;
        Student studentFromToken = null;

        try {

            PreparedStatement getStudentFromToken = getConnection().prepareStatement("SELECT idStudent, firstName, lastName, email, createdTime FROM students s INNER JOIN tokens t ON t.students_idStudent = s.idStudent WHERE t.token = ?");


            getStudentFromToken.setString(1, token);
            resultSet = getStudentFromToken.executeQuery();

            while (resultSet.next()) {
                studentFromToken = new Student();

                studentFromToken.setIdStudent(resultSet.getInt("idStudent"));
                studentFromToken.setFirstName(resultSet.getString("firstName"));
                studentFromToken.setLastName(resultSet.getString("lastName"));
                studentFromToken.setEmail(resultSet.getString("email"));
                studentFromToken.setCreatedTime(resultSet.getLong("createdTime"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentFromToken;
    }

}