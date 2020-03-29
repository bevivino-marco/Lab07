package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.BlackOut;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutageDAO {

	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	public List<BlackOut> getBO(int nercId) {
		String sql = "SELECT id, nerc_id,date_event_began,date_event_finished,tag_id,customers_affected FROM  poweroutages \n" + 
				"WHERE nerc_id =?";
		List<BlackOut> blackOut= new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nercId);
			ResultSet res = st.executeQuery();
//BlackOut(int id, Year anno, int nercId, LocalDate inizio, LocalDate fine, int durata, int affette, int tag)
			while (res.next()) {
				Year anno;
			    double durata;
				LocalDateTime i;
				LocalDateTime f;
				//Date di=res.getDate("date_event_began");
				i = res.getTimestamp("date_event_began").toLocalDateTime();
				f = res.getTimestamp("date_event_finished").toLocalDateTime();
				durata = (i.until(f,ChronoUnit.MINUTES));
				
				
				blackOut.add(new BlackOut (res.getInt("id"),res.getDate("date_event_began").toLocalDate().getYear() , res.getInt("nerc_id"),i
						,f,durata,res.getInt("tag_id"), res.getInt("customers_affected")));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return blackOut;
	}

}
