package model;
import org.junit.jupiter.api.Test;

import com.mysql.cj.protocol.SocksProxySocketFactory;

import model.planning.TimeSlot;
import model.planning.Planning;
import tools.Db;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;


public class TestPlanning {
    @Test
    void equal() {
        try {
            Db connection = new Db();
            int cleanerId = connection.DAOAddCleaner(
                                "Doe",
                                "null",
                                "John",
                                "null",
                                "null",
                                LocalDate.now(),
                                false,
                                new Address("28", "av yves thepot", "29000", "quimper"),
                                0, 0,
                                null,
                                null,
                                null,
                                null,
                                false,
                                null,
                                null);

            ArrayList<TimeSlot> ts = new ArrayList<TimeSlot>();

            ts.add(new TimeSlot(LocalDateTime.now(), 0.5));
            ts.add(new TimeSlot(LocalDateTime.now(), 2.5));
            ts.add(new TimeSlot(LocalDateTime.now(), 1.5));
            ts.add(new TimeSlot(LocalDateTime.now(), 0.5));
            ts.add(new TimeSlot(LocalDateTime.now(), 5.5));

            Planning p = new Planning(ts);
            connection.DAOWritePlanning(p, cleanerId);

            Planning planning = connection.DAOReadPlanning(cleanerId);

            assert p.equals(planning): "r/w plannings are not the same";
            System.out.println("Planning ended successfully");
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}