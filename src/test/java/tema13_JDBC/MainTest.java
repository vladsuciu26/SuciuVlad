package tema13_JDBC;

import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class MainTest {
    Connection conn;

    @Before
    public void setUp() throws Exception {

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/test;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE", "sa", "");
            Statement statement = conn.createStatement();

            final String createTable =
                    "DROP TABLE IF EXISTS accommodation cascade;\n" +
                            "DROP TABLE IF EXISTS room_fare cascade;\n" +
                            "DROP TABLE IF EXISTS accommodation_fare_relation cascade;\n" +

                            "CREATE TABLE accommodation\n" +
                            "(\n" +
                            "id serial NOT NULL,\n" +
                            "type character varying(32) NOT NULL,\n" +
                            "bed_type character varying(32) NOT NULL,\n" +
                            "max_guests integer NOT NULL,\n" +
                            "description character varying(512),\n" +
                            "PRIMARY KEY (id)\n" +
                            ");\n" +
                            "\n" +
                            "CREATE TABLE room_fare\n" +
                            "(\n" +
                            "id serial NOT NULL,\n" +
                            "value integer NOT NULL,\n" +
                            "season character varying(32),\n" +
                            "PRIMARY KEY (id)\n" +
                            ");\n" +
                            "\n";

            final String createRelationTable =
                    "CREATE TABLE accommodation_fare_relation\n" +
                            "(\n" +
                            "id serial PRIMARY KEY NOT NULL,\n" +
                            "id_accommodation integer NOT NULL,\n" +
                            "FOREIGN KEY (id_accommodation)\n" +
                            "REFERENCES accommodation(id),\n" +
                            "id_room_fare integer NOT NULL,\n" +
                            "FOREIGN KEY (id_room_fare)\n" +
                            "REFERENCES room_fare(id));";

            statement.execute(createTable);
            statement.execute(createRelationTable);

            insertDataForAccommodation(conn);
            insertDataForRoomFare(conn);
            insertDataForAccommodationFareRelation(conn);

            conn.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertDataForAccommodation(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("insert into accommodation(id, type, bed_type, max_guests, description) " +
                "values (?, ?, ?, ?, ?)");
        insertRowForAccommodation(ps, 1, "hotel", "single", 1, "A room assigned to one person");
        insertRowForAccommodation(ps, 2, "hotel", "double", 2, "A room assigned to two people");
        insertRowForAccommodation(ps, 3, "hotel", "triple", 3, "A room that can accommodate three persons");
        insertRowForAccommodation(ps, 4, "hotel", "queen", 2, " A room with a queen-sized bed");
        insertRowForAccommodation(ps, 5, "hotel", "twin", 4, "A room with two twin beds");
    }

    private void insertDataForRoomFare(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("insert into room_fare(id, value, season) values (?, ?, ?)");
        insertRowForRoomFare(ps, 1, 100, "summer");
        insertRowForRoomFare(ps, 2, 150, "summer");
        insertRowForRoomFare(ps, 3, 300, "autumn");
        insertRowForRoomFare(ps, 4, 400, "autumn");
        insertRowForRoomFare(ps, 5, 700, "winter");
        insertRowForRoomFare(ps, 6, 800, "winter");
        insertRowForRoomFare(ps, 7, 350, "spring");
    }

    private void insertDataForAccommodationFareRelation(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("insert into accommodation_fare_relation(id, id_accommodation, id_room_fare) " +
                "values (?, ?, ?)");
        insertRowForAccommodationRoomFare(ps, 1, 2, 2);
        insertRowForAccommodationRoomFare(ps, 2, 2, 3);
        insertRowForAccommodationRoomFare(ps, 3, 4, 6);
        insertRowForAccommodationRoomFare(ps, 4, 4, 7);
        insertRowForAccommodationRoomFare(ps, 5, 5, 4);
        insertRowForAccommodationRoomFare(ps, 6, 5, 5);
    }

    private void insertRowForAccommodation(PreparedStatement ps, int id, String type, String bed_type, int max_guests, String description)
            throws SQLException {

        ps.setInt(1, id);
        ps.setString(2, type);
        ps.setString(3, bed_type);
        ps.setInt(4, max_guests);
        ps.setString(5, description);
        ps.executeUpdate();
    }

    private void insertRowForRoomFare(PreparedStatement ps, int id, double value, String season) throws SQLException {
        ps.setInt(1, id);
        ps.setDouble(2, value);
        ps.setString(3, season);
        ps.executeUpdate();
    }

    private void insertRowForAccommodationRoomFare(PreparedStatement ps, int id, int id_accommodation, int id_room_fare)
            throws SQLException {

        ps.setInt(1, id);
        ps.setInt(2, id_accommodation);
        ps.setInt(3, id_room_fare);
    }

    @Test
    public void testSelectForAccommodation() throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from accommodation");
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            String type = resultSet.getString("type");
            String bedType = resultSet.getString("bed_type");
            int maxGuests = resultSet.getInt("max_guests");
            String description = resultSet.getString("description");

            System.out.println(type + " - " + bedType + ", " + maxGuests + ", " + description);
        }
    }

    @Test
    public void testSelectForRoomFare() throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from room_fare");
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            int value = resultSet.getInt("value");
            String season = resultSet.getString("season");
            System.out.println("$" + value + " - " + season);
        }
    }

    @Test
    public void testSelectForAccommodationFareRelation() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select accommodation.type, accommodation.bed_type," +
                " accommodation.max_guests, accommodation.description, room_fare.value, room_fare.season " +
                "from accommodation_fare_relation" +
                " inner join accommodation on accommodation_fare_relation.id_accommodation = accommodation.id" +
                " inner join room_fare on accommodation_fare_relation.id_room_fare = room_fare.id");

        ResultSet resultSet = ps.executeQuery();

        while(resultSet.next()) {
            String type = resultSet.getString("type");
//            String bedType = resultSet.getString("bed_type");
//            int maxGuests = resultSet.getInt("max_guests");
//            String description = resultSet.getString("description");
//            int value = resultSet.getInt("value");
//            String season = resultSet.getString("season");

            System.out.println(type);

//            System.out.println("$" + value + " - " + season);

//            System.out.println(type + " : " + bedType + ", " + maxGuests + ", " + description);
        }
    }
}