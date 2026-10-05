/*
 * @ (#) PositionDAO        1.0     10/6/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */

package fit.lythithuy.dao;

import fit.lythithuy.model.Position;
import fit.lythithuy.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
 * @description:
 * @author: Thuy, Ly Thi
 * @version: 1.0
 * @created: 10/6/2026  3:55 AM
 */
public class PositionDAO {
    private DBUtil dbutil;

    public PositionDAO(DataSource dataSource) {
        dbutil = new DBUtil(dataSource);
    }

    public List<Position> getAll() {
        List<Position> list = new ArrayList<>();
        String sql = "SELECT * FROM positions";

        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Position(
                        rs.getInt("id"),
                        rs.getString("title")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Position getById(int id) {
        String sql = "SELECT * FROM positions WHERE id = ?";

        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Position(
                            rs.getInt("id"),
                            rs.getString("title")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Position position) {
        String sql = "INSERT INTO positions(title) VALUES (?)";

        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, position.getTitle());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Position position) {
        String sql = "UPDATE positions SET title=? WHERE id=?";

        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, position.getTitle());
            ps.setInt(2, position.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM positions WHERE id=?";

        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

